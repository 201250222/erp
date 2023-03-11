package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.SalaryDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.service.BonusService;
import com.nju.edu.erp.service.sheetService.SalaryService;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private SalaryDao salaryDao;
    private StaffService staffService;
    private AccountService accountService;
    private BonusService bonusService;

    @Autowired
    public SalaryServiceImpl(SalaryDao salaryDao, StaffService staffService,
                             AccountService accountService, BonusService bonusService){
        this.salaryDao = salaryDao;
        this.staffService = staffService;
        this.accountService = accountService;
        this.bonusService = bonusService;
    }


    public List<SalarySheetVO> makeAllSalarySheets(UserVO userVO, String date) {
        List<SalarySheetVO> res = new ArrayList<>();
        for(StaffPO staffPO: staffService.showAllStaff()){
            if(staffPO.getRole()!=Role.ADMIN){
                SalarySheetVO salarySheetVO = new SalarySheetVO();
                List<SalarySheetPO> POs = salaryDao.findByDateAndStaffName(date, staffPO.getName());
                if(POs.size()==1){
                    BeanUtils.copyProperties(POs.get(0), salarySheetVO);
                    res.add(salarySheetVO);
                    continue;
                }
                assert POs.size()==0: "疑似以前给同一个员工在同一个工资周期开了多份工资";

                if(staffPO.getRole()==Role.GM){
                    if(!date.endsWith("01-01")) continue;
                }
                salarySheetVO.setReleaseDate(date);
                salarySheetVO.setStaffName(staffPO.getName());

                res.add(makeASheet(userVO, salarySheetVO));
            }

        }
        return res;
    }


    public SalarySheetVO makeASheet(UserVO userVO, SalarySheetVO salarySheetVO) {

        SalarySheetPO salarySheetPO = new SalarySheetPO();
        BeanUtils.copyProperties(salarySheetVO, salarySheetPO);
        salarySheetPO.setOperator(userVO.getName());
        salarySheetPO.setCreateTime(new Date());
        SalarySheetPO latest = salaryDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "GZD");
        salarySheetPO.setId(id);
        salarySheetPO.setState(SalarySheetState.PENDING);

        String releaseDate = salarySheetPO.getReleaseDate();
        String staffName = salarySheetPO.getStaffName();

        StaffInfoVO staffInfoVO = null;
        for(StaffInfoVO staffInfoVO1: staffService.showStaffInfo()){
            if(staffInfoVO1.getName().equals(staffName)){
                staffInfoVO = staffInfoVO1;
                break;
            }
        }
        assert staffInfoVO != null: "未能在staff数据库中找到前端传进来的staffName";

        Integer originalSalary = staffInfoVO.getSalary().calculate(staffInfoVO.getRoleSalary(),staffInfoVO.getPerformanceSalary());
        // 年终奖
        String mm = releaseDate.substring(3,5);
        if(mm.equals("12")){
            BigDecimal bonus = bonusService.selectBonusById(staffInfoVO.getId()).getBonus();
            if(bonus!=null){
                originalSalary += bonus.intValue();
            }
        }
        salarySheetPO.setStaffID(staffInfoVO.getId());
        salarySheetPO.setStaffBankCard(staffInfoVO.getBankCard());


        // 应发工资
        salarySheetPO.setOriginSalary(originalSalary);
        // 缺勤扣除/税
        Integer absenceDeduction = 0,tax;
        boolean isMonthly = false;
        if(staffInfoVO.getRole()!= Role.ADMIN || staffInfoVO.getRole()!=Role.GM){
            absenceDeduction = (monthlyAbsence(staffService.selectByName(staffName), releaseDate)*originalSalary)/30;
            isMonthly = true;
        }
        tax = taxCalculator(originalSalary,isMonthly);
        salarySheetPO.setAbsenceDeduction(absenceDeduction);
        salarySheetPO.setTax(tax);
        // 计算实际工资
        Integer actualSalary = Math.max(0, originalSalary-tax-absenceDeduction);
        salarySheetPO.setActualSalary(actualSalary);

        salaryDao.save(salarySheetPO);
        BeanUtils.copyProperties(salarySheetPO, salarySheetVO);
        System.out.println(actualSalary);
        return salarySheetVO;
    }

    @Override
    public void approval(String salarySheetId, BaseEnum state) {
        if(state.equals(SalarySheetState.FAILURE)){
            SalarySheetPO salarySheet = salaryDao.findOneById(salarySheetId);
            if(salarySheet.getState() == SalarySheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = salaryDao.updateState(salarySheetId, (SalarySheetState) state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
        }
        else if(state.equals(SalarySheetState.SUCCESS)){
            int effectLines = salaryDao.updateState(salarySheetId, (SalarySheetState) state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
            // 汇钱
            boolean isSucceed = accountService.updateAccount("account0", BigDecimal.valueOf(-salaryDao.findOneById(salarySheetId).getActualSalary()));
            if(!isSucceed) throw new RuntimeException("账户余额不足！");
        }
        else {
            throw new RuntimeException("状态更新失败");
        }
    }

    @Override
    public List<SheetVO> getSheetByState(BaseEnum state) {
        List<SheetVO> res = new ArrayList<>();
        List<SalarySheetPO> all;
        if(state == null) {
            all = salaryDao.findAll();
        } else {
            all = salaryDao.findAllByState((SalarySheetState) state);
        }
        for(SalarySheetPO po: all) {
            SalarySheetVO vo = new SalarySheetVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
        }
        return res;
    }

    public SalarySheetVO getSheetById(String salarySheetId) {
        SalarySheetVO salarySheetVO = new SalarySheetVO();
        BeanUtils.copyProperties(salaryDao.findOneById(salarySheetId),salarySheetVO);
        return salarySheetVO;
    }

    /**
     *
     * @param clockInPOS 某个员工的打卡记录和
     * @param date 发工资的日期
     * @return 缺勤次数
     */
    private Integer monthlyAbsence(List<ClockInPO> clockInPOS, String date){
        int times=0;
        int y=Integer.parseInt(date.substring(0,4)), m=Integer.parseInt(date.substring(5,7));
        Calendar c1 = Calendar.getInstance();
        if(1==m){
            c1.set(y-1, Calendar.DECEMBER,1,0,0,0);
        }
        Calendar c2 = Calendar.getInstance();
        c2.set(y, m-1, 1, 0,0,0);
        for(ClockInPO clockInPO: clockInPOS){
            if(c1.before(clockInPO.getDate()) && c2.after(clockInPO.getDate())){
                times++;
            }
        }
        times = c1.getActualMaximum(Calendar.DAY_OF_MONTH)-times;
        return Math.min(times, 30);
    }
    /**
     * 表驱动计算税
     * @param originalSalary 应发工资
     * @param isMonthly 是否是月薪制
     * @return 应缴税款
     */
    public Integer taxCalculator(Integer originalSalary, boolean isMonthly){

        int[] salary = new int[]{0,5000,8000,17000,30000,40000,60000,85000};
        int[] taxRate = new int[]{0,3,10,20,25,30,35,45};
        double tax = 0;
        int cnt=1, ratio=isMonthly? 1: 12;

        while(cnt<8 && originalSalary > salary[cnt]*ratio){
            tax += (salary[cnt] - salary[cnt-1]) * ratio * taxRate[cnt-1]/100.0;
            cnt++;
        }
        tax += (originalSalary - salary[cnt-1]*ratio)*taxRate[cnt-1]/100.0;
        return (int)tax;
    }

    @Override
    public void makeSheet(UserVO userVO, SheetVO sheetVO) {
    }
}

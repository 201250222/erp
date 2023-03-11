package com.nju.edu.erp.HRTest;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.ClockInPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.service.salaryStrategy.Salary;
import com.nju.edu.erp.service.salaryStrategy.strategies.MonthPerformanceSalary;
import com.nju.edu.erp.service.salaryStrategy.strategies.MonthSalary;
import com.nju.edu.erp.service.salaryStrategy.strategies.YearSalary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class HRTests {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private StaffService staffService;

    @Test
    void test_01(){
        List<StaffPO> staffPOS = staffDao.showAllStaff();
        for (StaffPO po : staffPOS) {
            System.out.println(po);
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void test_02(){
        StaffVO staffVO = new StaffVO("test","test",new Date(),"test",Role.SALE_MANAGER,"test");
        int res = staffService.addStaff(staffVO);
        Assertions.assertEquals(1, res);
        List<StaffPO> staffPOS = staffService.showAllStaff();
        for (StaffPO po : staffPOS) {
            System.out.println(po);
        }
    }

    @Test
    void test_03(){
        List<StaffInfoVO> list = staffService.showStaffInfo();
        for (StaffInfoVO infoVO : list) {
            System.out.println(infoVO);
        }
    }

    @Test
    void test_04(){
        staffService.clockIn("67");
    }


    @Test
    void test_05(){
        List<ClockInPO> list = staffService.selectByName("67");
        for (ClockInPO clockInPO : list) {
            System.out.println(clockInPO);
        }
    }

    @Test
    void test_06(){
        staffService.updateSalary(Role.SALE_MANAGER, 5000);
        test_03();
    }

    @Test
    void test_07(){
        new MonthSalary().release("20220701");
    }

    @Test
    /*
     * 薪资策略使用示例INVENTORY_MANAGER, //库存管理人员
    SALE_STAFF, // 进货销售人员
    FINANCIAL_STAFF, // 财务人员
    SALE_MANAGER, //销售经理
    HR, // 人力资源人员
    GM, // 总经理
    ADMIN // 最高权限
     */

    void test_08(){
        List<StaffInfoVO> list = staffService.showStaffInfo();
        for (StaffInfoVO vo : list) {
            System.out.println(vo);
        }
        StaffInfoVO saleManager = list.get(6);
        switch (saleManager.getRole()) {
            case SALE_MANAGER:
            case SALE_STAFF:
                saleManager.setSalary(new Salary(new MonthPerformanceSalary()));
                break;
            case GM:
                saleManager.setSalary(new Salary(new YearSalary()));
                break;
            case HR:
            case FINANCIAL_STAFF:
            case INVENTORY_MANAGER:
                saleManager.setSalary(new Salary(new MonthSalary()));
                break;
            default:
                break;
        }
        System.out.println(saleManager.getSalary().calculate(saleManager.getRoleSalary(),saleManager.getPerformanceSalary()));
        System.out.println(saleManager.getSalary().release("20221231"));

    }

    @Test
    void test_09(){
        List<StaffInfoVO> list = staffService.showStaffInfo();
        StaffInfoVO saleManager = list.get(6);
        System.out.println(saleManager);
        System.out.println(saleManager.getSalary().calculate(saleManager.getRoleSalary(),saleManager.getPerformanceSalary()));
    }

}

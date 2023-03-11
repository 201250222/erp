package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BonusDao;
import com.nju.edu.erp.dao.SalaryDao;
import com.nju.edu.erp.model.po.BonusPO;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.BonusVO;
import com.nju.edu.erp.service.BonusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    private BonusDao bonusDao;
    @Autowired
    private SalaryDao salaryDao;


    @Override
    public List<BonusVO> showTodoBonus() {
        List<BonusVO> bonusVOList = new ArrayList<>();
        List<SalarySheetPO> salarySheetList = salaryDao.findAll();
        for (SalarySheetPO sheetPO : salarySheetList) {
            SalarySheetPO tmp = salaryDao.findAllSum(sheetPO.getStaffName());
            sheetPO.setOriginSalary(tmp.getOriginSalary());
            sheetPO.setTax(tmp.getTax());
            sheetPO.setAbsenceDeduction(tmp.getAbsenceDeduction());
            sheetPO.setActualSalary(tmp.getActualSalary());
            BonusVO bonusVO = new BonusVO();
            BeanUtils.copyProperties(sheetPO, bonusVO);
            bonusVO.setStaffId(sheetPO.getStaffID());
            bonusVO.setBonus(new BigDecimal(0));
            bonusVOList.add(bonusVO);
        }
        if(showBonus()!=null) bonusDao.deleteAll();
        if(showBonus()==null||showBonus().size()==0) makeBonus(bonusVOList);
        return bonusVOList;
    }

    @Override
    public boolean makeBonus(List<BonusVO> bonusVOS) {
        int count = bonusVOS.size() + 1;
        for (BonusVO bonusVO : bonusVOS) {
            BonusPO bonusPO = new BonusPO();
            BeanUtils.copyProperties(bonusVO,bonusPO);
            if(bonusDao.makeBonus(bonusPO)==1) --count;
        }
        return count==0;
    }

    @Override
    public int makeOneBonus(BonusVO bonusVO) {
        BonusPO bonusPO = new BonusPO();
        BeanUtils.copyProperties(bonusVO, bonusPO);
        return bonusDao.makeBonus(bonusPO);
    }

    @Override
    public List<BonusPO> showBonus() {
        return bonusDao.showBonus();
    }

    @Override
    public BonusVO selectBonusById(Integer id) {
        BonusVO bonusVO = new BonusVO();
        BeanUtils.copyProperties(bonusDao.selectBonusById(id), bonusVO);
        return bonusVO;
    }

    @Override
    public int updateOneBonus(BonusVO bonusVO) {
        BonusPO bonusPO = new BonusPO();
        BeanUtils.copyProperties(bonusVO, bonusPO);
        return bonusDao.updateOneBonus(bonusPO);
    }

}

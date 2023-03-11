package com.nju.edu.erp.bonus;

import com.nju.edu.erp.dao.SalaryDao;
import com.nju.edu.erp.model.po.SalarySheetPO;
import com.nju.edu.erp.model.vo.BonusVO;
import com.nju.edu.erp.service.BonusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BonusTest {

    @Autowired
    private BonusService bonusService;
    @Autowired
    private SalaryDao salaryDao;

    @Test
    public void test(){
        List<BonusVO> list = bonusService.showTodoBonus();
        for (BonusVO bonusVO : list) {
            System.out.println(bonusVO);
        }
    }

    @Test
    public void test1(){
        List<SalarySheetPO> salarySheetList = salaryDao.findAll();
        for (SalarySheetPO salarySheetPO : salarySheetList) {
            System.out.println(salarySheetPO);
            SalarySheetPO tmp = salaryDao.findAllSum(salarySheetPO.getStaffName());
            System.out.println(tmp);
        }
    }
}

package com.nju.edu.erp.creditTest;

import com.nju.edu.erp.dao.CreditSheetDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.po.CreditSheetPO;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetContentVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.service.Impl.CreditServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CreditTests {

    @Autowired
    CreditServiceImpl creditService;

    @Autowired
    private CreditSheetDao creditSheetDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void Test_1(){
        List<SheetVO> voList = creditService.getSheetByState(null);
        for(SheetVO vo : voList){
            CreditSheetVO creditSheetVO = (CreditSheetVO) vo;
            System.out.println(creditSheetVO);
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void Test_2(){
        UserVO userVO = UserVO.builder()
                .name("67")
                .role(Role.GM)
                .password("123456")
                .build();
        List<CreditSheetContentVO> list = new ArrayList<>();
        CreditSheetContentVO creditSheetContentVO = CreditSheetContentVO.builder()
                .accountName("account1")
                .amount(new BigDecimal(10000))
                .remark("NO")
                .build();
        list.add(creditSheetContentVO);
        CreditSheetVO creditSheetVO1 = CreditSheetVO.builder()
                .customer(1)
                .operator("67")
                .creditSheetContent(list)
                .build();
        creditService.makeSheet(userVO, creditSheetVO1);
        creditService.makeSheet(userVO, creditSheetVO1);
        creditService.makeSheet(userVO, creditSheetVO1);
        List<SheetVO> voList = creditService.getSheetByState(null);
        for(SheetVO vo : voList){
            CreditSheetVO creditSheetVO = (CreditSheetVO) vo;
            System.out.println(creditSheetVO);
        }
    }

    @Test
    public void Test_3(){
        List<CreditSheetPO> poList = creditSheetDao.selectAllCreditSheet();
        for(CreditSheetPO creditSheetPO : poList){
            System.out.println(creditSheetPO);
        }
    }

    @Test
    public void Test_4(){
        List<CreditSheetPO> poList = creditSheetDao.selectCreditSheetByState(CreditSheetState.PENDING);
        for(CreditSheetPO creditSheetPO : poList){
            System.out.println(creditSheetPO);
        }
    }

}

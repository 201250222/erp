package com.nju.edu.erp.ManageTable;

import com.nju.edu.erp.model.vo.table.BalanceTableVO;
import com.nju.edu.erp.service.ManageCondService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@SpringBootTest
public class ManageCondTest {
    @Autowired
    private ManageCondService manageCondService;

    @Test
    @Transactional
    @Rollback(value = true)
    void test() throws ParseException {
        BalanceTableVO balanceTableVO = BalanceTableVO.builder()
                .beginDate("2020-01-01")
                .endDate("2022-07-07")
                .actualIncoming(null)
                .discounts(null)
                .outgoings(null)
                .profits(null)
                .build();
        balanceTableVO = manageCondService.makeBalanceInquiry(balanceTableVO);
        System.out.println("begin date: "+ balanceTableVO.getBeginDate());
        System.out.println("end date: "+ balanceTableVO.getEndDate());
        System.out.println("actual incoming: "+ balanceTableVO.getActualIncoming());
        System.out.println("discounts: "+ balanceTableVO.getDiscounts());
        System.out.println("outgoings: "+ balanceTableVO.getOutgoings());
        System.out.println("profits: "+balanceTableVO.getProfits());
    }
}

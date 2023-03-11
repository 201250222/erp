package com.nju.edu.erp.establishment;

import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class AccountTest {

    @Autowired
    private EstablishmentService establishmentService;

    @Test
    @Transactional
    @Rollback
    public void test_insert(){
        AccountVO accountVO = AccountVO.builder().name("account").cardNum("000123").build();
        establishmentService.addAccount(accountVO);
    }

    @Test
    public void test_select(){
        List<AccountVO> list = establishmentService.showAccount();
        for (AccountVO accountVO : list) {
            System.out.println(accountVO);
        }
    }
}

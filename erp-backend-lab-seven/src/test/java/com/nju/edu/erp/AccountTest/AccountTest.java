package com.nju.edu.erp.AccountTest;

import com.nju.edu.erp.dao.AccountDao;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class AccountTest {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountDao accountDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void test_1(){
        List<AccountPO> list = accountService.selectAllAccount();
        for(AccountPO po : list){
            System.out.println(po);
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void test_2(){
        accountService.createAccount("test", "1012939012");
        Assertions.assertEquals("1012939012", accountService.selectAccount("test").getCardNum());
        accountService.modifyAccount("test", "tests");
        Assertions.assertEquals("1012939012", accountService.selectAccount("tests").getCardNum());
        accountService.updateAccount("tests", new BigDecimal(100));
        Assertions.assertEquals(new BigDecimal(100), accountService.selectAccount("tests").getBalance());
    }
}

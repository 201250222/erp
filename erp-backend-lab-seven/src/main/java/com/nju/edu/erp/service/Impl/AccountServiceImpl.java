package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    @Override
    public boolean createAccount(String accountName, String cardNum) {
        List<AccountPO> list = accountDao.selectAccount();
        for(AccountPO accountPO : list){
            if(accountName.equals(accountPO.getName()) || cardNum.equals(accountPO.getCardNum())){
                return false;
            }
        }
        accountDao.addAccount(accountName, cardNum);
        return true;
    }

    @Override
    public boolean removeAccount(String accountName) {
        List<AccountPO> list = accountDao.selectAccount();
        for(AccountPO accountPO : list){
            if(accountName.equals(accountPO.getName())){
                accountDao.removeAccount(accountName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modifyAccount(String oldName, String newName) {
        List<AccountPO> list = accountDao.selectAccount();
        for(AccountPO accountPO : list){
            if(oldName.equals(accountPO.getName())){
                accountDao.modifyAccount(oldName, newName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateAccount(String accountName, BigDecimal amount) {
        List<AccountPO> list = accountDao.selectAccount();
        for(AccountPO accountPO : list){
            if(accountName.equals(accountPO.getName())){
                if(amount.compareTo(BigDecimal.ZERO) < 0){
                    if(amount.add(accountPO.getBalance()).compareTo(BigDecimal.ZERO) < 0){
                        return false;
                    }
                }
                accountDao.updateAccount(accountName, amount.add(accountPO.getBalance()));
                return true;
            }
        }
        return false;
    }

    @Override
    public AccountPO selectAccount(String accountName) {
        return accountDao.selectAccountByName(accountName);
    }

    @Override
    public List<AccountPO> selectAllAccount() {
        return accountDao.selectAccount();
    }
}

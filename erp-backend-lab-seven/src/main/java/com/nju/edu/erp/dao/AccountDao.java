package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface AccountDao {

    // 新建账户
    int addAccount(String name, String cardNum);

    // 删除账户
    int removeAccount(String name);

    // 更新账户名称
    int modifyAccount(String oldName, String newName);

    // 更新账户余额
    int updateAccount(String name, BigDecimal amount);

    // 根据名称搜索账户
    AccountPO selectAccountByName(String name);

    // 获取账户列表
    List<AccountPO> selectAccount();

}

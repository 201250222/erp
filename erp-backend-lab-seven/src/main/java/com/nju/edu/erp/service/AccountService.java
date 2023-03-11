package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.AccountPO;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    /**
     * 创建账户，返回执行结果
     * @param accountName 账户名称
     * @param cardNum 银行卡号
     */
    boolean createAccount(String accountName, String cardNum);

    /**
     * 删除账户，返回执行结果
     * @param accountName 账户名称
     */
    boolean removeAccount(String accountName);

    /**
     * 修改账户名称,返回执行结果
     * @param oldName 旧名称
     * @param newName 新名称
     */
    boolean modifyAccount(String oldName, String newName);

    /**
     * 更新账户余额，返回执行结果
     * @param accountName 账户名称
     * @param amount 修改金额
     */
    boolean updateAccount(String accountName, BigDecimal amount);

    /**
     * 查找指定账户,返回查找结果,失败返回null
     * @param accountName 账户名称
     */
    AccountPO selectAccount(String accountName);

    /**
     * 获取所有账户信息
     */
    List<AccountPO> selectAllAccount();
}

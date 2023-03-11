package com.nju.edu.erp.web.controller;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 新建账户
     */
    @Authorized (roles = {Role.ADMIN})
    @PostMapping(value = "/account-create")
    public Response createAccount(@RequestBody AccountVO accountVO){
        boolean answer = accountService.createAccount(accountVO.getName(), accountVO.getCardNum());
        if(answer){
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("00000", "新建失败");
        }
    }

    /**
     * 删除账户
     */
    @Authorized (roles = {Role.ADMIN})
    @GetMapping(value = "/account-remove")
    public Response removeAccount(@RequestParam(value = "accountName") String accountName){
        boolean answer = accountService.removeAccount(accountName);
        if(answer){
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("00000", "新建失败");
        }
    }

    /**
     * 修改账户名
     */
    @Authorized (roles = {Role.ADMIN})
    @GetMapping(value = "/account-modify")
    public Response modifyAccount(@RequestParam(value = "oldName") String oldName, @RequestParam(value = "newName") String newName){
        boolean answer = accountService.modifyAccount(oldName, newName);
        if(answer){
            return Response.buildSuccess();
        }else{
            return Response.buildFailed("00000", "新建失败");
        }
    }

    /**
     * 查询指定账户余额
     */
    @Authorized (roles = {Role.ADMIN})
    @PostMapping(value = "/account-select")
    public Response selectAccount(@RequestBody AccountVO accountVO){
        AccountPO accountPO = accountService.selectAccount(accountVO.getName());
        return Response.buildSuccess(accountPO);
    }

    /**
     * 获取所有账户信息
     */
    @GetMapping(value = "/account-selectAll")
    public Response selectAllAccount(){
        List<AccountPO> list = accountService.selectAllAccount();
        return Response.buildSuccess(list);
    }
}

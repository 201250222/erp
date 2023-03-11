package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.table.BalanceTableVO;
import com.nju.edu.erp.service.ManageCondService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/manage-cond")
public class ManageCondController {

    private final ManageCondService manageCondService;

    @Autowired
    public ManageCondController(ManageCondService manageCondService){
        this.manageCondService = manageCondService;
    }

    /**
     * 查看经营情况表
     * @param balanceTableVO
     * @return
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/make-inquiry")
    public Response makeManageCondInquiry(@RequestBody BalanceTableVO balanceTableVO) throws ParseException {
        return Response.buildSuccess(manageCondService.makeBalanceInquiry(balanceTableVO));
    }

}

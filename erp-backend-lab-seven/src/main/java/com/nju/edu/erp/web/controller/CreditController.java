package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.service.sheetService.CreditService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    /**
     * 制定收款单
     */
    @Authorized (roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/sheet-make")
    public Response makeCreditSheet(UserVO userVO, @RequestBody CreditSheetVO creditSheetVO){
        creditService.makeSheet(userVO, creditSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 总经理审批收款单
     * @param id 收款单id
     * @param state 通过审核后的状态
     */
    @Authorized (roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/sheet-pass")
    public Response passCreditSheet(@RequestParam(value = "id") String id,
                                    @RequestParam(value = "state") CreditSheetState state){
            creditService.approval(id, state);
        return Response.buildSuccess();
    }

    /**
     * 根据状态获取收款单, 传null获取所有
     */
    @GetMapping(value = "/sheet-select-by-state")
    public Response selectCreditByState(@RequestParam(value = "state", required = false) CreditSheetState state){
        return Response.buildSuccess(creditService.getSheetByState(state));
    }

    /**
     * 根据收款单Id搜索收款单具体信息
     * @param id 收款单Id
     * @return 进货单全部信息
     */
    @GetMapping(value = "/sheet-select-by-id")
    public Response selectCreditById(@RequestParam(value = "id") String id){
        return Response.buildSuccess(creditService.selectCreditById(id));
    }
}

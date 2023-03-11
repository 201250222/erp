package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.service.sheetService.PaymentService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 制定付款单
     * @param paymentSheetVO 付款单
     */
    @PostMapping(value = "/sheet-make")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.FINANCIAL_STAFF})
    public Response makePaymentSheet(@RequestBody PaymentSheetVO paymentSheetVO){
        paymentService.makeSheet(null,paymentSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 总经理审批付款单
     * @param id 付款单id
     * @param state 是否通过审批
     */
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.FINANCIAL_STAFF})
    @GetMapping(value = "/sheet-pass")
    public Response passPaymentSheet(@RequestParam(value="id") String id,
                                    @RequestParam(value="state") PaymentSheetState state){
        paymentService.approval(id, state);
        return Response.buildSuccess();
    }

    /**
     * 根据状态获取单据, 传null获取所有
     */
    @GetMapping(value = "/sheet-select-by-state")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.FINANCIAL_STAFF})
    public Response selectPaymentByState(@RequestParam(value = "state", required = false) PaymentSheetState state){
        return Response.buildSuccess(paymentService.getSheetByState(state));
    }

    /**
     * 根据Id搜索单据具体信息
     * @param id 付款单Id
     * @return 条目全部信息
     */
    @GetMapping(value = "/sheet-select-by-id")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.FINANCIAL_STAFF})
    public Response selectPaymentById(@RequestParam(value = "id") String id){
        return Response.buildSuccess(paymentService.selectPaymentById(id));
    }
}

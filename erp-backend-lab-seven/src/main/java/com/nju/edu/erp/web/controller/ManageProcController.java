package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.ManageProcVO;
import com.nju.edu.erp.model.vo.RedFlushSheetVO;
import com.nju.edu.erp.service.ManageProcService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/manage-proc")
public class ManageProcController {

    private final ManageProcService manageProcService;

    @Autowired
    public ManageProcController(ManageProcService manageProcService){
        this.manageProcService = manageProcService;
    }

    /**
     * 查询销售单据
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/sale-sheet")
    public Response searchSaleSheet(@RequestBody ManageProcVO manageProcVO){
        return Response.buildSuccess(manageProcService.searchSale(manageProcVO));
    }

    /**
     * 查询进货单据
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/purchase-sheet")
    public Response searchPurchaseSheet(@RequestBody ManageProcVO manageProcVO){
        return Response.buildSuccess(manageProcService.searchPurchase(manageProcVO));
    }

    /**
     * 查询财务单据
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/financial-sheet")
    public Response searchFinancialSheet(@RequestBody ManageProcVO manageProcVO){
        return Response.buildSuccess(manageProcService.searchFinancial(manageProcVO));
    }

    /**
     * 查询库存类单据
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/inventory-sheet")
    public Response searchInventorySheet(@RequestBody ManageProcVO manageProcVO){
        return Response.buildSuccess(manageProcService.searchInventory(manageProcVO));
    }

    /**
     * 红冲
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/red-flush")
    public Response searchInventorySheet(@RequestBody RedFlushSheetVO redFlushSheetVO){
        manageProcService.makeRedFlush(redFlushSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 红冲并复制
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/red-flush/copy")
    public Response copy(@RequestBody RedFlushSheetVO redFlushSheetVO){
        manageProcService.copy(redFlushSheetVO);
        return Response.buildSuccess();
    }
}

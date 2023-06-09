package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.WarehouseInputSheetState;
import com.nju.edu.erp.enums.sheetState.WarehouseOutputSheetState;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.warehouse.GetWareProductInfoParamsVO;
import com.nju.edu.erp.service.sheetService.WarehouseService;
import com.nju.edu.erp.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    public WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @PostMapping("/product/count")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseOutput(@RequestBody GetWareProductInfoParamsVO getWareProductInfoParamsVO){
        return Response.buildSuccess(warehouseService.getWareProductInfo(getWareProductInfoParamsVO));
    }


    @GetMapping("/inputSheet/approve")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseInputSheetApprove(UserVO user,
                                               @RequestParam(value = "sheetId") String sheetId,
                                               @RequestParam(value = "state") WarehouseInputSheetState state) {
        if (state.equals(WarehouseInputSheetState.FAILURE) || state.equals(WarehouseInputSheetState.SUCCESS)) {
            warehouseService.approvalInputSheet(user, sheetId, state);
        }
        else {
            throw new MyServiceException("C00001", "越权访问！");
        }
        return Response.buildSuccess();
    }

    @GetMapping("/inputSheet/state")
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER})
    public Response getWarehouseInputSheet(@RequestParam(value = "state", required = false) WarehouseInputSheetState state) {
        List<WarehouseInputSheetPO> ans = warehouseService.getWareHouseInputSheetByState(state);
        return Response.buildSuccess(ans);
    }


    @GetMapping("/outputSheet/state")
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER, Role.GM})
    public Response getWarehouseOutSheet(@RequestParam(value = "state", required = false) WarehouseOutputSheetState state) {
        List<WarehouseOutputSheetPO> ans = warehouseService.getWareHouseOutSheetByState(state);
        return Response.buildSuccess(ans);
    }

    @GetMapping("/inputSheet/content")
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER, Role.GM})
    public Response getWarehouseInputSheetContent(@RequestParam(value = "sheetId") String sheetId) {
        List<WarehouseInputSheetContentPO> ans = warehouseService.getWHISheetContentById(sheetId);
        return Response.buildSuccess(ans);
    }

    @GetMapping("/outputSheet/content")
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER, Role.GM})
    public Response getWarehouseOutputSheetContent(@RequestParam(value = "sheetId") String sheetId) {
        List<WarehouseOutputSheetContentPO> ans = warehouseService.getWHOSheetContentById(sheetId);
        return Response.buildSuccess(ans);
    }

    @GetMapping("/outputSheet/approve")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehouseOutputSheetApprove(UserVO user,
                                                @RequestParam(value = "sheetId") String sheetId,
                                                @RequestParam(value = "state") WarehouseOutputSheetState state) {
        if (state.equals(WarehouseOutputSheetState.FAILURE) || state.equals(WarehouseOutputSheetState.SUCCESS)) {
            warehouseService.approvalOutputSheet(user, sheetId, state);
        }
        else {
            throw new MyServiceException("C00001", "越权访问！");
        }
        return Response.buildSuccess();
    }



    /**
     *库存查看：查询指定时间段内出/入库数量/金额/商品信息/分类信息
     * @param beginDateStr 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @param endDateStr   格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @return
     */
    @GetMapping("/sheetContent/time")
    @Authorized(roles = {Role.ADMIN,Role.INVENTORY_MANAGER})
    public Response getWarehouseIODetailByTime(@RequestParam String beginDateStr,@RequestParam String endDateStr) throws ParseException {
        List<WarehouseIODetailPO> ans=warehouseService.getWarehouseIODetailByTime(beginDateStr,endDateStr);
        return Response.buildSuccess(ans);
    }

    /**
     *库存查看：一个时间段内的入库数量合计
     * @param beginDateStr 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @param endDateStr   格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @return
     */
    @GetMapping("/inputSheet/time/quantity")
    @Authorized(roles = {Role.ADMIN,Role.INVENTORY_MANAGER})
    public Response getWarehouseInputProductQuantityByTime(@RequestParam String beginDateStr,@RequestParam String endDateStr) throws ParseException{
        int quantity= warehouseService.getWarehouseInputProductQuantityByTime(beginDateStr,endDateStr);
        return Response.buildSuccess(quantity);
    }

    /**
     *库存查看：一个时间段内的出库数量合计
     * @param beginDateStr 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @param endDateStr   格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @return
     */
    @GetMapping("/outputSheet/time/quantity")
    @Authorized(roles = {Role.ADMIN,Role.INVENTORY_MANAGER})
    public Response getWarehouseOutputProductQuantityByTime(@RequestParam String beginDateStr,@RequestParam String endDateStr) throws ParseException{
        int quantity= warehouseService.getWarehouseOutProductQuantityByTime(beginDateStr,endDateStr);
        return Response.buildSuccess(quantity);
    }

    /**
     * 库存盘点
     * 盘点的是当天的库存快照，包括当天的各种商品的
     * 名称，型号，库存数量，库存均价，批次，批号，出厂日期，并且显示行号。
     * 要求可以导出Excel
     *
     */
    @GetMapping("/warehouse/counting")
    @Authorized(roles = {Role.ADMIN, Role.INVENTORY_MANAGER})
    public Response getWarehouseCounting() {
        return Response.buildSuccess(warehouseService.warehouseCounting());
    }

    @GetMapping("/warehouse/counting/excel")
    public Response excel(HttpServletResponse response){
        warehouseService.excel(response);
        return Response.buildSuccess();
    }

    @GetMapping("/presentSheet/state")
    @Authorized(roles = {Role.ADMIN,Role.INVENTORY_MANAGER})
    public Response getWarehousePresentSheet(@RequestParam(value = "state", required = false) WarehousePresentSheetState state) {
        return Response.buildSuccess(warehouseService.getWarehousePresentSheetsByState(state));
    }

    @GetMapping("/presentSheet/approve")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response warehousePresentSheetApprove(UserVO user,
                                                @RequestParam(value = "sheetId") String sheetId,
                                                @RequestParam(value = "state") WarehousePresentSheetState state) {
        if (state.equals(WarehousePresentSheetState.FAILURE) || state.equals(WarehousePresentSheetState.SUCCESS)) {
            warehouseService.approvalPresentSheet(user, sheetId, state);
        }
        else {
            throw new MyServiceException("C00001", "越权访问！");
        }
        return Response.buildSuccess();
    }

}

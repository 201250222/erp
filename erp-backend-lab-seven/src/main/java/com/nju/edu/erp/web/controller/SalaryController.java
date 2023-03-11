package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.sheetService.SalaryService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService){
        this.salaryService = salaryService;
    }

    /**
     * 人力资源人员制定批量工资单,其中日期格式为：yyyy-mm-dd
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @GetMapping(value = "/all-sheets-make")
    public Response makeAllSalarySheets(UserVO userVO, @RequestParam(value = "date") String date) {
        return Response.buildSuccess(salaryService.makeAllSalarySheets(userVO, date));
    }

//    /**
//     * 人力资源人员制定工资单
//     */
//    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN})
//    @PostMapping(value = "/sheet-make")
//    public Response makeSalarySheet(UserVO userVO, @RequestBody SalarySheetVO salarySheetVO){
//        salaryService.makeSheet(userVO, salarySheetVO);
//        return Response.buildSuccess();
//    }

    /**
     * 总经理审批
     * @param salarySheetId 工资单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "approval")
    public Response approval(@RequestParam("salarySheetId") String salarySheetId,
                             @RequestParam("state") SalarySheetState state)  {
        if(state.equals(SalarySheetState.FAILURE) || state.equals(SalarySheetState.SUCCESS)) {
            salaryService.approval(salarySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 根据状态查看工资单
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) SalarySheetState state)  {
        return Response.buildSuccess(salaryService.getSheetByState(state));
    }

    /**
     * 根据工资单Id搜索工资单信息
     * @param id 工资单Id
     * @return 工资单全部信息
     */
    @GetMapping(value = "/find-sheet")
    public Response findBySheetId(@RequestParam(value = "id") String id)  {
        return Response.buildSuccess(salaryService.getSheetById(id));
    }
}

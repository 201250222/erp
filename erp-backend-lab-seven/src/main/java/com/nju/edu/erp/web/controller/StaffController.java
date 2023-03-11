package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import com.nju.edu.erp.model.vo.StaffInfoWithoutSalaryVO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * 添加新员工
     * @param staff 员工信息
     */
    @PostMapping("/add")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.HR})
    public Response addStaff(@RequestBody StaffVO staff){
        int res = staffService.addStaff(staff);
        if(res != 1) return Response.buildFailed("staffService.addStaff(staff)","updated rows not equal 1");
        return Response.buildSuccess();
    }

    /**
     * 修改一位岗位对应的工资
     * @param role 岗位
     * @param targetSalary 修改后的工资
     */
    @GetMapping("/amend")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.HR})
    public Response amendStaffSalary(@RequestParam("role") Role role,
                                     @RequestParam("targetSalary") Integer targetSalary){
        if(staffService.updateSalary(role,targetSalary)!=1)
            return Response.buildFailed("staffService.updateSalary(role,targetSalary)","updated rows not equal 1");
        return Response.buildSuccess();
    }

    /**
     * 员工打卡
     * @param name 员工姓名
     */
    @GetMapping("/clockIn")
    public Response staffClockIn(@RequestParam String name){
        int res = staffService.clockIn(name);
        if(res!=1) return Response.buildFailed("staffService.clockIn(id)","updated rows not equal 1");
        return Response.buildSuccess();
    }

    /**
     * 查看所有员工所有信息
     * @return StaffInfoVOList 包括员工对应的岗位的信息
     */
    @GetMapping("/show")
    @Authorized(roles = {Role.GM, Role.ADMIN, Role.HR,Role.FINANCIAL_STAFF})
    public Response showStaffInfo(){
        List<StaffInfoVO> list = staffService.showStaffInfo();
        List<StaffInfoWithoutSalaryVO> vos = new ArrayList<>();
        for (StaffInfoVO staffInfoVO : list) {
            StaffInfoWithoutSalaryVO vo = new StaffInfoWithoutSalaryVO();
            BeanUtils.copyProperties(staffInfoVO, vo);
            vos.add(vo);
        }
        return Response.buildSuccess(vos);
    }
}

package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.BonusVO;
import com.nju.edu.erp.service.BonusService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;

    /**
     * 每年初次制定年终奖首先调用这个方法查看员工工作详情
     * @return List<BonusVO>
     */
    @GetMapping("/showPerformance")
    @Authorized(roles = {Role.ADMIN, Role.GM})
    public Response firstMakeInAYear(){
        return Response.buildSuccess(bonusService.showTodoBonus());
    }

    /**
     * 在/showPerformance返回的列表中更改bonus
     * 应提交所有的bonusVO即使没填写bonus属性
     * @param bonusVOS 所有员工的年终奖信息
     */
    @PostMapping("/make")
    @Authorized(roles = {Role.ADMIN, Role.GM})
    public Response makeBonus(@RequestBody List<BonusVO> bonusVOS){
        boolean res = bonusService.makeBonus(bonusVOS);
        return res?Response.buildSuccess():Response.buildFailed("bonusService.makeBonus(bonusVOS)","所有年终奖信息没完全存储");
    }

    /**
     * 查看所有已经制定了的bonus
     * @return List<BonusPO>
     */
    @GetMapping("/showMade")
    @Authorized(roles = {Role.ADMIN, Role.GM})
    public Response showBonus(){
        return Response.buildSuccess(bonusService.showBonus());
    }

    /**
     * 根据id查看该员工的BonusVO
     * @param id 员工id
     * @return BonusVO
     */
    @GetMapping("/selectId")
    @Authorized(roles = {Role.ADMIN, Role.GM})
    public Response selectBonusById(@RequestParam(value = "id") Integer id){
        return Response.buildSuccess(bonusService.selectBonusById(id));
    }

    /**
     * 更新一个员工的BonusVO
     * 用于：/showMade请求后，想要修改某一个员工的bonus 或 /selectId后，修改该员工的bonus
     * @param bonusVO 更新后的BonusVo
     */
    @PostMapping("/updateOne")
    @Authorized(roles = {Role.ADMIN, Role.GM})
    public Response updateOne(@RequestBody BonusVO bonusVO){
        return Response.buildSuccess(bonusService.updateOneBonus(bonusVO));
    }


}

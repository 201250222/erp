package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.promote.PromoteVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PromoteService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/promote")
public class PromoteController {

    @Autowired
    private PromoteService promoteService;

    /**
     * 制定促销策略
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @PostMapping("/create-promotion")
    public Response createPromotion(UserVO userVO, @RequestBody PromoteVO promoteVO){
        return promoteService.createPromotion(userVO, promoteVO);
    }

    /**
     * 返回所有促销内容
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @PostMapping("/show-promotion")
    public Response showPromotion(){
        return Response.buildSuccess(promoteService.showAllPromotions());
    }


}

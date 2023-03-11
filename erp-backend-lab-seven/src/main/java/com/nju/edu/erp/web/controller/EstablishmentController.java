package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.service.EstablishmentService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @Autowired
    public EstablishmentController(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    /**
     * 用期初建账信息初始化数据库，在所有的add执行后使用
     * 调用后不可以再add，但是可以show或者select
     */
    @GetMapping("/initDB")
    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF, Role.GM})
    public Response initDB(){
        establishmentService.initializeDataBase();
        return Response.buildSuccess();
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addCategory")
    public Response addCategory(@RequestBody CategoryVO categoryVO){
        return Response.buildSuccess(establishmentService.addCategory(categoryVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addCategoryList")
    public Response addCategoryList(@RequestBody List<CategoryVO> categoryVO){
        return Response.buildSuccess(establishmentService.addCategoryList(categoryVO));
    }

    @GetMapping(value = "/showCategory")
    public Response showCategory(){
        return Response.buildSuccess(establishmentService.showCategory());
    }

    @PostMapping(value = "/selectCategory")
    public Response selectCategory(@RequestBody CategoryVO categoryVO){
        return Response.buildSuccess(establishmentService.selectCategory(categoryVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addProduct")
    public Response addProduct(@RequestBody ProductInfoVO productInfoVO){
        return Response.buildSuccess(establishmentService.addProduct(productInfoVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addProductList")
    public Response addProductList(@RequestBody List<ProductInfoVO> productInfoVO){
        return Response.buildSuccess(establishmentService.addProductList(productInfoVO));
    }

    @GetMapping(value = "/showProduct")
    public Response showProduct(){
        return Response.buildSuccess(establishmentService.showProduct());
    }

    @PostMapping(value = "/selectProduct")
    public Response selectProduct(@RequestBody ProductInfoVO productInfoVO){
        return Response.buildSuccess(establishmentService.selectProduct(productInfoVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addCustomer")
    public Response addCustomer(@RequestBody CustomerVO customerVO){
        return Response.buildSuccess(establishmentService.addCustomer(customerVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addCustomerList")
    public Response addCustomerList(@RequestBody List<CustomerVO> customerVO){
        return Response.buildSuccess(establishmentService.addCustomerList(customerVO));
    }

    @GetMapping("/showCustomer")
    public Response showCustomer(){
        return Response.buildSuccess(establishmentService.showCustomer());
    }

    @PostMapping(value = "/selectCustomer")
    public Response selectCustomer(@RequestBody CustomerVO customerVO){
        return Response.buildSuccess(establishmentService.selectCustomer(customerVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addAccount")
    public Response addAccount(@RequestBody AccountVO accountVO){
        return Response.buildSuccess(establishmentService.addAccount(accountVO));
    }

    @Authorized(roles = {Role.ADMIN, Role.FINANCIAL_STAFF})
    @PostMapping(value = "/addAccountList")
    public Response addAccountList(@RequestBody List<AccountVO> accountVO){
        return Response.buildSuccess(establishmentService.addAccountList(accountVO));
    }

    @GetMapping("/showAccount")
    public Response showAccount(){
        return Response.buildSuccess(establishmentService.showAccount());
    }

    @PostMapping(value = "/selectAccount")
    public Response selectAccount(@RequestBody AccountVO accountVO){
        return Response.buildSuccess(establishmentService.selectAccount(accountVO));
    }
}

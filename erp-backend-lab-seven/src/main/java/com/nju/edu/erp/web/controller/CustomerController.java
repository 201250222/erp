package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findByType")
    public Response findByType(@RequestParam CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

    @PostMapping("/add")
    public Response addCustomer(@RequestBody CustomerPO customerPO){
        int result = customerService.addCustomer(customerPO);
        if(result==1) return Response.buildSuccess();
        return Response.buildFailed("customerService.addCustomer(customerPO)", " affects row != 1");
    }

    @GetMapping("/delete")
    public Response deleteCustomerById(@RequestParam Integer id){
        int result = customerService.deleteCustomerById(id);
        if(result==1) return Response.buildSuccess();
        return Response.buildFailed("customerService.deleteCustomerById(id)", " affects row != 1");
    }

}

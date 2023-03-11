package com.nju.edu.erp.establishment;

import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class CustomerTest {

    @Autowired
    private EstablishmentService establishmentService;

    @Test
    @Transactional
    @Rollback
    public void test_insert(){
        CustomerVO customerVO = CustomerVO.builder()
                .name("yzh")
                .address("nowhere")
                .email("2668523393")
                .level(1)
                .lineOfCredit(new BigDecimal(1))
                .operator("admin")
                .payable(null)
                .phone("111111")
                .receivable(null)
                .type("type")
                .zipcode("000")
                .build();
        establishmentService.addCustomer(customerVO);
    }

    @Test
    public void test_select(){
        List<CustomerVO> list = establishmentService.showCustomer();
        for (CustomerVO customerVO : list) {
            System.out.println(customerVO);
        }
    }
}

package com.nju.edu.erp.establishment;

import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
public class EstablishmentTest {

    @Autowired
    private EstablishmentService establishmentService;

    @Test
    @Transactional
    @Rollback
    public void init(){
        CategoryVO categoryVO = CategoryVO.builder()
                .id(1)
                .isLeaf(true)
                .itemCount(1)
                .itemIndex(1)
                .parentId(1)
                .name("test")
                .build();
        establishmentService.addCategory(categoryVO);
        ProductInfoVO productInfoVO = new ProductInfoVO("0000000000400000","dell",4,"DELL",500,null,null,null,null);
        establishmentService.addProduct(productInfoVO);
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
        AccountVO accountVO = AccountVO.builder().name("account").cardNum("000123").build();
        establishmentService.addAccount(accountVO);
        establishmentService.initializeDataBase();
    }
}

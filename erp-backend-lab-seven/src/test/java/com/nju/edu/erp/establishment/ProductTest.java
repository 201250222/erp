package com.nju.edu.erp.establishment;

import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private EstablishmentService establishmentService;

    @Test
    @Transactional
    @Rollback
    public void test_insert(){
        ProductInfoVO productInfoVO = new ProductInfoVO("0000000000400000","dell",4,"DELL",500,null,null,null,null);
        establishmentService.addProduct(productInfoVO);
    }

    @Test
    public void test_select(){
        List<ProductInfoVO> list = establishmentService.showProduct();
        for (ProductInfoVO productInfoVO : list) {
            System.out.println(productInfoVO);
        }
    }
}

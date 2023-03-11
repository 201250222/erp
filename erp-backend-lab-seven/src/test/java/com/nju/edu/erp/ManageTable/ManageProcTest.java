package com.nju.edu.erp.ManageTable;

import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.ManageProcVO;
import com.nju.edu.erp.service.ManageProcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ManageProcTest {
    @Autowired
    private ManageProcService manageProcService;

    @Test
    @Transactional
    @Rollback(value = true)
    void test_all(){
        ManageProcVO manageProcVO = ManageProcVO.builder()
                .beginDate(null)
                .endDate(null)
                .customer(null)
                .salesman(null)
                .build();
        List<ManageProcSheetVO> list_sale = manageProcService.searchSale(manageProcVO);
        List<ManageProcSheetVO> list_purchase = manageProcService.searchPurchase(manageProcVO);
        List<ManageProcSheetVO> list_financial = manageProcService.searchFinancial(manageProcVO);
        List<ManageProcSheetVO> list_inventory = manageProcService.searchInventory(manageProcVO);
        System.out.println("SaleSheet is ");
        System.out.println(list_sale);
        System.out.println("PurchaseSheet is ");
        System.out.println(list_purchase);
        System.out.println("FinancialSheet is ");
        System.out.println(list_financial);
        System.out.println("InventorySheet is ");
        System.out.println(list_inventory);
    }
}

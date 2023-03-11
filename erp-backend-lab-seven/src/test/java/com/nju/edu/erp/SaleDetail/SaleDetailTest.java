package com.nju.edu.erp.SaleDetail;

import com.nju.edu.erp.model.vo.table.SaleDetailParamsVO;
import com.nju.edu.erp.model.vo.table.SaleDetailTableVO;
import com.nju.edu.erp.service.sheetService.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SaleDetailTest {

    @Autowired
    private SaleService saleService;

    @Test
    public void test(){
        SaleDetailParamsVO saleDetailParamsVO = new SaleDetailParamsVO(
                null, null,
                null,2,null,0
        );
        List<SaleDetailTableVO> saleDetailTableVOS = saleService.showSaleDetail(saleDetailParamsVO);
        for (SaleDetailTableVO tableVO : saleDetailTableVOS) {
            System.out.println(tableVO);
        }
    }
}

package com.nju.edu.erp.payment;

import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.service.sheetService.CreditService;
import com.nju.edu.erp.service.sheetService.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PaymentTests {

    @Autowired
    private PaymentSheetDao paymentSheetDao;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CreditService creditService;

    @Test
    void test() {
        // System.out.println(creditService.getSheetByState(null));
        List<SheetVO> list = paymentService.getSheetByState(null);
        for(SheetVO vo : list){
            PaymentSheetVO paymentSheetVO = (PaymentSheetVO) vo;
            System.out.println(paymentSheetVO);
        }
    }

    @Test
    @Transactional
    @Rollback
    void test_dao_insert(){
//        PaymentSheetPO po = paymentSheetDao.getLatest();
//        PaymentSheetPO newPO = new PaymentSheetPO("FKD-20220524-00003","account1","test",new BigDecimal(10000), PaymentSheetState.PENDING);
//        paymentSheetDao.addPaymentSheet(newPO);
//        List<PaymentSheetContentPO> list = new ArrayList<>();
//        list.add(new PaymentSheetContentPO(0,"FKD-20220524-00003",1,new BigDecimal(1000),"test"));
//        list.add(new PaymentSheetContentPO(0,"FKD-20220524-00003",1,new BigDecimal(2000),"test"));
//        list.add(new PaymentSheetContentPO(0,"FKD-20220524-00003",1,new BigDecimal(1500),"test"));
//        list.add(new PaymentSheetContentPO(0,"FKD-20220524-00003",1,new BigDecimal(3000),"test"));
//        list.add(new PaymentSheetContentPO(0,"FKD-20220524-00003",1,new BigDecimal(800),"test"));
//        paymentSheetDao.saveBatch(list);
    }

    @Test
    void test_dao_select(){
        List<PaymentSheetPO> list2 = paymentSheetDao.selectPaymentSheetByState(PaymentSheetState.PENDING);
        for (PaymentSheetPO paymentSheetPO : list2) {
            System.out.println(paymentSheetPO);
        }
        List<PaymentSheetContentPO> list1 = paymentSheetDao.selectPaymentSheetContentById("XJFYD-20220524-00003");
        for (PaymentSheetContentPO contentPO : list1) {
            System.out.println(contentPO);
        }
    }

    @Test
    void test_service(){
        List<SheetVO> list = paymentService.getSheetByState(null);
        for (SheetVO paymentSheetVO : list) {
            System.out.println((PaymentSheetVO) paymentSheetVO);
        }
    }

}

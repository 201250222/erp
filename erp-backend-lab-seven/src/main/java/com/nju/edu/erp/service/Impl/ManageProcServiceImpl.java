package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetContentVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.model.vo.inventory.ExcessiveSheetVO;
import com.nju.edu.erp.model.vo.inventory.WarehousePresentSheetVO;
import com.nju.edu.erp.model.vo.inventory.UnderSheetVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.service.ManageProcService;
import com.nju.edu.erp.service.sheetService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ManageProcServiceImpl implements ManageProcService {

    private final SaleService saleService;
    private final SaleReturnsService saleReturnsService;
    private final PurchaseService purchaseService;
    private final PurchaseReturnsService purchaseReturnsService;
    private final SalaryService salaryService;
    private final CreditService creditService;
    private final PaymentService paymentService;

    @Autowired
    public ManageProcServiceImpl(SaleService saleService, SaleReturnsService saleReturnsService,
                                 PurchaseService purchaseService, PurchaseReturnsService purchaseReturnsService,
                                 SalaryService salaryService, CreditService creditService,
                                 PaymentService paymentService){
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
        this.purchaseService = purchaseService;
        this.purchaseReturnsService = purchaseReturnsService;
        this.salaryService = salaryService;
        this.creditService = creditService;
        this.paymentService = paymentService;
    }

    @Override
    public List<ManageProcSheetVO> searchSale(ManageProcVO manageProcVO) {
        List<ManageProcSheetVO> ans = new LinkedList<>();
        List<SheetVO> saleList = saleService.getSheetByState(null);
        List<SheetVO> saleReturnsList = saleReturnsService.getSheetByState(null);
        for(SheetVO sale : saleList){
            SaleSheetVO saleSheetVO = (SaleSheetVO) sale;
            saleSheetVO.setType(ManageProcType.XSCHD);
            ans.add(saleSheetVO);
        }
        for(SheetVO saleReturn : saleReturnsList){
            SaleReturnsSheetVO saleReturnsSheetVO = (SaleReturnsSheetVO) saleReturn;
            saleReturnsSheetVO.setType(ManageProcType.XSTHD);
            ans.add(saleReturnsSheetVO);
        }
        ans.removeIf(manageProcSheetVO -> (!manageProcSheetVO.betweenDate(manageProcVO.getBeginDate(), manageProcVO.getEndDate()))
                || (!manageProcSheetVO.isCustomer(manageProcVO.getCustomer()))
                || (!manageProcSheetVO.isSalesMan(manageProcVO.getSalesman()))
                || (!manageProcSheetVO.isAllSuccessful(manageProcVO.isAllSuccessful())));
        return ans;
    }

    @Override
    public List<ManageProcSheetVO> searchPurchase(ManageProcVO manageProcVO) {
        List<ManageProcSheetVO> ans = new LinkedList<>();
        List<SheetVO> purchaseList = purchaseService.getSheetByState(null);
        List<SheetVO> purchaseReturnList = purchaseReturnsService.getSheetByState(null);
        for(SheetVO purchase : purchaseList){
            PurchaseSheetVO purchaseSheetVO = (PurchaseSheetVO) purchase;
            purchaseSheetVO.setType(ManageProcType.JHD);
            ans.add(purchaseSheetVO);
        }
        for(SheetVO purchaseReturn : purchaseReturnList){
            PurchaseReturnsSheetVO purchaseReturnsSheetVO = (PurchaseReturnsSheetVO) purchaseReturn;
            purchaseReturnsSheetVO.setType(ManageProcType.JHTHD);
            ans.add(purchaseReturnsSheetVO);
        }
        ans.removeIf(manageProcSheetVO -> (!manageProcSheetVO.betweenDate(manageProcVO.getBeginDate(), manageProcVO.getEndDate()))
                || (!manageProcSheetVO.isCustomer(manageProcVO.getCustomer()))
                || (!manageProcSheetVO.isSalesMan(manageProcVO.getSalesman()))
                || (!manageProcSheetVO.isAllSuccessful(manageProcVO.isAllSuccessful())));
        return ans;
    }

    @Override
    public List<ManageProcSheetVO> searchFinancial(ManageProcVO manageProcVO) {
        List<ManageProcSheetVO> ans = new LinkedList<>();
        List<SheetVO> creditList = creditService.getSheetByState(null);
        List<SheetVO> paymentList = paymentService.getSheetByState(null);
        List<SheetVO> salaryList = salaryService.getSheetByState(null);
        for(SheetVO credit : creditList){
            CreditSheetVO creditSheetVO = (CreditSheetVO) credit;
            creditSheetVO.setType(ManageProcType.SKD);
            ans.add(creditSheetVO);
        }
        for(SheetVO payment : paymentList){
            PaymentSheetVO paymentSheetVO = (PaymentSheetVO) payment;
            paymentSheetVO.setType(ManageProcType.FKD);
            ans.add(paymentSheetVO);
        }
        for(SheetVO salary : salaryList){
            SalarySheetVO salarySheetVO = (SalarySheetVO) salary;
            salarySheetVO.setType(ManageProcType.GZD);
            ans.add(salarySheetVO);
        }
        ans.removeIf(manageProcSheetVO -> (!manageProcSheetVO.betweenDate(manageProcVO.getBeginDate(), manageProcVO.getEndDate()))
                || (!manageProcSheetVO.isCustomer(manageProcVO.getCustomer()))
                || (!manageProcSheetVO.isSalesMan(manageProcVO.getSalesman()))
                || (!manageProcSheetVO.isAllSuccessful(manageProcVO.isAllSuccessful())));
        return ans;
    }

    @Override
    public List<ManageProcSheetVO> searchInventory(ManageProcVO manageProcVO) {

        ExcessiveSheetVO excessiveSheetVO = new ExcessiveSheetVO(16, "0000000000400000", new BigDecimal(10), "67", new Date(), "", ManageProcType.BYD);
        UnderSheetVO underSheetVO = new UnderSheetVO(16, "0000000000400001", new BigDecimal(10), "67", new Date(), "", ManageProcType.BSD);
        WarehousePresentSheetVO warehousePresentSheetVO = new WarehousePresentSheetVO("ZSD-20220709-00000","0000000000400001",10, "XSD-20220523-00000","xiaoshoujingli",new Date(),ManageProcType.ZSD,new BigDecimal(20000), WarehousePresentSheetState.SUCCESS);
        List<ManageProcSheetVO> ans = new LinkedList<>();
        ans.add(excessiveSheetVO);
        ans.add(underSheetVO);
        ans.add(warehousePresentSheetVO);
        return ans;
    }

    @Override
    public void makeRedFlush(RedFlushSheetVO redFlushSheetVO) {
        switch(redFlushSheetVO.getManageProcType()){
            case XSCHD:
                SaleSheetVO saleSheetVO = redFlushSheetVO.getSaleSheetVO();
                for(SaleSheetContentVO saleSheetContentVO : saleSheetVO.getSaleSheetContent()){
                    saleSheetContentVO.setQuantity(-saleSheetContentVO.getQuantity());
                }
                saleService.makeSheet(redFlushSheetVO.getUserVO(), saleSheetVO);
                break;
            case XSTHD:
                SaleReturnsSheetVO saleReturnsSheetVO = redFlushSheetVO.getSaleReturnsSheetVO();
                for(SaleReturnsSheetContentVO saleReturnsSheetContentVO : saleReturnsSheetVO.getSaleReturnsSheetContent()){
                    saleReturnsSheetContentVO.setQuantity(-saleReturnsSheetContentVO.getQuantity());
                }
                saleReturnsService.makeSheet(redFlushSheetVO.getUserVO(), saleReturnsSheetVO);
                break;
            case JHD:
                PurchaseSheetVO purchaseSheetVO = redFlushSheetVO.getPurchaseSheetVO();
                for(PurchaseSheetContentVO purchaseSheetContentVO : purchaseSheetVO.getPurchaseSheetContent()){
                    purchaseSheetContentVO.setQuantity(-purchaseSheetContentVO.getQuantity());
                }
                purchaseService.makeSheet(redFlushSheetVO.getUserVO(), purchaseSheetVO);
                break;
            case JHTHD:
                PurchaseReturnsSheetVO purchaseReturnsSheetVO = redFlushSheetVO.getPurchaseReturnsSheetVO();
                for(PurchaseReturnsSheetContentVO purchaseReturnsSheetContentVO : purchaseReturnsSheetVO.getPurchaseReturnsSheetContent()){
                    purchaseReturnsSheetContentVO.setQuantity(-purchaseReturnsSheetContentVO.getQuantity());
                }
                purchaseReturnsService.makeSheet(redFlushSheetVO.getUserVO(), purchaseReturnsSheetVO);
                break;
            case SKD:
                CreditSheetVO creditSheetVO = redFlushSheetVO.getCreditSheetVO();
                for(CreditSheetContentVO creditSheetContentVO : creditSheetVO.getCreditSheetContent()){
                    creditSheetContentVO.setAmount(creditSheetContentVO.getAmount().negate());
                }
                creditService.makeSheet(redFlushSheetVO.getUserVO(), creditSheetVO);
                break;
            case FKD:
                PaymentSheetVO paymentSheetVO = redFlushSheetVO.getPaymentSheetVO();
                for(PaymentSheetContentVO paymentSheetContentVO : paymentSheetVO.getPaymentSheetContent()){
                    paymentSheetContentVO.setAmount(paymentSheetContentVO.getAmount().negate());
                }
                paymentService.makeSheet(redFlushSheetVO.getUserVO(), paymentSheetVO);
                break;
            case GZD:
                SalarySheetVO salarySheetVO = redFlushSheetVO.getSalarySheetVO();
                salarySheetVO.setOriginSalary(-salarySheetVO.getOriginSalary());
                salarySheetVO.setAbsenceDeduction(-salarySheetVO.getAbsenceDeduction());
                salarySheetVO.setTax(-salarySheetVO.getTax());
                salarySheetVO.setActualSalary(-salarySheetVO.getActualSalary());
                salaryService.makeSheet(redFlushSheetVO.getUserVO(), salarySheetVO);
                break;
            default:
        }
    }

    @Override
    public void copy(RedFlushSheetVO redFlushSheetVO) {
        switch(redFlushSheetVO.getManageProcType()){
            case XSCHD:
                SaleSheetVO saleSheetVO = redFlushSheetVO.getSaleSheetVO();
                saleService.makeSheet(redFlushSheetVO.getUserVO(), saleSheetVO);
                break;
            case XSTHD:
                SaleReturnsSheetVO saleReturnsSheetVO = redFlushSheetVO.getSaleReturnsSheetVO();
                saleReturnsService.makeSheet(redFlushSheetVO.getUserVO(), saleReturnsSheetVO);
                break;
            case JHD:
                PurchaseSheetVO purchaseSheetVO = redFlushSheetVO.getPurchaseSheetVO();
                purchaseService.makeSheet(redFlushSheetVO.getUserVO(), purchaseSheetVO);
                break;
            case JHTHD:
                PurchaseReturnsSheetVO purchaseReturnsSheetVO = redFlushSheetVO.getPurchaseReturnsSheetVO();
                purchaseReturnsService.makeSheet(redFlushSheetVO.getUserVO(), purchaseReturnsSheetVO);
                break;
            case SKD:
                CreditSheetVO creditSheetVO = redFlushSheetVO.getCreditSheetVO();
                creditService.makeSheet(redFlushSheetVO.getUserVO(), creditSheetVO);
                break;
            case FKD:
                PaymentSheetVO paymentSheetVO = redFlushSheetVO.getPaymentSheetVO();
                paymentService.makeSheet(redFlushSheetVO.getUserVO(), paymentSheetVO);
                break;
            case GZD:
                SalarySheetVO salarySheetVO = redFlushSheetVO.getSalarySheetVO();
                salaryService.makeSheet(redFlushSheetVO.getUserVO(), salarySheetVO);
                break;
            default:
        }
    }
}

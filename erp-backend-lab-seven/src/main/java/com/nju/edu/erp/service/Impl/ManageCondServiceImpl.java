package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.inventory.WarehousePresentSheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.table.BalanceTableVO;
import com.nju.edu.erp.service.ManageCondService;
import com.nju.edu.erp.service.ManageProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ManageCondServiceImpl implements ManageCondService {

    private final ManageProcService manageProcService;

    @Autowired
    public ManageCondServiceImpl(ManageProcService manageProcService){
        this.manageProcService = manageProcService;
    }

    @Override
    public BalanceTableVO makeBalanceInquiry(BalanceTableVO balanceTableVO) throws ParseException {
        String beginDate = balanceTableVO.getBeginDate();
        String endDate = balanceTableVO.getEndDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ManageProcVO manageProcVO = ManageProcVO.builder()
                .beginDate(simpleDateFormat.parse(beginDate))
                .endDate(simpleDateFormat.parse(endDate))
                .customer(null)
                .salesman(null)
                .isAllSuccessful(true)
                .build();

        List<ManageProcSheetVO> saleSheets = manageProcService.searchSale(manageProcVO);
        List<ManageProcSheetVO> financialSheets = manageProcService.searchFinancial(manageProcVO);
        List<ManageProcSheetVO> purchaseSheets = manageProcService.searchPurchase(manageProcVO);
        List<ManageProcSheetVO> warehouseSheets = manageProcService.searchInventory(manageProcVO);

        // 折让后的收入（销售收入、商品类收入（进货退货差价，代金券与实际收款差额收入））
        BigDecimal actualIncoming = BigDecimal.ZERO;
        // 折让的金额（销售折让金额，代金券总额，销售退货就按比例折算）
        BigDecimal discounts = BigDecimal.ZERO;
        // 支出（销售成本、商品类支出（商品报损 商品赠出）、人力成本）
        BigDecimal outgoings = BigDecimal.ZERO;

        for(ManageProcSheetVO sheet: financialSheets){
            if(sheet.getClass()== SalarySheetVO.class){
                actualIncoming = outgoings.add(BigDecimal.valueOf(((SalarySheetVO) sheet).getActualSalary()));
            }
        }
        for (ManageProcSheetVO sheet: saleSheets){
            if(sheet.getClass() == SaleSheetVO.class){
                actualIncoming = actualIncoming.add(((SaleSheetVO) sheet).getFinalAmount());
                discounts = discounts.add(((SaleSheetVO) sheet).getDiscount().add(((SaleSheetVO) sheet).getVoucherAmount()));
                List<SaleSheetContentVO> saleSheetContents = ((SaleSheetVO) sheet).getSaleSheetContent();

            }
            else if(sheet.getClass() == SaleReturnsSheetVO.class){
                for(SaleReturnsSheetContentVO sheetContentVO: ((SaleReturnsSheetVO) sheet).getSaleReturnsSheetContent()){
                    actualIncoming = actualIncoming.subtract(sheetContentVO.getTotalAmount());
                }
            }
        }
        for (ManageProcSheetVO sheet: purchaseSheets){
            if(sheet.getClass()== PurchaseSheetVO.class){
                outgoings = outgoings.add(((PurchaseSheetVO) sheet).getTotalAmount());
            }
            else if(sheet.getClass() == PurchaseReturnsSheetVO.class){
                outgoings = outgoings.subtract(((PurchaseReturnsSheetVO) sheet).getTotalAmount());
            }
        }
        for(ManageProcSheetVO sheet: warehouseSheets){
            if(sheet.getClass() == WarehousePresentSheetVO.class){
                outgoings = outgoings.add(((WarehousePresentSheetVO) sheet).getValue());
            }
        }

        balanceTableVO.setActualIncoming(actualIncoming);
        balanceTableVO.setDiscounts(discounts);
        balanceTableVO.setOutgoings(outgoings);
        balanceTableVO.setProfits(actualIncoming.subtract(outgoings));
        return balanceTableVO;
    }

}

package com.nju.edu.erp.service.sheetService;

import com.nju.edu.erp.model.po.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;


import com.nju.edu.erp.model.vo.table.SaleDetailParamsVO;
import com.nju.edu.erp.model.vo.table.SaleDetailTableVO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SaleService extends SheetService{

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return
     */
    CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr);

    /**
     * 根据销售单Id搜索销售单信息
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    SaleSheetVO getSaleSheetById(String saleSheetId);

    /**
     * 查看销售明细表
     * @author 201250222
     * @param saleDetailParamsVO 筛选参数
     * @return 销售明细表
     */
    List<SaleDetailTableVO> showSaleDetail(SaleDetailParamsVO saleDetailParamsVO);
}

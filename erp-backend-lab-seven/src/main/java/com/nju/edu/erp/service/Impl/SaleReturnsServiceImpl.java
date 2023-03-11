package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.SaleReturnsSheetDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.dao.WarehouseDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.service.sheetService.SaleReturnsService;
import com.nju.edu.erp.service.sheetService.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {

    SaleReturnsSheetDao saleReturnsSheetDao;

    ProductService productService;

    ProductDao productDao;

    SaleSheetDao saleSheetDao;

    CustomerService customerService;

    WarehouseService warehouseService;

    WarehouseDao warehouseDao;

    @Autowired
    public SaleReturnsServiceImpl(SaleReturnsSheetDao saleReturnsSheetDao, ProductService productService, ProductDao productDao, SaleSheetDao saleSheetDao, CustomerService customerService, WarehouseService warehouseService, WarehouseDao warehouseDao){
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.productService = productService;
        this.productDao = productDao;
        this.saleSheetDao = saleSheetDao;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
        this.warehouseDao = warehouseDao;
    }

    /**
     * 制定销售退货单
     */
    @Override
    @Transactional
    public void makeSheet(UserVO userVO, SheetVO saleReturnsSheetVO){
        SaleReturnsSheetPO saleReturnsSheetPO = new SaleReturnsSheetPO();
        BeanUtils.copyProperties(saleReturnsSheetVO, saleReturnsSheetPO);
        // 此处根据制定单据人员确定操作员
        saleReturnsSheetPO.setOperator(userVO.getName());
        saleReturnsSheetPO.setCreateTime(new Date());
        SaleReturnsSheetPO latest = saleReturnsSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSTHD");
        saleReturnsSheetPO.setId(id);
        saleReturnsSheetPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        List<SaleSheetPO> saleSheetPOList = saleSheetDao.findAllSheet();
        Integer supplier = ((SaleReturnsSheetVO)saleReturnsSheetVO).getSupplier();
        List<SaleReturnsSheetContentPO> newSaleSheetContentPOList = new LinkedList<>();
        for(SaleReturnsSheetContentVO saleReturnsSheetContentVO : ((SaleReturnsSheetVO)saleReturnsSheetVO).getSaleReturnsSheetContent()){
            Integer count = saleReturnsSheetContentVO.getQuantity();
            String pid = saleReturnsSheetContentVO.getPid();
            BigDecimal totalAmount = BigDecimal.ZERO;
            SaleReturnsSheetContentPO newSaleReturnsSheetContentPO = new SaleReturnsSheetContentPO();
            newSaleReturnsSheetContentPO.setSaleReturnsSheetId(id);
            BeanUtils.copyProperties(saleReturnsSheetContentVO, newSaleReturnsSheetContentPO);
            for(SaleSheetPO saleSheetPO : saleSheetPOList){
                if(Objects.equals(saleSheetPO.getSupplier(), supplier)){
                    List<SaleSheetContentPO> saleSheetContentPOList = saleSheetDao.findContentBySheetId(saleSheetPO.getId());
                    for(SaleSheetContentPO saleSheetContentPO : saleSheetContentPOList){

                        if(Objects.equals(saleSheetContentPO.getPid(), pid)){
                            if(saleSheetContentPO.getQuantity() >= count){
                                count = 0;
                                BigDecimal calculator = saleSheetContentPO.getTotalPrice().divide(new BigDecimal(saleSheetContentPO.getQuantity()),4, RoundingMode.DOWN).multiply(new BigDecimal(count));
                                totalAmount = totalAmount.add(calculator);
                                break;
                            }else{
                                count -= saleSheetContentPO.getQuantity();
                                totalAmount = totalAmount.add(saleSheetContentPO.getTotalPrice());
                            }
                        }
                    }
                }
            }
            if(count != 0) {
                throw new RuntimeException("退货数量超出购买数量");
            }else {
                newSaleReturnsSheetContentPO.setTotalAmount(totalAmount);
            }
            newSaleSheetContentPOList.add(newSaleReturnsSheetContentPO);
        }
        saleReturnsSheetDao.saveSheetContent(newSaleSheetContentPOList);
        saleReturnsSheetDao.saveSheet(saleReturnsSheetPO);
    }

    /**
     * 根据状态获取销售退货单[不包括content信息](state == null 则获取所有销售退货单)
     *
     * @param state 销售退货单状态
     * @return 销售退货单
     */
    @Override
    public List<SheetVO> getSheetByState(BaseEnum state){
        List<SheetVO> res = new ArrayList<>();
        List<SaleReturnsSheetPO> all;
        if(state == null) {
            all = saleReturnsSheetDao.findAllSheet();
        } else {
            all = saleReturnsSheetDao.findAllByState((SaleReturnsSheetState) state);
        }
        for(SaleReturnsSheetPO po: all) {
            SaleReturnsSheetVO vo = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleReturnsSheetContentPO> alll = saleReturnsSheetDao.findContentBySaleReturnsSheetId(po.getId());
            List<SaleReturnsSheetContentVO> vos = new ArrayList<>();
            for (SaleReturnsSheetContentPO p : alll) {
                SaleReturnsSheetContentVO v = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setSaleReturnsSheetContent(vos);
            res.add(vo);
        }
        return res;
    }

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单id
     * @param state           销售退货单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleReturnsSheetId, BaseEnum state) {
        SaleReturnsSheetPO saleReturnsSheet = saleReturnsSheetDao.findOneById(saleReturnsSheetId);
        if (state.equals(SaleReturnsSheetState.FAILURE)) {
            if (saleReturnsSheet.getState() == SaleReturnsSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = saleReturnsSheetDao.updateState(saleReturnsSheetId, (SaleReturnsSheetState) state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleReturnsSheetState prevState;
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleReturnsSheetDao.updateStateV2(saleReturnsSheetId, prevState, (SaleReturnsSheetState) state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                //审批完成，修改库存状态
                List<SaleReturnsSheetContentPO> contents = saleReturnsSheetDao.findContentBySaleReturnsSheetId(saleReturnsSheetId);
                BigDecimal payableToDeduct = BigDecimal.ZERO;

                for (SaleReturnsSheetContentPO content : contents) {
                    String pid = content.getPid();
                    Integer quantity = content.getQuantity();
                    List<WarehousePO> warehousePOs = warehouseDao.findByPidOrderByPurchasePricePos(pid);
                    BigDecimal unitPrice = content.getTotalAmount().divide(new BigDecimal(content.getQuantity().toString()), 4, RoundingMode.DOWN);
                    for (WarehousePO warehousePO : warehousePOs) {
                        if (warehousePO == null) throw new RuntimeException("单据发生错误！请联系管理员！");
                        Integer quantityToBeDeducted;
                        if (warehousePO.getQuantity() >= quantity) {
                            quantityToBeDeducted = quantity;
                            quantity = 0;
                        } else {
                            quantityToBeDeducted = warehousePO.getQuantity();
                            quantity -= warehousePO.getQuantity();
                        }
                        warehousePO.setQuantity(quantityToBeDeducted);
                        warehouseDao.deductQuantity(warehousePO);
                        ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                        productInfoVO.setQuantity(productInfoVO.getQuantity() - quantityToBeDeducted);
                        productService.updateProduct(productInfoVO);
                        payableToDeduct = payableToDeduct.add(unitPrice.multiply(BigDecimal.valueOf(quantityToBeDeducted)));
                    }
                    if (quantity > 0) {
                        saleReturnsSheetDao.updateState(saleReturnsSheetId, SaleReturnsSheetState.FAILURE);
                        throw new RuntimeException("商品数量不足！审批失败！");
                    }
                }
                Integer supplier = saleReturnsSheet.getSupplier();
                CustomerPO customer = customerService.findCustomerById(supplier);

                customer.setPayable(customer.getPayable().subtract(payableToDeduct));
                customerService.updateCustomer(customer);
            }
        }
    }
}

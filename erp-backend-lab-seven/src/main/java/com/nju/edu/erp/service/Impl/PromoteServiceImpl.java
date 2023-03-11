package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.PromoteDao;
import com.nju.edu.erp.model.po.PromotePO;
import com.nju.edu.erp.model.po.SaleSheetContentPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.vo.promote.PromoteVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PromoteService;
import com.nju.edu.erp.service.promote.*;
import com.nju.edu.erp.service.sheetService.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PromoteServiceImpl implements PromoteService {

    private PromoteDao promoteDao;
    private CustomerDao customerDao;
    private WarehouseService warehouseService;

    @Autowired
    public PromoteServiceImpl(PromoteDao promoteDao, CustomerDao customerDao, WarehouseService warehouseService){
        this.promoteDao = promoteDao;
        this.customerDao = customerDao;
        this.warehouseService = warehouseService;
    }

    /**
     * 制定促销策略
     * @param userVO
     * @param promoteVO 具体优惠策略
     * @return
     */
    @Override
    public Response createPromotion(UserVO userVO, PromoteVO promoteVO) {
        PromotePO promotePO = new PromotePO();
        BeanUtils.copyProperties(promoteVO, promotePO);
        promotePO.setOperator(userVO.getName());
        // 条件检查
        switch (promotePO.getPromoteTrigger()){
            case 0://组合商品降价
                if((promotePO.getProductId1()==null || promotePO.getAmount1()==null)
                        ||(promotePO.getProductId2()==null || promotePO.getAmount2()==null)
                        ||(promotePO.getProductId3()==null || promotePO.getAmount3()==null)
                        ||(promotePO.getProductId1().equals(promotePO.getProductId2()))
                        ||(promotePO.getProductId1().equals(promotePO.getProductId3()))
                        ||(promotePO.getProductId2().equals(promotePO.getProductId3()))
                ){
                    return Response.buildFailed("10000", "非法的组合商品降价信息！");
                }
                break;
            case 1://总价降价
                if(promotePO.getTotalPrice()==null){
                    return Response.buildFailed("10001","非法的总价降价信息！");
                }
                break;
            default:
                return Response.buildFailed("10002","非法的触发类型参数！");
        }
        switch (promotePO.getPromoteType()){
            case 0:
                if(promotePO.getDiscount()==null){
                    return Response.buildFailed("10010","非法的折扣信息！");
                }
                double maxDiscount;
                switch (userVO.getRole()){
                    case SALE_STAFF -> maxDiscount = 0.1;
                    case SALE_MANAGER -> maxDiscount = 0.2;
                    default -> maxDiscount = 1.0;
                }
                if(promotePO.getDiscount()<0 || promotePO.getDiscount() > maxDiscount){
                    return Response.buildFailed("10010", "非法的折扣信息");
                }
                break;
            case 1:
                if(promotePO.getPresentId()==null || promotePO.getPresentNumber()==null){
                    return Response.buildFailed("10011", "非法的赠品信息！");
                }
                break;
            case 2:
                if(promotePO.getVoucherPrice()==null){
                    return Response.buildFailed("10012","非法的代金券信息！");
                }
                break;
            default:
                return Response.buildFailed("10013","非法的促销类型参数！");
        }
        promoteDao.save(promotePO);
        return Response.buildSuccess();
    }

    /**
     * 返回所有的促销策略
     */
    @Override
    public List<PromoteVO> showAllPromotions() {
        List<PromotePO> all = promoteDao.findAll();
        List<PromoteVO> res = new ArrayList<>();
        for(PromotePO promotePO: all){
            PromoteVO promoteVO = new PromoteVO();
            BeanUtils.copyProperties(promotePO, promoteVO);
            res.add(promoteVO);
        }
        return res;
    }

    /**
     * 寻找合适的促销策略并使用
     * @param saleSheetPO
     * @param saleSheetContentPOs
     * @return
     */
    @Override
    public SaleSheetPO promote(SaleSheetPO saleSheetPO, List<SaleSheetContentPO> saleSheetContentPOs){
        // 找到符合条件的促销
        List<PromotePO> list = findAllTriggered(saleSheetPO,saleSheetContentPOs);
        if(list.isEmpty()){
            return saleSheetPO;
        }
        // 如何选择多个符合条件的促销策略中选择未实现，这里随便挑的
        PromotePO promotePO = list.get(0);
        PromoteStrategy promoteStrategy = switch (promotePO.getPromoteType()) {
            case 0 -> new DiscountPromote(saleSheetPO, promotePO.getDiscount());
            case 1 -> new PresentPromote(saleSheetPO, promotePO.getPresentId(), promotePO.getPresentNumber(),warehouseService);
            default -> new VoucherPromote(saleSheetPO, promotePO.getVoucherPrice());
        };
        Promote p = new Promote(promoteStrategy);
        return p.doPromotion();
    }

    /**
     * 寻找满足促销条件的促销策略
     * @param saleSheetPO
     * @param saleSheetContentPOs
     * @return
     */
    private List<PromotePO> findAllTriggered(SaleSheetPO saleSheetPO, List<SaleSheetContentPO> saleSheetContentPOs){
        List<PromotePO> res = new ArrayList<>();
        int level = customerDao.findOneById(saleSheetPO.getSupplier()).getLevel();
        // 组合商品降价
        List<PromotePO> productMatchList = promoteDao.findAllByTriggerType(0);
        for(PromotePO promotePO: productMatchList){
            if(saleSheetPO.getCreateTime().after(promotePO.getBeginTime())
                    &&saleSheetPO.getCreateTime().before(promotePO.getEndTime())){
                continue;
            }
            if(promotePO.getCustomerLevel()!=level){
                continue;
            }
            ArrayList<String> pids = new ArrayList<>(Arrays.asList(promotePO.getProductId1(),promotePO.getProductId2(),promotePO.getProductId3()));
            int[] amounts = new int[]{promotePO.getAmount1(), promotePO.getAmount2(), promotePO.getAmount3()};
            int cnt=0;
            for(SaleSheetContentPO saleSheetContentPO: saleSheetContentPOs){
                int idx = pids.indexOf(saleSheetContentPO.getPid());
                if(idx>=0 && saleSheetContentPO.getQuantity()>=amounts[idx]){
                    cnt++;
                }
            }
            if(cnt>=pids.size()){
                res.add(promotePO);
            }
        }
        // 总价促销
        List<PromotePO> totalPriceList = promoteDao.findAllByTriggerType(1);
        for (PromotePO promotePO: totalPriceList){
            if(saleSheetPO.getCreateTime().after(promotePO.getBeginTime())
                    &&saleSheetPO.getCreateTime().before(promotePO.getEndTime())){
                continue;
            }
            if(promotePO.getCustomerLevel()!=level){
                continue;
            }
            if(saleSheetPO.getRawTotalAmount().compareTo(promotePO.getTotalPrice())>=0){
                res.add(promotePO);
            }
        }

        return res;
    }


}

package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.SaleReturnsSheetContentPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.vo.table.SaleDetailParamsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleReturnsSheetDao {
    /**
     * 获取最近一条销售退货单
     * @return 最近一条销售退货单
     */
    SaleReturnsSheetPO getLatest();

    /**
     * 通过purchaseReturnsSheetId找到对应条目
     * @param saleReturnsSheetId 进货退货单id
     * @return
     */
    SaleReturnsSheetPO findOneById(String saleReturnsSheetId);

    /**
     * 存入一条销售单记录
     * @param toSave 一条销售单记录
     * @return 影响的行数
     */
    int saveSheet(SaleReturnsSheetPO toSave);

    /**
     * 把销售退货单上的具体内容存入数据库
     * @param saleReturnsSheetContent 入销售退货单上的具体内容
     */
    int saveSheetContent(List<SaleReturnsSheetContentPO> saleReturnsSheetContent);

    /**
     * 查找所有销售退货单
     */
    List<SaleReturnsSheetPO> findAllSheet();

    /**
     * 查找指定状态的销售单
     * @param state 具体状态
     * @return 查找结果
     */
    List<SaleReturnsSheetPO> findAllByState(SaleReturnsSheetState state);

    /**
     * 根据id查找对应的销售退货单具体内容
     * @param id 单据id
     * @return 查找结果
     */
    List<SaleReturnsSheetContentPO> findContentBySaleReturnsSheetId(String id);

    /**
     * 根据 purchaseReturnsSheetId 找到条目， 并更新其状态为state
     * @param saleReturnsSheetId 进货退货单id
     * @param state 进货退货单状态
     * @return 影响的条目数
     */
    int updateState(String saleReturnsSheetId, SaleReturnsSheetState state);

    /**
     * 根据 purchaseReturnsSheetId 和 prevState 找到条目， 并更新其状态为state
     * @param saleReturnsSheetId 进货退货单id
     * @param prevState 进货退货单之前的状态
     * @param state 进货退货单状态
     * @return 影响的条目数
     */
    int updateStateV2(String saleReturnsSheetId, SaleReturnsSheetState prevState, SaleReturnsSheetState state);

    /**
     * 查看符合销售明细条件的所有销售退货单
     * @author 201250222
     * @param saleDetailParamsVO 筛选条件
     * @return 销售退货单
     */
    List<SaleReturnsSheetPO> selectSheetByParam(SaleDetailParamsVO saleDetailParamsVO);
}

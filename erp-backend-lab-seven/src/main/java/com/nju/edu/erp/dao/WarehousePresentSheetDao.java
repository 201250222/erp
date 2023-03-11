package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.po.WarehousePresentSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarehousePresentSheetDao {
    WarehousePresentSheetPO getLatest();
    void save(WarehousePresentSheetPO toSave);
    List<WarehousePresentSheetPO> findAll();
    List<WarehousePresentSheetPO> findAllByState(WarehousePresentSheetState state);
    WarehousePresentSheetPO getById(String id);
    int updateById(WarehousePresentSheetPO warehousePresentSheetPO);
}

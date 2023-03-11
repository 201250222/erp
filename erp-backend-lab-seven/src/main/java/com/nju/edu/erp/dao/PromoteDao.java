package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.PromotePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PromoteDao {

    /**
     * 返回最近的一条促销策略
     */
    PromotePO getLatest();

    /**
     * 将一条促销策略存入数据库
     */
    int save(PromotePO toSave);

    /**
     * 返回所有的促销策略
     */
    List<PromotePO> findAll();

    /**
     * 根据触发类型返回促销策略
     */
    List<PromotePO> findAllByTriggerType(Integer promoteTrigger);
}

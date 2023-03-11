package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SalaryDao {
    /**
     * 获取最近一条工资单
     * @return
     */
    SalarySheetPO getLatest();
    /**
     * 存入一条工资单记录
     * @param toSave 一条工资单记录
     * @return 影响的行数
     */
    int save(SalarySheetPO toSave);
    /**
     * 返回所有工资单
     * @return 工资单列表
     */
    List<SalarySheetPO> findAll();

    /**
     * 根据state返回工资单
     * @param state 工资单状态
     * @return 工资单列表
     */
    List<SalarySheetPO> findAllByState(SalarySheetState state);

    /**
     * 根据单号更新状态
     * @param salarySheetId
     * @param state
     * @return
     */
    int updateState(String salarySheetId, SalarySheetState state);

    /**
     * 根据单号寻找工资单
     * @param salarySheetId
     * @return
     */
    SalarySheetPO findOneById(String salarySheetId);

    /**
     * 返回该发放日期下的该员工的工资单
     * @param releaseDate
     * @param staffName
     * @return
     */
    List<SalarySheetPO> findByDateAndStaffName(String releaseDate, String staffName);

    /**
     * 返回所有工资单(GROUP BY staff_id and SUM)
     * @Author 201250222
     * @return 工资单列表
     */
    SalarySheetPO findAllSum(String staffName);
}

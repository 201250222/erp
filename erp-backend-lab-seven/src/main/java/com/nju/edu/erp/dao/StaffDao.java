package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.ClockInPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StaffDao {
    List<StaffPO> showAllStaff();

    /**
     * 添加员工
     * @param staffPO
     * @return updated row number
     */
    int addStaff(StaffPO staffPO);

    /**
     * 员工签到
     * @return update rows
     */
    int clockIn(ClockInPO clockInPO);

    /**
     * 根据员工姓名查询该员工签到情况
     */
    List<ClockInPO> selectById(String name);

    /**
     * 展示所有员工所有信息
     */
    List<StaffInfoVO> showStaffInfo();

    /**
     * 更改岗位薪资
     */
    int updateSalary(String role, Integer salary);
}

package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.ClockInPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import com.nju.edu.erp.model.vo.StaffVO;

import java.util.List;

public interface StaffService {

    List<StaffPO> showAllStaff();

    /**
     * 添加员工
     * @param staff:StaffVO
     * @return updated row number
     */
    int addStaff(StaffVO staff);

    /**
     * 员工签到
     * @return update rows
     */
    int clockIn(String name);

    /**
     * 根据员工姓名查询该员工签到情况
     */
    List<ClockInPO> selectByName(String name);

    /**
     * 展示所有员工所有信息
     */
    List<StaffInfoVO> showStaffInfo();

    /**
     * 更改岗位薪资
     */
    int updateSalary(Role role, Integer salary);
}

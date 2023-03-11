package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.ClockInPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDao staffDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<StaffPO> showAllStaff() {
        return staffDao.showAllStaff();
    }

    @Override
    public int addStaff(StaffVO staff) {
        StaffPO po = new StaffPO(0,staff.getName(),staff.getGender(),staff.getBirthday(),staff.getPhone(),staff.getRole(),staff.getBankCard(),0);
        User user = new User(0, staff.getName(), "123456",staff.getRole());
        userDao.createUser(user);
        return staffDao.addStaff(po);
    }

    @Override
    public int clockIn(String name) {
        return staffDao.clockIn(new ClockInPO(name, Calendar.getInstance()));
    }

    @Override
    public List<StaffInfoVO> showStaffInfo() {
        return staffDao.showStaffInfo();
    }

    @Override
    public List<ClockInPO> selectByName(String name) {
        return staffDao.selectById(name);
    }

    @Override
    public int updateSalary(Role role, Integer salary) {
        return staffDao.updateSalary(role.toString(),salary);
    }
}

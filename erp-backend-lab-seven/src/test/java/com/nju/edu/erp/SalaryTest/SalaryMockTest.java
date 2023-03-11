package com.nju.edu.erp.SalaryTest;

import com.nju.edu.erp.dao.SalaryDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.ClockInPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffInfoVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.service.Impl.SalaryServiceImpl;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.service.salaryStrategy.Salary;
import com.nju.edu.erp.service.salaryStrategy.strategies.MonthPerformanceSalary;
import com.nju.edu.erp.service.salaryStrategy.strategies.YearSalary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SalaryMockTest {

    @InjectMocks
    SalaryServiceImpl salaryService;

    @Mock
    private SalaryDao salaryDao;

    @Mock
    private StaffService staffService;

    @Mock
    private AccountService accountService;

    @Test
    public void Test01(){
        int[] original = new int[]{4000,7000,9000};
        int[] tax = new int[]{0,60,190};
        int[] res = new int[3];
        for(int i=0; i<3; i++){
            res[i] = salaryService.taxCalculator(original[i], true);
        }
        Assert.assertArrayEquals(res,tax);
    }
    @Test
    public void Test02(){
        UserVO userVO = UserVO.builder()
                .name("123")
                .password("456")
                .role(Role.FINANCIAL_STAFF)
                .build();

        StaffInfoVO staffInfoVO1 = StaffInfoVO.builder()
                .id(0)
                .bankCard("1234567890")
                .birthday(new Date())
                .gender("女")
                .name("22")
                .performanceSalary(1233)
                .phone("12345678901")
                .roleSalary(5000)
                .role(Role.SALE_STAFF)
                .salary(new Salary(new MonthPerformanceSalary()))
                .build();
        StaffInfoVO staffInfoVO2 = StaffInfoVO.builder()
                .id(1)
                .bankCard("1234567890")
                .birthday(new Date())
                .gender("女")
                .name("33")
                .performanceSalary(10000)
                .phone("12345678901")
                .roleSalary(1000000)
                .role(Role.GM)
                .salary(new Salary(new YearSalary()))
                .build();

        List<StaffInfoVO> staffInfoVOS = new ArrayList<>();
        staffInfoVOS.add(staffInfoVO1);
        staffInfoVOS.add(staffInfoVO2);

        StaffPO staffPO1 = new StaffPO();
        StaffPO staffPO2 = new StaffPO();
        BeanUtils.copyProperties(staffInfoVO1, staffPO1);
        BeanUtils.copyProperties(staffInfoVO2, staffPO2);

        List<StaffPO> staffPOS = new ArrayList<>();
        staffPOS.add(staffPO1);
        staffPOS.add(staffPO2);

        String date = "2022-01-01";

        Mockito.when(staffService.showAllStaff()).thenReturn(staffPOS);
        Mockito.when(staffService.showStaffInfo()).thenReturn(staffInfoVOS);
        Mockito.when(salaryDao.findByDateAndStaffName(date,staffInfoVO1.getName())).thenReturn(new ArrayList<>());
        Mockito.when(salaryDao.getLatest()).thenReturn(null);

        List<ClockInPO> clockInPOS1 = new ArrayList<>();
        for(int i=0; i<31; i++){
            Calendar c = Calendar.getInstance();
            c.set(2021,Calendar.DECEMBER,i+1);
            ClockInPO clockInPO = ClockInPO.builder()
                    .date(c)
                    .staffName(staffInfoVO1.getName())
                    .build();
            clockInPOS1.add(clockInPO);
        }

        Mockito.when(staffService.selectByName(staffInfoVO1.getName())).thenReturn(clockInPOS1);

        salaryService.makeAllSalarySheets(userVO, date);
    }





}

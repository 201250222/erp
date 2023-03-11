package com.nju.edu.erp.SalaryTest;

import com.nju.edu.erp.dao.SaleReturnsSheetDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.vo.SalarySheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.Impl.SalaryServiceImpl;
import com.nju.edu.erp.service.sheetService.SalaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SalaryTest {
    @Autowired
    SalaryServiceImpl salaryService;

    @Autowired
    SaleReturnsSheetDao saleReturnsSheetDao;

    @Test
    public void Test_1(){
        UserVO userVO = UserVO.builder()
                .name("67")
                .role(Role.GM)
                .password("123456")
                .build();
        SalarySheetVO salarySheetVO = SalarySheetVO.builder()
                .id("GZD-20220701-00000")
                .staffID(1)
                .staffName("seecoder")
                .releaseDate("2022-09-01")
                .staffBankCard("123456")
                .originSalary(10000)
                .tax(200)
                .absenceDeduction(0)
                .actualSalary(9800)
                .operator("67")
                .state(SalarySheetState.PENDING)
                .build();
        salaryService.makeASheet(userVO, salarySheetVO);
    }
}

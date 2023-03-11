package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffPO {
    /**
     * 员工id
     */
    private Integer id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 职位
     */
    private Role role;
    /**
     * 银行账户
     */
    private String bankCard;
    /**
     * 提成
     */
    private Integer performanceSalary;
}

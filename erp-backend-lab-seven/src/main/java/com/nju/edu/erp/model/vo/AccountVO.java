package com.nju.edu.erp.model.vo;


import com.nju.edu.erp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    /**
     * 账户名称
     */
    private String name;

    /**
     * 银行卡号
     */
    private String cardNum;

}

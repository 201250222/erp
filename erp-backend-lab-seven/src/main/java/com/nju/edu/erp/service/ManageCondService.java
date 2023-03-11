package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.table.BalanceTableVO;

import java.text.ParseException;

public interface ManageCondService {
    BalanceTableVO makeBalanceInquiry(BalanceTableVO balanceTableVO) throws ParseException;
}

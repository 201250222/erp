package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClockInPO {
    /**
     * 员工姓名
     */
    String staffName;
    /**
     * 打卡日期
     */
    Calendar date;

    public Date getDate(){
        return date.getTime();
    }

    public void setDate(Date sqlDate){
        date = Calendar.getInstance();
        date.setTime(sqlDate);
    }
}

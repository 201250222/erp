package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.PaymentSheetDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetContentVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.sheetService.PaymentService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentSheetDao paymentSheetDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @Override
    public void makeSheet(UserVO userVO, SheetVO sheetVO) {
        PaymentSheetVO paymentSheetVO = (PaymentSheetVO) sheetVO;
        PaymentSheetPO paymentSheetPO = new PaymentSheetPO();
        BeanUtils.copyProperties(paymentSheetVO, paymentSheetPO);
        paymentSheetPO.setOperator(paymentSheetVO.getOperator());
        paymentSheetPO.setCreateTime(new Date());
        PaymentSheetPO last = paymentSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(last==null?null:last.getId(), "FKD");
        paymentSheetPO.setId(id);
        paymentSheetPO.setPaymentSheetState(PaymentSheetState.PENDING);
        BigDecimal total = new BigDecimal(0);
        List<PaymentSheetContentPO> paymentSheetContentPOS = new ArrayList<>();
        for(PaymentSheetContentVO paymentSheetContentVO: paymentSheetVO.getPaymentSheetContent()){
            PaymentSheetContentPO po = new PaymentSheetContentPO();
            BeanUtils.copyProperties(paymentSheetContentVO,po);
            po.setPaymentSheetId(id);
            BigDecimal amount = paymentSheetContentVO.getAmount();
            total = total.add(amount);
            paymentSheetContentPOS.add(po);
        }
        paymentSheetDao.saveBatch(paymentSheetContentPOS);
        paymentSheetPO.setTotalAmount(total);
        paymentSheetDao.addPaymentSheet(paymentSheetPO);
    }

    @Override
    public void approval(String id, BaseEnum a) {
        PaymentSheetState ans = (PaymentSheetState) a;
        if(ans==PaymentSheetState.SUCCESS){
            paymentSheetDao.updatePaymentSheet(id,PaymentSheetState.SUCCESS);
            PaymentSheetPO paymentSheetPO = paymentSheetDao.selectPaymentSheetById(id);
            String accountName = paymentSheetPO.getAccountName();
            accountService.updateAccount(accountName, paymentSheetPO.getTotalAmount().multiply(new BigDecimal(-1)));
            List<PaymentSheetContentPO> list = paymentSheetDao.selectPaymentSheetContentById(id);
            for (PaymentSheetContentPO paymentSheetContentPO : list) {
                Integer customer = paymentSheetContentPO.getCustomer();
                CustomerPO customerPO = customerService.findCustomerById(customer);
                customerPO.setReceivable(customerPO.getReceivable().add(paymentSheetContentPO.getAmount()));
                customerService.updateCustomer(customerPO);
            }
        }else{
            paymentSheetDao.updatePaymentSheet(id,PaymentSheetState.FAILURE);
        }
    }

    @Override
    public List<SheetVO> getSheetByState(BaseEnum state1) {
        PaymentSheetState state = (PaymentSheetState) state1;
        List<SheetVO> list = new ArrayList<>();
        List<PaymentSheetPO> poList;
        if(state == null){
            poList = paymentSheetDao.selectAllPaymentSheet();
        }else{
            poList = paymentSheetDao.selectPaymentSheetByState(state);
        }
        for (PaymentSheetPO po : poList) {
            PaymentSheetVO paymentSheetVO = new PaymentSheetVO();
            List<PaymentSheetContentVO> paymentSheetContentVOS = new ArrayList<>();
            BeanUtils.copyProperties(po,paymentSheetVO);
            // paymentSheetVO.setPaymentSheetState(getState(paymentSheetVO.getId()));
            List<PaymentSheetContentPO> paymentSheetContentPOS = paymentSheetDao.selectPaymentSheetContentById(po.getId());
            for (PaymentSheetContentPO contentPO : paymentSheetContentPOS) {
                PaymentSheetContentVO paymentSheetContentVO = new PaymentSheetContentVO();
                BeanUtils.copyProperties(contentPO, paymentSheetContentVO);
                paymentSheetContentVOS.add(paymentSheetContentVO);
            }
            paymentSheetVO.setPaymentSheetContent(paymentSheetContentVOS);
            list.add(paymentSheetVO);
        }
        return list;
    }

    @Override
    public PaymentSheetVO selectPaymentById(String id) {
        PaymentSheetPO paymentSheetPO = paymentSheetDao.selectPaymentSheetById(id);
        PaymentSheetVO paymentSheetVO = new PaymentSheetVO();
        BeanUtils.copyProperties(paymentSheetPO, paymentSheetVO);
        // paymentSheetVO.setPaymentSheetState(getState(paymentSheetVO.getId()));
        List<PaymentSheetContentPO> paymentSheetContentPOList = paymentSheetDao.selectPaymentSheetContentById(paymentSheetPO.getId());
        List<PaymentSheetContentVO> list = new ArrayList<>();
        for (PaymentSheetContentPO paymentSheetContentPO : paymentSheetContentPOList) {
            PaymentSheetContentVO paymentSheetContentVO = new PaymentSheetContentVO();
            BeanUtils.copyProperties(paymentSheetContentPO,paymentSheetContentVO);
            list.add(paymentSheetContentVO);
        }
        paymentSheetVO.setPaymentSheetContent(list);
        return paymentSheetVO;
    }

    @Deprecated
    private PaymentSheetState getState(String id){
        String state = paymentSheetDao.getStateById(id);
        switch (state){
            case "审批完成":
            case "SUCCESS":
                return PaymentSheetState.SUCCESS;
            case "待审批":
            case "PENDING":
                return PaymentSheetState.PENDING;
            case "审批失败":
            case "FAILURE":
                return PaymentSheetState.FAILURE;
        }
        return null;
    }

    @Override
    public List<PaymentSheetPO> selectPaymentSheetByDate(Date beginTime, Date endTime) {
        return paymentSheetDao.selectPaymentSheetByDate(beginTime,endTime);
    }
}

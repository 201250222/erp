package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CreditSheetDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.po.CreditSheetContentPO;
import com.nju.edu.erp.model.po.CreditSheetPO;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetContentVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.service.AccountService;
import com.nju.edu.erp.service.sheetService.CreditService;
import com.nju.edu.erp.service.CustomerService;
import org.springframework.stereotype.Service;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    CreditSheetDao creditSheetDao;
    CustomerService customerService;
    AccountService accountService;

    @Autowired
    CreditServiceImpl(CreditSheetDao creditSheetDao, CustomerService customerService,
                      AccountService accountService){
        this.creditSheetDao = creditSheetDao;
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @Override
    public void makeSheet(UserVO userVO, SheetVO sheetVO) {
        CreditSheetVO creditSheetVO = (CreditSheetVO) sheetVO;
        CreditSheetPO creditSheetPO = new CreditSheetPO();
        BeanUtils.copyProperties(creditSheetVO, creditSheetPO);
        creditSheetPO.setCreateTime(new Date());
        CreditSheetPO latest = creditSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
        creditSheetPO.setId(id);
        creditSheetPO.setCreditSheetState(CreditSheetState.PENDING);
        BigDecimal total = BigDecimal.ZERO;
        List<CreditSheetContentPO> list = new ArrayList<>();
        for(CreditSheetContentVO creditSheetContentVO : creditSheetVO.getCreditSheetContent()){
            CreditSheetContentPO creditSheetContentPO = new CreditSheetContentPO();
            BeanUtils.copyProperties(creditSheetContentVO, creditSheetContentPO);
            creditSheetContentPO.setCreditSheetId(id);
            BigDecimal amount = creditSheetContentVO.getAmount();
            total = total.add(amount);
            list.add(creditSheetContentPO);
        }
        creditSheetDao.saveBatch(list);
        creditSheetPO.setTotalAmount(total);
        creditSheetDao.addCreditSheet(creditSheetPO);
    }

    @Override
    public void approval(String id, BaseEnum a) {
        CreditSheetState ans = (CreditSheetState) a;
        if(ans == CreditSheetState.SUCCESS){
            creditSheetDao.updateCreditSheet(id, CreditSheetState.SUCCESS);
            CreditSheetPO creditSheetPO = creditSheetDao.selectCreditSheetById(id);
            Integer customer = creditSheetPO.getCustomer();
            CustomerPO customerPO = customerService.findCustomerById(customer);
            customerPO.setReceivable(customerPO.getReceivable().add(creditSheetPO.getTotalAmount()));
            customerService.updateCustomer(customerPO);
            List<CreditSheetContentPO> list = creditSheetDao.selectCreditSheetContentById(id);
            for(CreditSheetContentPO creditSheetContentPO : list){
                String accountName = creditSheetContentPO.getAccountName();
                BigDecimal amount = creditSheetContentPO.getAmount();
                accountService.updateAccount(accountName, amount);
            }
        }else{
            creditSheetDao.updateCreditSheet(id, CreditSheetState.FAILURE);
        }
    }

    @Override
    public List<SheetVO> getSheetByState(BaseEnum s) {
        CreditSheetState state = (CreditSheetState) s;
        List<SheetVO> list = new ArrayList<>();
        List<CreditSheetPO> creditSheetPOList;
        if(state == null){
            creditSheetPOList = creditSheetDao.selectAllCreditSheet();
        }else{
            creditSheetPOList = creditSheetDao.selectCreditSheetByState(state);
        }
        for(CreditSheetPO creditSheetPO : creditSheetPOList){
            CreditSheetVO creditSheetVO = new CreditSheetVO();
            List<CreditSheetContentVO> creditSheetContentVOList = new ArrayList<>();
            BeanUtils.copyProperties(creditSheetPO, creditSheetVO);
            List<CreditSheetContentPO> creditSheetContentPOList = creditSheetDao.selectCreditSheetContentById(creditSheetPO.getId());
            for(CreditSheetContentPO creditSheetContentPO : creditSheetContentPOList){
                CreditSheetContentVO creditSheetContentVO = new CreditSheetContentVO();
                BeanUtils.copyProperties(creditSheetContentPO, creditSheetContentVO);
                creditSheetContentVOList.add(creditSheetContentVO);
            }
            creditSheetVO.setCreditSheetContent(creditSheetContentVOList);
            list.add(creditSheetVO);
        }
        return list;
    }

    @Override
    public CreditSheetVO selectCreditById(String id) {
        CreditSheetPO creditSheetPO = creditSheetDao.selectCreditSheetById(id);
        CreditSheetVO creditSheetVO = new CreditSheetVO();
        BeanUtils.copyProperties(creditSheetPO, creditSheetVO);
        List<CreditSheetContentPO> creditSheetContentPOList = creditSheetDao.selectCreditSheetContentById(creditSheetPO.getId());
        List<CreditSheetContentVO> list = new ArrayList<>();
        for(CreditSheetContentPO creditSheetContentPO : creditSheetContentPOList){
            CreditSheetContentVO creditSheetContentVO = new CreditSheetContentVO();
            BeanUtils.copyProperties(creditSheetContentPO, creditSheetContentVO);
            list.add(creditSheetContentVO);
        }
        creditSheetVO.setCreditSheetContent(list);
        return creditSheetVO;
    }
}

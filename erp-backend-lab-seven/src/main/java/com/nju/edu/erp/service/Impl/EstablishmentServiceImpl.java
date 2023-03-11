package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CategoryDao;
import com.nju.edu.erp.dao.EstablishmentDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.model.po.CategoryPO;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.*;
import com.nju.edu.erp.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentDao establishmentDao;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AccountService accountService;
    private final CategoryDao categoryDao;

    @Autowired
    public EstablishmentServiceImpl(EstablishmentDao establishmentDao, CategoryService categoryService, ProductService productService,
                                    CustomerService customerService, AccountService accountService, CategoryDao categoryDao) {
        this.establishmentDao = establishmentDao;
        this.categoryService = categoryService;
        this.productService = productService;
        this.customerService = customerService;
        this.accountService = accountService;
        this.categoryDao = categoryDao;
    }

    @Override
    public void initializeDataBase() {
        List<CategoryVO> categoryVOList = showCategory();
        for (CategoryVO categoryVO : categoryVOList) {
            categoryService.createCategory(categoryVO.getParentId(),categoryVO.getName());
        }
        List<ProductInfoVO> productInfoVOList = showProduct();
        for (ProductInfoVO productInfoVO : productInfoVOList) {
            CreateProductVO createProductVO = new CreateProductVO();
            BeanUtils.copyProperties(productInfoVO, createProductVO);
            productService.createProduct(createProductVO);
        }
        List<CustomerVO> customerVOList = showCustomer();
        for (CustomerVO customerVO : customerVOList) {
            CustomerPO customerPO = new CustomerPO();
            BeanUtils.copyProperties(customerVO, customerPO);
            customerService.addCustomer(customerPO);
        }
        List<AccountVO> accountVOList = showAccount();
        for (AccountVO accountVO : accountVOList) {
            accountService.createAccount(accountVO.getName(),accountVO.getCardNum());
        }
    }

    @Override
    public int addCategory(CategoryVO categoryVO) {
        CategoryPO categoryPO = new CategoryPO();
        BeanUtils.copyProperties(categoryVO, categoryPO);
        return establishmentDao.addCategory(categoryPO);
    }

    @Override
    public int addCategoryList(List<CategoryVO> categoryVOList) {
        int count = 0;
        for (CategoryVO categoryVO : categoryVOList) {
            if(addCategory(categoryVO)==1) count++;
        }
        return count;
    }

    @Override
    public int addProduct(ProductInfoVO productInfoVO) {
        // 查找商品对应的分类
        CategoryPO category = categoryDao.findByCategoryId(productInfoVO.getCategoryId());
        // 无法增加商品的情况
        if (category == null) {
            throw new MyServiceException("B0001", "当前分类不存在");
        }
        if (category.isLeaf() == false) {
            throw new MyServiceException("B0002", "当前分类无法增加商品");
        }
        // 生成商品ID
        String productId = generateProductId(category);
        ProductPO productPO = new ProductPO();
        BeanUtils.copyProperties(productInfoVO, productPO);
        productPO.setId(productId);
        if(productPO.getPurchasePrice()==null){
            productPO.setPurchasePrice(establishmentDao.averagePurchase(productPO.getId()));
        }
        if(productPO.getRetailPrice()==null){
            productPO.setRetailPrice(establishmentDao.averageRetail(productPO.getId()));
        }
        return establishmentDao.addProduct(productPO);
    }

    @Override
    public int addProductList(List<ProductInfoVO> productInfoVOList) {
        int count = 0;
        for (ProductInfoVO productInfoVO : productInfoVOList) {
            if(addProduct(productInfoVO)==1) count++;
        }
        return count;
    }

    @Override
    public int addCustomer(CustomerVO customerVO) {
        CustomerPO customerPO = new CustomerPO();
        BeanUtils.copyProperties(customerVO,customerPO);
        CustomerPO before = establishmentDao.selectCustomerByName(customerPO.getName());
        if(before==null){
            customerPO.setReceivable(new BigDecimal(0));
            customerPO.setPayable(new BigDecimal(0));
        }else {
            customerPO.setReceivable(before.getReceivable());
            customerPO.setPayable(before.getPayable());
        }
        return establishmentDao.addCustomer(customerPO);
    }

    @Override
    public int addCustomerList(List<CustomerVO> customerVOList) {
        int count = 0;
        for (CustomerVO customerVO : customerVOList) {
            if(addCustomer(customerVO)==1) count++;
        }
        return count;
    }

    @Override
    public int addAccount(AccountVO accountVO) {
        AccountPO accountPO = new AccountPO();
        BeanUtils.copyProperties(accountVO,accountPO);
        return establishmentDao.addAccount(accountPO);
    }

    @Override
    public int addAccountList(List<AccountVO> accountVOList) {
        int count = 0;
        for (AccountVO accountVO : accountVOList) {
            if(addAccount(accountVO)==1) count++;
        }
        return count;
    }

    @Override
    public List<CategoryVO> showCategory() {
        return establishmentDao.showCategory();
    }

    @Override
    public List<ProductInfoVO> showProduct() {
        return establishmentDao.showProduct();
    }

    @Override
    public List<CustomerVO> showCustomer() {
        return establishmentDao.showCustomer();
    }

    @Override
    public List<AccountVO> showAccount() {
        return establishmentDao.showAccount();
    }

    @Override
    public List<CategoryVO> selectCategory(CategoryVO categoryVO) {
        return establishmentDao.selectCategory(categoryVO);
    }

    @Override
    public List<ProductInfoVO> selectProduct(ProductInfoVO productInfoVO) {
        return establishmentDao.selectProduct(productInfoVO);
    }

    @Override
    public List<CustomerVO> selectCustomer(CustomerVO customerVO) {
        return establishmentDao.selectCustomer(customerVO);
    }

    @Override
    public List<AccountVO> selectAccount(AccountVO accountVO) {
        return establishmentDao.selectAccount(accountVO);
    }


    private String generateProductId(CategoryPO categoryPO) {
        StringBuffer ans = new StringBuffer();
        String categoryStr = categoryPO.getId().toString();
        String indexStr = categoryPO.getItemIndex().toString();
        if (indexStr.length() > 5) {
            throw new MyServiceException("B0006", "当前分类下商品编号已用完！");
        }
        for (int i = 0; i < 11 - categoryStr.length(); i++)
            ans.append('0');
        ans.append(categoryStr);
        for (int i = 0; i < 5 - indexStr.length(); i++)
            ans.append('0');
        ans.append(indexStr);
        return ans.toString();
    }
}

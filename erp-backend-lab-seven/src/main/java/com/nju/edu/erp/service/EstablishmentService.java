package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;

import java.util.List;

public interface EstablishmentService {

    /**
     * 根据期初建账的信息来初始化erp系统使用的数据库的category，product，customer，account表
     */
    void initializeDataBase();

    /**
     * 添加商品分类
     * @param categoryVO 商品分类VO
     */
    int addCategory(CategoryVO categoryVO);
    int addCategoryList(List<CategoryVO> categoryVOList);
    List<CategoryVO> showCategory();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param categoryVO 分类VO
     * @return 符合条件的结果
     */
    List<CategoryVO> selectCategory(CategoryVO categoryVO);

    /**
     * 添加商品信息 进价/售价默认值为上半年平均（service计算） 最近进价/最近售价为null
     * @param productInfoVO 商品信息VO
     */
    int addProduct(ProductInfoVO productInfoVO);
    int addProductList(List<ProductInfoVO> productInfoVOList);
    List<ProductInfoVO> showProduct();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name，categoryId的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param productInfoVO 商品信息VO
     * @return 符合条件的结果
     */
    List<ProductInfoVO> selectProduct(ProductInfoVO productInfoVO);

    /**
     * 添加客户信息 应收应付应该使用之前遗留下来的（service计算）
     * @param customerVO 客户VO
     */
    int addCustomer(CustomerVO customerVO);
    int addCustomerList(List<CustomerVO> customerVOList);
    List<CustomerVO> showCustomer();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name，level，type的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param customerVO 客户VO
     * @return 符合条件的结果
     */
    List<CustomerVO> selectCustomer(CustomerVO customerVO);

    /**
     * 添加账户 余额默认为0
     * @param accountVO 账户VO
     */
    int addAccount(AccountVO accountVO);
    int addAccountList(List<AccountVO> accountVOList);
    List<AccountVO> showAccount();

    /**
     * 查询符合条件的结果，筛选条件可以是name，cardNum的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param accountVO 账户VO
     * @return 符合条件的结果
     */
    List<AccountVO> selectAccount(AccountVO accountVO);

}

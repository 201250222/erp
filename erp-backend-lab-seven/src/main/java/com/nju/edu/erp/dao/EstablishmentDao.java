package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.AccountPO;
import com.nju.edu.erp.model.po.CategoryPO;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.AccountVO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface EstablishmentDao {
    /**
     * 添加商品分类
     * @param categoryPO 商品分类PO
     */
    int addCategory(CategoryPO categoryPO);
    List<CategoryVO> showCategory();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param categoryVO 分类VO
     * @return 符合条件的结果
     */
    List<CategoryVO> selectCategory(CategoryVO categoryVO);

    /**
     * 添加商品信息 进价/售价默认值为上半年平均（service计算） 最近进价/最近售价为null
     * @param productPO 商品信息PO
     */
    int addProduct(ProductPO productPO);
    List<ProductInfoVO> showProduct();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name，categoryId的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param productInfoVO 商品信息VO
     * @return 符合条件的结果
     */
    List<ProductInfoVO> selectProduct(ProductInfoVO productInfoVO);

    /**
     * 添加客户信息 应收应付应该使用之前遗留下来的（service计算）
     * @param customerPO 客户PO
     */
    int addCustomer(CustomerPO customerPO);
    List<CustomerVO> showCustomer();

    /**
     * 查询符合条件的结果，筛选条件可以是id，name，level，type的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param customerVO 客户VO
     * @return 符合条件的结果
     */
    List<CustomerVO> selectCustomer(CustomerVO customerVO);

    /**
     * 添加账户 余额默认为0
     * @param accountPO 账户PO
     */
    int addAccount(AccountPO accountPO);
    List<AccountVO> showAccount();

    /**
     * 查询符合条件的结果，筛选条件可以是name，cardNum的任意组合，不使用的条件置为null，如果有筛选条件的扩展只需要修改mapper
     * @param accountVO 账户VO
     * @return 符合条件的结果
     */
    List<AccountVO> selectAccount(AccountVO accountVO);

    /**
     * 获取进货单中商品购买平均价格
     * @param pid 商品id
     */
    BigDecimal averagePurchase(String pid);

    /**
     * 获取销售单中商品售卖平均价格
     * @param pid 商品id
     */
    BigDecimal averageRetail(String pid);

    /**
     * 通过名字查询客户，获取之前遗留的应收应付
     * @param name 客户名
     * @return 客户具体信息
     */
    CustomerPO selectCustomerByName(String name);
}

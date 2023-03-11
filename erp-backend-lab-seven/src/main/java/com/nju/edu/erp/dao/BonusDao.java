package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.BonusPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BonusDao {
    /**
     * 制定一条年终奖
     * @param bonusPO 所有的年终奖列表
     * @return 执行成功 return 1;
     */
    int makeBonus(BonusPO bonusPO);

    /**
     * 查看已经制定的年终奖
     * @return 所有的年终奖信息
     */
    List<BonusPO> showBonus();

    /**
     * 查看单独员工的年终奖
     * @param id 员工id
     * @return 该员工的年终奖
     */
    BonusPO selectBonusById(Integer id);

    /**
     * 修改一位员工的年终奖
     * @param bonusPO
     * @return
     */
    int updateOneBonus(BonusPO bonusPO);

    /**
     * 删除所有bonus
     */
    int deleteAll();
}

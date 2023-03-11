package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.BonusPO;
import com.nju.edu.erp.model.vo.BonusVO;

import java.util.List;

public interface BonusService {

    /**
     * 在制定Bonus前通过此方法来获取BonusVO
     * @return List<BonusVO>
     */
    List<BonusVO> showTodoBonus();

    /**
     * 制定年终奖
     * @param bonusVOS 所有的年终奖列表
     * @return 执行成功与否
     */
    boolean makeBonus(List<BonusVO> bonusVOS);

    /**
     * 指定一条
     * @param bonusVO
     * @return
     */
    int makeOneBonus(BonusVO bonusVO);

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
    BonusVO selectBonusById(Integer id);

    /**
     * 修改一位员工的年终奖
     * @param bonusVO 修改后的年终奖信息
     * @return 修改成功返回1
     */
    int updateOneBonus(BonusVO bonusVO);
}

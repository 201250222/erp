package com.nju.edu.erp.establishment;

import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private EstablishmentService establishmentService;

    @Test
    @Transactional
    @Rollback
    public void test_insert(){
        CategoryVO categoryVO = CategoryVO.builder()
                .id(1)
                .isLeaf(true)
                .itemCount(1)
                .itemIndex(1)
                .parentId(1)
                .name("test")
                .build();
        establishmentService.addCategory(categoryVO);
        List<CategoryVO> list = new ArrayList<>();
        CategoryVO categoryVO1 = CategoryVO.builder()
                .id(2)
                .isLeaf(true)
                .itemCount(2)
                .itemIndex(0)
                .parentId(2)
                .name("test1")
                .build();
        list.add(categoryVO1);
        establishmentService.addCategoryList(list);
    }

    @Test
    public void test_select(){
        List<CategoryVO> list = establishmentService.showCategory();
        for (CategoryVO categoryVO : list) {
            System.out.println(categoryVO);
        }
    }
}

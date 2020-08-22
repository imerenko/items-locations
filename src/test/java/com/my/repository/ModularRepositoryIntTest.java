package com.my.repository;

import com.my.Application;
import com.my.model.Modular;
import com.my.model.ModularSectionAssignment;
import com.my.model.ModularSectionItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ModularRepositoryIntTest {

    @Autowired
    private ModularRepository modularRepository;

    @Test
    public void getModularByPlanId() {
       Modular result = modularRepository.getModularByPlanId("US", 100,  11251049);
       System.out.println("result: " + result);
    }

    @Test
    public void getModularSectionItemsByGtin() {
        List<ModularSectionItem> result = modularRepository.getModularSectionItemsByGtin("US", 100,  "19416425154");
        System.out.println("result: " + result);
    }

    @Test
    public void getModularSectionAssignmentsBySectionName() {
        List<ModularSectionAssignment> result = modularRepository.getModularSectionAssignmentsBySectionName("US", 100,  "A1-1");
        System.out.println("result: " + result);
    }

}

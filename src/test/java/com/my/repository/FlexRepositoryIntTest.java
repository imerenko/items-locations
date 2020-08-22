package com.my.repository;

import com.my.Application;
import com.my.model.Flex;
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
public class FlexRepositoryIntTest {

    @Autowired
    private FlexRepository flexRepository;

    @Test
    public void getFlexesByGtin() {
       List<Flex> result = flexRepository.getFlexesByGtin("US", 100, "19416425155");
        System.out.println("result: " + result);
    }

    @Test
    public void getFlexesBySectionName() {
        List<Flex> result = flexRepository.getFlexesBySectionName("US", 100, "A1-3");
        System.out.println("result: " + result);
    }
}

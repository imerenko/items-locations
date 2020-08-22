package com.my.repository;

import com.my.Application;
import com.my.model.Section;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SectionRepositoryIntTest {

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    public void getSectionByName() {
      Section result =  sectionRepository.getSectionByName("US", 100,  "A1-1");
      System.out.println("result: " + result);
    }
}

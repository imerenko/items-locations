package com.my.repository;

import com.my.Application;
import com.my.model.Item;
import com.my.model.Section;
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
public class GraphShortCutRepositoryIntTest {

    @Autowired
    private GraphShortCutRepository graphShortCutRepository;

    @Test
    public void getSectionsByItem() {
        List<Section> result = graphShortCutRepository.getSectionsByItem("US", 100, "19416425155");
        System.out.println("result: " + result);
    }

    @Test
    public void getItemsBySection() {
        List<Item> result = graphShortCutRepository.getItemsBySection("US", 100, "A1-3");
        System.out.println("result: " + result);
    }
}

package com.dharma.news;

import com.dharma.news.data.entity.News;
import com.dharma.news.data.entity.QNews;
import com.dharma.news.data.repo.NewsRepo;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    NewsRepo newsRepo;

    @Test
    void contextLoads() {
        String code = "BHP.AX";
        System.out.println("--------------search " + code);
        List<News> list = Lists.newArrayList(newsRepo.findAll(QNews.news.code.eq(code)));
        Iterator<News> itr = list.stream().sorted((a, b) -> {
            return b.getDate().compareTo(a.getDate());
        }).iterator();

        itr.forEachRemaining(a -> {
            System.out.println("-------------------a-----------" + a.getDate());
        });


    }

}

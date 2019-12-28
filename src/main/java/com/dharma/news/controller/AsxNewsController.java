package com.dharma.news.controller;

import com.dharma.news.parser.AsxNewsParser;
import com.dharma.news.service.ElasticNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AsxNewsController {
    @Value("${selenium.pathfile}")
    private String seleniumpath;
    @Autowired
    AsxNewsParser parser;

    @Autowired
    ElasticNewsService elasticNewsService;

    public void run() {

    }

    /*
        import to what page
     */

    /*
        Schedule import by date , or can be manually trigger
     */
    //@Scheduled(cron = "0 16 15 ? * MON-FRI")


    @GetMapping("/importpage")
    public void all(@RequestParam int page) {
        System.out.println("Rimportpage --  : " + page);
        elasticNewsService.importnewsbypage(page);
    }

    @GetMapping("/import")
    public String imports() {
        System.out.println("Run news parser : ");
        elasticNewsService.importnewsbydate();
        return "running import now ";
    }

}

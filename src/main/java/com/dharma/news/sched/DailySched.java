package com.dharma.news.sched;

import com.dharma.news.service.ElasticNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailySched {
    @Autowired
    ElasticNewsService fundNewsService;
    @Autowired
    ElasticNewsService elasticNewsService;

    @Scheduled(cron = "0 16 15 ? * MON-FRI")
    public void run(){
      //  fundNewsService.importElasticNews(true);
  //      elasticNewsService.importnews(true);
        System.out.println("-------------------RUN SCHELD---------------------"  + elasticNewsService);
        elasticNewsService.importnewsbydate();
    }

}

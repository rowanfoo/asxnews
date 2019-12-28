package com.dharma.news.service;

import com.dharma.news.data.entity.News;
import com.dharma.news.data.repo.NewsRepo;
import com.dharma.news.parser.AsxNewsParser;
import com.dharma.news.util.Loadhref;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Log
public class ElasticNewsService {

    @Resource
    NewsRepo newsRepo;
    String urls = "";

    @Autowired
    HtmlPage page;
    @Autowired
    AsxNewsParser hotcopperParser;
    @Autowired
    Loadhref href;


    /**
     * import all news for today
     */
    public void importnewsbydate() {
        importnews(true, 0);

    }

    /**
     * import to what page no
     */
    public void importnewsbypage(int pageno) {
        importnews(false, pageno);

    }


    private void importnews(boolean stopdate, int end) {
        System.out.println("----- setLoopElasticNews--------");
        page.loadbasePage();
        int stop = 28;
        if (!stopdate) stop = end;
        //    System.out.println( "----- setLoopElasticNews-   stop -------"  + end );


        for (int x = 0; x < stop; x++) {//check if page not loaded , will retry 3 times.

            urls = "https://hotcopper.com.au/announcements/asx/page-" + x + "/";
            System.out.println("----- URLS   -------" + urls);

            int count = 0;
            String content = "";
            while (count < 3) {
                content = page.getPage(urls);
                if (!hotcopperParser.isEmptyPage(content)) {

                    break;
                } else {
                    System.out.println("----- URLS  TRY AGAIN  -------" + urls);
                    x++;
                }
            }
            count = 0;


            ArrayList<News> arr = hotcopperParser.parse(content);
            log.info("------------ URLS--" + urls);

            if (stopdate) {
                //               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                //System.out.println("----- setLoopElasticNews-  DATE-------" + arr.get(0).getDate());
                LocalDate localDate = LocalDate.parse(arr.get(0).getDate() + "");
                if (!LocalDate.now().isEqual(localDate)) return;
            }
            arr.forEach((a) -> {
                //   log.info("------------ URLS--" + a);
                newsRepo.save(a);

                //                System.out.println( "----- importnews  load data --------" + a.getCode() );
//                String text = href. loadData(a.getLink());
//
//                try {
//                    int number = new Random().nextInt(10);
//                    TimeUnit.SECONDS.sleep(number);
//                } catch (InterruptedException e) {e.printStackTrace();}
//
//                if(text != null){
//                    a.setNotes(text);
//                    newsRepo.save(a);
//                    log.info( "----- SAVE--------" +a.getCode()   +  "   index:  "+ a.getDate()  );
//                };

            });

        }


        System.out.println("---------------------END IMPORTING  -----");
    }


}

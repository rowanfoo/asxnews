package com.dharma.news.controller.web;

import com.dharma.news.data.entity.News;
import com.dharma.news.data.entity.QNews;
import com.dharma.news.data.repo.NewsRepo;
import com.dharma.news.service.ElasticNewsWebService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class NewsFinderController {
    @Autowired
    ElasticNewsWebService elasticNewsWebService;

    @Autowired
    NewsRepo newsRepo;

    @GetMapping("/hello")
    //@ApiOperation(value = "iindex page",  notes = "index html , search page")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }


    @GetMapping("/news/code/{code}/date/{date}")
    public Iterable<News> search(@PathVariable String code, @PathVariable String date) {
        System.out.println("--------------search " + code);
        return newsRepo.findAll(QNews.news.code.eq(code).and(QNews.news.date.eq(LocalDate.parse(date))));
    }

    @GetMapping("/news/code/{code}")
    public Iterator<News> code(@PathVariable String code) {
        System.out.println("--------------search " + code);
        List<News> list = Lists.newArrayList(newsRepo.findAll(QNews.news.code.eq(code)));
        Iterator<News> itr = list.stream().sorted((a, b) -> {
            return b.getDate().compareTo(a.getDate());
        }).iterator();

//        itr.forEachRemaining(a->{
//            System.out.println("-------------------a-----------"+a);
//        });

        return itr;

//        return newsRepo.findAll(QNews.news.code.eq(code) )  ;
    }


    @GetMapping("/news/codes/{code}")
    public Iterable<News> search(@PathVariable List<String> code) {
        System.out.println("--------------search++no date " + code);
        code.forEach(a -> System.out.println("----mycodes-----" + a));
//        return null;
        return newsRepo.findAll(QNews.news.code.in(code));
    }


//    @PostMapping("/search")
//    public String search(Model model, @RequestParam Map<String, String> params) {
//
//        String date = params.get("MyNoteDate");
//        String search = params.get("search");
//        String checksearch = params.get("checksearch");
//
//        if (date.isEmpty()) {
//            System.out.println("--------------EMPTY " + date);
//            date = LocalDate.now().toString();
//        }
//
//        if (!search.isEmpty()) {
//            checksearch = search;
//        }
////        params.forEach((a,b)->{
////                System.out.println("--------------param " + a +"  =  " + b);
////
////                }
////
////        );
//        System.out.println("--------------RUN " + checksearch + date);
//        List<News> data = elasticNewsWebService.search(checksearch, date);
//        model.addAttribute("data", data);
//        model.addAttribute("date", date);
//        System.out.println("-------------------------data  ---: " + data.size());
//
//
//        return "hello";
//    }


}

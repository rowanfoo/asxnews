package com.dharma.news.controller.web;

import com.dharma.news.data.entity.News;
import com.dharma.news.data.entity.QNews;
import com.dharma.news.data.repo.NewsRepo;
import com.dharma.news.service.ElasticNewsWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
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


    @GetMapping("/news/code/{code}")
    public Iterable<News> search(@PathVariable String code, @RequestParam String date) {
        System.out.println("--------------search " + code);
        return newsRepo.findAll(QNews.news.code.eq(code).and(QNews.news.date.eq(LocalDate.parse(date))));
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

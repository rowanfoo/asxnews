package com.dharma.news;

import com.dhamma.pesistence.entity.data.Portfolio;
import com.dhamma.pesistence.entity.repo.PortfolioRepo;
import com.dharma.news.data.entity.QNews;
import com.dharma.news.data.repo.NewsRepo;
import com.dharma.news.service.ElasticNewsService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableScheduling
@Log
public class DemoApplication implements CommandLineRunner {
    @Value("${selenium.pathfile}")
    private String seleniumpath;
    @Value("${holidays}")
    String holidays;
    @Autowired
    NewsRepo newsRepo;
    @Autowired
    ElasticNewsService elasticNewsService;
    @Autowired
    private ApplicationContext context;

    static String arg = null;

    @Bean
    public WebDriver webdriver() {
        File src = new File(seleniumpath);

        System.out.println("START  AsxNewsChromeBrowser: ");
        System.out.println("-------CHROMW AOURCW-" + src.getAbsolutePath());

        WebDriver driver;
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox", "--disable-gpu");
        ChromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        driver = new ChromeDriver(ChromeOptions);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); // just after you have initiated browser
        return driver;
    }


    @Bean
    public ArrayList<LocalDate> holidays() {
        ArrayList<LocalDate> arr = new ArrayList<>();
        System.out.println("---- HOLIDAYS  ------" + holidays);
        StringTokenizer st = new StringTokenizer(holidays, ",");
        System.out.println("---- Split by space ------");

        while (st.hasMoreTokens()) {
            // System.out.println(st.nextElement());
            arr.add((LocalDate.parse(st.nextToken())));
        }
        return arr;

    }



    public static void main(String[] args) {


        if (args.length > 1) {
            arg = args[0];
        }

        SpringApplication.run(DemoApplication.class, args);
//        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.NONE).run();


        System.out.println("--------------run2----------");


    }




    @Override
    public void run(String... args) throws Exception {

        if (arg != null) {
            log.info("--------------RUN IMPORT PAGE NO--------------" + arg);

            elasticNewsService.importnewsbypage(Integer.parseInt(arg));
        } else {
            log.info("--------------RUN IMPORT FILE NOW--------------" + LocalDateTime.now());
            elasticNewsService.importnewsbydate();
            log.info("--------------RUN IMPORT FILE NOW-----OK!!!!---------");

        }
        Unirest.get("http://192.168.0.10:10100/scheduler/rowan").asJson();
        System.exit(SpringApplication.exit(context, () -> 0));



    }

}


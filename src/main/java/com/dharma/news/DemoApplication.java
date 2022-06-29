package com.dharma.news;

import com.dharma.news.data.repo.NewsRepo;
import com.dharma.news.service.ElasticNewsService;
import com.mashape.unirest.http.Unirest;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableScheduling
@Log
public class DemoApplication implements CommandLineRunner {
    @Value("${selenium.pathfile}")
    private String seleniumpath;
    @Value("${holidays}")
    String holidays;

    @Bean
    public WebDriver webdriver() {
        File src = new File(seleniumpath);

        System.out.println("START  AsxNewsChromeBrowser: ");
        System.setProperty("webdriver.chrome.driver", src.getAbsolutePath());

        WebDriver driver;
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox", "--disable-gpu");


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

//    @Value("${selenium.weburl}")
//    String weburl;
//
//    @Bean
//    public WebDriver webdriver() {
////        DesiredCapabilities dcap = DesiredCapabilities.chrome();
////        String driverPath = System.getProperty("user.dir") + "/exe/chromedriver";
////        System.setProperty("webdriver.chrome.driver", driverPath);
//
//        // You should check the Port No here.
//
//        DesiredCapabilities dcap = DesiredCapabilities.chrome();
//
//        final ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("-no-sandbox");
//        chromeOptions.addArguments("-window-size=1024,768");
//        dcap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//
//
//        URL gamelan = null;
//        try {
//            System.out.println("------------------" + weburl);
//            gamelan = new URL(weburl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        WebDriver driver = new RemoteWebDriver(gamelan, dcap);
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
////        // Get URL
////        driver.get("https://www.google.com/");
////        // Print Title
////        System.out.println(driver.getTitle());
//
//        return driver;
//    }


    @Autowired
    NewsRepo newsRepo;

    static String arg = null;

    public static void main(String[] args) {


        if (args.length > 1) {
            arg = args[0];
        }

        //        SpringApplication.run(DemoApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DemoApplication.class)
                .web(WebApplicationType.NONE).run();


        System.out.println("--------------run2----------");

        //SpringApplication.exit(ctx, () -> 0);

    }

    //    @Autowired
//    WebDriver webdriver;
    @Autowired
    ElasticNewsService elasticNewsService;

    @Override
    public void run(String... args) throws Exception {

        elasticNewsService.importnewsbydate();
//        Unirest.get("http://192.168.0.10:10100/scheduler/rowan").asJson();


        //        http://localhost:9000/importpage?page=9
        //      elasticNewsService.importnewsbypage(9);
//running code.
//        if (arg != null) {
//            log.info("--------------RUN IMPORT PAGE NO--------------" + arg);
//
//            elasticNewsService.importnewsbypage(Integer.parseInt(arg));
//        } else {
//            log.info("--------------RUN IMPORT FILE NOW--------------" + LocalDateTime.now());
//            elasticNewsService.importnewsbydate();
//            log.info("--------------RUN IMPORT FILE NOW-----OK!!!!---------");
//
//        }


//        webdriver.get("https://hotcopper.com.au/");
//        System.out.println("----- URLS   -------" + webdriver.getPageSource());
//
//        webdriver.get("https://hotcopper.com.au/announcements/asx/page-1/");
//        System.out.println("----- URLS   -------" + webdriver.getPageSource());

//        webdriver.get("https://hotcopper.com.au/announcements/asx/page-0/");
//        System.out.println("----- URLS   -------" + webdriver.getPageSource());

//        List<News> news = newsRepo.findAll();
//        news.forEach(a -> {
//
//                    String mycode = a.getCode().substring(0, a.getCode().indexOf("."));
//                    System.out.println("===================" + mycode);
//                    a.setCode(mycode + ".AX");
//                    newsRepo.save(a);
//                }
//
//        );

    }

}


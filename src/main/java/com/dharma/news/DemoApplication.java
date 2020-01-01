package com.dharma.news;

import com.dharma.news.data.repo.NewsRepo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableScheduling

public class DemoApplication implements CommandLineRunner {
    @Value("${selenium.pathfile}")
    private String seleniumpath;
    @Value("${holidays}")
    String holidays;

//    @Bean
//    public WebDriver webdriver() {
//        File src = new File(seleniumpath);
//
//        System.out.println("START  AsxNewsChromeBrowser: ");
//        System.setProperty("webdriver.chrome.driver", src.getAbsolutePath());
//
//        WebDriver driver;
//        ChromeOptions ChromeOptions = new ChromeOptions();
//        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
//        driver = new ChromeDriver(ChromeOptions);
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); // just after you have initiated browser
//        return driver;
//    }

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

    @Value("${selenium.weburl}")
    String weburl;


    @Bean
    public WebDriver webdriver() {
        DesiredCapabilities dcap = DesiredCapabilities.chrome();
        String driverPath = System.getProperty("user.dir") + "/exe/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        // You should check the Port No here.
        URL gamelan = null;
        try {
            System.out.println("------------------" + weburl);
            gamelan = new URL(weburl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriver driver = new RemoteWebDriver(gamelan, dcap);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        // Get URL
//        driver.get("https://www.google.com/");
//        // Print Title
//        System.out.println(driver.getTitle());

        return driver;
    }


    @Autowired
    NewsRepo newsRepo;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

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
//    @Configuration
//    @EnableSwagger2
//    public class SwaggerConfig {
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.any())
//                    .paths(PathSelectors.any())
//                    .build();
//        }
//    }

}


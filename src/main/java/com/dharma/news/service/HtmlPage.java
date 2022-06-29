package com.dharma.news.service;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
/**
 * Setup up the webdriver and load page (url)
 */
public class HtmlPage {
    @Value("${url.base}")
    String baseurl;


    WebDriver webdriver;
    @Autowired
    private ApplicationContext applicationContext;

    // No idea why hotcopper ,, cant lead the annoucement-1 page
    // got to load a any page first.
    public void loadbasePage() {
        webdriver = applicationContext.getBean(WebDriver.class);
        webdriver.get(baseurl);
        webdriver.getPageSource();
    }


    public String getPage(String url) {
        webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        webdriver.get(url);
        //  System.out.println("------URL :" + url2);
        return webdriver.getPageSource();

    }

    public void close() {
        webdriver.close();
    }


}

package com.tp.activite4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// aactivité 4
public class RechercheGoogleTest {

    @Test
    public void testRechercheSelenium() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //driver.get("https://www.google.com");
        driver.get("https://www.bing.com");
        driver.manage().window().maximize();

        WebElement champRecherche = driver.findElement(By.name("q"));
        champRecherche.sendKeys("Selenium WebDriver");
        champRecherche.submit();

        Thread.sleep(3000);

        String titre = driver.getTitle();
        System.out.println("Titre après recherche : " + titre);

        // Assertion : le titre doit contenir "Selenium WebDriver"
        Assert.assertTrue("Le titre ne contient pas le mot clé recherché !",
                titre.contains("Selenium WebDriver"));

        driver.quit();
    }
}

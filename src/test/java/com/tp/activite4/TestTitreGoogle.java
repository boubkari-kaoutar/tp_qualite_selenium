package com.tp.activite4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// activité 4

public class TestTitreGoogle {

    @Test
    public void testTitrePageGoogle() {

        // 1. Chemin vers chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        // 2. Lancer Chrome
        WebDriver driver = new ChromeDriver();

        // 3. Ouvrir Google
        driver.get("https://www.google.com");

        // 4. Récupérer le titre
        String titre = driver.getTitle();
        System.out.println("Titre obtenu : " + titre);

        // 5. Assertion : vérifier que le titre contient "Google"
        Assert.assertTrue("Le titre ne contient pas Google !", titre.contains("Google"));

        // 6. Fermer le navigateur
        driver.quit();
    }
}

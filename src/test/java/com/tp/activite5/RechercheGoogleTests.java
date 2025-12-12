package com.tp.activite5;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// activité 5

public class RechercheGoogleTests {

    @Test
    public void testRechercheValide() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🔹 On travaille avec Bing
        driver.get("https://www.bing.com/");

        // Champ de recherche (même name="q" que Google)
        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );
        search.sendKeys("Selenium WebDriver");
        search.submit();

        // Vérifier que le titre contient "selenium"
        String title = driver.getTitle();
        System.out.println("Titre après recherche : " + title);
        Assert.assertTrue(title.toLowerCase().contains("selenium"));

        // Vérifier qu'il y a au moins un résultat
        List<WebElement> results = driver.findElements(By.cssSelector("h2, h3"));
        Assert.assertFalse("Aucun résultat trouvé !", results.isEmpty());

        driver.quit();
    }

    @Test
    public void testRechercheImages() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // 1) Ouvrir Bing
        driver.get("https://www.bing.com/");

        // 2) Taper la recherche
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Selenium WebDriver");
        search.submit();

        // 3) Cliquer sur l'onglet "Images"
        WebElement imagesTab = driver.findElement(By.partialLinkText("IMAGES"));
        imagesTab.click();

        // 4) Attendre un peu que les images se chargent
        Thread.sleep(3000);

        // 5) Récupérer toutes les balises <img> de la page
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Nombre d'images trouvées : " + images.size());

        // 6) Vérifier qu'il y en a au moins une
        Assert.assertTrue("Aucune image affichée !", images.size() > 0);

        // 7) Fermer le navigateur
        driver.quit();
    }

}

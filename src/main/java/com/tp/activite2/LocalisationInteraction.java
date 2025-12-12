package com.tp.activite2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocalisationInteraction {

    public static void main(String[] args) {

        // 1. Chemin vers chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        // 2. Lancer le navigateur Chrome
        WebDriver driver = new ChromeDriver();

        // ===== RECHERCHE 1 : Utilisation de By.name() =====
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

        WebElement champRecherche1 = driver.findElement(By.name("q"));
        champRecherche1.sendKeys("Java Programming");
        champRecherche1.submit();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String titre1 = driver.getTitle();
        System.out.println("Titre après recherche 1 : " + titre1);

        // ===== RECHERCHE 2 : Utilisation de By.xpath() =====
        driver.get("https://www.google.com");

        WebElement champRecherche2 = driver.findElement(By.xpath("//textarea[@name='q']"));
        champRecherche2.sendKeys("Automatisation des tests");
        champRecherche2.submit();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String titre2 = driver.getTitle();
        System.out.println("Titre après recherche 2 : " + titre2);

        // ===== RECHERCHE 3 : Utilisation de By.className() =====
        driver.get("https://www.google.com");

        try {
            Thread.sleep(1000);
            WebElement champRecherche3 = driver.findElement(By.className("gLFyf"));
            champRecherche3.sendKeys("Machine Learning");
            champRecherche3.submit();

            Thread.sleep(3000);

            String titre3 = driver.getTitle();
            System.out.println("Titre après recherche 3 : " + titre3);
        } catch (Exception e) {
            System.out.println("Erreur avec By.className : " + e.getMessage());
        }

        // 10. Fermer le navigateur
        driver.quit();
    }
}
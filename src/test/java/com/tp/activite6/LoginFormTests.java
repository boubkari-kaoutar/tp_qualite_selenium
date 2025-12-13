package com.tp.activite6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginFormTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private String urlLogin;

    @Before
    public void setUp() {
        // Configuration du driver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        // Créer une instance de Chrome
        driver = new ChromeDriver();

        // Initialiser WebDriverWait avec un timeout de 10 secondes
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // URL de la page de login
        urlLogin = "file:///C:\\Users\\hp\\Desktop\\tp1_a\\tp_selenium_qualite\\src\\main\\java\\com\\tp\\activite3\\login\\login.html";

        // Ouvrir la page
        driver.get(urlLogin);
        driver.manage().window().maximize();

        // CRITIQUE: Attendre que la page soit complètement chargée
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
            System.out.println("✓ Page chargée avec succès");
        } catch (Exception e) {
            System.err.println("✗ Échec du chargement de la page: " + e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown() {
        // Fermer le navigateur après chaque test
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * TC-01 : Connexion avec identifiants valides
     * Résultat attendu : redirection vers success.html
     */
    @Test
    public void test_TC01_ConnexionValide() throws InterruptedException {
        System.out.println("=== TEST TC-01 : Connexion avec identifiants valides ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Saisir les identifiants valides
        usernameField.clear();
        usernameField.sendKeys("admin");

        passwordField.clear();
        passwordField.sendKeys("admin123");

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre la redirection
        wait.until(ExpectedConditions.urlContains("success.html"));

        // Vérifier la redirection vers success.html
        String urlActuelle = driver.getCurrentUrl();
        System.out.println("URL actuelle : " + urlActuelle);

        Assert.assertTrue(
                "La redirection vers success.html n'a pas eu lieu",
                urlActuelle.contains("success.html")
        );

        // Vérifier le message de succès
        WebElement titreSucces = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        String texteSucces = titreSucces.getText();
        System.out.println("Message de succès : " + texteSucces);

        Assert.assertTrue(
                "Le message de succès n'est pas affiché correctement",
                texteSucces.toLowerCase().contains("connexion") &&
                        texteSucces.toLowerCase().contains("réussie")
        );

        // Vérifier la présence du bouton de déconnexion
        WebElement logoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logoutBtn")));
        Assert.assertTrue(
                "Le bouton de déconnexion n'est pas visible",
                logoutBtn.isDisplayed()
        );

        System.out.println("✓ TEST TC-01 RÉUSSI\n");
    }

    /**
     * TC-02 : Connexion avec mot de passe incorrect
     * Résultat attendu : message d'erreur "Identifiants invalides"
     */
    @Test
    public void test_TC02_MotDePasseIncorrect() throws InterruptedException {
        System.out.println("=== TEST TC-02 : Mot de passe incorrect ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Saisir un username valide mais mot de passe incorrect
        usernameField.clear();
        usernameField.sendKeys("admin");

        passwordField.clear();
        passwordField.sendKeys("motdepasse_incorrect");

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre l'affichage du message d'erreur
        WebElement messageErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String texteErreur = messageErreur.getText();
        System.out.println("Message d'erreur : " + texteErreur);

        Assert.assertTrue(
                "Le message d'erreur ne s'affiche pas",
                messageErreur.isDisplayed()
        );

        Assert.assertTrue(
                "Le message d'erreur ne contient pas 'Erreur' ou 'invalides'",
                texteErreur.toLowerCase().contains("erreur") ||
                        texteErreur.toLowerCase().contains("invalides")
        );

        System.out.println("✓ TEST TC-02 RÉUSSI\n");
    }

    /**
     * TC-03 : Connexion avec username incorrect
     * Résultat attendu : message d'erreur "Identifiants invalides"
     */
    @Test
    public void test_TC03_UsernameIncorrect() throws InterruptedException {
        System.out.println("=== TEST TC-03 : Username incorrect ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Saisir un username incorrect et mot de passe valide
        usernameField.clear();
        usernameField.sendKeys("utilisateur_incorrect");

        passwordField.clear();
        passwordField.sendKeys("admin123");

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre l'affichage du message d'erreur
        WebElement messageErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String texteErreur = messageErreur.getText();
        System.out.println("Message d'erreur : " + texteErreur);

        Assert.assertTrue(
                "Le message d'erreur ne s'affiche pas",
                messageErreur.isDisplayed()
        );

        Assert.assertTrue(
                "Le message d'erreur ne contient pas 'Erreur' ou 'invalides'",
                texteErreur.toLowerCase().contains("erreur") ||
                        texteErreur.toLowerCase().contains("invalides")
        );

        System.out.println("✓ TEST TC-03 RÉUSSI\n");
    }

    /**
     * TC-04 : Connexion avec les deux champs incorrects
     * Résultat attendu : message d'erreur "Identifiants invalides"
     */
    @Test
    public void test_TC04_IdentifiantsIncorrects() throws InterruptedException {
        System.out.println("=== TEST TC-04 : Les deux identifiants incorrects ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Saisir des identifiants complètement incorrects
        usernameField.clear();
        usernameField.sendKeys("utilisateur_faux");

        passwordField.clear();
        passwordField.sendKeys("motdepasse_faux");

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre l'affichage du message d'erreur
        WebElement messageErreur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String texteErreur = messageErreur.getText();
        System.out.println("Message d'erreur : " + texteErreur);

        Assert.assertTrue(
                "Le message d'erreur ne s'affiche pas",
                messageErreur.isDisplayed()
        );

        Assert.assertTrue(
                "Le message d'erreur ne contient pas 'Erreur' ou 'invalides'",
                texteErreur.toLowerCase().contains("erreur") ||
                        texteErreur.toLowerCase().contains("invalides")
        );

        System.out.println("✓ TEST TC-04 RÉUSSI\n");
    }

    /**
     * TC-05 : Username vide
     * Résultat attendu : validation HTML5 ou message d'erreur
     */
    @Test
    public void test_TC05_UsernameVide() throws InterruptedException {
        System.out.println("=== TEST TC-05 : Username vide ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Laisser username vide, saisir un mot de passe
        usernameField.clear();

        passwordField.clear();
        passwordField.sendKeys("admin123");

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre
        Thread.sleep(1000);

        // Vérifier que la validation HTML5 empêche la soumission
        // (l'URL ne doit pas changer)
        String urlActuelle = driver.getCurrentUrl();

        Assert.assertFalse(
                "Le formulaire a été soumis malgré un champ vide",
                urlActuelle.contains("success.html")
        );

        System.out.println("✓ TEST TC-05 RÉUSSI : Le formulaire n'a pas été soumis\n");
    }

    /**
     * TC-06 : Mot de passe vide
     * Résultat attendu : validation HTML5 ou message d'erreur
     */
    @Test
    public void test_TC06_MotDePasseVide() throws InterruptedException {
        System.out.println("=== TEST TC-06 : Mot de passe vide ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Saisir username, laisser mot de passe vide
        usernameField.clear();
        usernameField.sendKeys("admin");

        passwordField.clear();

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre
        Thread.sleep(1000);

        // Vérifier que la validation HTML5 empêche la soumission
        String urlActuelle = driver.getCurrentUrl();

        Assert.assertFalse(
                "Le formulaire a été soumis malgré un champ vide",
                urlActuelle.contains("success.html")
        );

        System.out.println("✓ TEST TC-06 RÉUSSI : Le formulaire n'a pas été soumis\n");
    }

    /**
     * TC-07 : Les deux champs vides
     * Résultat attendu : validation HTML5 empêche la soumission
     */
    @Test
    public void test_TC07_ChampsVides() throws InterruptedException {
        System.out.println("=== TEST TC-07 : Les deux champs vides ===");

        // Localiser les éléments avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Laisser les deux champs vides
        usernameField.clear();
        passwordField.clear();

        // Cliquer sur le bouton de connexion
        loginBtn.click();

        // Attendre
        Thread.sleep(1000);

        // Vérifier que la validation HTML5 empêche la soumission
        String urlActuelle = driver.getCurrentUrl();

        Assert.assertFalse(
                "Le formulaire a été soumis malgré les champs vides",
                urlActuelle.contains("success.html")
        );

        System.out.println("✓ TEST TC-07 RÉUSSI : Le formulaire n'a pas été soumis\n");
    }

    /**
     * TC-08 : Vérifier que le bouton de connexion est cliquable
     * Résultat attendu : le bouton est visible et cliquable
     */
    @Test
    public void test_TC08_BoutonConnexionCliquable() {
        System.out.println("=== TEST TC-08 : Bouton de connexion cliquable ===");

        // Localiser le bouton avec attente explicite
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));

        // Vérifier que le bouton est affiché
        Assert.assertTrue(
                "Le bouton de connexion n'est pas visible",
                loginBtn.isDisplayed()
        );

        // Vérifier que le bouton est activé
        Assert.assertTrue(
                "Le bouton de connexion n'est pas activé",
                loginBtn.isEnabled()
        );

        System.out.println("✓ TEST TC-08 RÉUSSI : Le bouton est visible et cliquable\n");
    }

    /**
     * TC-09 : Vérifier la présence des champs du formulaire
     * Résultat attendu : tous les champs sont présents
     */
    @Test
    public void test_TC09_PresenceChamps() {
        System.out.println("=== TEST TC-09 : Présence des champs du formulaire ===");

        // Vérifier la présence du champ username avec attente explicite
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        Assert.assertTrue(
                "Le champ username n'est pas visible",
                usernameField.isDisplayed()
        );

        // Vérifier la présence du champ password
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        Assert.assertTrue(
                "Le champ password n'est pas visible",
                passwordField.isDisplayed()
        );

        // Vérifier la présence du bouton
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginBtn")));
        Assert.assertTrue(
                "Le bouton de connexion n'est pas visible",
                loginBtn.isDisplayed()
        );

        System.out.println("✓ TEST TC-09 RÉUSSI : Tous les champs sont présents\n");
    }

    /**
     * TC-10 : Vérifier le titre de la page de connexion
     * Résultat attendu : le titre contient "Connexion"
     */
    @Test
    public void test_TC10_TitrePage() {
        System.out.println("=== TEST TC-10 : Titre de la page ===");

        String titre = driver.getTitle();
        System.out.println("Titre de la page : " + titre);

        Assert.assertTrue(
                "Le titre ne contient pas 'Connexion'",
                titre.toLowerCase().contains("connexion") ||
                        titre.toLowerCase().contains("login")
        );

        System.out.println("✓ TEST TC-10 RÉUSSI\n");
    }
}
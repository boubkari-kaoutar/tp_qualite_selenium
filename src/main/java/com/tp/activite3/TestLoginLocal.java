package com.tp.activite3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLoginLocal {

    public static void main(String[] args) {

        // Configuration du driver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // URL de la page HTML locale (à modifier selon votre emplacement)
        // Exemple : "file:///C:/Users/VotreNom/Desktop/login.html"
        String urlLogin = "file:///C:\\Users\\hp\\Desktop\\tp1_a\\hibernate1\\TP 23  Migration de Eureka vers Consul\\ms_rest_template\\tp_selenium_qualite\\src\\main\\java\\com\\tp\\activite3\\login\\login.html";

        try {
            driver.manage().window().maximize();

            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("        TEST DE FORMULAIRE DE CONNEXION LOCAL");
            System.out.println("═══════════════════════════════════════════════════════\n");

            // ========== TEST 1 : Identifiants INCORRECTS ==========
            System.out.println("TEST 1 : Tentative de connexion avec identifiants INCORRECTS");
            System.out.println("─────────────────────────────────────────────────────");

            driver.get(urlLogin);
            attendre(2000);

            // Saisir des identifiants incorrects
            WebElement usernameField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement loginBtn = driver.findElement(By.id("loginBtn"));

            usernameField.clear();
            usernameField.sendKeys("utilisateur_incorrect");

            passwordField.clear();
            passwordField.sendKeys("motdepasse_incorrect");

            System.out.println("✓ Username saisi : utilisateur_incorrect");
            System.out.println("✓ Password saisi : motdepasse_incorrect");

            // Cliquer sur le bouton de connexion
            loginBtn.click();
            System.out.println("✓ Clic sur le bouton de connexion");

            attendre(2000);

            // Vérifier la présence d'un message d'erreur
            try {
                WebElement messageErreur = driver.findElement(By.id("message"));
                String texteErreur = messageErreur.getText();

                if (messageErreur.isDisplayed() &&
                        (texteErreur.contains("Erreur") || texteErreur.contains("Identifiants invalides"))) {
                    System.out.println("✓ Message d'erreur détecté : " + texteErreur);
                    System.out.println("✓ TEST 1 RÉUSSI : Message d'erreur affiché comme attendu\n");
                } else {
                    System.out.println("✗ Message d'erreur inattendu : " + texteErreur + "\n");
                }
            } catch (Exception e) {
                System.out.println("✗ Aucun message d'erreur trouvé");
                System.out.println("✗ TEST 1 ÉCHOUÉ\n");
            }

            // ========== TEST 2 : Identifiants CORRECTS ==========
            System.out.println("TEST 2 : Tentative de connexion avec identifiants CORRECTS");
            System.out.println("─────────────────────────────────────────────────────");

            driver.get(urlLogin);
            attendre(2000);

            // Saisir des identifiants corrects
            usernameField = driver.findElement(By.name("username"));
            passwordField = driver.findElement(By.name("password"));
            loginBtn = driver.findElement(By.id("loginBtn"));

            usernameField.clear();
            usernameField.sendKeys("admin");

            passwordField.clear();
            passwordField.sendKeys("admin123");

            System.out.println("✓ Username saisi : admin");
            System.out.println("✓ Password saisi : admin123");

            // Cliquer sur le bouton de connexion
            loginBtn.click();
            System.out.println("✓ Clic sur le bouton de connexion");

            attendre(3000);

            // Vérifier le succès de la connexion
            try {
                // Vérifier si l'URL a changé (redirection vers success.html)
                String urlActuelle = driver.getCurrentUrl();

                if (urlActuelle.contains("success.html")) {
                    System.out.println("✓ Redirection réussie vers : " + urlActuelle);

                    // Vérifier le message de succès
                    WebElement titreSucces = driver.findElement(By.tagName("h1"));
                    String texteSucces = titreSucces.getText();
                    System.out.println("✓ Message de succès : " + texteSucces);

                    // Vérifier le bouton de déconnexion
                    WebElement logoutBtn = driver.findElement(By.id("logoutBtn"));
                    if (logoutBtn.isDisplayed()) {
                        System.out.println("✓ Bouton 'Se déconnecter' visible");
                    }

                    System.out.println("✓ TEST 2 RÉUSSI : Connexion réussie\n");
                } else {
                    System.out.println("✗ Pas de redirection détectée");
                    System.out.println("URL actuelle : " + urlActuelle);
                    System.out.println("✗ TEST 2 ÉCHOUÉ\n");
                }

            } catch (Exception e) {
                System.out.println("✗ Erreur lors de la vérification du succès : " + e.getMessage());
                System.out.println("✗ TEST 2 ÉCHOUÉ\n");
            }

            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("        FIN DES TESTS");
            System.out.println("═══════════════════════════════════════════════════════");

        } catch (Exception e) {
            System.err.println("Erreur durant l'exécution : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermer le navigateur
            attendre(2000);
            driver.quit();
            System.out.println("\nNavigateur fermé.");
        }
    }

    /**
     * Méthode utilitaire pour attendre (pause)
     */
    private static void attendre(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
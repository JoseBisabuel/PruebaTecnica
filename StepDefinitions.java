package StepDefinitions;

import Funtions.CreateDriver;
import Funtions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class StepDefinitions {

    SeleniumFunctions functions = new SeleniumFunctions();

    WebDriver driver;

    public static boolean actual = Boolean.parseBoolean(null);

    /**** Atributo Login ******/
    Logger log = Logger.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        driver = Hooks.driver;
    }

    @Given("^Cargar Pagina Web Floristeria Munco Flor")
    public void iAmInAppMainSite() throws IOException {
        String url = functions.readProperties("MainAppUrlBase");
        log.info("Navegacion a: " + url);
        driver.get(url);
        functions.page_has_loaded();
    }

    @Then("^Cargar la informacion del DOM (.*)$")
    public void cargarLaInformacionDelDOMAzloginJson(String json) throws Exception {
        SeleniumFunctions.FileName = json;
        SeleniumFunctions.readJson();
        log.info("Inicialize archivo: " + json);
    }


    @And("^busqueme (.*) en el select (.*)")
    public void iSetTextInDropdown(String option, String element) throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByVisibleText(option);
    }


    @And("^Hacer Click en El elemento (.*)")
    public void iDoClickInElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        functions.waitForElementPresent(element);
        driver.findElement(SeleniumElement).click();
        log.info("hizo click en : " + element);
    }



    @And("^Tomar Captura de pantalla: (.*)")
    public void iTakeScreenshot(String TestCaptura) throws IOException {
        functions.ScreenSchot(TestCaptura);
    }



    /**
     * Buscador de Select por el texto
     **/
    @And("^Seleccione el Texto (.*) del Selector (.*)")
    public void iSetTextColombiaInDropdownCountry(String option, String element) throws Exception {
        Select opt = (Select) functions.selectOption(element);
        opt.selectByVisibleText(option);
    }



    //*Tiempo de espera*//

    /**
     * colocar tiempo de espera
     **/
    @And("^espereme (.*) segundos")
    public void iWaitSeconds(int seconds) throws InterruptedException {
        int secs = seconds * 1000;
        Thread.sleep(secs);
    }


    @Then("^Validar si el elemento (.*) se visualiza$")
    public void checkIfELementIsPresent(String element) throws Exception {
        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertTrue("Elemento no presente: " + element, isDisplayed);
    }

}

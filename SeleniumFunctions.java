package Funtions;

import StepDefinitions.Hooks;
import com.sun.source.util.SourcePositions;
import io.qameta.allure.Allure;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FilenameUtils.removeExtension;

public class SeleniumFunctions {
    WebDriver driver;
    public SeleniumFunctions(){driver = Hooks.driver;}

    private static Logger log = Logger.getLogger(SeleniumFunctions.class);
    public static Properties prop = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    /** Scenario Test Data **/
    public static Map<String, String> ScenaryData = new HashMap<>();
    public static Map<String, String> HandleMyWindows = new HashMap<>();
    public static String Environment = "";

    public static String PagesFilesPath = "src/test/resources/Pages/";
    public static String FileName = "";
    public static String GetFieldBy = "";
    public static String ValueToFind = "";
    public static int EXPLICIT_TIMEOUT = 60;
    public static String ElementText = "";
    public static String PathStore = "";
    public static boolean isDisplayed = Boolean.parseBoolean(null);

    public static Object readJson() throws Exception{
        FileReader reader = new FileReader(PagesFilesPath + FileName);
        try{
            if(reader != null){
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            }else{
                return null;
            }
        }catch (FileNotFoundException | NullPointerException e){
            log.error("ReadEntity: No existe el Archivo " + FileName);
            throw new IllegalStateException("ReadEntity: No existe el Archivo " +FileName, e);
        }
    }

    public static JSONObject ReadEntity(String element) throws Exception{
        JSONObject Entity = null;
        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;
    }

    public static By getCompleteElement(String element) throws Exception{
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if("className".equalsIgnoreCase(GetFieldBy)){
            result = By.className(ValueToFind);
        }else if("cssSelector".equalsIgnoreCase(GetFieldBy)){
            result = By.cssSelector(ValueToFind);
        }else if("id".equalsIgnoreCase(GetFieldBy)){
            result = By.id(ValueToFind);
        }else if("linkText".equalsIgnoreCase(GetFieldBy)){
            result = By.linkText(ValueToFind);
        }else if("name".equalsIgnoreCase(GetFieldBy)){
            result = By.name(ValueToFind);
        }else if("link".equalsIgnoreCase(GetFieldBy)){
            result = By.partialLinkText(ValueToFind);
        }else if("tagName".equalsIgnoreCase(GetFieldBy)){
            result = By.tagName(ValueToFind);
        }else if("XPath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        }
        return result;
    }

    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }


    public ISelect selectOption(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("esperedo Elemento: " + element));
        Select opt = new Select(driver.findElement(SeleniumElement));
        return opt;
    }


    //*SELECT*//

    public void waitForElementPresent(String element)throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT );
        log.info("esperando el elemento: " + element + " a ser presentado");
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
    }


    public void page_has_loaded(){
        String GetActual = driver.getCurrentUrl();
        System.out.println(String.format("Cheking si la pagina esta cargada. ", GetActual));
        log.info(String.format("Cheking si la pagina esta cargada. ", GetActual));
        new WebDriverWait(driver, EXPLICIT_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete")
        );
    }


    public void ScreenSchot(String TestCaptura) throws IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
        String screenShotName = readProperties("ScreenShotPath") + "\\" + readProperties("browser") + "\\" + TestCaptura + "_(" + dateFormat.format(GregorianCalendar.getInstance().getTime()) + ")";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("Screenshot saved as: " + screenShotName);
        log.info("Screenshot saved as: " + screenShotName);
        FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));
    }


    public String GetTextElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement));
        log.info(String.format("esperando el elemento: %s", element));

        ElementText = driver.findElement(SeleniumElement).getText();

        return ElementText;
    }

    public boolean isElementDisplayed(String element) throws Exception{
        try{
            By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
            log.info(String.format("Esperar elemento: ", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(SeleniumElement)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed = false;
            log.info(e);
        }
        log.info(String.format(" el elemento visible es: ", element, isDisplayed));
        System.out.println(String.format(" el elemento visible es: %s", element, isDisplayed));
        return isDisplayed;
    }


}


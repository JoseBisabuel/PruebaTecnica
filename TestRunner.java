package StepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/resources/Features"
        //"classpath:Features/CategoriaAmor/",
        },
        glue = "classpath:StepDefinitions"
        //tags = {"@Tags"}
)
public class TestRunner {

    @BeforeClass
    public static void tearBefore(){
        try {
            System.out.println("Limpiando reportes anteriores...");
            String[] cmdA = {"cmd.exe", "/c", "mvn -X clean"};
            Runtime.getRuntime().exec(cmdA);
            System.out.println("Reportes limpiados Exitosamente");
        }catch (Exception ex){
            System.out.println("Reportes anteriores no eliminados ");
            ex.printStackTrace();
        }
    }

    @AfterClass
    public static void teardown(){
        try {
            System.out.println("Genera reporte");
            String[] cmd = {"cmd.exe", "/c", "mvn allure:serve"};
            Runtime.getRuntime().exec(cmd);
            System.out.println("Reporte Generado Exitosamente");
        }catch (Exception ex){
            System.out.println("Reporte NO Generado");
            ex.printStackTrace();
        }
    }
}

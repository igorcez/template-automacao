package LifeCycle;

import Utils.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.FileInputStream;
import java.util.Properties;

public class LifeCycle implements BeforeAllCallback, BeforeEachCallback {
    private static String servico;
    private static ExtentReports extentReport;
    private static ExtentSparkReporter spark;
    private static ExtentTest extentTest;
    private static String nomeTeste;
    private static String requestBody;

    //Carrega servico a ser testado a partir do properties da aplicacao
    //Seta variaveis de configuracao do RestAssured
    //Inicializa classes de relatorios
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Properties properties = new Properties();

        properties
                .load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/application.properties"));
        LifeCycle.servico = properties.getProperty("test.servico");

        Utils utils = new Utils();

        RestAssured.baseURI = utils.getValorDePropriedade("test.base_host");
        RestAssured.basePath = utils.getValorDePropriedade("test.base_path");

        LifeCycle.spark = new ExtentSparkReporter(properties.getProperty("test.pathReports") + extensionContext.getDisplayName()+ ".html");
        LifeCycle.extentReport = new ExtentReports();
        LifeCycle.extentReport.attachReporter(spark);
    }


    //Atualiza nome do teste para o displayName do cenario que esta em execucao
    //Cria extentTest nos realtorios incializados anteriormente
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        nomeTeste = extensionContext.getDisplayName();

        extentTest = LifeCycle.getExtentReport()
                .createTest(nomeTeste);
    }


    public static String getServico() {
        return servico;
    }

    public static ExtentReports getExtentReport() {
        return extentReport;
    }

    public static ExtentSparkReporter getSpark() {
        return spark;
    }

    public static String getNomeTeste() {
        return nomeTeste;
    }

    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    public static String getRequestBody() {
        return requestBody;
    }

    public static void setRequestBody(String requestBody) {
        LifeCycle.requestBody = requestBody;
    }
}

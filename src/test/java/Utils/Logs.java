package Utils;

import com.aventstack.extentreports.Status;
import org.junit.Assert;
import LifeCycle.LifeCycle;

public interface Logs {

    //adociona um log de aceite ao relatorio
    default void logPass(String log) {
        LifeCycle.getExtentTest()
                .log(Status.PASS, log);
        LifeCycle.getExtentReport().flush();
    }

    //adociona um log de falha ao relatorio, mas sem parar a execucao do teste
    default void logFail(String log) {
        LifeCycle.getExtentTest()
                .log(Status.FAIL, log);
        LifeCycle.getExtentReport().flush();
    }

    //adociona um log de falha ao relatorio, interrompendo a execucao do teste
    default void logAssertFail(String log) {
        LifeCycle.getExtentTest()
                .log(Status.FAIL, log);
        LifeCycle.getExtentReport().flush();
        Assert.fail(log);
    }

    //adociona um log de falha ao relatorio, interrompendo a execucao do teste quando occore uma excecao
    default void logAssertFail(String log, Throwable t) {
        String mensagem = log + " >>>> " + t.getMessage();
        LifeCycle.getExtentTest()
                .log(Status.FAIL, mensagem);
        t.printStackTrace();
        LifeCycle.getExtentReport().flush();
        Assert.fail(mensagem);
    }

    //adociona um log de informacao ao relatorio
    default void logInfo(String log) {
        LifeCycle.getExtentTest()
                .log(Status.INFO, log);
        LifeCycle.getExtentReport().flush();
    }

    //formata json para relatorio html
    default void logJson(String head, String jsonHead, String foot, String jsonFoot) {
        LifeCycle.getExtentTest()
                .log(Status.INFO,
                        "<b>" + head + "</b><br>" +
                                "<pre><code>" +
                                jsonHead +
                                "</pre></code>" +

                                "<b>" + foot + "</b></br>" +
                                "<pre><code>" + jsonFoot + "</pre></code>"
                );
        LifeCycle.getExtentReport().flush();

    }

    //formata json para relatorio html
    default void logJson(String head, String jsonHead) {
        LifeCycle.getExtentTest()
                .log(Status.INFO,
                        "<b>" + head + "</b><br>" +
                                "<pre><code>" +
                                jsonHead +
                                "</pre></code>"
                );
        LifeCycle.getExtentReport().flush();

    }

}

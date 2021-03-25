package Tests;

import Enum.TodosConstants;
import LifeCycle.LifeCycle;
import Pojo.Todos;
import Requests.Requests;
import Utils.TodosUtils;
import Utils.Utils;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith({LifeCycle.class})
public class TodosTest {
    private final Utils utils = new Utils();

    @DisplayName("Todos - checa resposta")
    @Test
    public void checarStatusTodos() {

        Response response = new Requests().GET("1");

        utils.logInfo("<h4>Verificando status</h4>");

        utils.logJson("Response GET/todos", response.getBody().prettyPrint());

        if (utils.checkStatus(200, response)) {
            utils.logPass("Status retornou [ " + response.getStatusCode() + " ] conforme o esperado [200]");
        } else {
            utils.logInfo(response.getBody().prettyPrint());
            utils.logAssertFail("Response GET/todos retornou status NAO esperado!" +
                    "Esperava [ 200 ] e retornou [ " + response.getStatusCode() + " ] <br>");
        }
    }

    @DisplayName("Todos - checa campos")
    @Test
    public void checarCamposTodos() {
        AtomicBoolean isAllOk = new AtomicBoolean(true);

        Response response = new Requests().GET(String.valueOf(TodosConstants.TodosConstants.getId()));

        utils.logInfo("<h4>Verificando campos</h4>");

        utils.logJson("Response GET/todos", response.getBody().prettyPrint());

        Todos todos = response.as(Todos.class);

        LinkedHashMap<String, Object> keyValue = new LinkedHashMap<String, Object>() {{
            put("id", TodosConstants.TodosConstants.getId());
            put("userId", TodosConstants.TodosConstants.getUserId());
            put("title", TodosConstants.TodosConstants.getTitle());
            put("completed", TodosConstants.TodosConstants.isCompleted());
        }};

        keyValue.forEach((k, v) -> {
            boolean isOk = false;
            Object obtained = "";

            switch (k) {
                case ("id"):
                    isOk = todos.checkId(TodosConstants.TodosConstants.getId());
                    obtained = todos.getId();
                    break;
                case ("userId"):
                    isOk = todos.checkUserId(TodosConstants.TodosConstants.getUserId());
                    obtained = todos.getUserId();
                    break;
                case ("title"):
                    isOk = todos.checkTitle(TodosConstants.TodosConstants.getTitle());
                    obtained = todos.getTitle();
                    break;
                case ("completed"):
                    isOk = todos.checkCompleted(TodosConstants.TodosConstants.isCompleted());
                    obtained = todos.isCompleted();
                    break;
            }

            if (isOk) {
                utils.logPass(TodosUtils.logMessage(k, obtained, v, true));
            } else {
                utils.logFail(TodosUtils.logMessage(k, obtained, v, true));
                isAllOk.set(false);
            }
        });

        if (!isAllOk.get()) {
            utils.logAssertFail("Algum campo retornou diferente do esperado!");
        }
    }
}

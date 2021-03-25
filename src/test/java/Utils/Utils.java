package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import LifeCycle.LifeCycle;

public class Utils implements Logs {

    //retorna valor do arquivo de properties corresponde ao servico sendo testado
    public String getValorDePropriedade(String property) {
        Properties properties = new Properties();
        String result = "";

        try {
            properties
                    .load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + LifeCycle.getServico() + ".properties"));
            result = properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String geraCpf(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String retorno = null;

        if (d2 >= 10)
            d2 = 0;
        retorno = "";

        if (comPontos)
            retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return retorno;
    }

    private int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    //verifica codigo do status da requisicao
    public boolean checkStatus(int status, Response response) {
        try {
            response.then().statusCode(status);
            return true;
        } catch (AssertionError a) {
            return false;
        }
    }

    //retorna string em formato de JSON a partir de um objeto
    public String getJsonFromObject(Object o) {
        ObjectMapper Obj = new ObjectMapper();

        try {
            String jsonStr = Obj.writeValueAsString(o);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(jsonStr);
            return gson.toJson(je);
        } catch (IOException e) {
            //TODO:VER ISSO
            logAssertFail("Erro ao tentar beutift json", e);
        }

        return null;
    }


}

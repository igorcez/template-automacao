package Utils;

public class TodosUtils {

    public  static String logMessage(String field, Object obtained, Object expected, boolean isSuccess) {
        return "Campo [ " + field + " ] " +
                ( isSuccess ? "" : "MAO" ) + "retornou conforme esperado! Esperava [ " + expected + " ]"
                + " e retornou [ " + obtained + " ]";
        }
 }

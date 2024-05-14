package metier;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LoginService {

    // Vérifie si l'adresse e-mail est valide
    public static boolean isValidEmailAddress(String email) {
        try {
            String apiKey = getAPIKeyFromConfig();
            String result = executeEmailVerificationRequest(email, apiKey);
            return parseEmailVerificationResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupère la clé API à partir du fichier de configuration
    private static String getAPIKeyFromConfig() throws Exception {
        String strJson = getJSONFromFile("./src/metier/config.json");
        JSONParser parser = new JSONParser();
        JSONObject mainJsonObject = (JSONObject) parser.parse(strJson);
        return (String) mainJsonObject.get("apiKey");
    }

    // Exécute une requête de vérification d'adresse e-mail
    private static String executeEmailVerificationRequest(String email, String apiKey) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.hunter.io/v2/email-verifier?email=" +
                email + "&api_key=" + apiKey);
        CloseableHttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        client.close();
        return result;
    }

    // Analyse le résultat de la vérification d'adresse e-mail
    private static boolean parseEmailVerificationResult(String result) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);
        JSONObject data = (JSONObject) json.get("data");
        String resultValue = (String) data.get("result");
        return resultValue.equals("deliverable");
    }

    // Récupère le contenu d'un fichier JSON
    private static String getJSONFromFile(String filename) {
        StringBuilder jsonText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText.toString();
    }
}

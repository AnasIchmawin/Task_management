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


public class service {

    // Valider l'adresse e-mail
    public static boolean isValidEmailAddress(String email) {
        try {
            String strJson = getJSONFromFile("./src/metier/config.json");
            JSONParser parser = new JSONParser();
            Object object = parser.parse(strJson);
            JSONObject maiJsonObject = (JSONObject) object;
            String apiKey = (String) maiJsonObject.get("apiKey");

            // Créer une requête HTTP pour vérifier l'adresse e-mail
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(
                    "https://api.hunter.io/v2/email-verifier?email=" + email + "&api_key=" + apiKey);

            CloseableHttpResponse response = client.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            client.close();

            // Analyser la réponse JSON pour déterminer si l'e-mail est valide
            JSONParser parser2 = new JSONParser();
            JSONObject json = (JSONObject) parser2.parse(result);
            JSONObject data = (JSONObject) json.get("data");
            String resultValue = (String) data.get("result");

            // Convertir la valeur en booléen
            boolean emailExists = resultValue.equals("deliverable");

            return emailExists;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer le fichier JSON à partir d'un chemin .
    private static String getJSONFromFile(String filename) {
        String jsonText = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}





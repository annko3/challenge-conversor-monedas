import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Converse {

    public static double convertirMoneda(int eleccion, double valor, Map<String, Double> tasasDeCambio) {
        double resultado;

        switch (eleccion) {
            case 1:
                resultado = valor * tasasDeCambio.get("ARS");
                break;
            case 2:
                resultado = valor / tasasDeCambio.get("ARS");
                break;
            case 3:
                resultado = valor * tasasDeCambio.get("BRL");
                break;
            case 4:
                resultado = valor / tasasDeCambio.get("BRL");
                break;
            case 5:
                resultado = valor * tasasDeCambio.get("PEN");
                break;
            case 6:
                resultado = valor / tasasDeCambio.get("PEN");
                break;
            default:
                return 0;
        }
        return resultado;
    }


    public static Map<String, Double> obtenerTasasDeCambio() throws IOException {
        String url_str = "https://v6.exchangerate-api.com/v6/c0beabdb1de65c465c87a735/latest/USD";
        Map<String, Double> tasasDeCambio = new HashMap<>();
        try {
            // Making Request
            URL url = new URL("https://v6.exchangerate-api.com/v6/c0beabdb1de65c465c87a735/latest/USD");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            tasasDeCambio.put("ARS", conversionRates.get("ARS").getAsDouble());
            tasasDeCambio.put("BRL", conversionRates.get("BRL").getAsDouble());
            tasasDeCambio.put("PEN", conversionRates.get("PEN").getAsDouble());

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }

        return tasasDeCambio;
    }
}
package service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class ApiService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;

    public ApiService(String apiKey) {
        this.apiKey = apiKey;
    }

    // Metodo para obtener las tasas de conversión basadas en la moneda base
    public String getRates(String baseCurrency) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        //URL de la api con la moneda base
        String url = API_URL + apiKey + "/latest/" + baseCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Error en la solicitud: " + response.statusCode());
        }


        //System.out.println("INFO: " + response.body()); Esto devuelve toda la lista de las cotizaciones de las monedas en referencia a la que se eligió como base.
        return response.body(); // Devuelve el JSON de la API como un String

    }
}

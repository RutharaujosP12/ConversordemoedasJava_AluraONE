
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Scanner;


public class Main {

    public static double buscarTaxa(String moedaDestino) {
                try {
                    String API_KEY = "895f9abcb14cb7042fb02041";// Substitua pela sua chave real
                    String url = "https://v6.exchangerate-api.com/v6/895f9abcb14cb7042fb02041/latest/BRL";

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                    JsonObject taxas = json.getAsJsonObject("conversion_rates");

                    return taxas.get(moedaDestino).getAsDouble();

                } catch (Exception e) {
                    System.out.println("Erro ao buscar taxa: " + e.getMessage());
                    return -1;
                }
            }

            public static void main(String[] args) {

                Locale.setDefault(Locale.US);
                Scanner sc = new Scanner(System.in);

                System.out.println("""
                Bem-vindo ao conversor de moedas em tempo real =)
                
                ENG: Welcome to the real-time currency converter
                SPAN: Bienvenido al convertidor de monedas en tiempo real
                
                Escolha o idioma / Choose language / Elija el idioma:
                1. English (ENG)
                2. Português (PT-BR)
                3. Español (SPAN)
                """);

                int idioma = sc.nextInt();
                String[] opcoesMoedas = new String[7];

                double real = 0.0;

                switch (idioma) {
                    case 1 -> {
                        System.out.println("Enter your amount in BRL (Brazilian Real):");
                        real = sc.nextDouble();

                        System.out.println("""
                        Select the option you want to convert to:
                        1. US Dollar (USD)
                        2. Canadian Dollar (CAD)
                        3. British Pound (GBP)
                        4. Japanese Yen (JPY)
                        5. Euro (EUR)
                        6. Swiss Franc (CHF)
                        0. Exit
                        """);
                    }
                    case 2 -> {
                        System.out.println("Digite o valor em R$ (real):");
                        real = sc.nextDouble();

                        System.out.println("""
                        Selecione a moeda para conversão:
                        1. Dólar americano (USD)
                        2. Dólar canadense (CAD)
                        3. Libra esterlina (GBP)
                        4. Iene japonês (JPY)
                        5. Euro (EUR)
                        6. Franco suíço (CHF)
                        0. Sair
                        """);
                    }
                    case 3 -> {
                        System.out.println("Ingrese el valor en BRL (real brasileño):");
                        real = sc.nextDouble();

                        System.out.println("""
                        Seleccione la moneda a la que desea convertir:
                        1. Dólar estadounidense (USD)
                        2. Dólar canadiense (CAD)
                        3. Libra esterlina (GBP)
                        4. Yen japonés (JPY)
                        5. Euro (EUR)
                        6. Franco suizo (CHF)
                        0. Salir
                        """);
                    }
                    default -> {
                        System.out.println("Opção inválida.");
                        sc.close();
                        return;
                    }
                }

                int escolha = sc.nextInt();

                String codigoMoeda = switch (escolha) {
                    case 1 -> "USD";
                    case 2 -> "CAD";
                    case 3 -> "GBP";
                    case 4 -> "JPY";
                    case 5 -> "EUR";
                    case 6 -> "CHF";
                    default -> null;
                };

                if (codigoMoeda != null) {
                    double taxa = buscarTaxa(codigoMoeda);
                    if (taxa != -1) {
                        double convertido = real * taxa;
                        System.out.printf("Valor convertido em %s: %.2f%n", codigoMoeda, convertido);
                    } else {
                        System.out.println("Erro ao obter taxa de câmbio.");
                    }
                } else {
                    System.out.println("Opção inválida. Encerrando.");
                }

                sc.close();
            }
        //final String API_KEY = 895f9abcb14cb7042fb02041;
        //final String BASE_URL = https://v6.exchangerate-api.com/v6/" +895f9abcb14cb7042fb02041+ "/latest/BRL";



        }


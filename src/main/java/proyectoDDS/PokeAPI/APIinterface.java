package proyectoDDS.PokeAPI;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIinterface
{
    static APIinterface instance;

    public static APIinterface getInstance(){
        if(instance != null){
            return instance;
        }
        else{
            instance = new APIinterface();
            return instance;
        }
    }

    public String getImage(String nombrePokemon) {
        String url_imagen = new String();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://pokeapi.co/api/v2/" + "pokemon/" + nombrePokemon)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            url_imagen = obj.getJSONObject("sprites").getString("front_default");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (InterruptedException e) {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        return url_imagen;
    }

    public List<String> getMoves(String nombrePokemon){
        List<String> movimientos = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://pokeapi.co/api/v2/" + "pokemon/" + nombrePokemon)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            JSONArray arrayDeMovimientos = obj.getJSONArray("moves");
            int cantidadDeMovimientos = arrayDeMovimientos.length();
            for (int i = 0; i < cantidadDeMovimientos; i++) {
                String movimiento = arrayDeMovimientos.getJSONObject(i).getJSONObject("move").getString("name");
                movimientos.add(movimiento);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (InterruptedException e) {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        return movimientos;
    }

    public List<String> getPokemonThatLearn(String nombreMovimiento){
        List<String> listaPokemon = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://pokeapi.co/api/v2/" + "move/" + nombreMovimiento)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            JSONArray arrayDePokemon = obj.getJSONArray("learned_by_pokemon");
            int cantidadDePokemon = arrayDePokemon.length();
            for (int i = 0; i < cantidadDePokemon; i++) {
                String pokemon = arrayDePokemon.getJSONObject(i).getString("name");
                listaPokemon.add(pokemon);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (InterruptedException e) {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        return listaPokemon;
    }

}
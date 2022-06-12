package proyectoDDS;
import proyectoDDS.PokeAPI.*;
import java.util.*;

public class Interfaz {
    static APIinterface pokeApi = APIinterface.getInstance();
    static Scanner unScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Ingrese nombre del Pokemon");
        String nombrePokemon = unScanner.nextLine();
        String url = pokeApi.getImage(nombrePokemon);
        System.out.println("La imagen esta en " + url);
        List<String> movimientos;
        movimientos = pokeApi.getMoves(nombrePokemon);
        for (String movimiento : movimientos) {
            System.out.println("movimiento: " + movimiento);
        }
        System.out.println("Ingrese movimiento");
        String nombreMovimiento = unScanner.nextLine();
        List<String> listaPokemon;
        listaPokemon = pokeApi.getPokemonThatLearn(nombreMovimiento);
        for (String pokemon : listaPokemon) {
            System.out.println("Pokemon: " + pokemon);
        }
    }

}

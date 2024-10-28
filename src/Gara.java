import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gara {
    private List<Cavallo> cavalli;
    private int lunghezzaPercorso;

    public Gara(int lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
        this.cavalli = new ArrayList<>();
    }

    public void aggiungiCavallo(String nome) {
        cavalli.add(new Cavallo(nome, lunghezzaPercorso));
    }

    public void iniziaGara() {
        // Avvia i thread dei cavalli
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        // Aspetta che tutti i cavalli finiscano la gara
        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La gara è finita!");
    }
}
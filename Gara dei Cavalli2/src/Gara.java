import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Gara {
    private List<Cavallo> cavalli;
    private int lunghezzaPercorso;
    private List<Cavallo> classifica;

    public Gara(int lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
        this.cavalli = new ArrayList<>();
        this.classifica = new ArrayList<>();
    }

    public void aggiungiCavallo(String nome, int velocita) {
        cavalli.add(new Cavallo(nome, lunghezzaPercorso, velocita, this));
    }

    public synchronized void registraArrivo(Cavallo cavallo) {
        classifica.add(cavallo);
    }

    public void iniziaGara() {
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La gara è finita!");
        mostraClassifica();
    }

    private void mostraClassifica() {
        classifica.sort(Comparator.comparingInt(Cavallo::getDistanzaPercorsa).reversed());

        System.out.println("Classifica dei primi 3 cavalli:");
        for (int i = 0; i < Math.min(3, classifica.size()); i++) {
            System.out.println((i + 1) + ". " + classifica.get(i).getNome() + " con " + classifica.get(i).getDistanzaPercorsa() + " metri percorsi");
        }
    }

    public void salvaClassificaSuFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("Classifica della gara:\n");
            for (int i = 0; i < Math.min(3, classifica.size()); i++) {
                writer.write((i + 1) + ". " + classifica.get(i).getNome() + " con " + classifica.get(i).getDistanzaPercorsa() + " metri percorsi\n");
            }
            writer.write("--------------\n");
            System.out.println("Classifica salvata su file: " + filePath);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio su file: " + e.getMessage());
        }
    }
}

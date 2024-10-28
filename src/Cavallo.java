import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int distanzaDaPercorrere;
    private int distanzaPercorsa = 0;
    private Random random;

    public Cavallo(String nome, int distanzaDaPercorrere) {
        this.nome = nome;
        this.distanzaDaPercorrere = distanzaDaPercorrere;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (distanzaPercorsa < distanzaDaPercorrere) {
            // Simula l'avanzamento del cavallo
            int passo = random.nextInt(10) + 1; // Avanza di 1-10 metri
            distanzaPercorsa += passo;
            if (distanzaPercorsa > distanzaDaPercorrere) {
                distanzaPercorsa = distanzaDaPercorrere; // Non superare la distanza
            }
            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");
            try {
                Thread.sleep(500); // Aspetta mezzo secondo prima del prossimo passo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nome + " ha completato la gara!");
    }
}
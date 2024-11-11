import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int distanzaDaPercorrere;
    private int distanzaPercorsa = 0;
    private int velocita; // Velocità specificata dall'utente
    private boolean infortunato = false; // Indica se il cavallo è infortunato
    private Random random;
    private Gara gara;

    public Cavallo(String nome, int distanzaDaPercorrere, int velocita, Gara gara) {
        this.nome = nome;
        this.distanzaDaPercorrere = distanzaDaPercorrere;
        this.velocita = velocita;
        this.gara = gara;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (distanzaPercorsa < distanzaDaPercorrere && !infortunato) {
            // Simula l'infortunio con una probabilità del 10%
            if (random.nextInt(100) < 10) {
                infortunato = true;
                System.out.println(nome + " si è infortunato ed esce dalla gara!");
                break;
            }

            // Avanza di un numero di metri pari alla velocità
            distanzaPercorsa += velocita;
            if (distanzaPercorsa > distanzaDaPercorrere) {
                distanzaPercorsa = distanzaDaPercorrere; // Limita alla distanza totale
            }
            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");

            try {
                Thread.sleep(1000); // Aspetta un secondo prima del prossimo passo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!infortunato) {
            System.out.println(nome + " ha completato la gara!");
            gara.registraArrivo(this);
        }
    }

    public String getNome() {
        return nome;
    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public boolean isInfortunato() {
        return infortunato;
    }
}

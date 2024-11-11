import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci la lunghezza del percorso in metri: ");
        int lunghezzaPercorso = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline rimasto

        Gara gara = new Gara(lunghezzaPercorso);
        System.out.print("Inserisci il numero di cavalli: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline rimasto

        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nomeCavallo = scanner.nextLine();
            System.out.print("Inserisci la velocità del cavallo (metri per secondo): ");
            int velocita = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline rimasto
            gara.aggiungiCavallo(nomeCavallo, velocita);
        }

        gara.iniziaGara();

        System.out.print("Inserisci il percorso completo del file per salvare i risultati: ");
        String filePath = scanner.nextLine();
        gara.salvaClassificaSuFile(filePath);

        scanner.close();
    }
}

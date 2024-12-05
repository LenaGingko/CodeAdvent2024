package day01;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day01 {
    public static void main(String[] args) {

        try {
            File file = new File("Daten/Werte1.txt");
            Scanner scanner = new Scanner(file);

            List<Integer> lList = new ArrayList<>();
            List<Integer> rList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\\s+");//Leerzeichen trennt
                if (parts.length == 2) { //2 listen
                    lList.add(Integer.parseInt(parts[0]));
                    rList.add(Integer.parseInt(parts[1]));
                }
            }
            scanner.close();

            Collections.sort(lList);
            Collections.sort(rList);

            int totalDifferenz = 0;
            int differenz;
            for (int i = 0; i < lList.size(); i++) {
                differenz = Math.abs(lList.get(i) - rList.get(i));
                totalDifferenz += differenz;
                //System.out.println("Paar " + (i + 1) + ": " + lList.get(i) + " + " + rList.get(i) + " -> Differenz: " + differenz);
            }

            int similarityScore;
            int totalSimilarityScore = 0;
            for (int i = 0; i < lList.size(); i++) {
                similarityScore = 0;
                int rAnzahl = 0; //Anzahl Nummer in rechter Liste
                for (int j = 0; j < rList.size(); j++) {
                    if(Objects.equals(lList.get(i), rList.get(j))){
                        rAnzahl++;
                    }
                }
                similarityScore += lList.get(i) * rAnzahl;
                System.out.println("Zahl " + lList.get(i) + " - rAnzahl: " + rAnzahl + " -> similarityScore: " + similarityScore);
                totalSimilarityScore += similarityScore;
                System.out.println("totalSimilarityScore: " + totalSimilarityScore);
            }

            System.out.println("Differenz: " + totalDifferenz);
            System.out.println("totalSimilarityScore: " + totalSimilarityScore);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
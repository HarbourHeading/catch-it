package com.app;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Finish {
    public List<Integer> topScores(int playerScore) {

        String path = "topScores.txt";
        List<Integer> topScores = new ArrayList<>();

        readFile(topScores, path);

        topScores.add(playerScore);
        topScores.sort(Collections.reverseOrder());
        topScores = topScores.subList(0, Math.min(topScores.size(), 3)); // In case of new file creation (where the array is smaller than 3): take lowest of array and 3

        writeFile(topScores, path);

        return topScores;
    }

    public void readFile(List<Integer> topScores, String path) {

        try (BufferedReader scoreReader = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = scoreReader.readLine()) != null) {
                topScores.add(Integer.parseInt(line));
            }

        } catch (FileNotFoundException e) {
            new File(path);
        } catch (IOException e) {
            System.out.println("IOException occurred.");
            System.exit(1);
        }
    }

    public void writeFile(List<Integer> topScores, String path) {

        try (BufferedWriter scoreWriter = new BufferedWriter(new FileWriter(path))) {

            for (Integer score : topScores) {
                scoreWriter.write(score.toString());
                scoreWriter.newLine();
            }

        } catch (FileNotFoundException e) {
            new File(path);
        } catch (IOException e) {
            System.out.println("IOException occurred.");
            System.exit(1);
        }
    }
}

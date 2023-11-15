package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class MyThread extends Thread {
    Socket client;

    public MyThread(Socket socket) {
        this.client = socket;
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            System.out.println("client connected");

            ArrayList<String> words = new ArrayList<>();
            words.add("palla");
            words.add("pallone");
            words.add("testa");
            words.add("smeraldo");
            words.add("mattone");
            words.add("computer");
            words.add("elettronica");
            words.add("armadio");
            words.add("sedia");
            words.add("argotone");
            words.add("tavolo");
            words.add("porta");
            words.add("sassolino");
            words.add("lupo");
            words.add("pneumonoultramicroscopicsilocovolcanoconiosis");
            words.add("hippopotomonstrosesquippedaliofobia");
            words.add("bob");

            String word = words.get(new Random().nextInt(words.size()));

            String newString = new String();
            for (int i = 0; i < word.length(); i++) {
                newString += "*";
            }

            Boolean haswon = false;
            int counter = 0;

            System.out.println("parola scelta= " + word);
            do {
                out.writeBytes(newString + "\n");
                String guess = in.readLine();
                System.out.println("received from client: " + guess);

                if (guess.length() > 1) {
                    if (word.equals(guess)) {
                        out.writeBytes("1\n");
                        haswon = true;
                    } else {
                        out.writeBytes("2\n");
                    }
                } else {
                    Character letter = guess.charAt(0);
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == letter) {
                            newString = newString.substring(0, i) + letter + newString.substring(i + 1);
                        }
                    }
                    if(word.equals(newString)){
                        out.writeBytes("1\n");
                    }else{
                        out.writeBytes("3\n");
                    }
                }
                counter++;
            } while (!haswon);

            System.out.println("client won");

            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}

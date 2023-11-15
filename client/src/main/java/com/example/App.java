package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        try{
            Socket socket = new Socket("localhost", 3000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Scanner input = new Scanner(System.in);

            String answer = "";

            Boolean haswon = false;
            do{
                answer = in.readLine();
                
                System.out.println(answer);

                String guess = input.nextLine();

                out.writeBytes(guess+"\n");
                answer = in.readLine();

                if(answer.equals("2")){
                    System.out.println("quella non è la parola giusta!");
                }
                if(answer.equals("1")){
                    haswon = true;
                }

            }while(!haswon);
            
            System.out.println("HAI VINTO!!");
            socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}

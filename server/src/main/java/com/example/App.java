package com.example;
import java.net.*;

public class App 
{
    public static void main( String[] args )
    {

        try{
            System.out.println("server started and executing");
            ServerSocket server = new ServerSocket(3000);
            

            while(true){
                Socket client = server.accept();
                MyThread clientManager = new MyThread(client);
                clientManager.start();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}

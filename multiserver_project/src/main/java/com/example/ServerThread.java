package com.example;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
   
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread (Socket socket){

        this.client = socket;
    }

    public void run(){
        try{
            comunica();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception{

        inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                outVersoClient.writeBytes(stringaRicevuta+"(=>server in chiusura...)" + "\n");
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
            }
        }
    }

   }

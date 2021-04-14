package com.dkit.oopca5.server;

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class CAOClientHandler implements Runnable
{
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket socket;
    int clientNumber;

    public CAOClientHandler(Socket clientSocket, int clientNumber) {
        try {
            InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
            this.socketReader = new BufferedReader(isReader);

            OutputStream os = clientSocket.getOutputStream();
            this.socketWriter = new PrintWriter(os, true);

            this.clientNumber = clientNumber;

            this.socket = clientSocket;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        String message;
        try
        {
            while ((message = socketReader.readLine()) != null)
            {
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                if (message.startsWith("Time"))
                {
                    LocalTime time =  LocalTime.now();
                    socketWriter.println(time);
                }
                else if (message.startsWith("Echo"))
                {
                    message = message.substring(5);
                    socketWriter.println(message);
                }
                else
                {
                    socketWriter.println("I'm sorry I don't understand :(");
                }
            }

            socket.close();

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }
}


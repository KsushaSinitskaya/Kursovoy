package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {
    private ServerSocket serverSocket;

    private boolean isStopped = false;

    @Override
    public void run() {
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket;
            try {
                clientSocket = this.serverSocket.accept();
                new Thread(new Worker(clientSocket)).start();
            } catch (IOException e) {
                System.out.println("Server socket closed");
                //e.printStackTrace();
            }
        }

    }

    private void openServerSocket() {
        System.out.println("Opening server socket...");
        this.isStopped = false;
        try {
            this.serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            System.out.println("Error opening server socket");
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }
}

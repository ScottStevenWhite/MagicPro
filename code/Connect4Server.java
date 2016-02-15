import java.net.*;
import java.io.*;
import java.util.*;

public class Connect4Server implements Runnable {  //after run()  is setup
    // Create an object of the ServerSocket
    ServerSocket server;
    // Create and Initialize an object of the PrintWriter class
    PrintWriter writer = new PrintWriter(System.out);
    // Create and Initialize an arraylist that stores the Connect4Request
    private ArrayList<Connect4Request> request = new ArrayList<Connect4Request>();
    
    /** 
     * Construct the server, set up the socket. 
     *  
     * @throws SocketException if the socket or port cannot be obtained properly. 
     * @throws IOException if the port cannot be reused. 
     */
    public Connect4Server ( int port) throws SocketException,IOException {
        // The port address must be within this range
        if ((port > 1025) && (port < 65535)) {
            // Initilizes port with given address
            this.server = new ServerSocket(port);
            // If the server is closed, this port number can be reused
            server.setReuseAddress(true);
        }
        else {
            writer.println("Invalid port number! The port number has to be betweeb 1025 and 65535.");
            writer.flush();
        }
    }
    // Default Constructor
    public Connect4Server() throws SocketException,IOException { 
        // Initilizes port with default number 9999
        this.server = new ServerSocket(9999); 
        writer.println("Free port: " + server.getLocalPort());   
        writer.flush();
        server.setReuseAddress(true); 
    }
    //TODO: Write mathods to read and interpret inputs from client
    
    public void run() {
        while (true) {
            try {
                //TODO: Read Requests and Respond
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            
            
        }
        
    }
    
    
    
}
import java.net.*;
import java.io.*;
import java.util.*;
class Connect4Request { 
    public String request; 
    public Socket socket; 
    public PrintWriter out; 
    public BufferedReader in; 
    public Connect4Request( String request, Socket socket, PrintWriter out, BufferedReader in) { 
        this.request = request; 
        this.socket = socket; 
        this.out = out; 
        this.in = in; 
    }  
}
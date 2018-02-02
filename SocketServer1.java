import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer1{
    
    //static ServerSocket variable
    private static ServerSocket server;
    private static ServerSocket server1; 
    //socket server port on which it will listen
    private static int port = 9876;
    private static int port1 = 9879;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        server1 = new ServerSocket(port1);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for client 1 request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            
            if(message != "")
              System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            
            Socket socket1 = server1.accept();
            ObjectOutputStream oos1 = new ObjectOutputStream(socket1.getOutputStream());
            oos1.writeObject(message);
            
            
            ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());
            //convert ObjectInputStream object to String  
            String message1 = (String) ois1.readObject();
            
           
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject(message1);
            //close resources
            ois.close();
            oos.close();
            ois1.close();
            oos1.close();
            socket.close();
            socket1.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
    
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        
        InetAddress host1 = InetAddress.getLocalHost();
        Socket socket1 = null;
        ObjectOutputStream oos1 = null;
        ObjectInputStream ois1 = null;
        for(int i=0; i<5;i++){
            //establish socket connection to server
            socket1 = new Socket("172.16.2.187" , 9879);
            //write to socket using ObjectOutputStream
           
            ois1 = new ObjectInputStream(socket1.getInputStream());
            String message1 = (String) ois1.readObject();
            System.out.println("Message: " + message1);
            
            
            oos1 = new ObjectOutputStream(socket1.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos1.writeObject("exit");
            else{ 
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\nEnter string 2");
                String str2 = br1.readLine();
                oos1.writeObject(str2);
                //oos1.writeObject(str2);
            }
            //read the server response message
          
            //close resources
            ois1.close();
            oos1.close();
            Thread.sleep(100);
        }
    }
}
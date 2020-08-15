package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.CORBA.Stub;
import model.Answer;
import model.Student;

/**
 *
 * @author Lê Quang Đạo
 */
public class Client1 {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 11001;
        String maSV = "B16DCCN058";
        String hovaten = "Le Quang Dao";
        String IP = "127....";
        int group = 2;
        
        try {
            Socket client = new Socket(serverIP, serverPort);
            System.out.println("Connected: "+ client);
            Student student = new Student(maSV, hovaten, IP, group);
//            Gui du lieu kieu data
            DataOutputStream dataOut= new DataOutputStream(client.getOutputStream());
            dataOut.writeUTF(student.getMaSV());
            dataOut.writeUTF(student.getHovaten());
            dataOut.writeUTF(student.getIP());
            dataOut.writeInt(student.getGroup());
               
            // Gui du lieu kieu Object
//            ObjectOutput objOut = new ObjectOutputStream(client.getOutputStream());
//            objOut.writeObject(student);
            
            ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());
            Answer answer = (Answer) objIn.readObject();
            System.out.println("Da nhan Objcet: " + answer.getStudent());
                   
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

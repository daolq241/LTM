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
        try {
            Socket client = new Socket("localhost", 11001);
            System.out.println("Connected: "+ client);
            Student student = new Student("B16DCCN058", "Le Quang Dao", "ip", 2);
            
            // Gui du lieu kieu data len server
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(student.getMaSV());
            dos.writeUTF(student.getHovaten());
            dos.writeUTF(student.getIP());
            dos.writeInt(student.getGroup());
            
            //Gui du lieu len server kieu Object
//            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
//            oos.writeObject(student);
            
            //Nhan object tu server
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Answer answer = (Answer) ois.readObject();
            System.out.println("Da nhan Objcet: " + answer.getStudent().toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

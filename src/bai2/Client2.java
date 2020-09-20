package bai2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Student;

/**
 *
 * @author Lê Quang Đạo
 */
public class Client2 {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 11001);
            Student student = new Student("B16DCCN058", "Le Quang Dao", "ip", 2);
            
            // Gui du lieu len kieu data
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(student.getMaSV());
            dos.writeUTF(student.getHovaten());
            dos.writeUTF(student.getIP());
            dos.writeInt(student.getGroup());
            
            // Nhan code int tu sever
            DataInputStream dis = new DataInputStream(client.getInputStream());
            dis.readInt();
            int code = dis.readInt();
            if (code == 1) {
                // Chuan hoa xau1 => gui len xau chuan hoa
                String str = dis.readUTF().trim();
                str = str.replaceAll("\\s+", " ");
                String temp[] = str.split(" ");
                str = "";
                for (int i = 0; i < str.length(); i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() + 
                            String.valueOf(temp[i].substring(1)).toLowerCase();
                    if (i < temp.length) {
                        str += " ";
                    }
                }
                dos.writeUTF(str);
                
                //Dem so lan xau2 xuat hien trong xau3 => gui len so lan
                String xau2 = dis.readUTF();
                String xau3 = dis.readUTF();
                int dem = 0;
                for (int i = 0; i < xau3.length() - xau2.length() + 1; i++) {
                    if (xau3.substring(i, i + xau2.length()).contains(xau2)) {
                        dem++;
                    }
                }
                dos.writeUTF(String.valueOf(dem));

            } 
            // Tuong tu
            else if (code == 2) {
                //Dem so lan xau2 xuat hien trong xau3 => gui len so lan
                String xau2 = dis.readUTF();
                String xau3 = dis.readUTF();
                int dem = 0;
                for (int i = 0; i < xau3.length() - xau2.length() + 1; i++) {
                    if (xau3.substring(i, i + xau2.length()).contains(xau2)) {
                        dem++;
                    }
                }
                dos.writeUTF(String.valueOf(dem));
                
                // Chuan hoa xau1 => gui len xau chuan hoa
                String str = dis.readUTF().trim();
                str = str.replaceAll("\\s+", " ");
                String temp[] = str.split(" ");
                str = "";
                for (int i = 0; i < str.length(); i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() 
                            + String.valueOf(temp[i].substring(1)).toLowerCase();
                    if (i < temp.length) {
                        str += " ";
                    }
                }
                dos.writeUTF(str);
            }
            //Nhan Object tu server
            ObjectInputStream obi = new ObjectInputStream(client.getInputStream());
            Answer answer = (Answer) obi.readObject();
            System.out.println("Da nhan Object: " + answer.getStudent().toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

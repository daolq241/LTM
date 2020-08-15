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

        String serverIP = "localhost";
        int serverPort = 11001;
        String maSV = "B16DCCN058111";
        String hovaten = "Le Quang Dao";
        String IP = "127....";
        int group = 2;

        try {
            Socket client = new Socket(serverIP, serverPort);
            Student student = new Student(maSV, hovaten, IP, group);
            // Gui du lieu len kieu data
            DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
            dataOut.writeUTF(student.getMaSV());
            dataOut.writeUTF(student.getHovaten());
            dataOut.writeUTF(student.getIP());
            dataOut.writeInt(student.getGroup());

            // Nhan code int tu sever
            DataInputStream dataIn = new DataInputStream(client.getInputStream());
            int code = dataIn.readInt();
            if (code == 1) {
                // Chuan hoa xau1 => gui len xau chuan hoa
                String str = dataIn.readUTF().trim();
                str = str.replaceAll("\\s+", " ");
                String temp[] = str.split(" ");
                str = "";
                for (int i = 0; i < str.length(); i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
                    if (i < temp.length) {
                        str += " ";
                    }
                }
                dataOut.writeUTF(str);
                //Dem so lan xau2 xuat hien trong xau3 => gui len so lan
                String xau2 = dataIn.readUTF();
                String xau3 = dataIn.readUTF();
                int dem = 0;
                for (int i = 0; i < xau3.length() - xau2.length() + 1; i++) {
                    if (xau3.substring(i, i + xau2.length()).contains(xau2)) {
                        dem++;
                    }
                }
                dataOut.writeUTF(String.valueOf(dem));

            } // Tuong tu
            else if (code == 2) {
                //Dem so lan xau2 xuat hien trong xau3 => gui len so lan
                String xau2 = dataIn.readUTF();
                String xau3 = dataIn.readUTF();
                int dem = 0;
                for (int i = 0; i < xau3.length() - xau2.length() + 1; i++) {
                    if (xau3.substring(i, i + xau2.length()).contains(xau2)) {
                        dem++;
                    }
                }
                dataOut.writeUTF(String.valueOf(dem));
                // Chuan hoa xau1 => gui len xau chuan hoa
                String str = dataIn.readUTF().trim();
                str = str.replaceAll("\\s+", " ");
                String temp[] = str.split(" ");
                str = "";
                for (int i = 0; i < str.length(); i++) {
                    str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
                    if (i < temp.length) {
                        str += " ";
                    }
                }
                dataOut.writeUTF(str);
            }

//            ObjectInputStream obi = new ObjectInputStream(client.getInputStream());
//            Answer answer = (Answer) obi.readObject();
//            System.out.println("Da nhan Object: " + answer.getStudent().toString());

        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}

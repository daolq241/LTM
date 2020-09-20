package bai3;

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
public class Client3 {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 11001);
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            Student s = new Student("B16DCCN058", "Le Quang Dao", "ip", 2);
            
            dos.writeUTF(s.getMaSV());
            dos.writeUTF(s.getHovaten());
            dos.writeInt(s.getGroup());
            
            //Nhan code, 2 so; 
            //Code = 1 => uscln; Code = 2 => bscnn 
            DataInputStream dis = new DataInputStream(client.getInputStream());
            dis.readInt(); // -- Phai co dong nay moi nhan dc code int;
            int code = dis.readInt();
            int num1 = dis.readInt();
            int num2 = dis.readInt();
            if (code == 1) {
                dos.writeInt(USCLN(num1, num2));
            } else if (code == 2) {
                dos.writeInt(BSCNN(num1, num2));
            }
            
            // Nhan so n, nhan n so lien tiep;
            dis.readInt();
            int n = dis.readInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = dis.readInt();
            }
            if (code == 1) { //Gui day tang dan
                sxTang(arr);
                for (int i = 0; i < n; i++) {
                    dos.writeInt(arr[i]);
                }
            } else if (code == 2) { //Gui day giam dan
                sxGiam(arr);
                for (int i = 0; i < n; i++) {
                    dos.writeInt(arr[i]);
                }
            }
            
            // Nhan Object tu server
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Answer answer = (Answer) ois.readObject();
            System.out.println("Da nhan Object " + answer.getStudent().toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int USCLN(int a, int b) {
        if (b == 0) {
            return a;
        }
        return USCLN(b, a % b);
    }

    private static int BSCNN(int a, int b) {
        return (a * b / USCLN(a, b));
    }

    private static void sxTang(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void sxGiam(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}

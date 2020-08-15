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
        String serverIP = "localhost";
        int serverPort = 11001;
        String maSV = "B16DCCN058";
        String hovaten = "Le Quang Dao";
        String IP = "127....";
        int group = 2;

        try {
            Socket client = new Socket(serverIP, serverPort);
            DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
            Student student = new Student(maSV, hovaten, IP, group);
            dataOut.writeUTF(student.getMaSV());
            dataOut.writeUTF(student.getHovaten());
            dataOut.writeInt(student.getGroup());

            //Nhan code, 2 so; 
            //Code = 1 => uscln; Code = 2 => bscnn
            DataInputStream dataIn = new DataInputStream(client.getInputStream());
            int code = dataIn.readInt();
            int num1 = dataIn.readInt();
            int num2 = dataIn.readInt();
            if (code == 1) {
                dataOut.writeInt(USCLN(num1, num2));
            } else if (code == 2) {
                dataOut.writeInt(BSCNN(num1, num2));
            }
            // Nhan so n, nhan n so lien tiep;
            // 
            int n = dataIn.readInt();

            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = dataIn.readInt();
            }
            if (code == 1) { //Gui day tang dan
                sxTang(arr);
                for (int i = 0; i < n; i++) {
                    dataOut.writeInt(arr[i]);
                }
            } else if (code == 2) { //Gui day giam dan
                sxGiam(arr);
                for (int i = 0; i < n; i++) {
                    dataOut.writeInt(arr[i]);
                }
            }

//            ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());
//            Answer answer = (Answer) objIn.readObject();
//            System.out.println("Da nhan Obj " + answer.getStudent());
        } catch (IOException ex) {
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

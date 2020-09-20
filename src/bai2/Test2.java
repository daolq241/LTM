package bai2;

/**
 *
 * @author Lê Quang Đạo
 */
public class Test2 {

    public static void main(String args[]) {
//        Số lần xau2 xuất hiện trong xâu 3

//        String xau2 = "aaa";
//        String xau3 = "aaa aa aaa ";
        String xau2 = "ab";
        String xau3 = "abaaabaaaba";

        int dem = 0;
        for (int i = 0; i < xau3.length() - xau2.length() + 1; i++) {
            if (xau3.substring(i, i + xau2.length()).contains(xau2)) {
                dem++;
            }
        }
        String soLan = String.valueOf(dem);
        System.out.println(soLan);
    }
}

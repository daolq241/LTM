package bai2;

/**
 *
 * @author Lê Quang Đạo
 */
public class Test2 {

    public static void main(String args[]) {

//        String xau2 = "abc";
//        String xau3 = "ababc123abc abc abc";

        String xau2 = "ab";
        String xau3 = "abaaabaaaba";

        int dem = 0;
        for (int i = 0; i < xau3.length() - xau2.length() + 1 ; i++) {
               if(xau3.substring(i, i+xau2.length()).contains(xau2))
                   dem++;
        }
        for(int i = 0; i < -5; i++) {
            System.out.println("vo li");
        }
//        String solan = String.valueOf(dem);
        System.out.println(String.valueOf(dem));
    }
}

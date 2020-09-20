package functions;

/**
 *
 * @author Lê Quang Đạo
 */
public class Functions {

    public static void main(String[] args) {
        String inputDaoChuoi = "abcd efg hik";
        String reversed = daoChuoi(inputDaoChuoi);
        System.out.println("The reversed string is: " + reversed);
        System.out.println("=====================");

        String inputChuanHoa = "   lE   quANG   DAO  abc DEF gHI ";
        System.out.println("String da chuan hoa: " + chuanHoa(inputChuanHoa));
        System.out.println("=====================");
        String xau1 = "ab";
        String xau2 = "abcdefghab ijk ab";
        System.out.println(demLanXH(xau1, xau2));
        System.out.println("=====================");
        int a1[] = {2,4,6,3,5,7};
        sxTang(a1);
        for (int i = 0; i < a1.length; i++) {
            System.out.print(a1[i] + " ");
        }
        System.out.println();
        sxGiam(a1);
        for (int i = 0; i < a1.length; i++) {
            System.out.print( a1[i] + " ");
        }
        System.out.println();
        System.out.println("=====================");
        int a=10, b=15;
        System.out.println("a=10, b=15");
        System.out.println("UCLN: " + UCLN(a, b));
        System.out.println("BCNN: " + BCNN(a, b));
        
    }
    

    // Dao Chuoi
    public static String daoChuoi(String input) {
        if (input.isEmpty()) {
            return input;
        }
        return daoChuoi(input.substring(1)) + input.charAt(0);
    }

    // Chuan Hoa 
    public static String chuanHoa(String input) {
        String answer = "";
        input = input.trim();
        input = input.replaceAll("\\s+", " "); //Thay the tat ca khoang trang >1 thanh 1 khoang trang
        String s1[] = input.split(" ");
        for (int i = 0; i < s1.length; i++) {
            answer += String.valueOf(s1[i].charAt(0)).toUpperCase()
                        + String.valueOf(s1[i].substring(1)).toLowerCase();
            if (i < s1.length - 1) {
                answer += " ";
            }
        }
        return answer;
    }
    
    // Dem so lan xau1 xuat hien trong xau2
    public static int demLanXH(String xau1, String xau2) {
        int dem = 0;
        for (int i = 0; i < xau2.length() - xau1.length() +1; i++) {
            if(xau2.substring(i, i + xau1.length()).contains(xau1)) 
                dem++;
        }
        return dem;
    }
    
    //Sap xep mang tang dan, giam dan
    public static void sxTang(int a[]) {
        int temp;
        for (int i = 0; i < a.length -1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[i] > a[j]) {
                   temp = a[i];
                   a[i] = a[j];
                   a[j] = temp;
                }
            }
        }
    }
    public static void sxGiam(int a[]) {
        int temp;
        for (int i = 0; i < a.length -1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[i] < a[j]) {
                   temp = a[i];
                   a[i] = a[j];
                   a[j] = temp;
                }
            }
        }
    }
    
    //UCLN,BCNN
    public static int UCLN(int a, int b){
        if (b==0) return a;
        return(UCLN(b, a%b));
    }
    public static int BCNN(int a, int b) {
        return ((a*b)/UCLN(a, b));
    }
}

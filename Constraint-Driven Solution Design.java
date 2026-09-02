import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read number of test cases
        String tStr = br.readLine();
        if (tStr == null || tStr.trim().isEmpty()) return;
        int t = Integer.parseInt(tStr.trim());
        
        // Process each test case
        while (t-- > 0) {
            String s = br.readLine();
            if (s == null) break;
            s = s.trim();
            
            int n = s.length();
            int mid = n / 2;
            
            // Extract the left and right halves
            String leftHalf = s.substring(0, mid);
            String rightHalf;
            
            if (n % 2 == 0) {
                rightHalf = s.substring(mid);
            } else {
                rightHalf = s.substring(mid + 1);
            }
            
            // Convert to char arrays and sort to compare frequencies
            char[] leftArr = leftHalf.toCharArray();
            char[] rightArr = rightHalf.toCharArray();
            
            Arrays.sort(leftArr);
            Arrays.sort(rightArr);
            
            // Check if both sorted halves are identical
            if (Arrays.equals(leftArr, rightArr)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
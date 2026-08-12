
import java.util.Arrays;

public class Runningsumimperative {

    public Runningsumimperative() {
    }

    public static int[] runningSum(int[] var0) {
        int[] var1 = new int[var0.length];
        int var2 = 0;
        for (int var3 = 0; var3 < var0.length; ++var3) {
            var2 += var0[var3];
            var1[var3] = var2;
        }
        return var1;
    }

    public static void main(String[] var0) {
        int[] var1 = new int[]{1, 2, 3, 4};
        int[] var2 = runningSum(var1);
        System.out.println(Arrays.toString(var2));
    }
}

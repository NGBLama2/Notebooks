package practice;

import java.sql.SQLOutput;
import java.util.Random;

public class disorganizeArr {
    public static void main(String[] args) {
        int[] tempArr = new int[16];

        for (int i = 1; i < 17 ; i++) {
             tempArr[i-1] = i;
        }

        int[] numArr = new int[16];
        int[] recordArr = new int[16];

        Random rd = new Random();

        for (int i = 0; i < 16; i++) {
            // 验证该位置是否已经有
            int index = rd.nextInt(16);
            if(recordArr[index]==1){
                i--;
                continue;
            }
            numArr[i] = tempArr[index];
            recordArr[index] = 1;
        }

        for (int i = 0; i < 16; i++) {
            System.out.print(numArr[i]+",");
        }
    }
}

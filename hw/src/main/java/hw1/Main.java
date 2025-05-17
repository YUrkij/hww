package hw1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {-10,2,3,6,7,7,7,9,11,1000,1010,1100};
        int[] arr2 = {-5,0,1,1,2,3,6,8,22,45,678};

        System.out.println(Arrays.toString(split(arr, arr2)));
    }

    public static int[] split(int[] arr1, int[] arr2) {
        int traker1 = 0;
        int traker2 = 0;
        int[] result = new int[arr1.length + arr2.length];
        while(traker1 +  traker2 < result.length) {
            result[traker1 +  traker2] = (traker1 < arr1.length) && ((traker2 == arr2.length) || arr1[traker1] < arr2[traker2]) ? arr1[traker1++] : arr2[traker2++];
        }
        return result;
    }
}
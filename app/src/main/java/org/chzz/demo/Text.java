package org.chzz.demo;

/**
 * Created by copy on 2017/12/10.
 * Description:
 * User: copy
 * Date: 2017-12-10
 * Time: 下午5:35
 */
public class Text {

    public static void main(String[] args) {

        int[] a = {8, 2, 1, 5, 1, 4, 3, 9, 8, 7, 10, 30, 50, 15, 16, 17};
        //BubbleSort(a);
        select_sort(a);
    }

    /**
     * 冒泡算法
     *
     * @param args
     */
    private static void BubbleSort(int[] args) {

        int count = 0;
        for (int i = 0; i < args.length - 1; i++) {
            int temp = 0;
            for (int j = args.length - 1; j > i; j--) {

                if (args[i] > args[j]) {
                    temp = args[i];
                    args[i] = args[j];
                    args[j] = temp;
                    count = count + 1;
                    continue;
                }
            }
        }
        int c = count;
        int[] a = args;
    }

    /**
     * 选择比序
     * @param args
     */
    private static void select_sort(int[] args) {
        int count=0;
        for (int i = 0; i < args.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < args.length; j++) {
                if (args[j] < args[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = args[i];
                args[i] = args[minIndex];
                args[minIndex] = temp;
            }
        }
        int c = count;
        int[] a = args;
    }
}

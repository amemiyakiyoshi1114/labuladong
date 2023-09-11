package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MySort {
    public int[] bubbleSort(int[] source) {
        //将目标数组进行拷贝
        int[] result = Arrays.copyOf(source,source.length);
        for(int i = 0 ; i < result.length ; i++){
            //标记是否进行了交换
            int flag  = 0;
            for(int j = 0 ; j < result.length - i ; j++){
                if(result[j] >  result[j+1]){
                    int  temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                    flag = 1;
                }
            }
            //如果没有发生交换，则冒泡结束，返回结果
            if(flag == 0){
                break;
            }
        }
        return result;
    }
    public int[] selectSort(int[] source){
        int[] result = Arrays.copyOf(source,source.length);
        //找最大，一共需要找length次
        for(int i = 0 ; i < source.length-1 ; i++){
            int nowMin = i;
            //找到最小的一个
            for(int j = i + 1 ; j < source.length ; j++){
                if (result[j] < result[nowMin]) {
                    nowMin = j;
                }
            }
            //和当前未操作最小位数交换
            int temp = result[i];
            result[i] = result[nowMin];
            result[nowMin] = temp;
        }
        return result;
    }
    public int[] insertSort(int[] source){
        int[] result = Arrays.copyOf(source,source.length);
        for(int i = 1 ; i < source.length; i++){
            //记录当前i位置数据
            int temp = result[i];
            //开始整体挪动，因为左部分一定是有序的
            int j = i;
            for(; j > 0&& result[j-1] > temp; j--){
                //每循环一次就将前一个元素挪到当前j位置
                result[j] = result[j-1];
                //一定不会造成数据丢失，因为已经记录了i位置，
            }
            //挪到空了，插入i位置的数据
            result[j] = temp;
        }
        return result;
    }
    public int[] shellSort(int[] source){

    }

}

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
        int[] result = Arrays.copyOf(source,source.length);
        //设置gap的递减规则,每次在gap范围内做插入排序
        int gap = source.length;
        int temp = 0;
        //我们能保证gap在逐渐减小的过程中，gap所划分的序列都是有序的，
        //所以当gap最后为0时整个序列成为一个子序列，必然是有序的
        for(int i = gap / 2 ; i >= 1; i /= 2){
            //划分完成后，实现一个朴素的插入排序
            //每个gap，默认第一个元素有序
            for(int j = i ; j < result.length; j++){
                temp = result[j];
                int k = j - i;
                while(k > 0 && result[k] > temp){
                    result[k+i] = result[k];
                    k -= i;
                }
                //将比他大的元素都依照gap挪动后空出的位置插入temp
                result[k+i] = temp;
            }
        }
        return result;
    }
    public int[] mergeSort(int[] source){
        //用一个内部类来实现merge的递归函数
        class Merge {
            int[] mergeTwo(int[] left, int[] right){
                int[] kidResult = new int[left.length + right.length];
                int i = 0;
                //通过设置循环外的计数旗，每次循环修改一次左/右数组，
                while(left.length > 0 && right.length > 0){//寻找本次最小值
                    if(left[0] <= right[0]){
                        kidResult[i++] = left[0];
                        left = Arrays.copyOfRange(left,1,left.length);
                    }
                    else {
                        kidResult[i++] = right[0];
                        right = Arrays.copyOfRange(right,1,right.length);
                    }
                }
                while (left.length > 0){//如果左右不一样长，把剩余的全部贴过去，结果是必然有序的
                    kidResult[i++] = left[0];
                    left = Arrays.copyOfRange(left,1,left.length);
                }
                while (right.length > 0){
                    kidResult[i++] = right[0];
                    right = Arrays.copyOfRange(right,1,right.length);
                }
                return kidResult;
            }

        }
        int[] result = Arrays.copyOf(source,source.length);
        //如果数组中少于两个元素，不必归并直接返回
        if(result.length < 2){
            return result;
        }
        int middle = Math.floorDiv(result.length,2);
        int[] firstLeft = Arrays.copyOfRange(result,0,middle);
        int[] firstRight = Arrays.copyOfRange(result,middle,result.length);
        Merge m = null;
        //递归实现mergesort，
        result = m.mergeTwo(mergeSort(firstLeft),mergeSort(firstRight));
        return result;
    }
    public int[] quickSort(int[] source){
        class Quick{
            void swap(int[] arry,int index1, int index2){
                int temp = arry[index1];
                arry[index1] = arry[index2];
                arry[index2] = temp;
            }
            int partition(int[] array,int left, int right){
                //设定基准值，虽然我们一般都以第一个数为基准，但我们可以优化一下
                //找从左往右先简单地过滤一次，将比基准小的交换到左边，最后交换left和index-1
                int pivot = left;
                int index = pivot + 1;
                for(int i = index ; i <= right; i++){
                    if(array[i] < array[pivot]){
                        swap(array,i,index);
                        index++;
                    }
                }
                swap(array,pivot,index-1);
                return index - 1;
            }
            int[] qui(int[] array,int left,int right){
                if(left < right){
                    int partitionIndex = partition(array,left,right);
                    qui(array,left,partitionIndex-1);
                    qui(array,partitionIndex+1,right);
                }
                return array;
            }
        }
        int[] result = Arrays.copyOf(source,source.length);
        Quick q = null;
        return q.qui(result,0,result.length-1);

        //补充一下普通款
//        void Quick_Sort(int *arr, int begin, int end){
//            if(begin > end)
//                return;
//            int tmp = arr[begin];
//            int i = begin;
//            int j = end;
//            while(i != j){
//                while(arr[j] >= tmp && j > i)
//                    j--;
//                while(arr[i] <= tmp && j > i)
//                    i++;
//                if(j > i){
//                    int t = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = t;
//                }
//            }
//            arr[begin] = arr[i];
//            arr[i] = tmp;
//            Quick_Sort(arr, begin, i-1);
//            Quick_Sort(arr, i+1, end);
//        }
    }
    public int[] heapSort(int[] source){
        //堆是近似完全二叉树的结构，所以我们使用数组来存储这个堆
        int[] result = Arrays.copyOf(source,source.length);
        return result;

    }


}

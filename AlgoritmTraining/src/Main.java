import java.util.*;

public class Main {
    public static int maxProfit(int[] prices) {
        //遍历整个价值区间，寻找当前索引为起点的最长递增子序列，差值为当前利润，叠加到最后即为利润
        //每开始一个新的子序列寻找，如果找得到我们就购入，找不到我们就不买，即回退
        int result = 0 ;
        int flag = 0;
        for(int i = 0 ; i < prices.length-1 ;i++){
            for(int j = i+1 ; j < prices.length;j++){
                flag = j;
                if(prices[j] >= prices[j-1]){
                    continue;
                }
                else {
                    flag = j-1;
                    break;
                }
            }
            if(prices[flag] > prices[i]){
                result += prices[flag] - prices[i];
                i = flag;
            }
        }
        return result;
    }


    public static  int getNoMoreThanTwo(int[] prices){
        PriorityQueue<Integer> money = new PriorityQueue<>( (a,b) -> (b-a));
        //int result = 0 ;
        int flag = 0;
        for(int i = 0 ; i < prices.length-1 ;i++){
            for(int j = i+1 ; j < prices.length;j++){
                flag = j;
                if(prices[j] >= prices[j-1]){
                    continue;
                }
                else {
                    flag = j-1;
                    break;
                }
            }
            if(prices[flag] > prices[i]){
                //result += prices[flag] - prices[i];
                money.add(prices[flag] - prices[i]);
                i = flag;
            }
        }
        //return result;
        //return money.poll() + money.poll();
        int result = 0;
        int count = 0;
        while(!money.isEmpty() && count < 2){
            result += money.poll();
            count++;
        }
        return result;
    }
    public static void main(String[] args) {
       System.out.println(getNoMoreThanTwo(new int[]{1,2,4,2,5,7,2,4,9,0}));
//        PriorityQueue<Integer> money = new PriorityQueue<>( (a,b) -> (b-a));
//        money.add(1);
//        money.add(3);
//        money.add(4);
        //System.out.println(money.poll() + money.poll());

    }
}
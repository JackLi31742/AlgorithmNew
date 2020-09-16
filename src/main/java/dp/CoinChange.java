package dp;

import java.util.Arrays;

public class CoinChange {
	
	public static void main(String[] args) {
		int[]coins= {1, 2, 5};
		
		System.out.println(coinChange(coins, 11));;
	}

	/**
	 * 322. 零钱兑换
	 * 给定不同面额的硬币 coins 和一个总金额 amount。
	 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
	 * 如果没有任何一种硬币组合能组成总金额，返回 -1。

		说明:
		你可以认为每种硬币的数量是无限的。
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChange(int[] coins, int amount) {
		int count=0;
		
		Arrays.sort(coins);
		
		
		coinChange(coins, amount, count, coins.length-1);
		return min;
    }
	static int min=Integer.MAX_VALUE;
	public static int coinChange(int[] coins, int amount,int count,int k) {
		for (int i = k;  i >=0 ; i--) {
			//商
			int quotient=amount/coins[i];
			//余数
			int remainder=amount%coins[i];
			count+=quotient;
			if (remainder==0) {
				if (count<min) {
					min=count;
				}
				return min;
			}else {
				coinChange(coins, remainder, count, k-1);
			}
		}
		return min;
	}
}

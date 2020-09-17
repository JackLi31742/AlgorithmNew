package dp;

import java.util.Arrays;

public class CoinChange {
	
	public static void main(String[] args) {
		int[]coins= {186,419,83,408};
		
		System.out.println(coinChange2(coins, 6249));;
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
	static int min=Integer.MAX_VALUE;
	public static int coinChange(int[] coins, int amount) {
		
		Arrays.sort(coins);
		
		coinChange(coins, amount, 0, coins.length-1);
		if (min==Integer.MAX_VALUE) {
			return -1;
		}
		return min;
    }
	public static void coinChange(int[] coins, int amount,int count,int k) {
		int temp=amount;
		//商
		int quotient=temp/coins[k];
		
		//每次商需要减一操作，而且每个组成的硬币的种类如果找不到也要减一
		for (int i = k;  i >=0 ; i--) {
			System.out.println(temp+","+count+","+k+","+i);
			//商
			quotient=temp/coins[i];
			//余数
			int remainder=temp%coins[i];
			count+=quotient;
			System.out.println(quotient+","+remainder+","+count);
			if (remainder==0) {
				if (count<min) {
					min=count;
				}
				break;
				
			}else {
				
				temp=remainder;
			}
		}
		System.out.println("--------------------------");
		if (k>=0) {
			
			coinChange(coins, amount, 0, k-1);
		}
		
	}
	
	/**
	 * 使用dp
	 * F(i)=F(i-ci)+1
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChange2(int[] coins, int amount) {
		
		int max=amount+1;
		int[]dp=new int[max];
		//为了最后好比较，返回-1的时候
		Arrays.fill(dp, max);
		dp[0]=0;
		
		for (int i = 1; i < dp.length; i++) {
			int min=max;
			for (int j = 0; j < coins.length; j++) {
				if (i>=coins[j]) {
					
					int count=dp[i-coins[j]]+1;
					if (count<min) {
						min=count;
					}
				}
			}
			
			dp[i]=min;
		}
		
		return dp[amount]>amount?-1:dp[amount];
	}
	
}

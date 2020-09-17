package dp;

public class Stock {

	public static void main(String[] args) {
		int[]prices= {7,1,5,3,6,4};
		
		System.out.println(maxProfit2(prices));;
	}
	/**
	 * 121. 买卖股票的最佳时机
	 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int max=0;
		for (int i = 0; i < prices.length; i++) {
			for (int j = i+1; j < prices.length; j++) {
				int profit=prices[j]-prices[i];
				if (profit>max) {
					max=profit;
				}
			}
		}
		return max;
    }
	/**
	 * dp
	 * 暴力破解是以i天作为买入的时间，dp是以i天作为卖出的时间，从0到i
	 * f(i)=Max{f(i-1),p[i]-minPrice}
	 * @param prices
	 * @return
	 */
	public int maxProfit12(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
		int[]dp=new int[prices.length+1];
		dp[0]=0;
		int min=prices[0];
		for (int i = 1; i < dp.length; i++) {
			min=Math.min(min, prices[i-1]);
			dp[i]=Math.max(dp[i-1], prices[i-1]-min);
		}
		
		return dp[prices.length];
	}
	
	/**
	 * 122. 买卖股票的最佳时机 II
	 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

	注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

		f(i,j)代表i天到j天的最大利润
		f(i,j)=f(i,x)+f(x+1,j)
	 * @param prices
	 * @return
	 */
	public static int maxProfit2(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
		int len=prices.length;
		int[][]dp=new int[len][len];
		//初始化
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp.length; j++) {
//				if (j<=i) {
//					dp[i][j]=0;
//				}
//			}
//		}
		
		for (int i = 0; i < len; i++) {
			//代表i天到j天的最小价格
			int minPrice=prices[i];
			int minIndex=i;
			//代表i天到j天的最大价格
//			int maxPrice=prices[i];
			for (int j = i+1; j < len; j++) {
//				if (j>i) {
					
				if (prices[j]<minPrice) {
					minPrice=prices[j];
					minIndex=j;
				}
//				if (prices[j]>maxPrice) {
//					
//					maxPrice=prices[j];
//				} 
//				}
				int max=0;
				int x=i;
				while(x<j) {
					int profit=dp[i][x]+dp[x+1][j];
					if (profit>max) {
						max=profit;
					}
					x++;
				}
				
				dp[i][j]=max;
			}
		}
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}
		return dp[0][len-1];
    }
}

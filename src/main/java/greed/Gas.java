package greed;

public class Gas {

	
	public static void main(String[] args) {
		int[] gas= {1,2,3,4,5};
		
		int[] cost= {3,4,5,1,2};
		
		System.out.println(canCompleteCircuit(gas, cost));;
	}
	/**
	 * 134. 加油站
	 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		
		int len=cost.length;
		
		for (int start = 0; start < len; start++) {
			
			int capacity=0;
			boolean flag=false;
			
			for (int i = start; i < 2*len; i++) {
				
				capacity+=gas[i%len]-cost[i%len];
				
				if (capacity<0) {
					flag=true;
					break;
				}
			}
			
			if (!flag) {
				return start;
			}
		}
		return -1;
    }
}

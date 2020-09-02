package recursion;

public class Fibonacci {

	public static void main(String[] args) {
		int n=46;
//		System.out.println(fib(n));
		System.out.println(fib2(n));;
		
		System.out.println(numWays(n));
	}
	
	/**
	 * 剑指 Offer 10- I. 斐波那契数列
	 *509. 斐波那契数
	 */
	public static int fib(int n) {
		int result=0;
		if (n==0) {
			result=0;
		}
		if (n==1) {
			result=1;
		}
		if (n>=2) {
			result= fib(n-1)+fib(n-2);
		}
		
		return result;
    }
	
	
	/**非递归
	 * 自底向上进行迭代
	 * 若 N == 2，则返回 fib(2-1) + fib(2-2) = 1。
		使用迭代的方法，我们至少需要三个变量存储 fib(N), fib(N-1) 和 fib(N-2)。

	 * @param n
	 * @return
	 */
	public static int fib2(int n) {
		if (n<=1) {
			return n;
		}
		int first=0;
		int second=1;
		int third=0;
		while(n>=2) {
			third=first+second;
			first=second;
			second=third;
			n--;
		}
		
		return third;
	}
	
	/**
	 * 
	 * result=numWays(n-1)+1+numWays(n-2)+2;（这是错的）
	 * 
	 * 通过归纳，跳上n级台阶的解法是最后只剩1个台阶和最后只剩2个台阶解法的和
	 * result=numWays(n-1)+numWays(n-2);
	 * 70. 爬楼梯
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
	 * 
	 * 可以根据第一步的走法把所有走法分为两类，第一类是第一步走了 1 个台阶，另一类是第一步走了 2 个台阶。
	 * 所以 n 个台阶的走法就等于先走 1 阶后，n-1 个台阶的走法 加上先走 2 阶后，n-2 个台阶的走法。
	 * 
	 * 递归终止条件就是 f(1)=1，f(2)=2
	 * 
	 * 到达n阶只可能来自n-1和n-2,所以f(n)=f(n-1)+f(n-2)
	 */
	
	public static int numWays(int n) {
		
		if (n<=1) {
			return 1;
		}
		long first=1;
		long second=1;
		long third=0;
		while(n>=2) {
			third=first+second;
			first=second;
			second=third;
			n--;
		}
		
		return Long.valueOf(third).intValue();
    }
	
	/**
	 * 剑指 Offer 10- II. 青蛙跳台阶问题
	 * @param n
	 * @return
	 */
	public int numWays2(int n) {
        if (n<=1) {
			return 1;
		}
		long first=1;
		long second=1;
		long third=0;
		while(n>=2) {
			third=Double.valueOf((first+second)%(Math.pow(10, 9)+7)).longValue();
			first=second;
			second=third;
			n--;
		}
		
		return Long.valueOf(third).intValue();
    }
}

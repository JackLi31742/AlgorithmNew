package recursion;

import javafx.geometry.HorizontalDirection;

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
	
	/**
	 * 1 个细胞的生命周期是 3 小时，1 小时分裂一次。求 n 小时后，容器内有多少细胞？
	 * 假设细胞到了第三个小时是先分裂完再死亡
	 */
	public int num(int hour) {
		if (hour==0) {
			return 1;
		}
		if (hour==1) {
			return 2;
		}
		if (hour==2) {
			return 4;
		}
//		if (hour>=3) {
			return 2*num(hour-1)-num(hour-3);
//		}
	}
	
	/**
	 * 实现n!
	 */
	
	public int getN(int n) {
		if (n<=1) {
			return 1;
		}
		return n*getN(n-1);
	}
	
	public int getN2(int n) {
		if (n<=1) {
			return 1;
		}
		int result=1;
		for (int i = 2; i <= n; i++) {
			result=result*i;
		}
		return result;
	}
	
	/**
	 * 剑指 Offer 64. 求1+2+…+n
	 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。  
	 * 
	 * 利用逻辑运算符的短路性质。

		以逻辑运算符 & 为例，对于 A & B 这个表达式，
		如果 A 表达式返回 False ，那么 A & B 已经确定为 False ，此时不会去执行表达式 B。
		同理，对于逻辑运算符 ||， 对于 A || B 这个表达式，如果 A 表达式返回True ，
		那么 A || B 已经确定为 True ，此时不会去执行表达式 B。
		
		利用这一特性，我们可以将判断是否为递归的出口看作 A & B 表达式中的 A 部分，
		递归的主体函数看作 B 部分。如果不是递归出口，则返回 True，并继续执行表达式 B 的部分，否则递归结束。
	 * @param n
	 * @return
	 */
	public int sumNums(int n) {
		boolean flag=n>0&&(n+=sumNums(n-1))>0;
		return n;
    }
	
	public int sumNums2(int n) {
		boolean flag=n<=0||(n+=sumNums(n-1))<0;
		return n;
	}
}

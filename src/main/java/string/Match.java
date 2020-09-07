package string;

public class Match {

	public static void main(String[] args) {
		System.out.println(gcd(4,8));;
	}
	
	/**
	 * 28 暴力破解
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		
		if (needle.equals("")) {
			return 0;
		}
		int i=0;//遍历haystack
		int j=0;//遍历needle
		
		int hlen=haystack.length();
		int nlen=needle.length();
		
		while(i<hlen&&j<nlen){
			if (haystack.charAt(i)==needle.charAt(j)) {
				i++;
				j++;
			}else {
				i=i-j+1;
				j=0;
				
			}
			//i和j都再次加了一次
			if (j==nlen) {
				return i-j;
			}
		}
		
		return -1;
    }
	/**
	 * 1071. 字符串的最大公因子
	 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

		返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

		数学理论，如果str1+str2=str2+str1，那么最大公因子就是两个字符串长度的gcd，然后取前缀，否则就是""
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String gcdOfStrings(String str1, String str2) {
		if ((str1+str2).equals(str2+str1)) {
			int len=gcd(str1.length(),str2.length());
			return str1.substring(0, len);
		}else {
			return "";
		}
		
    }
	
	/**
	 * 辗转相除
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a,int b) {
		int temp=0;
		while(b>0) {
			temp=a%b;
			a=b;
			b=temp;
		}
		return a;
	}
}

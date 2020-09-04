package string;

public class Match {

	public static void main(String[] args) {
		String aString="";
		String bString="dfas";
		
		System.out.println(bString.indexOf(aString));
		
	}
	
	/**
	 * 28. 实现 strStr()
	 *  Java的 indexOf() 
	 *  用sunday算法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}
		
    }
	
	/**
	 * 1071. 字符串的最大公因子
	 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

		返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

	 * @param str1
	 * @param str2
	 * @return
	 */
	public String gcdOfStrings(String str1, String str2) {
		
    }
}

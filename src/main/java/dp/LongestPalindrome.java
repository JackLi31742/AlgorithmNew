package dp;

public class LongestPalindrome {
	
	public static void main(String[] args) {
		String string="babad";
		
		System.out.println(longestPalindrome3(string));;
		System.out.println(string.length());
	}
	
	/**
	 * 5. 最长回文子串
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {

		if (s==null||s.length()<2) {
			return s;
		}
//		String result="";
		int max=0;
		int begin=0;
		char[] arr = s.toCharArray();
		int len=arr.length;
		for (int i = 0; i < len; i++) {
//			for (int j = i+1; j <= s.length(); j++) {
			//由于求最长，所以，从最长的开始检测，而且由于不是substring，所以要注意边界
			for (int j = len-1; j >= i; j--) {
				//先判断是不是比他大，因为这个判断比较快，如果小的话，就没有必要检查是否是回文串了
				if ((j-i+1)>max&&checkPalindrome(arr,i,j)) {
					max=j-i+1;
					//不用每次都求出结果，可以最后一次拿到
//					result=String.valueOf(arr, i, max);
					begin=i;
					
				}
			}
		}
//		System.out.println(max);
		return String.valueOf(arr, begin, max);
    }
	
	public static boolean checkPalindrome(String s) {
		int left=0;
		int right=s.length()-1;
		while(left<right) {
			if (s.charAt(left)!=s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	//substring和charAt每次都会进行边界检查
	public static boolean checkPalindrome(char[] arr,int left,int right) {
		while(left<right) {
			if (arr[left]!=arr[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	/**
	 * dp[i][j]从i到j是否是回文串
	 * dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j)
	 * @param s
	 * @return
	 */
	public static String longestPalindrome2(String s) {
		
		if (s==null||s.length()<2) {
			return s;
		}
		int len=s.length();
		boolean[][] dp=new boolean[len][len];
		
		for (int i = 0; i < len; i++) {
			dp[i][i]=true;
		}
		
		//为了得到dp[i+1][j-1]，i需要先计算后边的
		for (int i = len-2;i>=0;i--) {
			for (int j = 1; j < len; j++) {
				//如果一个字符串的头尾两个字符相等，才有必要继续判断下去
				dp[i][j]=s.charAt(i)==s.charAt(j)&&dp[i+1][j-1];
				dp[j][i]=dp[j+1][i-1]&&s.charAt(j)==s.charAt(i);
			}
		}
		
	}
	
	
	
	/**
	 * 516. 最长回文子序列
	 */
	public int longestPalindromeSubseq(String s) {

    }
}

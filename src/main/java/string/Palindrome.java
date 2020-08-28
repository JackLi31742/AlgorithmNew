package string;

public class Palindrome {

	public static void main(String[] args) {
		
		String string=" ";
		
		System.out.println(isPalindrome(string));;
	}

	/**
	 * 125. 验证回文串
	 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

	说明：本题中，我们将空字符串定义为有效的回文串。
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if (s==null) {
			return false;
		}
		if (s.equals("")) {
			return true;
		}
		s=s.toLowerCase();
		int left=0;
		int right=s.length()-1;
		
		while(left<=right) {
			//不能用if，因为可能连续好几个字符都不是字母和数字
			//同时由于left和right都在做加减操作，所以必须要小心是否越界
			while (left<=right&&!check(s.charAt(left))) {
				left++;
			}
			while (left<=right&&!check(s.charAt(right))) {
				right--;
			}
			if (left<=right&&s.charAt(left)!=s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
    }
	
	/**
	 * 判断字符是不是字母或者数字
	 * @param ch
	 * @return
	 */
	public static boolean check(char ch) {
		if (Character.isDigit(ch)||Character.isLetter(ch)) {
			return true;
		}
		return false;
	}
}

package string;

public class Match {

	public static void main(String[] args) {
//		System.out.println(gcd(4,8));;
		
		String hayString="aaaaaaaaaaaaaaaaaaa" ;
		String llString="aaaaaaaaaaaa";
		
		
//		String hayString="hello";
//		String llString="ll";
		System.out.println(hayString.indexOf(llString));
//		System.out.println((int)'a');
//		System.out.println((int)'b');
//		System.out.println((int)'c');
//		System.out.println((int)'o');
		System.out.println(strStr4(hayString, llString));
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
	 * 另一种暴力破解
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr2(String haystack, String needle) {
		if (haystack==null||needle==null) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int hlen=haystack.length();
		int nlen=needle.length();
		
		//可以有等于号，或者+1
		for (int i = 0; i < hlen-nlen+1; i++) {
			//每次都得重新设置
			boolean notEqual=false;

			for (int j = 0; j < nlen; j++) {
				//随着j增加，i+j也在增加
				if (haystack.charAt(i+j)!=needle.charAt(j)) {
					notEqual=true;
					break;
				}
			}
			//走完一次，如果都相等
			if (!notEqual) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * RK算法，将字符串变成hashcode
	 * 
	 * hash函数可以将字符串转换为一个整数，则hash结果不同的字符串肯定不同，但hash结果相同的字符串则很有可能相同
	 * @param haystack
	 * @param needle
	 * @return
	 */
	
	private static final int HASHSIZE=Integer.MAX_VALUE;
	private static final int PRIME=31;
	
	public static int strStr3(String haystack, String needle) {
		
		int hLen=haystack.length();
		int nLen=needle.length();
		
		char[] hArr=haystack.toCharArray();
		char[] nArr = needle.toCharArray();
		
		int nHash=0;
		
		for (int i = 0; i < nLen; i++) {
			nHash+=nArr[i]*PRIME%HASHSIZE;
		}
		
		for (int i = 0; i < hLen-nLen+1; i++) {
			int hHash=0;
			for (int j = i; j < i+nLen; j++) {
				hHash+=hArr[j]*PRIME%HASHSIZE;
			}
			
			if (hHash==nHash&&compare(hArr, nArr, i, 0)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	public static int strStr4(String haystack, String needle) {
		
		if (haystack==null||needle==null||haystack.length()<needle.length()) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int hLen=haystack.length();
		int nLen=needle.length();
		
		char[] hArr=haystack.toCharArray();
		char[] nArr = needle.toCharArray();
		
		int nHash=0;
		int hHash=0;
		for (int i = 0; i < nLen; i++) {
			nHash=(nHash*PRIME+nArr[i])%HASHSIZE;
			hHash=(hHash*PRIME+hArr[i])%HASHSIZE;
			
		}
		
//		System.out.println(nHash);
//		System.out.println(hHash);
		
//		if (nHash<0) {
//			nHash=(nHash%HASHSIZE+HASHSIZE)%HASHSIZE;
//		}
//		if (hHash<0) {
//			hHash=(hHash%HASHSIZE+HASHSIZE)%HASHSIZE;
//		}
		
		if (hHash==nHash&&compare(hArr, nArr, 0, 0)) {
			return 0;
		}
		//计算Math.pow(PRIME, nLen)
		int base=1;
		
		for (int i = 0; i < nLen; i++) {
			base=(base*PRIME)%HASHSIZE;
		}
		
		for (int i = 1; i < hLen-nLen+1; i++) {
//			System.out.println(hArr[i+nLen-1]+","+hArr[i-1]);
			hHash=(hHash*PRIME+hArr[i+nLen-1]-hArr[i-1]*base)%HASHSIZE;
//			System.out.println(hHash);
//			if (hHash<0) {
//				hHash=(hHash%HASHSIZE+HASHSIZE)%HASHSIZE;
//			}
			if (hHash==nHash&&compare(hArr, nArr, i, 0)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	
	public static boolean compare(char[] hArr,char[] nArr,int i,int j) {
		
		while(i<hArr.length&&j<nArr.length) {
			if (hArr[i]!=nArr[j]) {
				return false;
			}
			i++;
			j++;
		}
		return true;
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

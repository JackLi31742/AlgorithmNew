package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Match {

	public static void main(String[] args) {
//		System.out.println(gcd(4,8));;
		
		String hayString="cbaebabacd";
		String llString="abc";
		
		
//		String hayString="hello";
//		String llString="ll";
//		System.out.println(hayString.indexOf(llString));
//		System.out.println((int)'a');
//		System.out.println((int)'b');
//		System.out.println((int)'c');
//		System.out.println((int)'o');
//		System.out.println(strStr4(hayString, llString));
		findAnagrams(hayString, llString);
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
	
	/**
	 * 242. 有效的字母异位词
	 * 字母异位词指字母相同，但排列不同的字符串。
	 * HASH
	 * 计算两个字符串中每个字母的出现次数并进行比较。
	 * 用一个计数器表计算 s 字母的频率，用 t减少计数器表中的每个字母的计数器，然后检查计数器是否回到零。
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {
		if (s==null||t==null||s.length()!=t.length()) {
			return false;
		}
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		
		for (int i = 0; i < sArr.length; i++) {
			
			map.put(sArr[i], map.getOrDefault(sArr[i], 0)+1);
		}
		
		for (int i = 0; i < tArr.length; i++) {
			if (!map.containsKey(tArr[i])) {
				return false;
			}
			
			map.put(tArr[i], map.get(tArr[i])-1);
		}
		
		Set<Entry<Character,Integer>> entrySet = map.entrySet();
		
		for (Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue()>0) {
				return false;
			}
		}
		
		return true;
    }
	
	/**
	 * 438. 找到字符串中所有字母异位词
	给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
	
	字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
	
	说明：
	
	
	不考虑答案输出的顺序。

	 * @param s
	 * @param p
	 * @return
	 */
	public static List<Integer> findAnagrams(String s, String p) {

		List<Integer> result =new ArrayList<Integer>();
		if (s==null||p==null||s.length()<p.length()) {
			return result;
		}
		
		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		
		HashMap<Character, Integer> pMap=new HashMap<Character, Integer>();
		HashMap<Character, Integer> sMap=new HashMap<Character, Integer>();
		
		for (int i = 0; i < pArr.length; i++) {
			
			pMap.put(pArr[i], pMap.getOrDefault(pArr[i], 0)+1);
			sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0)+1);
		}
		
		if (sMap.equals(pMap)) {
			result.add(0);
		}
		
		for (int i = 1; i < sArr.length-pArr.length+1; i++) {
			
			if (sMap.get(sArr[i-1])==1) {
				sMap.remove(sArr[i-1]);
			}else {
				sMap.put(sArr[i-1], sMap.get(sArr[i-1])-1);
			}
			sMap.put(sArr[i+pArr.length-1], sMap.getOrDefault(sArr[i+pArr.length-1], 0)+1);
			
			if (sMap.equals(pMap)) {
				result.add(i);
			}
		}
		
		return result;
    }
	
	
}

package string;

import java.util.HashMap;
import java.util.Map;

public class Sunday {

	public static void main(String[] args) {
		String aString="ll";
		String bString="hello";
		
//		char[] arr=aString.toCharArray();
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//			if (arr[i]=='人') {
//				System.out.println(true);
//			}
//		}
		System.out.println(bString.indexOf(aString));
		System.out.println(strStr(bString, aString));
		
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		
		getLastIndex("abcb", map);
		System.out.println(map);
	}
	
	/**
	 * 28. 实现 strStr()
	 *  Java的 indexOf() 
	 *  用sunday算法
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
		if (haystack==null||needle==null) {
			return -1;
		}
		if (needle.equals("")) {
			return 0;
		}
		
		int i=0,j=0;
		int len1=haystack.length();
		int len2=needle.length();
		
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		getLastIndex(needle, map);
		
		while(i<=(len1-len2)&&j<len2) {
			if (haystack.charAt(i+j)==needle.charAt(j)) {
				j++;
				//j走完最后一个匹配的会再加一次，退出循环，所以是等于len2
				if (j==len2) {
					return i;
				}
			}else {
//				System.out.println(map.get(haystack.charAt(i+len2)));
				if (i+len2>=len1) {
					return -1;
				}
				if (map.get(haystack.charAt(i+len2))==null) {
					i=i+len2+1;
				}else {

					i=i+len2-map.get(haystack.charAt(i+len2));

					
				}
				j=0;
			}
			
		}
		
		
		return -1;
	
		
    }
	/**
	 * 预处理needle，得到每个字符最后出现的位置
	 * @param needle
	 * @param map
	 */
	public static void getLastIndex(String needle,Map<Character, Integer> map) {
		for (int i = 0; i < needle.length(); i++) {
			//map会自动覆盖相同的key
			map.put(needle.charAt(i), i);
		}
		
	}
}

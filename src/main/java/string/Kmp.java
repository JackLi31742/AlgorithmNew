package string;

import java.util.Arrays;

/**
 * 28. 实现 strStr()
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 当 needle 是空字符串时我们应当返回 0 
 *
 */
public class Kmp {

	public static void main(String[] args) {
		String s1="aaaaa";
		String s2="bba";
//		System.out.println(s1.indexOf(s2));
//		System.out.println(strStr2(s1, s2));
		
		;
		System.out.println(Arrays.toString(pmt2("bba".toCharArray())));
	}
	

	
	/**
	 * kmp
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr2(String haystack, String needle) {
		
		if (needle.equals("")) {
			return 0;
		}
		//转成数组比用.charAt快
		return strStr2(haystack.toCharArray(), needle.toCharArray());
    }
	
	public static  int strStr2(char[] haystack, char[] needle) {
		
		
		int i=0;//遍历haystack
		int j=0;//遍历needle
		
		int hlen=haystack.length;
		int nlen=needle.length;
		
		int[]pmt=pmt2(needle);
		while(i<hlen&&j<nlen){
			if (haystack[i]==needle[j]) {
				i++;
				j++;
			}
			//由于做了i++，j++，必须要再次判断
			if (i<hlen&&j<nlen&&haystack[i]!=needle[j]) {
				if (j>0) {//说明模式串需要回溯
					j=pmt[j-1];
				}else {//第一个字符就不匹配，i继续向前走
					i++;
				}
			}
			//i和j都再次加了一次
			if (j==nlen) {
				return i-j;
			}
		}
		
		return -1;
    }
	/**
	 * 部分匹配表
	 * @param needle
	 * @return
	 */
	public static int[] pmt(char[] needle){
		int nlen=needle.length;
		int[]pmt=new int[nlen];
		pmt[0]=0;
		int i=0;//i在前缀
		int j=1;//j在后缀
		while(i<nlen&&j<nlen){
			if (needle[i] != needle[j]) {
//				System.out.println("i="+i+","+needle.charAt(i)+",j="+j+","+needle.charAt(j));
				if (i > 0) {
					i = pmt[i - 1];
				} else {
					//说明目前的没有匹配到，匹配下一个
					j++;
				}
			}
			if (i<nlen&&j<nlen&&needle[i]==needle[j]) {
//				System.out.println("i="+i+",pmt[j-1]="+pmt[j-1]+","+needle.charAt(i)+",j="+j+","+needle.charAt(j));
				//pmt[j-1]=i，i回退之后，就不相等了
				pmt[j]=i+1;
				i++;
				j++;
			}
//			pmt[j]=i;
		}
		return pmt;
	}
	
	public static int[] pmt2(char[] needle){
		int nlen=needle.length;
		int[]pmt=new int[nlen];
		pmt[0]=0;
		int i=0;//i在前缀
		int j=1;//j在后缀
		while(i<nlen&&j<nlen){
			if(i>0&&needle[i]!=needle[j]){
				i=pmt[i-1];
			}else if (i<nlen&&j<nlen&&needle[i]==needle[j]) {
				i++;
				pmt[j]=i;
				j++;
			}else {
				//继续下一个
				j++;
			}
		}
		
		return pmt;
	}
	
	
	
	//获取到一个字符串(子串) 的部分匹配值表
		public static  int[] kmpNext(String dest) {
			//创建一个next 数组保存部分匹配值
			int[] next = new int[dest.length()];
			next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
			for(int i = 1, j = 0; i < dest.length(); i++) {
				//当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
				//直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
				//这时kmp算法的核心点
				while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
					System.out.println("i="+i+",pmt[j-1]="+next[j-1]+","+dest.charAt(i)+",j="+j+","+dest.charAt(j));
					j = next[j-1];
				}
				
				//当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
				if(dest.charAt(i) == dest.charAt(j)) {
					System.out.println("i="+i+","+dest.charAt(i)+",j="+j+","+dest.charAt(j));
					
					j++;
				}
				next[i] = j;
			}
			return next;
		}
}

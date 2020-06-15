package tree;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string="i like like like java do you like a java";
		
		System.out.println(getNodes(string));
		
	}
	
	//通过原始的字符串得到字符串中每个字符出现的次数
	public static HashMap<Character, Integer> getNodes(String s) {
		HashMap<Character, Integer> map=new HashMap<Character, Integer>();
		
		for (int i = 0; i < s.length(); i++) {
			char ch=s.charAt(i);
			//map初始的value是null
			if (map.get(ch)==null) {
				
				map.put(ch, 1);
			}else {
				map.put(ch, map.get(ch)+1);
			}
		}
		
		return map;
	}

}

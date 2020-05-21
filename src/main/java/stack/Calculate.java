package stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str="3+20*2";
		char[]arr=str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String group = matcher.group();
			System.out.println("start:"+start+",end:"+end+",group:"+group);
		}
		
	}

}

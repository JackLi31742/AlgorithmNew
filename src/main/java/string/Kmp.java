package string;

public class Kmp {

	public static void main(String[] args) {
		String s1="硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String s2="尚硅谷你尚硅你";
		System.out.println(s1.indexOf(s2));
	}
}

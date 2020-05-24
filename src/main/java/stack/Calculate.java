package stack;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 227. 基本计算器 II
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
        字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 */
public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str="42";
		Calculate calculate=new Calculate();
		System.out.println(calculate.calculate2(str));;
		
	}
	/**
	 * @param str
	 */
	public int calculate(String s) {
		
        if(s == null || s.trim().length() == 0) {
            return 0;
        }
        
		//由于在下边判断数字入栈时，使用到了i==arr.length-1，所以必须要把字符串本身的两段的空格去除
		char[] arr=s.trim().toCharArray();
		
		Stack<Integer> numStack=new Stack<>();
		Stack<Character> operStack=new Stack<>();

		int num=0;
		for (int i = 0; i < arr.length; i++) {
			
			char ch=arr[i];
			//在字符串中间还有可能有空格，也要去除
			if (ch!=' ') {
				if (Character.isDigit(ch)) {
					//如果连着的字符是数字
					num=num*10+ch-'0';
					//如果走到最后，肯定是数字，直接入栈
					if (i==arr.length-1) {
						numStack.push(num);
					}
				}else {
					numStack.push(num);
					num=0;
					
					if (operStack.isEmpty()) {
						operStack.push(ch);
					}else {
						//两个优先级相同，要先计算之前的，不能push进operStack中A
//						if (priority(ch)>priority(operStack.peek())) {
//							operStack.push(ch);
//						}else {
//							num1=numStack.pop();
//							num2=numStack.pop();
//							oper=operStack.pop();
//							result=calculate(num1,num2,oper);
//							numStack.push(result);
//							operStack.push(ch);
//						}
						
						while(priority(ch)<=priority(operStack.peek())){
							calculate(numStack,operStack);
							//可能operStack.pop()完之后，栈是空的
							if (operStack.isEmpty()) {
								break;
							}
						}
						operStack.push(ch);
					}
				}
			}
		
		}
		
		while(!operStack.isEmpty()){
			calculate(numStack,operStack);
		}
		//如果输入的字符串只有"1"，如果用result，就会返回错误的0，因为没有经过计算，经过计算后，result就等于numStack.pop()
		return numStack.pop();
    }
	
	/**
	 * 上边的有bug，如果表达式里有(，会在数栈中push很多num=0的值
	 * @param s
	 * @return
	 */
	public int calculate2(String s) {
		if(s == null || s.trim().length() == 0) {
            return 0;
        }
		
		char[] arr=s.toCharArray();
		
		Stack<Integer> numStack=new Stack<>();
		Stack<Character> operStack=new Stack<>();

		for (int i = 0; i < arr.length; ) {
			
			char ch=arr[i];
			//在字符串中间还有可能有空格，也要去除
			if (ch!=' ') {
				if (Character.isDigit(ch)) {
					//如果连着的字符是数字
					int num = 0;
					while (i < arr.length && Character.isDigit(arr[i])) {
										//小心别跟ch混了
						num = num * 10 + arr[i] - '0';
						i++;
					}
					numStack.push(num);
					
				}else {
					
					if (!operStack.isEmpty()) {
						//两个优先级相同，要先计算之前的，不能push进operStack中A
						while(!operStack.isEmpty()&&priority(ch)<=priority(operStack.peek())){
							calculate(numStack,operStack);
						}
					}
					operStack.push(ch);
					i++;
				}
			}else {
				//用来走指针，必须要放在else里，否则，上边if走完，还要走一个，指针就走多了
				i++;
			}
			
			
		
		}
		
		while(!operStack.isEmpty()){
			calculate(numStack,operStack);
		}
		//如果输入的字符串只有"1"，如果用result，就会返回错误的0，因为没有经过计算，经过计算后，result就等于numStack.pop()
		return numStack.pop();
	}
	
	/**
	 * 判断优先级
	 * @param ch
	 * @return
	 */
	public int priority(char ch){
		if (ch=='+'||ch=='-') {
			return 0;
		}else if (ch=='*'||ch=='/') {
			return 1;
		}else {
			return -1; // 假定目前的表达式只有 +, - , * , /
		}
	}
	
	
	public void calculate(Stack<Integer> numStack,Stack<Character> operStack){
		
		
		int num1=numStack.pop();
		int num2=numStack.pop();
		char oper=operStack.pop();
		int result=calculate(num1,num2,oper);
		numStack.push(result);
	}
	/**
	 * 具体计算
	 * @param num1
	 * @param num2
	 * @param oper
	 * @return
	 */
	public int calculate(int num1,int num2,char oper) {
		int result=0;
		switch (oper) {
		case '+':
			result=num2+num1;
			break;
		case '-':
			result=num2-num1;
			break;
		case '*':
			result=num2*num1;
			break;
		case '/':
			result=num2/num1;
			break;
		default:
			break;
		}
		return result;
	}
	
	/**
	 * 用正则表达式截取数字
	 * @param str
	 */
	public static void isDigit(String str){
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

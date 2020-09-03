package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 不能用Character，应该用Byte
 *
 */
public class HuffmanCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string="i like like like java do you like a java";
//		System.out.println(getNodes(string));
		Map<Byte, String> codeMap=getCodeMap(getHuffmanTree(getCounts(string.getBytes())));
		byte[] result=zip(string.getBytes(),codeMap);
		System.out.println(Arrays.toString(result));
		
		Map<String, Byte> decodeMap=getDecodeMap(codeMap);
		
		;
		
		
		System.out.println(new String(unzip(result, decodeMap)));
//		decode(result);
//		System.out.println(decode(result));
//		System.out.println(Integer.toBinaryString(28|256));
//		int temp=28;
//		temp|=255;
//		System.out.println(Integer.toBinaryString(temp));
//		System.out.println(Integer.toBinaryString(-28));
		
//		System.out.println((byte)Integer.parseInt("011100", 2));
		
//		byte[] huffmanCodes=new byte[5];
		
//		System.out.println(Arrays.toString(huffmanCodes));
//		
//		int temp=-28|256;
//		String str = Integer.toBinaryString(temp); 
//		//取最后8位
//		System.out.println(str.substring(str.length() - 8));
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
		
//		System.out.println(Byte.valueOf(String.valueOf(168)));
		
	}
	
	//1、通过原始的字符串得到字符串中每个字符出现的次数
	public static HashMap<Byte, Integer> getCounts(byte[] bytes) {
		HashMap<Byte, Integer> map=new HashMap<Byte, Integer>();
		
		for (int i = 0; i < bytes.length; i++) {
			byte ch=bytes[i];
			//map初始的value是null
			if (map.get(ch)==null) {
				
				map.put(ch, 1);
			}else {
				map.put(ch, map.get(ch)+1);
			}
		}
		
		return map;
	}

	//2、通过字符出现次数，建立哈夫曼树
	public static HuffmanNode getHuffmanTree(HashMap<Byte, Integer> map) {
		List<HuffmanNode> list=new ArrayList<HuffmanNode>();
		for (Entry<Byte, Integer> item : map.entrySet()) {
			HuffmanNode node=new HuffmanNode(item.getKey(), item.getValue());
			list.add(node);
		}
		
		Comparator<HuffmanNode> comparator=new Comparator<HuffmanNode>() {

			@Override
			public int compare(HuffmanNode o1, HuffmanNode o2) {
				// TODO Auto-generated method stub
				return o1.weight-o2.weight;
			}
		};
		
		while(list.size()>1){
			Collections.sort(list, comparator);
			HuffmanNode left=list.get(0);
			HuffmanNode right=list.get(1);
			//为了让哈夫曼树更有意义，父节点不设data的值
			HuffmanNode parent =new HuffmanNode(null, left.weight+right.weight);
			parent.left=left;
			parent.right=right;
			list.remove(0);
			list.remove(0);
			list.add(parent);
		}
		
		return list.get(0);
		
	}
	
	//3、将哈夫曼树转换为哈夫曼编码
	//左边是0，右边是1
	public static Map<Byte, String> getCodeMap(HuffmanNode root){
		if (root==null) {
			return null;
		}
		Map<Byte, String> codes=new HashMap<Byte, String>();
		StringBuilder sb=new StringBuilder();
		getCodeMap(root.left, "0", sb, codes);
		getCodeMap(root.right, "1", sb, codes);
		return codes;
	}
	
	
	public static void getCodeMap(HuffmanNode node,String code,StringBuilder sb,Map<Byte, String> codes){
		
		//需要重新建立stringbuilder，否则所有的sb就都append到一起了
		StringBuilder sb2=new StringBuilder(sb);
		
		if (node!=null) {
			sb2.append(code);
			if (node.data==null) {//说明是非叶子节点，继续递归
				getCodeMap(node.left, "0", sb2, codes);
				getCodeMap(node.right, "1", sb2, codes);
			}else {//说明到了叶子节点，放到map里
				codes.put(node.data, sb2.toString());
			}
		}
	}
	
	
	//4、压缩，把得到的sb转换成byte[]
	//为了最后的能保存用于解码时验证，所以将byte数组改成int数组
	public static byte[] zip(byte[] bytes,Map<Byte, String> map) {
		StringBuilder sb =new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			byte ch=bytes[i];
			sb.append(map.get(ch));
		}
		
		//sb.toString()就是得到的最后编码后的字符串，
		//但是按照每8位（有符号数）转换成对应的byte，因为byte是8位，而int是32位
		//即sb.toString()是补码
//		System.out.println(sb.toString());
		int len=0;
		int bit=8;
		if (sb.length()%bit==0) {
			len=sb.length()/bit;
		}else {
			len=sb.length()/bit+1;
		}
		
		byte[] huffmanCodes=new byte[len+1];
//		huffmanCodes[0]=sb.length();
		int index=0;
		for (int i = 0; i < sb.length(); i+=bit) {
			
			if (i+bit>sb.length()) {
				//截取到最后不足8位，在高位补0
				//不用Byte.parseByte是因为会有数组越界，但本质也是用(byte)Integer.parseInt
//				System.out.println(sb.substring(i));
//				huffmanByte[index]=Byte.parseByte(sb.substring(i),2);
				huffmanCodes[index]=(byte)Integer.parseInt(sb.substring(i),2);
				//数组最后一个位置保存最后不够8位的长度是多少
				System.out.println("sb.substring(i):"+sb.substring(i));
				huffmanCodes[len]=(byte) sb.substring(i).length();
//				huffmanCodes[index]=Integer.parseInt(sb.substring(i),2);
			}else {
//				huffmanByte[index]=Byte.parseByte(sb.substring(i,i+8),2);
				huffmanCodes[index]=(byte)Integer.parseInt(sb.substring(i,i+bit),2);
//				huffmanCodes[index]=Integer.parseInt(sb.substring(i,i+32),2);
			}
			index++;
		}
		
		return huffmanCodes;
	}
	
	/**
	 * 
	   *     解码
	 *   1、将byte[]转换成原始字符串
	 * 	注意：正数最后显示的不是8位bit，会把高位的0舍去
	 */
	
	public static String decode(byte[] huffmanCodes) {
		
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < huffmanCodes.length-1; i++) {
			//按位或，正数会把高位补齐，负数不变
			int temp=huffmanCodes[i]|256;
			String str = Integer.toBinaryString(temp); 
			//huffmanCodes的倒数第二位保存的就是压缩时最后的一个byte，最后一位保存的是长度
			if (i==huffmanCodes.length-2&&huffmanCodes[huffmanCodes.length-1]>0) {
				System.out.println("str.length():"+str.length());
				System.out.println("huffmanCodes[huffmanCodes.length-1]:"+huffmanCodes[huffmanCodes.length-1]);
				sb.append(str.substring(str.length() - huffmanCodes[huffmanCodes.length-1]));
			}else {
				//取最后8位
				sb.append(str.substring(str.length() - 8));
					
			}
				
		}
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	//2、将之前Map<Character, String> map的key和value调转
	
	public static Map<String, Byte> getDecodeMap(Map<Byte, String> map){
		Map<String, Byte> decodeMap=new HashMap<String, Byte>();
		
		Set<Entry<Byte,String>> entrySet = map.entrySet();
		
		for (Entry<Byte, String> entry : entrySet) {
			decodeMap.put(entry.getValue(),entry.getKey());
		}
		
		return decodeMap;
	}
	
	//3、解码
	public static byte[] unzip(byte[] huffmanCodes,Map<String, Byte> decodeMap) {
		
		String str=decode(huffmanCodes);
		
		StringBuilder result=new StringBuilder();
		List<Byte> list=new ArrayList<Byte>();
		for (int i = 0; i < str.length(); ) {
			int j=1;
			boolean flag=true;
			Byte character=null;
			while(flag&&(i+j)<=str.length()) {
				String key=str.substring(i, i+j);
				character=decodeMap.get(key);
				if (character==null) {
					j++;
				}else {
					flag=false;
				}
			}
//			result.append(character);
			list.add(character);
			i+=j;
		}
		
		byte[] bytes=new byte[list.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i]=list.get(i);
		}
		
		return bytes;
		
	}
	
	
	//压缩文件
}

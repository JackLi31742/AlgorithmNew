package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HuffmanCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string="i like like like java do you like a java";
		
		System.out.println(getCodes(getHuffmanTree(getNodes(string))));
		
	}
	
	//1、通过原始的字符串得到字符串中每个字符出现的次数
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

	//2、通过字符出现次数，建立哈夫曼树
	public static HuffmanNode getHuffmanTree(HashMap<Character, Integer> map) {
		List<HuffmanNode> list=new ArrayList<HuffmanNode>();
		for (Entry<Character, Integer> item : map.entrySet()) {
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
	public static Map<Character, String> getCodes(HuffmanNode root){
		if (root==null) {
			return null;
		}
		Map<Character, String> codes=new HashMap<Character, String>();
		StringBuilder sb=new StringBuilder();
		getCodes(root.left, "0", sb, codes);
		getCodes(root.right, "1", sb, codes);
		return codes;
	}
	
	
	public static void getCodes(HuffmanNode node,String code,StringBuilder sb,Map<Character, String> codes){
		
		//需要重新建立stringbuilder，否则所有的sb就都append到一起了
		StringBuilder sb2=new StringBuilder(sb);
		
		if (node!=null) {
			sb2.append(code);
			if (node.data==null) {//说明是非叶子节点，继续递归
				getCodes(node.left, "0", sb2, codes);
				getCodes(node.right, "1", sb2, codes);
			}else {//说明到了叶子节点，放到map里
				codes.put(node.data, sb2.toString());
			}
		}
	}
	
}

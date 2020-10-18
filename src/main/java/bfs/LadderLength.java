package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 简单图最短路径
 	没有层次，因为没有绝对的前后，所以不能用dp
 *
 */
public class LadderLength {

	
	public static void main(String[] args) {
		
//		System.out.println(5%0);
		
		String start ="hit";
		
		String end = "cog";
		
		String[] arr= {"hot","dot","dog","lot","log"};
		Set<String> set=new HashSet<>();
		
		for (int i = 0; i < arr.length; i++) {
			
			set.add(arr[i]);
		}
		
		System.out.println(findLadders(start, end, set));;
	}
	/**
	 * 120. 单词接龙
	 * 给出两个单词（start和end）和一个字典，找出从start到end的最短转换序列，输出最短序列的长度。

		变换规则如下：
		
		每次只能改变一个字母。
		变换过程中的中间单词必须在字典中出现。(起始单词和结束单词不需要出现在字典中)
		
		如果不存在这样的转换序列，返回 0。
		所有单词具有相同的长度。
		所有单词只由小写字母组成。
		字典中不存在重复的单词。
		你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
		
		
		把start当成一个点，end当成一个点，start的每个字符当成每一层，没走一层，
		看这一层在不在dict中，如果在，就以这个点继续找，所以是BFS
		
		可以把最后的end加入到map中，这样，减少了逻辑处理，同时count可以初始化为1
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public static int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
		
		Map<String, Boolean> map=new HashMap<>();
		
		for (String word : dict) {
			map.put(word, false);
		}
		
		Queue<String> queue=new LinkedList<String>();
		
		int count=2;
		
		queue.add(start);
		
		while(!queue.isEmpty()) {
			
			int size=queue.size();
			System.out.println(queue);
			for (int k = 0; k < size; k++) {
				
				String s = queue.poll();
				
				
				char[] arr = s.toCharArray();
				
				for (int i = 0; i < arr.length; i++) {
					
					for (char j = 'a'; j < 'z'; j++) {
						
						if (arr[i]!=j) {
							
							char temp=arr[i];
							
							arr[i]=j;
							
							String tempWord =String.valueOf(arr);
							
							if (tempWord.equals(end)) {
								return count;
							}
							
							if (dict.contains(tempWord)&&!map.get(tempWord)) {
								queue.add(tempWord);
								map.put(tempWord, true);
							}
							
							arr[i]=temp;
						}
						
						
					}
				}
			}
			
			count++;
		}
		
		return -1;
    }
	
	/**
	 * dfs
	 * lintcode 121. 单词接龙 II
	 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
		
	}
	/**
	 * bfs还有点问题
	 * lintcode 121. 单词接龙 II
	 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	
	public static List<List<String>> findLadders(String start, String end, Set<String> dict) {

		//用来标记dict中的单词是否已经用过
		Map<String, Boolean> map=new HashMap<>();
		
		for (String word : dict) {
			map.put(word, false);
		}
		
		Queue<String> queue=new LinkedList<String>();
		
		
		queue.add(start);
		
		Map<String,List<String>> resultMap=new HashMap<>();
		
		List<List<String>> result = new ArrayList<List<String>>();
		
//		List<List<String>> list = new ArrayList<List<String>>();
		
		while(!queue.isEmpty()) {
			
			int size=queue.size();
			System.out.println("queue:"+queue);
			for (int k = 0; k < size; k++) {
				
				String s = queue.poll();
				List<String> tempList = resultMap.getOrDefault(s, new ArrayList<>());
//				List<String> tempList=new ArrayList<>();
				tempList.add(s);
				resultMap.put(s, tempList);
//				System.out.println("list:"+list);
				
				char[] arr = s.toCharArray();
				
				for (int i = 0; i < arr.length; i++) {
					
					trans(arr, result, resultMap, tempList, queue, k, i, end,dict,map);
				}
				
//				list.remove(s);
//				if (list.size()>0) {
//					
//					list.get(k%list.size()).addAll(tempList);
//				}else {
//					
//					list.add(tempList);
//				}
			}
			
			
		}
		
		return result;
    
		
    }
	
	public static void trans(char[] arr,List<List<String>> result,
			Map<String,List<String>> resultMap,List<String> tempList,Queue<String> queue,
			int k,int i,String end,Set<String> dict,Map<String, Boolean> map) {
		
		for (char j = 'a'; j < 'z'; j++) {
			
			if (arr[i]!=j) {
				
				char temp=arr[i];
				
				arr[i]=j;
				
				String tempWord =String.valueOf(arr);
				
				if (tempWord.equals(end)) {
					tempList.add(tempWord);
//					if (list.size()>0) {
//						
//						list.get(k%list.size()).addAll(tempList);
//					}else {
//						
//						list.add(tempList);
//					}
//					result.add(new ArrayList<>(list));
//					list.remove(list.get(k%list.size()));
				}
				
				if (dict.contains(tempWord)&&!map.get(tempWord)) {
					queue.add(tempWord);
					map.put(tempWord, true);
				}
				
				arr[i]=temp;
			}
			
			
		}
	}
	
}

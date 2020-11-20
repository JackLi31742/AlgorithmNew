package bfs;

import java.io.DataInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import sun.print.resources.serviceui;

/**
 * 简单图最短路径
 	没有层次，因为没有绝对的前后，所以不能用dp
 *
 */
public class WordLadder {

	
	public static void main(String[] args) {
		
//		System.out.println(5%0);
		
		String start ="qa";
		
		String end = "sq";
		
		String[] arr= {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
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
	
	//BFS版本
	public static int ladderLength(String start, String end, Set<String> dict) {
		
		//为了方便，将end加入dict
		dict.add(end);
		
		Queue<String> queue=new ArrayDeque<String>();
		
		//key是word，value是变化的步数
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		
		queue.add(start);
		map.put(start, 1);
		
		while(!queue.isEmpty()) {
			
			String word = queue.poll();
			
			List<String> nextWords = getNextWord(word);
			
			for (String nextWord : nextWords) {
				
				if (map.containsKey(nextWord)) {
					continue;
				}
				
				if (dict.contains(nextWord)) {
					
					queue.add(nextWord);
					map.put(nextWord, map.get(word)+1);
				}
				
			}
		}
		
		return map.get(end)==null?0:map.get(end);
				
		
	}
	
	public static List<String> getNextWord(String word) {
		
		List<String> list=new ArrayList<String>();
		
		char[] arr = word.toCharArray();
		
		for (int i = 0; i < arr.length; i++) {
			
			for(char j='a';j<='z';j++) {
				
				if (arr[i]!=j) {
					//不能直接替换
//					array[i]=j;
					char temp=arr[i];
					
					arr[i]=j;
					
					String tempWord =String.valueOf(arr);
					
					list.add(tempWord);
					
					arr[i]=temp;
				}
			}
		}
		
		return list;
	}
	
	public static int ladderLength2(String start, String end, Set<String> dict) {
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
	 * 
	 * lintcode 121. 单词接龙 II
	 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
		
		List<List<String>> result =new ArrayList<List<String>>();
		//为了方便，将end加入dict
		dict.add(end);
		
		Queue<String> queue=new ArrayDeque<String>();
		
		//key是word，value是下一个变化的列表
		HashMap<String, List<String>> map=new HashMap<String, List<String>>();
		
		//key是word，value是从起点开始变化的步数，不是到终点的步数
		HashMap<String, Integer> distance=new HashMap<String, Integer>();
		
		queue.add(start);
		
		map.put(start, new ArrayList<String>());
		
		distance.put(start,1);

		int min=Integer.MAX_VALUE;
		
		
		while(!queue.isEmpty()) {
			
			String word = queue.poll();
			
			List<String> nextWords = getNextWord(word);
			
			
			for (String nextWord : nextWords) {
				
				if (map.containsKey(nextWord)) {
					//这里不能直接过滤，否则会丢失解
					map.get(word).add(nextWord);
					
					//这里可以不做处理
//					distance.put(nextWord, Math.min(distance.get(nextWord), distance.get(word)+1));
//					
//					if (nextWord.equals(end)&&distance.get(end)<min) {
//						min=distance.get(end);
//					}
					
					continue;
				}
				
				if (dict.contains(nextWord)) {
					
					map.get(word).add(nextWord);
					queue.add(nextWord);
					
					//不能这么过滤，会丢解
//					if (!nextWord.equals(end)) {
						
						//对于每个nextWord，由于nextWord不一样，都建一个wordlist
						map.put(nextWord, new ArrayList<String>());
//					}
					
					distance.put(nextWord, distance.get(word)+1);
					if (nextWord.equals(end)&&distance.get(end)<min) {
						min=distance.get(end);
					}
				}
				
			}
		}
				
		//由于不知道每个单词对应的单词最多有几个，用dfs，每次都回退，这样，只需要建立一个path list即可
		List<String> path=new ArrayList<String>();
		
		//用来标记path路线是否已经走过，防止有环
		Set<String> visited=new HashSet<String>();
		
		path.add(start);
		visited.add(start);
		
		dfs(start, end, path,visited, result, distance,map,min);
		
		return result;
	}
	
	public static void dfs(String start, String end,List<String> path,Set<String> visited,
			List<List<String>> result,HashMap<String, Integer> distance,
			HashMap<String, List<String>> map,int min) {
		

//		path.add(start);
//		visited.add(start);
		
		if (path.size()>min) {
			return;
		}
		
		if (start.equals(end)) {
			if (path.size()==min) {
				result.add(new ArrayList<String>(path));
			}
			return ;
		}
		
		List<String> list = map.get(start);

		
		for (int i = 0; i < list.size(); i++) {
			
			String word = list.get(i);
			
			if (visited.contains(word)) {
				continue;
			}
			
			path.add(word);
			visited.add(word);
			//通过这里进行加速
			if (distance.get(start)+1==distance.get(word)) {
				
				dfs(word,end, path, visited,result,distance, map,min);
			}
			path.remove(word);
			visited.remove(word);
		}
		
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
	
	public static List<List<String>> findLadders2(String start, String end, Set<String> dict) {

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

package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 简单图最短路径
 	没有层次，因为没有绝对的前后，所以不能用dp
 *
 */
public class WordLadder2 {
	
	
	/**
	 * 121. 单词接龙 II
		给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。

		变换规则如下：
		
		每次只能改变一个字母。
		变换过程中的中间单词必须在字典中出现。
		
		最优算法: BFS + DFS，这样的话，就不会朝着相反方向搜索，节省时间
		BFS: 求出所有点到终点的距离
		DFS: 沿着离终点越来越近的路线找到所有路径
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
		
		List<List<String>> result =new ArrayList<>();
		
		if (start==null||end==null||dict==null) {
			return result;
		}
		
		dict.add(end);
		dict.add(start);
		
		Map<String, Integer> distance=bfs(start, end, dict);
		
		List<String> path=new ArrayList<>();
		
		
		path.add(start);
		dfs(start, end, dict, result, path,distance);
		
		return result;
    }
	
	public void dfs(String start, 
			String end, 
			Set<String> dict,
			List<List<String>> result,
			List<String> path,
			Map<String, Integer> distance) {
		
		if (start.equals(end)) {
			result.add(new ArrayList<>(path));
			return;
		}
		
		
		List<String> nextWords = getNextWords(start, dict);
		
		for (int i = 0; i < nextWords.size(); i++) {
			
			
			if (distance.get(nextWords.get(i))!=distance.get(start)-1) {
				continue;
			}
			path.add(nextWords.get(i));
			dfs(nextWords.get(i), end, dict, result, path, distance);
			path.remove(path.size()-1);
		}
		
	}
	
	/**
	 * 使用bfs得到最短路径
	 * 从终点开始搜索，到终点的最短路径
	 * @return
	 */
	public Map<String, Integer> bfs(String start, String end, Set<String> dict) {
		
		Queue<String> queue=new ArrayDeque<>();
		
		//key是单词，value是距离终点的距离
		Map<String, Integer> distance=new HashMap<>();
		
		queue.add(end);
		distance.put(end, 0);
		
		while(!queue.isEmpty()) {
			
			String word = queue.poll();
			
			List<String> words=getNextWords(word, dict);
			
			for (String nextWord : words) {
				
				if (distance.containsKey(nextWord)) {
					continue;
				}
				
				queue.add(nextWord);
				distance.put(nextWord, distance.get(word)+1);
			}
		}
		
		return distance;
	}
	
	
	public List<String> getNextWords(String word, Set<String> dict){
		
		List<String> words=new ArrayList<>();
		
		char[] array = word.toCharArray();
		
		for (int i = 0; i < array.length; i++) {
			
			for (char j = 'a'; j <= 'z'; j++) {
				
				if (array[i]!=j) {
					
					char temp=array[i];
					
					array[i]=j;
					
					String tempWord=String.valueOf(array);
					
					if (dict.contains(tempWord)) {
						words.add(tempWord);
					}
					
					array[i]=temp;
				}
			}
		}
		
		return words;
	}
	
}

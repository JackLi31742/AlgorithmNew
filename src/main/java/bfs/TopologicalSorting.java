package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class TopologicalSorting {

	/**
	 * 127. 拓扑排序
	   *       针对给定的有向图找到任意一种拓扑排序的顺序.
	 * 
	 * @param graph
	 * @return
	 */
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		// write your code here
		ArrayList<DirectedGraphNode> result=new ArrayList<DirectedGraphNode>();
		if (graph==null||graph.size()==0) {
			return result;
		}
		
		//统计每个点的入度
		HashMap<DirectedGraphNode, Integer> map=new HashMap<DirectedGraphNode, Integer>();
		
		for (DirectedGraphNode node : graph) {
			
			ArrayList<DirectedGraphNode> neighbors = node.neighbors;
			//图的链表法表示中，对于每个点的每个邻居，如果在链表中存在，那么入度就+1
			for (DirectedGraphNode neighbor : neighbors) {
				
				map.put(neighbor, map.getOrDefault(neighbor, 0)+1);
			}
		}
		
		Queue<DirectedGraphNode> queue=new ArrayDeque<DirectedGraphNode>();
		
		//对图的链表的节点，如果入度为0，加入到queue中
		for (DirectedGraphNode node : graph) {
			
			if (!map.containsKey(node)||map.get(node)==0) {
				
				queue.add(node);
				result.add(node);
			}
		}
		
		//BFS
		while(!queue.isEmpty()) {
			
			DirectedGraphNode node = queue.poll();
			
			ArrayList<DirectedGraphNode> neighbors = node.neighbors;

			//对于所有的邻居，入度都减1
			for (DirectedGraphNode neighbor : neighbors) {
				
				map.replace(neighbor, map.get(neighbor), map.get(neighbor)-1);
//				map.put(neighbor, map.get(neighbor)-1);
				
				if (map.get(neighbor)==0) {
					
					queue.add(neighbor);
					result.add(neighbor);
				}
			}
		}
		
		
		return result;
		
	}

	
	/**
	 * 616. 安排课程
		课被标号为 0 到 n-1 。
有一些课程需要“前置课程”，比如如果你要上课程0，你需要先学课程1，我们用一个匹配来表示他们： [0,1]

给你课程的总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
    }
}

class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
}

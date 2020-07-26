package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class AdjacencyMatrix {
	
	private ArrayList<String> vertexs;
	
	private int[][] edges;
	
	//建立的是edges对应的是否访问的数组
//	private boolean[][] visited;

	//vertexs对应的是否访问的数组
	private boolean[] isVisited;
	
	
	public AdjacencyMatrix(int n) {
		this.vertexs = new ArrayList<String>(n);
		this.edges = new int[n][n] ;
//		this.visited=new boolean[n][n];
		isVisited=new boolean[n];
	}

	public ArrayList<String> getVertexs() {
		return vertexs;
	}

	public void setVertexs(ArrayList<String> vertexs) {
		this.vertexs = vertexs;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	}


	//增加节点
	public void addVertex(String vertex) {
		this.vertexs.add(vertex);
	}
	//增加边
	public void addEdge(int v1,int v2,int weight) {
		this.edges[v1][v2]=weight;
		this.edges[v2][v1]=weight;
	}
	
	public void show() {
		for (int i = 0; i < edges.length; i++) {
			System.out.println(Arrays.toString(edges[i]));
		}
	}
	
	public void dfs(int i){
		isVisited[i]=true;
		System.out.println(this.vertexs.get(i));
		for (int j = 0; j < this.vertexs.size(); j++) {
			if (edges[i][j]==1&&isVisited[j]==false) {
				dfs(j);
			}
		}
	}
	
	
	public void bfs(int i){
		System.out.println(this.vertexs.get(i));
		isVisited[i]=true;
		
		LinkedList<Integer> queue=new LinkedList<>();
		
		queue.add(i);
		
		while(!queue.isEmpty()){
			int cur=queue.poll();
			for (int j = 0; j < edges[cur].length; j++) {
				if (edges[cur][j]==1&&!isVisited[j]) {
					System.out.println(this.vertexs.get(j));
					isVisited[j]=true;
					queue.add(j);
				}
			}
		}
	}
	

}

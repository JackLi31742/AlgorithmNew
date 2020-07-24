package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class AdjacencyMatrix {
	
	private ArrayList<String> vertexs;
	
	private int[][] edges;
	
	//建立的是edges对应的是否访问的数组
	private boolean[][] visited;

	//vertexs对应的是否访问的数组
	private boolean[] isVisited;
	public AdjacencyMatrix(int n) {
		this.vertexs = new ArrayList<String>(n);
		this.edges = new int[n][n] ;
		this.visited=new boolean[n][n];
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
	
	

}

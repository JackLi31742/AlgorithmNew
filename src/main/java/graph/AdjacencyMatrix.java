package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import sun.tools.jar.resources.jar;

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
	
	/**
	 * 最小生成树
	 */
	public void prim(int v){
		isVisited[v]=true;
		
		List<Integer> indexList=new ArrayList<>();
		
		indexList.add(v);
		
		while(indexList.size()<vertexs.size()){
			
			int maxValue=Integer.MAX_VALUE;
			//h1 和 h2 记录两个顶点的下标
			int h1 = -1;
			int h2 = -1;
			for (int i = 0; i < indexList.size(); i++) {
				
				for (int j = 0; j < vertexs.size(); j++) {
					//应该用indexList的值，而不是用i，i是indexList的索引
					int index=indexList.get(i);
					//isVisited[index]&&!isVisited[j] 解决了回路问题
					if (isVisited[index]&&!isVisited[j]&&edges[index][j]<maxValue) {
						maxValue=edges[index][j];
						h1 = indexList.get(i);
						h2 = j;
					}
				}
			}
			
			indexList.add(h2);
			//找到一条边是最小
			System.out.println("边<" + vertexs.get(h1) + "," + vertexs.get(h2) + "> 权值:" + edges[h1][h2]);
			isVisited[h2]=true;
		}
		
	}
	
	public void kruskal(){
		
	}
	

}

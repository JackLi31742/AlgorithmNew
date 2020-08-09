package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
		//得到所有的边
		ArrayList<EData> eDatas=getEData();
		//按照权值排序
		Comparator<EData> comparator=new Comparator<EData>() {

			@Override
			public int compare(EData o1, EData o2) {
				// TODO Auto-generated method stub
				return o1.weight-o2.weight;
			}
		};
		
		//排序
		Collections.sort(eDatas, comparator);
		
		System.out.println(eDatas);
		
		//判断有没有回路，没有就加入最小生成树
		
		ArrayList<EData> result=new ArrayList<>();
		
		int[] ends=new int[vertexs.size()];
		for (int i = 0; i < eDatas.size(); i++) {
			EData eData=eDatas.get(i);
			int start=eData.start;
			int end=eData.end;
			int e1=getEnd(ends, start);
			int e2=getEnd(ends, end);
			if (e1!=e2) {
				result.add(eData);
				ends[e1]=e2;
			}
		}
		
		System.out.println(result);
		
	}
	//把edges数组转换成Edata
	public ArrayList<EData> getEData(){
		ArrayList<EData> eDatas=new ArrayList<>();
		for (int i = 0; i < edges.length; i++) {
			for (int j = i+1; j < edges[i].length; j++) {
				if (edges[i][j]!=Integer.MAX_VALUE) {
					
					EData eData=new EData(i, j, edges[i][j]);
					eDatas.add(eData);
				}
			}
		}
		
		return eDatas;
	}
	
	/**
	 * 
	 * @param ends 每个顶点的终点，下标是顶点的下标，值是终点
	 * @param i 顶点的下标
	 */
	public int getEnd(int[] ends,int i){
		//ends[i]=0的时候，就是找到了终点
		while (ends[i]!=0) {
			i=ends[i];
		}
		return i;
	}
	

}

//和邻接表里的边节点不同
class EData{
	int start;//起点
	int end;//重点
	int weight;//权值
	
	public EData(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}
	
}
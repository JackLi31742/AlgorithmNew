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
	
	/**
	 *  最短距离
	 *  @param v 顶点的下标
	 */
	public void dijkstra(int v) {
		ArrayList<VData> list=getVData();
		VData startV=list.get(v);
		startV.dis=0;
		startV.visited=true;
		
		
		Comparator<VData> comparator=new Comparator<VData>() {

			@Override
			public int compare(VData o1, VData o2) {
				// TODO Auto-generated method stub
				return o1.dis-o2.dis;
			}
		};
		
		
		
		updateDis(v, list);
		ArrayList<VData> temp=new ArrayList<VData>(list.size());
		for (int i = 1; i < vertexs.size(); i++) {
			//不能破坏原来的list
			temp.addAll(list);
			Collections.sort(temp,comparator);
			//由于每次sort，每次都排序，每次取的都是后序最小的，每次取一个，所以取的每次都是i
			VData vData=temp.get(i);
			//置为true，就是把新的节点从未走到的状态加入到了已走到的状态，即从V集合到了S集合
			vData.visited=true;
			updateDis(vData.id, list);
			temp.clear();
		}
		
		System.out.println(list);
	}
	
	/**
	 *   更新距离
	 * @param v  从出发点经过v到其余各个点的距离
	 * @param list
	 */
	public void updateDis(int v,ArrayList<VData> list) {
		
		VData vv=list.get(v);
		for (int i = 0; i < edges[v].length; i++) {
			VData vData=list.get(i);
			if (!vData.visited) {
				//vv.dis是出发点到v的距离
				//edges[v][i]是v到i的距离
				//不能用integer.max_value，否则相加就成了负数
				int dis=vv.dis+edges[v][i];
				//vData.dis是出发点到i的距离
				if (dis<vData.dis) {
					vData.dis=dis;
					vData.parent=v;
				}
			}
		}
	}
	
	//将vertexs转换到VData
	public ArrayList<VData> getVData() {
		ArrayList<VData> list=new ArrayList<VData>();
		
		for (int i = 0; i < vertexs.size(); i++) {
			VData vData=new VData(i);
			list.add(vData);
		}
		
		return list;
	}
	
	/**
	 * 多源到其余各个顶点的距离
	 */
	public void floyd() {
		//距离
		int[][]dis=edges;
		//pre的行坐标是起点，纵坐标是终点，值是纵坐标的前驱
		int[][]pre=new int[edges.length][edges.length];
		for (int i = 0; i < pre.length; i++) {
			Arrays.fill(pre[i], i);
		}
		//中间节点k
		for (int k = 0; k < edges.length; k++) {
			//起点i
			for (int i = 0; i < edges.length; i++) {
				//终点j
				for (int j = 0; j < edges.length; j++) {
					int distance=dis[i][k]+dis[k][j];
					if (distance<dis[i][j]) {
						dis[i][j]=distance;
						pre[i][j]=pre[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < dis.length; i++) {
			System.out.println(Arrays.toString(dis[i]));
		}
		for (int i = 0; i < dis.length; i++) {
			System.out.println(Arrays.toString(pre[i]));
		}
	}

}

//和邻接表里的边节点不同
class EData{
	int start;//起点
	int end;//终点
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

//和邻接表里的顶点不同
class VData{
	int id;//该点的下标
	boolean visited;//该点是否被访问过
	int dis;//出发点到该点的距离
	int parent;//该点的前驱节点
	public VData(int id) {
		super();
		this.id = id;
		this.parent=-1;
		this.dis=10000;
		this.visited=false;
	}
	
	
	@Override
	public String toString() {
		return "VData [id=" + id + ", visited=" + visited + ", dis=" + dis + ", parent=" + parent + "]";
	}
	
	
}

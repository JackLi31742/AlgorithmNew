package graph;

public class Kruskal {
	
	//使用 INF 表示两个顶点不能连通
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		String vertexs[] = {"A", "B", "C", "D", "E","F","G"};
		AdjacencyMatrix graph = new AdjacencyMatrix(vertexs.length);
		//需要把节点加到graph中，否则初始化的时候，vertexs的list是空的
		for (String vertex : vertexs) {
			
			graph.addVertex(vertex);
		}
		int matrix[][] = {
			      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
			/*A*/ {   0,  12, INF, INF, INF,  16,  14},
			/*B*/ {  12,   0,  10, INF, INF,   7, INF},
			/*C*/ { INF,  10,   0,   3,   5,   6, INF},
			/*D*/ { INF, INF,   3,   0,   4, INF, INF},
			/*E*/ { INF, INF,   5,   4,   0,   2,   8},
			/*F*/ {  16,   7,   6, INF,   2,   0,   9},
			/*G*/ {  14, INF, INF, INF,   8,   9,   0}}; 
		
		graph.setEdges(matrix);
		graph.kruskal();
		graph.show();
		
	}

}

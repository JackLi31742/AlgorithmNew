package graph;

public class Floyd {

	public static void main(String[] args) {
		String vertexs[] = {"A", "B", "C", "D", "E","F","G"};
		//邻接矩阵
		final int N = 10000;// 表示不可以连接
		
        
        AdjacencyMatrix graph = new AdjacencyMatrix(vertexs.length);
		
		for (String vertex : vertexs) {
			
			graph.addVertex(vertex);
		}
		
		int matrix[][] = {
			      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
			/*A*/ { 0,   5,   7,   N,   N,   N,   2},
			/*B*/ { 5,   0,   N,   9,   N,   N,   3},
			/*C*/ { 7,   N,   0,   N,   8,   N,   N},
			/*D*/ { N,   9,   N,   0,   N,   4,   N},
			/*E*/ { N,   N,   8,   N,   0,   5,   4},
			/*F*/ { N,   N,   N,   4,   5,   0,   6},
			/*G*/ { 2,   3,   N,   N,   4,   6,   0}}; 
		
		graph.setEdges(matrix);
		
		graph.floyd();
	}
}

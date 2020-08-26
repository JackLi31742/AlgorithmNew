package graph;

public class Dijkstra {

	public static void main(String[] args) {
//		String vertexs[] = {"A", "B", "C", "D", "E","F","G"};
		String vertexs[] = {"A", "B1", "B2", "C1", "C2","C3","C4"
							,"D1","D2","D3","E1","E2","E3"
							,"F1","F2","G"};
		
		System.out.println(vertexs.length);
		//邻接矩阵
		final int N = 10000;// 表示不可以连接
		
        
        AdjacencyMatrix graph = new AdjacencyMatrix(vertexs.length);
		
		for (String vertex : vertexs) {
			
			graph.addVertex(vertex);
		}
		
		int matrix[][] = {
			      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
			/*A*/ { N,   5,   7,   N,   N,   N,   2},
			/*B*/ { 5,   N,   N,   9,   N,   N,   3},
			/*C*/ { 7,   N,   N,   N,   8,   N,   N},
			/*D*/ { N,   9,   N,   N,   N,   4,   N},
			/*E*/ { N,   N,   8,   N,   N,   5,   4},
			/*F*/ { N,   N,   N,   4,   5,   N,   6},
			/*G*/ { 2,   3,   N,   N,   4,   6,   N}}; 
		
		
		
		int[][] m = { 
				{ N, 5, 3, N, N, N, N, N, N, N, N, N, N, N, N, N },
				{ N, N, N, 1, 3, 6, N, N, N, N, N, N, N, N, N, N }, 
				{ N, N, N, N, 8, 7, 6, N, N, N, N, N, N, N, N, N },
				{ N, N, N, N, N, N, N, 6, 8, N, N, N, N, N, N, N }, 
				{ N, N, N, N, N, N, N, 3, 5, N, N, N, N, N, N, N },
				{ N, N, N, N, N, N, N, N, 3, 3, N, N, N, N, N, N }, 
				{ N, N, N, N, N, N, N, N, 8, 4, N, N, N, N, N, N },
				{ N, N, N, N, N, N, N, N, N, N, 2, 2, N, N, N, N }, 
				{ N, N, N, N, N, N, N, N, N, N, N, 1, 2, N, N, N },
				{ N, N, N, N, N, N, N, N, N, N, N, 3, 3, N, N, N }, 
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, 3, 5, N },
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, 5, 2, N }, 
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, 6, 6, N },
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, 4 },
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, 3 },
				{ N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N }};
		
		
		
		graph.setEdges(m);
		
		graph.dijkstra(0);
		
		
		System.out.println(graph.count);
		
	}
	
}



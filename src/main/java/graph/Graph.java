package graph;

//邻接矩阵
public class Graph {

	public static void main(String[] args) {
		String vertexs[] = {"A", "B", "C", "D", "E"};
//		AdjacencyMatrix graph = new AdjacencyMatrix(vertexs.length);
//		
//		for (String vertex : vertexs) {
//			
//			graph.addVertex(vertex);
//		}
//		
//		graph.addEdge(0, 1, 1);
//		graph.addEdge(0, 2, 1);
//		graph.addEdge(1, 2, 1);
//		graph.addEdge(1, 3, 1);
//		graph.addEdge(1, 4, 1);
//		
//		graph.show();
		
		AdjacencyList graph=new AdjacencyList(vertexs.length);
		
		for (String vertex : vertexs) {
			graph.addVNode(vertex);
		}
		
		graph.addENode(0, 1, 1);
		graph.addENode(0, 2, 1);
		graph.addENode(1, 2, 1);
		graph.addENode(1, 3, 1);
		graph.addENode(1, 4, 1);
		
		
		System.out.println(graph.getvNodes());
	}
	
	
}

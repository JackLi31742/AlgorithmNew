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
	
	/**
	 * 743. 网络延迟时间
	 * 有 N 个网络节点，标记为 1 到 N。

		给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，
		其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
		
		现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。

	 * @param times
	 * @param N
	 * @param K
	 * @return
	 */
	public int networkDelayTime(int[][] times, int N, int K) {

    }
	
	
	/**
	 * 有点类似翻译的例子
	 * 373. 查找和最小的K对数字
	 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。

		定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。

		找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。

	 * @param nums1
	 * @param nums2
	 * @param k
	 * @return
	 */
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

    }
	
}



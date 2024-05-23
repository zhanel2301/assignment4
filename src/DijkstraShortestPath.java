import java.util.ArrayList;
import java.util.List;

public class DijkstraShortestPath {
    private static final int INFINITY = Integer.MAX_VALUE;
    private int[][] graph;

    public DijkstraShortestPath(int[][] graph) {
        this.graph = graph;
    }

    private List<Integer> reconstructPath(int[] previous, int source, int target) {
        List<Integer> path = new ArrayList<>();
        for (int current = target; current != source; current = previous[current])
            path.add(current);
        path.add(source);

        int start = 0;
        int end = path.size() - 1;
        while (start < end) {
            int temp = path.get(start);
            path.set(start, path.get(end));
            path.set(end, temp);

            start++;
            end--;
        }

        return path;
    }

    private int minDistVertex(int[] distance, boolean[] visited) {
        int minDistance = INFINITY;
        int minVertex = -1;

        for (int v = 0; v < distance.length; v++) {
            if (!visited[v] && distance[v] <= minDistance) {
                minDistance = distance[v];
                minVertex = v;
            }
        }

        return minVertex;
    }

    public void shortestPath(int source, int target) {
        int numVertices = graph.length;
        int[] distance = new int[numVertices];
        int[] previous = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = INFINITY;
        }

        distance[source] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistVertex(distance, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != INFINITY && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    previous[v] = u;
                }
            }
        }

        List<Integer> path = reconstructPath(previous, source, target);
        System.out.println("Shortest Path from Edinburgh to Dundee: " + path);
    }

/*Edinburgh is 0, Perth is 1, Stirling is 2, Glasgow is 3, Dundee is 4*/
    public static void main(String[] args) {
        int[][] graph = {
                {0, 100, 50, 70, 0},
                {100, 0, 40, 0, 60},
                {50, 40, 0, 50, 0},
                {70, 0, 50, 0, 0},
                {0, 60, 0, 0, 0}
        };

        DijkstraShortestPath sh = new DijkstraShortestPath(graph);
        sh.shortestPath(0, 4);
    }
}

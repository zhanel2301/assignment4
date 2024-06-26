import java.util.LinkedList;
import java.util.Queue;

public class MyGraph {
    private int V, E;
    LinkedList<Integer>[] adj;

    public MyGraph(int nodes) {
        this.V = nodes;
        this.adj = new LinkedList[V];
        for(int v = 0; v < V; v ++)
            adj[v] = new LinkedList<>();
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
        E++;
    }

    public void dfs() {
        boolean[] visited = new boolean[V];
        System.out.print("Depth First Search: ");
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                visitNode(v, visited);
            }
        }
    }
    public void visitNode(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for(int w = 0; w < adj[v].size(); w++) {
            if(!visited [adj[v].get(w)])
                visitNode(adj[v].get(w), visited);
        }
    }


    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        System.out.print("Breadth First Search: ");
        while (!q.isEmpty()){
            int u = q.poll();
            visited[u] = true;
            System.out.print(u + " ");

            for(int w = 0; w < adj[u].size(); w++) {
                if(!visited[adj[u].get(w)]) {
                    visited[adj[u].get(w)] = true;
                    q.add(adj[u].get(w));
                }
            }
        }
    }

    public static void main(String[] args) {
        MyGraph g = new MyGraph(7);
        /*A=0, B=1, C=2, D=3, E=4, F=5, G=6
A: C B D
B: A C E G
C: A B D
D: C A
E: G F B
F: G E
G: F B*/
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(1, 6);
        g.addEdge(2, 3);
        g.addEdge(4, 6);
        g.addEdge(4, 5);
        g.addEdge(5, 6);

        g.dfs();
        System.out.println();
        g.bfs(0);
    }
}


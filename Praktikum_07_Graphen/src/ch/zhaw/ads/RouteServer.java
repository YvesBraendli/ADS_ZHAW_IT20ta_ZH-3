package ch.zhaw.ads;

import java.util.*;

public class RouteServer implements CommandExecutor {
    /*
    build the graph given a text file with the topology
    */
    public Graph<DijkstraNode, Edge> createGraph(String topo) throws Exception {
    	Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);

        Arrays.stream(topo.split("\n")).map(line -> line.split(" ")).forEach(values -> {
            try {
                double distance = Double.parseDouble(values[2]);
                graph.addEdge(values[0], values[1], distance);
                graph.addEdge(values[1], values[0], distance);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        return graph;
    }


    /*
    apply the dijkstra algorithm
    */
    public void dijkstraRoute(Graph<DijkstraNode, Edge> graph, String from, String to) {
    	graph.getNodes().forEach(node -> node.dist = Double.MAX_VALUE);

        DijkstraNode<Edge> firstNode = graph.findNode(from);
        firstNode.dist = 0;

        PriorityQueue<DijkstraNode<Edge>> redNodes = new PriorityQueue<>();
        redNodes.add(firstNode);

        while (!redNodes.isEmpty()) {
            DijkstraNode<Edge> current = redNodes.remove();
            if (current.getName().equals(to)) {
                return;
            }
            current.mark = true;
            for (Edge<DijkstraNode> edge : current.getEdges()) {
                DijkstraNode<Edge> neighbour = edge.getDest();
                if (!neighbour.mark) {
                    double dist = current.dist + edge.weight;
                    if (dist < neighbour.dist) {
                        neighbour.dist = dist;
                        neighbour.prev = current;
                        redNodes.remove(neighbour);
                        redNodes.add(neighbour);
                    }
                }
            }
        }
    }

    /*
    find the route in the graph after applied dijkstra
    the route should be returned with the start town first
    */
    public List<DijkstraNode<Edge>> getRoute(Graph<DijkstraNode, Edge> graph, String to) {
        List<DijkstraNode<Edge>> route = new LinkedList<>();
        DijkstraNode<Edge> town = graph.findNode(to);
        do {
            route.add(0,town);
            town = town.getPrev();
        } while (town != null);
        return route;
    }

    public String execute(String topo) throws Exception {
        Graph<DijkstraNode, Edge> graph = createGraph(topo);
        dijkstraRoute(graph, "Winterthur", "Lugano");
        List<DijkstraNode<Edge>> route = getRoute(graph, "Lugano");
        // generate result string
        StringBuffer buf = new StringBuffer();
        for (DijkstraNode<Edge> rt : route) buf.append(rt+"\n");
        return buf.toString();
    }

    public static void main(String[] args)throws Exception {
        String swiss =
                "Winterthur Zürich 25\n"+
                        "Zürich Bern 126\n"+
                        "Zürich Genf 277\n"+
                        "Zürich Luzern 54\n"+
                        "Zürich Chur 121\n"+
                        "Zürich Berikon 16\n"+
                        "Bern Genf 155\n"+
                        "Genf Lugano 363\n"+
                        "Lugano Luzern 206\n"+
                        "Lugano Chur 152\n"+
                        "Chur Luzern 146\n"+
                        "Luzern Bern 97\n"+
                        "Bern Berikon 102\n"+
                        "Luzern Berikon 41\n";
        RouteServer server = new RouteServer();
        System.out.println(server.execute(swiss));
    }
}
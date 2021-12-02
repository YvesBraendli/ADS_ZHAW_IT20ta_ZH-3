import java.util.*;
import java.awt.*;


public class LabyrinthServer implements CommandExecutor {
	ServerGraphics g = new ServerGraphics();
  
	public Graph<DijkstraNode, Edge> createGraph(String s) {
		Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
		String[] connections = s.split("\n");
		try {
			for (String connection : connections) {
				String[] values = connection.split(" ");
				String source = values[0];
				String destination = values[1];
				graph.addEdge(source, destination, 0);
				graph.addEdge(destination, source, 0);
			}
		} catch (Throwable t) {
			System.err.println(t);
		}
		return graph;
	}
  
	public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {
		g.setColor(Color.white);
		for(DijkstraNode<Edge> node : graph.getNodes()){
			for(Edge edge : node.getEdges()){
				DijkstraNode<Edge> neighbour = (DijkstraNode<Edge>) edge.getDest();
				g.drawPath(node.getName(),neighbour.getName() , false);
			}
		}
	}
  
	private boolean search(DijkstraNode<Edge> current, DijkstraNode<Edge> ziel) {
		current.setMark(true);
		if(current==ziel) return true;
		for(Edge edge : current.getEdges()){
			DijkstraNode neighbour = (DijkstraNode) edge.getDest();
			if(!neighbour.getMark()){
				neighbour.setPrev(current);
				if(search(neighbour, ziel)) return true;
			}
		}
		current.setMark(false);
		return false;
	}
  
	// search and draw result
	public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String zielNode) {
		g.setColor(Color.RED);
		if(search(graph.findNode(startNode), graph.findNode(zielNode))){
			DijkstraNode<Edge> current = graph.findNode(zielNode);
			while(current.getPrev()!=null){
				g.drawPath(current.getName(), current.getPrev().getName(),true);
				current = current.getPrev();
			}
		}
	}

	public String execute(String s) throws Exception {
		Graph<DijkstraNode, Edge> graph;
		graph = createGraph(s);
		drawLabyrinth(graph);
		drawRoute(graph, "0-6", "3-0");
		return g.getTrace();
	}

}
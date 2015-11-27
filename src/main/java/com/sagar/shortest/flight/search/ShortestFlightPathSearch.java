package com.sagar.shortest.flight.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sagar.shortest.flight.search.model.Edge;
import com.sagar.shortest.flight.search.model.Graph;
import com.sagar.shortest.flight.search.model.Vertex;

/**
 * This is a shortest path implementation class used to find out Shortest Path between various Nodes with the vertexes provided.
 * @version 1.0
 * @author Sagar Jangle.
 * @since Nov 26 2015
 * 
 * The algorithm typically works as below:
 * 
 * 1. All the nodes are partitioned into two distinct sets, Unsettled and settled.
 * 2. Initially all nodes are in the unsettled sets, e.g. they must be still evaluated. A node is moved to the settled set if a 
 * shortest path from the source to this node has been found.
 * 3. Initially the distance of each node to the source is set to a very high value.
 * 4. First only the source is in the set of unsettledNodes.
 * 5. The algorithms runs until the unsettledNodes are empty.
 * 6. In earch iteration it selects the node with the lowest distance from the source out the unsettled nodes.
 * 7. If reads all edges which are outgoing from the source and evaluates for each destination node in these edges which is not 
 * yet settled if the known distance from the source to this node can be reduced if the selected edge is used.
 * 8. If this can be done then the distance is updated and the node is added to the nodes which need evaluation.
 * 
 * Pseudo code as below:
 * 
 * Foreach node set distance[node] = HIGH
 * 	SettledNodes = empty
 * 	UnSettledNodes = empty
 * 
 * 	Add sourceNode to UnSettledNodes
 * 	distance[sourceNode]= 0
 * 
 *  while (UnSettledNodes is not empty) {
 *		evaluationNode = getNodeWithLowestDistance(UnSettledNodes)
 *		remove evaluationNode from UnSettledNodes  
 * 		add evaluationNode to SettledNodes
 * 		evaluatedNeighbors(evaluationNode)
 * 	}
 * 
 * 	getNodeWithLowestDistance(UnSettledNodes){ 
 * 		find the node with the lowest distance in UnSettledNodes and return it 
 * 	}
 * 
 * 	evaluatedNeighbors(evaluationNode){ 
 * 		Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes
 * 			{
 * 				edgeDistance = getDistance(edge(evaluationNode, destinationNode))
 * 				newDistance = distance[evaluationNode] + edgeDistance
 * 				if (distance[destinationNode]  > newDistance) {
 * 					distance[destinationNode]  = newDistance
 * 					add destinationNode to UnSettledNodes
 * 					}
 * 				}
 * 			}
 */

public class ShortestFlightPathSearch {
	  private final List<Vertex> nodes;
	  private final List<Edge> edges;
	  
	  private Set<Vertex> settledNodes;
	  private Set<Vertex> unSettledNodes;
	  
	  private Map<Vertex, Vertex> predecessors;
	  private Map<Vertex, Integer> distance;

	  /**
	   * A Constructor used to create a Graph using the Vertexes and Edges passed.  
	   * @param graph Graph of the Vertexes and Edges
	   */
	public ShortestFlightPathSearch(Graph graph) {
	    this.nodes = new ArrayList<Vertex>(graph.getVertexes());
	    this.edges = new ArrayList<Edge>(graph.getEdges());
	  }
	  
	  public void execute(Vertex source) {
	    settledNodes = new HashSet<Vertex>();
	    unSettledNodes = new HashSet<Vertex>();
	    distance = new HashMap<Vertex, Integer>();
	    predecessors = new HashMap<Vertex, Vertex>();
	    distance.put(source, 0);
	    unSettledNodes.add(source);
	    
	    while (unSettledNodes.size() > 0) {
	      Vertex node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      
	      findMinimalDistances(node);
	    }
	 }
	  
	  /**
	   * This method traves the entire tree and finds the distance of destination vertex with all the possible routes and add the lowest distance in the distance map.
	   * @param node Vertex
	   */
	private void findMinimalDistances(Vertex node) {
	    List<Vertex> adjacentNodes = getNeighbors(node);
	    for (Vertex target : adjacentNodes) {
	      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
	    	  distance.put(target, getShortestDistance(node) + getDistance(node, target));
	    	  predecessors.put(target, node);
	    	  unSettledNodes.add(target);
	      }
	    }
	  }
	  
	  /**
	   * This method finds the distance from Sorce Vertex to Destination Vertex by recursivaly going through all edges.   
	   * @param node Vertex
	   * @param target Vertex
	   * @return weight Int
	  */
	private int getDistance(Vertex node, Vertex target) {
	    for (Edge edge : edges) {
	      if (edge.getSource().equals(node)
	          && edge.getDestination().equals(target)) {
	        return edge.getWeight();
	      }
	    }
	    throw new RuntimeException("Should not happen");
	  }
	  
	 /**
	 * This method finds all the neighbours of the current Vertex passed. 
	 * @param node Current Vertex for which we need to find the neighbours.
	 * @return neighbors List of Vertexes
	 */
	private List<Vertex> getNeighbors(Vertex node) {
	    List<Vertex> neighbors = new ArrayList<Vertex>();
	    for (Edge edge : edges) {
	      if (edge.getSource().equals(node)
	          && !isSettled(edge.getDestination())) {
	        neighbors.add(edge.getDestination());
	      }
	    }
	    return neighbors;
	  }	  
	  
	 /**
	 * This method will find the minimum of all vertexes according to shortest distance towards the destination.  
	 * @param vertexes Set of Vertexes
	 * @return Vertex Minimum of Vertexes
	 */
	private Vertex getMinimum(Set<Vertex> vertexes) {
	    Vertex minimum = null;
	    for (Vertex vertex : vertexes) {
	      if (minimum == null) {
	        minimum = vertex;
	      } else {
	        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
	          minimum = vertex;
	        }
	      }
	    }
	    return minimum;
	  }
	  
	  private boolean isSettled(Vertex vertex) {
		    return settledNodes.contains(vertex);
	  }
	  
	  /**
	   * This method will find the distance of the vertex. If the distance is Null then it will be set to MAX_VALUE. 
	   * @param destination Vertex
	   * @return distance int
	  */
	private int getShortestDistance(Vertex destination) {
	    Integer d = distance.get(destination);
	    if (d == null) {
	      return Integer.MAX_VALUE;
	    } else {
	      return d;
	    }
	  }
	  
	  /**
	   * This method returns the path from the source to the selected target and NULL if no path exists. 
	   * @param target Vertex 
	   * @return path LinkedList<Vertex> 
	   */
	public LinkedList<Vertex> getPath(Vertex target) {
	    LinkedList<Vertex> path = new LinkedList<Vertex>();
	    Vertex step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // Put it into the correct order
	    Collections.reverse(path);
	    return path;
	  }	  
}
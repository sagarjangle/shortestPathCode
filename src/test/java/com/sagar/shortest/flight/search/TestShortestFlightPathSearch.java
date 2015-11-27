package com.sagar.shortest.flight.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sagar.shortest.flight.search.model.Edge;
import com.sagar.shortest.flight.search.model.Graph;
import com.sagar.shortest.flight.search.model.Vertex;

import static org.junit.Assert.*;

/**
 * This is the test cases created for testing the shortest path from various sources to destination. 
 * @version 1.0
 * @author Sagar Jangle.
 * @since Nov 26 2015
 */

public class TestShortestFlightPathSearch {

  private static List<Vertex> nodes;
  private static List<Edge> edges;

  	@BeforeClass
  	public static void setup() {
  		System.out.println("Creating Nodes and Edges for the test......");
  		// Create Test Nodes for this test.
  		createNodes();
	  
  		// Create Test edges for this test.
  		createEdges();
  	}
  
  	@AfterClass
  	public static void tearDown() {
  		System.out.println("Clearing nodes and edges ......");
  		nodes = null;
  		edges = null;
  	}
  	
  	@Test
  	public void testNodes() {
  		assertNotNull(nodes);
  	}
  
  	@Test
  	public void testEdges() {
  		assertNotNull(edges);
  	}
  	
  	@Test
  	public void testExecuteFromNode_0ToNode_10() {
  		// Find the shortest distance between nodes Node_0 to Node_10  
  		
	    Graph graph = new Graph(nodes, edges);
	    ShortestFlightPathSearch shortestFlightPath = new ShortestFlightPathSearch(graph);
	    
	    shortestFlightPath.execute(nodes.get(0));
	    
	    LinkedList<Vertex> path = shortestFlightPath.getPath(nodes.get(10));
	    
	    // Path should not be null as the path exists between Node_0 to Node_10
	    assertNotNull(path);	
	    
	    assertTrue(path.size() > 0);
	    
	    for (Vertex vertex : path) {
	      System.out.println(vertex);
	    }
  	}
  	
  	@Test
  	public void testExecuteFromNode_1ToNode_8() {
  		// Find the shortest distance between nodes Node_1 to Node_8
  		// As there is no path from Node_1 to Node_8, the path should be NULL.
  		
	    Graph graph = new Graph(nodes, edges);
	    ShortestFlightPathSearch shortestFlightPath = new ShortestFlightPathSearch(graph);
	    shortestFlightPath.execute(nodes.get(0));
	    
	    LinkedList<Vertex> path = shortestFlightPath.getPath(nodes.get(8));
	    
	    // Path should be null as the path doesnot exists between Node_1 to Node_8
	    assertTrue(path == null);		
  	}

  	private static void createEdges() {
  		System.out.println("Creating Edges .............");
	    edges = new ArrayList<Edge>();
		  
	    addLane("Edge_0", 0, 1, 85);
	    addLane("Edge_1", 0, 2, 217);
	    addLane("Edge_2", 0, 4, 173);
	    addLane("Edge_3", 2, 6, 186);
	    addLane("Edge_4", 2, 7, 103);
	    addLane("Edge_5", 3, 7, 183);
	    addLane("Edge_6", 5, 8, 250);
	    addLane("Edge_7", 8, 9, 84);
	    addLane("Edge_8", 7, 9, 167);
	    addLane("Edge_9", 4, 9, 502);
	    addLane("Edge_10", 9, 10, 40);
	    addLane("Edge_11", 1, 10, 600);

	    System.out.println("edges added : " + edges.toString());
	}

	private static void createNodes() {
		  System.out.println("Creating Nodes .............");
		    nodes = new ArrayList<Vertex>();
		    
		    for (int i = 0; i < 11; i++) {
		      Vertex location = new Vertex("Node_" + i, "Node_" + i);
		      nodes.add(location);
		    }
		    System.out.println("Nodes added : " + nodes.toString());	
	}
  
	private static void addLane(String laneId, int sourceLocNo, int destLocNo,int duration) {
		  Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		  edges.add(lane);
	}
}
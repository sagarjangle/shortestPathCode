package com.sagar.shortest.flight.search.model;

/**
 * This is a model class used to create the Edges of the Graph.
 * Graph will consists of Nodes (Airport Names / Locations) and Vertexes (connections between Airport Locations). 
 * @version 1.0
 * @author Sagar Jangle.
 * @since Nov 26 2015
 */

public class Edge {
	private final String id; 
	private final Vertex source;
	private final Vertex destination;
	private final int weight; 
	  
	/**
	 * A Constructor used to create Edges of the Graph.
	 * @param id EdgeId
	 * @param source Edge Source Vertex
	 * @param destination Edge Destination Vertex
	 * @param weight Weight assigned to Edge
	 */
	public Edge(String id, Vertex source, Vertex destination, int weight) {
	    this.id = id;
	    this.source = source;
	    this.destination = destination;
	    this.weight = weight;
	}
	  
	/**
	 * @return EdgeId
	 */
	public String getId() {
	    return id;
	}
	
	/**
	 * @return Destination Edge
	 */
	public Vertex getDestination() {
	    return destination;
	}

	/**
	 * @return Source Edge
	 */
	public Vertex getSource() {
	    return source;
	}
	
	/**
	 * @return Weight of the Edge
	 */
	public int getWeight() {
	    return weight;
	}
	  
	@Override
	public String toString() {
	    return source + " " + destination;
	}
}
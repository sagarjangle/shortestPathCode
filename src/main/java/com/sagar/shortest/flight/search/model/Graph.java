package com.sagar.shortest.flight.search.model;

import java.util.List;

/**
 * This is a model class used to create the Graph of Vertexes and Edges.
 * Graph will consists of Nodes (Airport Names / Locations) and Vertexes (connections between Airport Locations). 
 * @version 1.0
 * @author Sagar Jangle.
 * @since Nov 26 2015
 */

public class Graph {
	private final List<Vertex> vertexes;
	private final List<Edge> edges;

	/**
	 * A Constructor used to create Graph. 
	 * @param vertexes List of Vertexes
	 * @param edges List of Edges
	 */
	public Graph(List<Vertex> vertexes, List<Edge> edges) {
	    this.vertexes = vertexes;
	    this.edges = edges;
	}

	/**
	 * @return List of Vertexes
	 */
	public List<Vertex> getVertexes() {
	    return vertexes;
	}

	/**
	 * @return List of Edges
	 */
	public List<Edge> getEdges() {
	    return edges;
	}	
}
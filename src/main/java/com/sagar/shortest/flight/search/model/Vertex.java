package com.sagar.shortest.flight.search.model;

/**
 * This is a model class used to create the vertices of the Graph.
 * Graph will consists of Nodes (Airport Names / Locations) and Vertexes (connections between Airport Locations). 
 * @version 1.0
 * @author Sagar Jangle.
 * @since Nov 26 2015
 */
public class Vertex {
	
	final private String id;
	final private String name;
	  
	/**
	 * A Constructor used to create Vertexes of the Graph.
	 * @param id Vertex Id 
	 * @param name Vertex Name
	 */
	public Vertex(String id, String name) {
	    this.id = id;
	    this.name = name;
	}

	/**
	 * @return VertexId
	 */
	public String getId() {
	    return id;
	}

	/**
	 * @return Vertex Name
	 */
	public String getName() {
	    return name;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    
	    if (obj == null)
	      return false;
	    
	    if (getClass() != obj.getClass())
	      return false;
	    
	    Vertex other = (Vertex) obj;
	    
	    if (id == null) {
	      if (other.id != null)
	        return false;
	    } else if (!id.equals(other.id))
	      return false;
	    return true;
	}
	
	@Override
	public String toString() {
	    return name;
	}
}
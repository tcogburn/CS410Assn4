package cs410A3;

import java.util.ArrayList;

public class Face {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	Material M = new Material();
	public Face(){
		
	}
	public Face(Vertex v1, Vertex v2, Vertex v3, Material m){
		addVertex(v1);
		addVertex(v2);
		addVertex(v3);
		M = m;
	}
	public void addVertex(Vertex v){
		vertices.add(v);
	}
	public void setMaterial(Material m){
		M = m;
	}
}

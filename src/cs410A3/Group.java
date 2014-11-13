package cs410A3;

import java.util.ArrayList;

public class Group {
	String name;
	ArrayList<Face> faces = new ArrayList<Face>();
	public Group(){
		
	}
	public Group(String n){
		name = n;
	}
	public Group(ArrayList<Face> f){
		faces = f;
	}
	public void addFace(Face f){
		faces.add(f);
	}
}

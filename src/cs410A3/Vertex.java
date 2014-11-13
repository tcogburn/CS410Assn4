package cs410A3;

public class Vertex {
	int ID;
	double x;
	double y; 
	double z;
	double h;
	public Vertex(){
		x=0;
		y=0;
		z=0;
		h=1;
	}
	public Vertex(double a, double b, double c,int i){
		x = a;
		y = b;
		z = c;
		h = 1;
		ID = i;
	}
	public Vertex(double a, double b, double c, double d){
		x = a;
		y = b;
		z = c;
		h = d;
	}
	public Vertex(double a, double b, double c, double d, int i){
		x = a;
		y = b;
		z = c;
		h = d;
		ID = i;
	}

}

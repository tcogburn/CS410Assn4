package cs410A3;

public class Sphere {
	char s;
	String name;
	int X;
	int Y;
	int Z;
	int radius;
	int R;
	int G;
	int B;
	Material mat;
	public Sphere(char c,String n,int x,int y,int z,int rad,int r,int g, int b){
		s = c;
		name = n;
		X = x;
		Y = y;
		Z = z;
		radius = rad;
		R = r;
		G = g;
		B = b;
	}
	public void addMat(Material m ){
		mat = m;
	}
	public Sphere(char c,String n,int x,int y,int z,int rad){
		s = c;
		name = n;
		X = x;
		Y = y;
		Z = z;
		radius = rad;
	}
	public String toString(){
		return s+" "+name+" "+X+" "+Y+" "+Z+" "+radius+" "+R+" "+G+" "+B;
	}
	
}

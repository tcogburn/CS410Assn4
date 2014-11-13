package cs410A3;
public class Camera {
	char c;
	String name;
	double prp_x;
	double prp_y;
	double prp_z;
	double vpn_x;
	double vpn_y;
	double vpn_z;
	double near;
	double far;
	public Camera(char a,String n,double px, double py, double pz, double vx, double vy, double vz, double ne, double f){
		c = a;
		name = n;
		prp_x = px;
		prp_y = py;
		prp_z = pz;
		vpn_x = vx;
		vpn_y = vy;
		vpn_z = vz;
		near = ne;
		far = f;
	}
	public String toString(){
		return c+" "+name+" "+prp_x+" "+prp_y+" "+prp_z+" "+vpn_x+" "+vpn_y+" "+vpn_z+" "+near+" "+far;
	}
}

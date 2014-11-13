package cs410A3;

public class Ray {
	int ex;
	int ey;
	int ez;
	double px;
	double py;
	double pz;
	double magnitude;
	double[] ray = new double[3];
	double[] RO = new double[3];
	public Ray(int x1, int y1, int z1, double x2, double y2, double z2){
		ex = x1;
		ey = y1;
		ez = z1;
		RO[0] = ex;
		RO[1] = ey;
		RO[2] = ez;
		px = x2;
		py = y2;
		pz = z2;
		magnitude = Math.sqrt(Math.pow(px-ex,2)+Math.pow(py-ey,2)+Math.pow(pz-ez,2));
		ray[0] = (px-ex)/magnitude;
		ray[1] = (py-ey)/magnitude;
		ray[2] = (pz-ez)/magnitude;
	}
	public String toString(){
		return ex+" "+ey+" "+ez+" "+px+" "+py+" "+pz+" "+magnitude;
	}
	public double dotp(double[] a){
		double x = ray[0] * a[0]; 
		double y = ray[1] * a[1];
		double z = ray[2] * a[2];
		double result = x + y + z;
		return result;		
	}
}

package cs410A3;

public class Material {
	String name;
	double[] Ka = new double[3];
	double[] Kd = new double[3];
	double[] Ks = new double[3];
	public Material(String n, double[] ka, double[] kd, double[]ks){
		name = n;
		Ka = ka;
		Kd = kd;
		Ks = ks;				
	}
	public Material() {
		// TODO Auto-generated constructor stub
	}
}

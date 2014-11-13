package cs410A3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RayTracer {
	
	@SuppressWarnings("resource")
	public static void main(String args[]) throws FileNotFoundException{
		File f1 = new File(args[0]);
		File f2 = new File(args[1]);
		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		BufferedReader br2 = new BufferedReader(new FileReader(f2));
		String line;
		ArrayList<Material> mats = new ArrayList<Material>();
		ArrayList<Sphere> sp = new ArrayList<Sphere>();
		ArrayList<Camera> cam = new ArrayList<Camera>();
		ArrayList<Vertex> verts = new ArrayList<Vertex>();
		ArrayList<Face> faces = new ArrayList<Face>();
		ArrayList<Light> lights = new ArrayList<Light>();
		Scene sce = new Scene();
		Material m = new Material();
		int vcount = 0;
		try {
			String name = "";
			double[] Ka = new double[3];
			double[] Kd = new double[3];
			double[] Ks = new double[3];
			//Read In Objects
			while((line = br1.readLine())!= null){
				String delim = "[ ]+";
				String[] elements = line.split(delim);
				Group g = new Group();
				if(elements[0].length() > 0){
					if(elements[0].charAt(0)!= '#'){
						if(elements[0].equals("mtllib")){
							String path = f1.getAbsolutePath();
							path = path.substring(0, path.length()-args[0].length());
							path += elements[1];
							File f3 = new File(path);
							BufferedReader br3 = new BufferedReader(new FileReader(f3));
							while((line = br3.readLine())!= null){
								String[] items = line.split(delim);
								if(items[0].length() > 0){
									if(items[0].equals("newmtl")){
										name = items[1];
										Ka = new double[3];
										Kd = new double[3];
										Ks = new double[3];
									}else if(items[0].equals("Ka")){
										for(int i = 0; i < 3; i++){
											Ka[i] = Double.parseDouble(items[i+1]);
										}
									}else if(items[0].equals("Kd")){
										for(int i = 0; i < 3; i++){
											Kd[i] = Double.parseDouble(items[i+1]);
										}
									}else if(items[0].equals("Ks")){
										for(int i = 0; i < 3; i++){
											Ks[i] = Double.parseDouble(items[i+1]);
										}
										mats.add(new Material(name,Ka,Kd,Ks));
									}
								}
								
							}
						}else if(elements[0].equals("usemtl")){
							for(int i = 0; i < mats.size();i++){
								//System.out.println(mats.get(i).name +"?="+elements[1]);
								if(mats.get(i).name.equals(elements[1])){
									m = mats.get(i);
								}
							}
						}
						if(elements[0].charAt(0) == 's'){
							Sphere s = new Sphere(elements[0].charAt(0),elements[1],Integer.parseInt(elements[2]),Integer.parseInt(elements[3]),Integer.parseInt(elements[4]),Integer.parseInt(elements[5]));
							s.addMat(m);
							sp.add(s);
						}else if(elements[0].charAt(0) == 'v'){
							if(elements.length == 4){
								verts.add(new Vertex(Double.parseDouble(elements[1]),Double.parseDouble(elements[2]),Double.parseDouble(elements[3]),vcount));
								vcount++;
							}else if(elements.length == 5){
								verts.add(new Vertex(Double.parseDouble(elements[1]),Double.parseDouble(elements[2]),Double.parseDouble(elements[3]),Double.parseDouble(elements[3]),vcount));
								vcount++;
							}
						}else if(elements[0].charAt(0) == 'g'){
							g = new Group(elements[1]);
						}else if(elements[0].charAt(0) == 'f'){
							for(int i = 1; i < elements.length;i++){
								faces.add(new Face(verts.get(Integer.parseInt(elements[1])-1),verts.get(Integer.parseInt(elements[i])-1),verts.get(Integer.parseInt(elements[i])-1),m));
							}
						}
					}
				}
				
			}
			//Read In Cameras and lights
			while((line = br2.readLine())!= null){
				String delim = "[ ]+";
				String[] elements = line.split(delim);
				if(elements[0].length() > 0){
					if(elements[0].charAt(0) == 'c'){
						Camera c = new Camera(elements[0].charAt(0),elements[1],Double.parseDouble(elements[2]),Double.parseDouble(elements[3]),Double.parseDouble(elements[4]),Double.parseDouble(elements[5]),Double.parseDouble(elements[6]),Double.parseDouble(elements[7]),Double.parseDouble(elements[8]),Double.parseDouble(elements[9]));
						cam.add(c);
					}else if(elements[0].charAt(0) == 'r'){
						sce = new Scene(elements[0].charAt(0),elements[1],Integer.parseInt(elements[2]),Integer.parseInt(elements[3]),Integer.parseInt(elements[4]));
					}else if(elements[0].charAt(0) == 'l'){
						lights.add(new Light(Double.parseDouble(elements[1]),Double.parseDouble(elements[2]),Double.parseDouble(elements[3]),Double.parseDouble(elements[4]),Double.parseDouble(elements[5]),Double.parseDouble(elements[6]),Double.parseDouble(elements[7])));
					}
				}
				
			}			
			System.out.println(sp);
		
			for(int c = 0; c < cam.size();c++){
				Camera cur = cam.get(c);
				int[] RGB = new int[4];
				String filename = sce.name+"_"+cam.get(c).name+"_color.ppm";
				String filename2 = sce.name+"_"+cam.get(c).name+"_depth.ppm";
				PrintWriter writer = new PrintWriter(filename);
				PrintWriter writer2 = new PrintWriter(filename2);
				writer.println("P3 "+sce.width+" "+sce.width+" 256");
				writer2.println("P3 "+sce.width+" "+sce.width+" 256");
				for(int i = 0; i < sce.width;i++){
					for(int j = 0; j< sce.height;j++){
						double x = (2.0/sce.width) * i - 1.0;
						double y = (2.0/sce.height) * j - 1.0;
						//System.out.println("("+x+","+y+")");
						Ray r = new Ray(0,0,0,x,y,-cur.near);
						RGB = raycast(r,sp,cur);
						writer.println(RGB[0]+" "+RGB[1]+" "+RGB[2]);
						writer2.println(RGB[3]+" "+RGB[3]+" "+RGB[3]);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR:Could not read file model01.obj");
			e.printStackTrace();
		}
	}
	public static int[] raycast(Ray r,ArrayList<Sphere> s,Camera cur){
		double[] vecC = new double[3];
		double[] Q = new double[3];
		double[] N = new double[3];
		int[] RGB = new int[4];
		int R = 255;
		int G = 255;
		int B = 255;
		double D = 0;
		double n = 0;
		double c = 0;
		double v = 0;
		double d = 0;
		double q = 0;
		double inter = 0;
		double dist = 0;
		double dist2 = 0;
		double mindist = cur.far;
		
		for(int i = 0; i < s.size();i++){
			Sphere s1 = s.get(i);
			vecC[0] = s1.X - r.ex;
			vecC[1] = s1.Y - r.ey;
			vecC[2] = s1.Z - r.ez;
			//Vector to Origin of sphere
			c = Math.sqrt(Math.pow(vecC[0], 2)+Math.pow(vecC[1],2)+Math.pow(vecC[2],2));
			//Distance from E to V
			v = r.dotp(vecC);
			inter = Math.pow(s1.radius,2)-(Math.pow(c, 2)-Math.pow(v, 2));
			if(inter > 0){
				d = Math.sqrt(inter);
				//System.out.println(v);
				for(int j = 0; j < 3; j++){
					Q[j] = r.RO[j]+(r.ray[j]*(v-d));
					//Finding Normal to point of intersection
					N[j] = Q[j] - s.get(i).X;
				}
				//Magnitude of N
				n = Math.sqrt(Math.pow(N[0],2)+Math.pow(N[1],2)+Math.pow(N[2],2));
				dist = Math.sqrt(Math.pow(Q[0],2)+Math.pow(Q[1],2)+Math.pow(Q[2],2));
				dist2 = dist/cur.far;
				q = Math.sqrt(Math.pow(Q[0],2)+Math.pow(Q[1],2)+Math.pow(Q[2],2));
				for(int j = 0; j < 3; j++){
					Q[j] = Q[j]/q;
					//Normalized vector from point of intersection
					N[j] = N[j]/n;
				} 
				double k = (r.ray[0]*N[0])+(r.ray[1]*N[1])+(r.ray[2]*N[2]); 
				if(dist < mindist && dist > cur.near){
					mindist = dist;
					R = (int) (s1.R*(Math.abs(k))+10);
					G = (int) (s1.G*(Math.abs(k))+10);
					B = (int) (s1.B*(Math.abs(k))+10);
					D = 255-(Math.min(255,(255*(dist2))));
				}
			}
		}
		RGB[0] = R;
		RGB[1] = G;
		RGB[2] = B;
		RGB[3] = (int) D;
		return RGB;
	}
}

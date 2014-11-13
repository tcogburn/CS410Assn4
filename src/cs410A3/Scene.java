package cs410A3;

public class Scene {
	char r;
	String name;
	int height;
	int width;
	int rec;
	public Scene(){
		
	}
	public Scene(char c, String s,int w, int h, int re){
		r = c;
		name = s;
		height = h;
		width = w;
		rec = re;		
	}
	public String toString(){
		return r+" "+name+" "+height+" "+width+" "+rec;
	}
}

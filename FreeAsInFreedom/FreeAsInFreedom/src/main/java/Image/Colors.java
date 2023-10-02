import java.awt.*;

public class Colors {
	
	public static int [] a = {256, 0, 0};
	public static int [] b = {1,0,2,1,0,2};
	public static int step = 0;
	public static double div = 4;
	
	public static void main(String[]args) {
				

		int s = 1;
		p();
		step++;
		for(int i =0; i < 6; i++) {
			for(int j = 0; j < div; j++) {
				a[b[i]] += s*(256/div);
				p();
				step++;
			}
			s *= -1;
		}
		
	}
	
	public static void p() {
		if(step == 0) {
			System.out.print("from");
		}
		else if(step == div*6) {
			System.out.print("to");
		}
		else {
			System.out.print((int)(step*(100/(div*6))) + "%");
		}

		int [] e = {a[0], a[1], a[2]};
		for(int i = 0; i< e.length; i++) {
			if(e[i] == 256) {
				e[i] = 255;
			}
		}
		Color your_color = new Color(e[0],e[1],e[2]);

		String hex = "#"+Integer.toHexString(your_color.getRGB()).substring(2);
		System.out.println(" {color: " + hex.toUpperCase() + "}");
	}
}

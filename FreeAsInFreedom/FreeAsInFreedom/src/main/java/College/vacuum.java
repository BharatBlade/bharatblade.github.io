package College;
import java.util.ArrayList;
import java.util.Arrays;

public class vacuum {
	int x = 0;
	int y = 0;
	int powerLevel = 100;
	char d = '^';
	char [][] field;
	ArrayList<Integer> percept = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
																	//T,Du,Df,Db,Dl,Dr,Gu,Gf,Gb,Gl,Gr
	public vacuum(char [] [] field){
		this.field = field;
	}
	public vacuum(int x, int y, char [][] field) throws InterruptedException{
		this.x = x;
		this.y = y;
		this.field = field;
		field[x][y] = d;
		for(int i = 0; i < field.length; i++)
			for(int j = 0; j < field[i].length; j++)
				System.out.print(field[i][j]);
			System.out.println();
		detect();
		Thread.sleep(250);
	}
	public void move() throws InterruptedException{
		while(true){
			if(powerLevel == 2){System.exit(0);}
			if(percept.get(0) == 1){
				int chance = (int)(Math.random()*2);
				if(chance == 0){turnLeft();}
				else{turnRight();}
			}
			else{
				if(percept.get(2) == 1){
					moveForward();
					percept.set(2, 0);
					powerLevel -= 4;
				}
				else if(percept.get(3) == 1){
					turnRight();
					turnRight();
					moveForward();
					percept.set(3, 0);
					powerLevel -= 4;
				}
				else if(percept.get(4) == 1){
					turnLeft();
					moveForward();
					percept.set(4, 0);
					powerLevel -= 4;
				}
				else if(percept.get(5) == 1){
					turnRight();
					moveForward();
					percept.set(5, 0);
					powerLevel -= 4;
				}
				else{
					if(percept.get(7) == 1){
						moveForward();
						percept.set(7, 0);
						System.exit(0);
					}
					else if(percept.get(8) == 1){
						turnRight();
						turnRight();
						moveForward();
						percept.set(8, 0);
						System.exit(0);
					}
					else if(percept.get(9) == 1){
						turnLeft();
						moveForward();
						percept.set(9, 0);
						System.exit(0);
					}
					else if(percept.get(10) == 1){
						turnRight();
						moveForward();
						percept.set(10, 0);
						System.exit(0);
					}
					else{
						moveForward();
						int chance = (int)(Math.random()*2);
						if(chance == 0){turnLeft();}
						else{turnRight();}
					}
				}
			}
			
			
		}
	}
	public void turnLeft() throws InterruptedException{
		if(d == '^'){d = '<';}
		else if(d == '<'){d = 'V';}
		else if(d == 'V'){d = '>';}
		else if(d == '>'){d = '^';}
		field[x][y] = d;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++)
				System.out.print(field[i][j]);
			System.out.println();
		}
		powerLevel -= 2;
		detect();
		Thread.sleep(250);
	}
	public void turnRight() throws InterruptedException{
		if(d == '^'){d = '>';}
		else if(d == '>'){d = 'V';}
		else if(d == 'V'){d = '<';}
		else if(d == '<'){d = '^';}
		field[x][y] = d;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++)
				System.out.print(field[i][j]);
			System.out.println();
		}
		powerLevel -= 2;
		detect();
		Thread.sleep(250);
	}
	public void moveForward() throws InterruptedException{
		field[x][y] = ' ';
		if(d == '^'){x-=2;}
		else if(d == 'V'){x+=2;}
		else if(d == '>'){y+=2;}
		else if(d == '<'){y-=2;}
		field[x][y] = d;
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++)
				System.out.print(field[i][j]);
			System.out.println();
		}
		powerLevel -= 2;
		detect();
		Thread.sleep(250);
	}
	public void detect(){
		for(int i = 0; i < percept.size(); i++){
			percept.set(i, 0);
		}
		if(d == '^'){
			if(field[x-1][y] == '-' || field[x-1][y] == '+'){percept.set(0, 1);}
			else if(field[x-2][y] == 'X'){percept.set(0, 1);}
			else if(field[x-2][y] == '#'){percept.set(2, 1);percept.set(0, 0);}
			else if(field[x-2][y] == 'G'){percept.set(7, 1);percept.set(0, 0);}
			else if(field[x-1][y] != '-' && field[x-1][y] != '+' && field[x-2][y] != 'X'){percept.set(0, 0);}
			try{if(field[x+2][y] == '#'){percept.set(3, 1);}}
			catch (Exception e){}
			try{if(field[x][y-2] == '#'){percept.set(4, 1);}}
			catch (Exception e){}
			try{if(field[x][y+2] == '#'){percept.set(5, 1);}}
			catch (Exception e){}
			try{if(field[x+2][y] == 'G'){percept.set(8, 1);}}
			catch (Exception e){}
			try{if(field[x][y-2] == 'G'){percept.set(9, 1);}}
			catch (Exception e){}
			try{if(field[x][y+2] == 'G'){percept.set(10, 1);}}
			catch (Exception e){}
			System.out.println(percept);
		}
		else if(d == 'V'){
			if(field[x+1][y] == '-' || field[x+1][y] == '+'){percept.set(0, 1);}
			else if(field[x+2][y] == 'X'){percept.set(0, 1);}
			else if(field[x+2][y] == '#'){percept.set(2, 1);percept.set(0, 0);}
			else if(field[x+2][y] == 'G'){percept.set(7, 1);percept.set(0, 0);}
			else if(field[x+1][y] != '-' || field[x+1][y] != '+' && field[x+2][y] != 'X'){percept.set(0, 0);}
			try{if(field[x-2][y] == '#'){percept.set(3, 1);}}
			catch (Exception e){}
			try{if(field[x][y+2] == '#'){percept.set(4, 1);}}
			catch (Exception e){}
			try{if(field[x][y-2] == '#'){percept.set(5, 1);}}
			catch (Exception e){}
			try{if(field[x-2][y] == 'G'){percept.set(8, 1);}}
			catch (Exception e){}
			try{if(field[x][y+2] == 'G'){percept.set(9, 1);}}
			catch (Exception e){}
			try{if(field[x][y-2] == 'G'){percept.set(10, 1);}}
			catch (Exception e){}
			System.out.println(percept);
		}
		else if(d == '<'){
			if(field[x][y-1] == '|' || field[x][y-1] == '+'){percept.set(0, 1);}
			else if(field[x][y-2] == 'X'){percept.set(0, 1);}
			else if(field[x][y-2] == '#'){percept.set(2, 1);percept.set(0, 0);}
			else if(field[x][y-2] == 'G'){percept.set(7, 1);percept.set(0, 0);}
			else if(field[x][y-1] != '|' || field[x][y-1] != '+'&& field[x][y-2] != 'X'){percept.set(0, 0);}
			try{if(field[x][y+2] == '#'){percept.set(3, 1);}}
			catch (Exception e){}
			try{if(field[x-2][y] == '#'){percept.set(5, 1);}}
			catch (Exception e){}
			try{if(field[x+2][y] == '#'){percept.set(4, 1);}}
			catch (Exception e){}
			try{if(field[x][y+2] == 'G'){percept.set(8, 1);}}
			catch (Exception e){}
			try{if(field[x-2][y] == 'G'){percept.set(10, 1);}}
			catch (Exception e){}
			try{if(field[x+2][y] == 'G'){percept.set(9, 1);}}
			catch (Exception e){}
			System.out.println(percept);
		}
		else if(d == '>'){
			if(field[x][y+1] == '|' || field[x][y+1] == '+'){percept.set(0, 1);}
			else if(field[x][y+2] == 'X'){percept.set(0, 1);}
			else if(field[x][y+2] == '#'){percept.set(2, 1);percept.set(0, 0);}
			else if(field[x][y+2] == 'G'){percept.set(7, 1);percept.set(0, 0);}
			else if(field[x][y+1] != '|' || field[x][y+1] != '+' && field[x][y+2] != 'X'){percept.set(0, 0);}
			try{if(field[x][y-2] == '#'){percept.set(3, 1);}}
			catch (Exception e){}
			try{if(field[x+2][y] == '#'){percept.set(5, 1);}}
			catch (Exception e){}
			try{if(field[x-2][y] == '#'){percept.set(4, 1);}}
			catch (Exception e){}
			try{if(field[x][y-2] == 'G'){percept.set(8, 1);}}
			catch (Exception e){}
			try{if(field[x+2][y] == 'G'){percept.set(10, 1);}}
			catch (Exception e){}
			try{if(field[x-2][y] == 'G'){percept.set(9, 1);}}
			catch (Exception e){}
			System.out.println(percept);
		}
	}
}
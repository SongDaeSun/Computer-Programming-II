package class_11_02;

import java.util.Random;

public abstract class Fish extends GameObject {
	int hungerPoint;
	int catchScore;
	
	public Fish(GameManager GM, int x, int y) {
		super(GM);
		pos_x=x;
		pos_y=y;
	}
	
	abstract public void Move();

	public void Respawn() {
		Random rand = new Random();
		int new_x, new_y;
		
		do{
			new_x = rand.nextInt(GameManager.MAX_X);
			new_y = rand.nextInt(GameManager.MAX_Y);
		}while(!super.GM.IsEmpty(new_x, new_y));
		System.out.println("respawned: "+new_x +  ", " +new_y);
		pos_x = new_x;
		pos_y = new_y;
	}
	
}


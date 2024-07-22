package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up, left,down;
	public  BufferedImage sprite_up;
	public  BufferedImage sprite_down;
	public  BufferedImage sprite_right;
	public int lastDir = 1;
	public  BufferedImage sprite_left;
	
	
	public int life = 100;
	
	private BufferedImage playerDamage;
	public boolean isDamaged = false;   //esta no dano

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
		playerDamage = Game.spritesheet.getSprite(48, 0, 16, 48);
		sprite_up = Game.spritesheet.getSprite(64, 0, 16, 16);
		sprite_down = Game.spritesheet.getSprite(80, 0, 16, 16);
		sprite_right = Game.spritesheet.getSprite(32,0 , 16, 16);
	}
	
	public void tick(){
		
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir =1;
		//	Sound.andarEffect.play();
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir =2;
		//	Sound.coletarEffect.play();
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
		//	Sound.coletarEffect.play();
			lastDir =3;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
		//	Sound.coletarEffect.play();
			lastDir =4;
		}
		
		verificarPegaFruta();
	
		
		//GANHAR O JOGO
		if(Game.frutas_contagem == Game.frutas_atual) {
			//PODE COLOCAR A LÓGICA DE QUANDO GANHOU O JOGO
		//	System.out.println("Ganhamos o jogo");
			World.restartGame();
		}
	}

	public void verificarPegaFruta() {
		for(int i =0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Fruta) {
				if(Entity.isColidding(this, current)) {
					Game.frutas_atual++;
					Game.entities.remove(i);
					Sound.mastigarEffect.play();
					return;
				
					
				}
			}
			
		}
	}
	

	public void render(Graphics g) {
			
			if(lastDir ==1) {
				g.drawImage(sprite_right,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(lastDir ==2) {
				g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else if(lastDir ==3) {
				g.drawImage(sprite_up,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}else  {
				g.drawImage(sprite_down,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		
	}

	private int playerDamage() {
		// TODO Auto-generated method stub
		return 0;
	}


}

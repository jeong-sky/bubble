package bubble.test.ex17;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

//백그라운드에서 관찰
public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;
	
	//플레이어, 버블
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(enemy.getState()==0) {
			
			//색상확인
			Color leftColor = new Color(image.getRGB(enemy.getX()-10, enemy.getY()+25));
			Color rightColor = new Color(image.getRGB(enemy.getX()+50+15, enemy.getY()+25));
			int bottomColor = image.getRGB(enemy.getX()+20, enemy.getY()+50 +5) + 
							image.getRGB(enemy.getX()+50-20, enemy.getY()+50 +5);
			//System.out.println("바텀컬러"+ bottomColor);
		
			//바닥 충돌 확인
			if(bottomColor != -2) {
				//System.out.println("바닥에 충동함");
				enemy.setDown(false);
			} else {
				if(!enemy.isUp() && !enemy.isDown()) {
					enemy.down();
				}
			}
			
			//외벽충돌 확인
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue() == 0) {
				//System.out.println("왼쪽 충돌");
				enemy.setLeft(false);
				if(!enemy.isRight()) {
					enemy.right();
				}
			} else if(rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
				//System.out.println("오른쪽 충돌");
				if(!enemy.isLeft()) {
					enemy.left();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

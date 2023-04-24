package bubble.test.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//백그라운드에서 관찰
public class BackgroundPlayerService implements Runnable {
	
	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			//색상확인
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));
//			System.out.println("leftColor"+leftColor);
//			System.out.println("rightColor"+rightColor);
//			
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽 충돌");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if(rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue() == 0) {
				player.setRightWallCrash(true);
				player.setRight(false);
				System.out.println("오른쪽 충돌");
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
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

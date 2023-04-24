package bubble.test.ex11;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
	
	//위치상태
	private int x;
	private int y;
	
	//움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	//벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	//플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	
	private ImageIcon playerR, playerL;

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
		
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		//System.out.println("left");
		left = true;
		new Thread(()-> {
			while(left) {
				setIcon(playerL);
				x = x-SPEED;
				setLocation(x, y);		
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		//System.out.println("right");
		right=true;
		new Thread(()-> {
			while(right) {
				setIcon(playerR);
				x = x+SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}).start();
	}

	//left+up, right+up
	@Override
	public void up() {
		// TODO Auto-generated method stub
		//System.out.println("up");
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y-JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up=false;
			down();
			
		}).start();
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		//System.out.println("down");
		down=true;
		new Thread(()->{
			while(down) {
				y = y+JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down=false;
		}).start();

	}

}

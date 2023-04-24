package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {
	
	//의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;
	
	//위치상태
	private int x;
	private int y;
	
	//움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	//적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)
	
	private ImageIcon bubble; //물방울
 	private ImageIcon bubbled; //가둔 물방울
 	private ImageIcon bomb; //물방울이 터진 상태
 
 
 	public Bubble(BubbleFrame mContext) {
 		this.mContext = mContext;
 		this.player = mContext.getPlayer();
 		initObject();
 		initSetting();
// 		initThread();
 	}

//	private void initThread() {
//		new Thread(()-> {
//			if(player.getPd() == PlayerDirection.LEFT) {
//				left();
//			}else {
//				right();
//			}
//		}).start();
//	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");	
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}


	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		
		for(int i=0; i<400; i++) {
			x--;
			setLocation(x,y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i<400; i++) {
			x++;
			setLocation(x,y);
			
			if(backgroundBubbleService.righttWall()) {
				right = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x,y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clearBubble(); //천장에 버블이 도착하고 3초후에 메모리에서 소멸
	}

	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(3000);
			mContext.remove(this); //BubbleFrame의 bubble이 메모리에서 소멸
			mContext.repaint(); //BubbleFrame의 전체를 다시 그린다.(메모리에 있는것만!)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

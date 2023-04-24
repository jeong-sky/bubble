package bubble.test.ex06;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
	
	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			
			//키보드 클릭이벤트
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println(e.getKeyCode());
				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:	
					if(!player.isLeft()) {
						player.left();						
					}
					break;

				case KeyEvent.VK_RIGHT:
					if(!player.isRight()) {
						player.right();						
					}
					break;
					
				case KeyEvent.VK_UP:	
					if(!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;

				case KeyEvent.VK_DOWN:
					player.down();
					break;
				}
			}
			
			//키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
					
				}
			}
			
		});
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);
		//backgroundMap.setLocation(300, 300);
		//backgroundMap.setSize(1000, 600);
		//add(backgroundMap); //jframe에 jlabel이 그려진다.
		
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); //absoulte 레이아웃을 자유롭게
		setLocationRelativeTo(null); //jframe을 가운데로
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창을 끌때 jvm도 같이 종료
	}
	
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}

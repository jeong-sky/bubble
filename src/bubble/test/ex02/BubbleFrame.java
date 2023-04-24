package bubble.test.ex02;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
	
	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true);
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

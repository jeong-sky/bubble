package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

// 윈도우 창이 되었음.
// 창 내부에 패널을 하나 가지고 있다.
public class BubbleFrame extends JFrame {
	private JTextField txtIiiii;

	public BubbleFrame() {
		setSize(1000, 640);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(246, 142, 91, 23);
		getContentPane().add(btnNewButton);
		
		txtIiiii = new JTextField();
		txtIiiii.setText("iiiii");
		txtIiiii.setBounds(144, 303, 96, 21);
		getContentPane().add(txtIiiii);
		txtIiiii.setColumns(10);
		 setVisible(true);
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}

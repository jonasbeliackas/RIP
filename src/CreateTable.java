import java.awt.EventQueue;

import javax.swing.JFrame;

public class CreateTable {

	private JFrame frame;

	
	public CreateTable() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

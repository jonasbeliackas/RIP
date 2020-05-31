import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateRouter {

	private JFrame frame;
	private Router router; 
	private GUI gui;
	public CreateRouter(GUI gui) {
		this.gui = gui;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 275, 175);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRouterName = new JLabel("Router Name");
		lblRouterName.setBounds(10, 10, 85, 26);
		frame.getContentPane().add(lblRouterName);
		
		JLabel lblIpAdress = new JLabel("IP Adress");
		lblIpAdress.setBounds(10, 46, 85, 26);
		frame.getContentPane().add(lblIpAdress);
		
		JTextPane txtName = new JTextPane();
		txtName.setBounds(79, 17, 164, 19);
		frame.getContentPane().add(txtName);
		
		JTextPane txtIP = new JTextPane();
		txtIP.setBounds(79, 53, 164, 19);
		frame.getContentPane().add(txtIP);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				router = new Router(txtName.getText(),txtIP.getText());
				gui.addRouter(router);
				gui.resetTable();
				frame.setVisible(false);
			}
		});
		btnCreate.setBounds(90, 101, 85, 21);
		frame.getContentPane().add(btnCreate);
	}
	
}

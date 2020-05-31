import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;

public class AddNodes {

	private JFrame frame;
	private JTextField txtNode;
	private Router router;

	public AddNodes(Router router) {
		this.router = router;
		initialize();
		frame.setVisible(true);
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 356, 86);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtNode = new JTextField();
		txtNode.setBounds(73, 10, 152, 29);
		frame.getContentPane().add(txtNode);
		txtNode.setColumns(10);

		JLabel lblIpadrres = new JLabel("IpAdrres");
		lblIpadrres.setBounds(10, 18, 46, 13);
		frame.getContentPane().add(lblIpadrres);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(235, 14, 85, 21);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				router.AddNode(txtNode.getText());
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnAdd);
	}
}

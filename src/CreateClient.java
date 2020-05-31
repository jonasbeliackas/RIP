import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class CreateClient {

	private JFrame frame;
	private PC pc;
	private Vector<Router> routers;
	private Vector<PC> pcvVector;
	private Vector<String> names=new Vector<String>();
	private Vector<String> ips = new Vector<String>();
	public CreateClient(Vector<Router> routers,Vector<PC> pc) {
		this.routers = routers;
		pcvVector = pc;
		initialize();
		frame.setVisible(true);
	}


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 329, 164);

		frame.getContentPane().setLayout(null);
		Vector<String> tmpStrings = new Vector<String>();
		
		for(int i = 0 ; i < routers.size();i++ )
		{
			if(routers.get(i).isOnline()) {
			tmpStrings.add(routers.get(i).nameString+" "+routers.get(i).iPadressString );
			names.add(routers.get(i).nameString);
			ips.add(routers.get(i).iPadressString);
			}

		}
		//System.out.println(tmpStrings);
		JComboBox comboBox = new JComboBox(tmpStrings);
		comboBox.setBounds(135, 6, 169, 21);
		frame.getContentPane().add(comboBox);

		JLabel lblConnectToRouter = new JLabel("connect to router");
		lblConnectToRouter.setBounds(10, 10, 115, 13);
		frame.getContentPane().add(lblConnectToRouter);

		JLabel lblClinetName = new JLabel("Clinet Name");
		lblClinetName.setBounds(10, 43, 115, 13);
		frame.getContentPane().add(lblClinetName);

		JTextPane txtName = new JTextPane();
		txtName.setBounds(135, 37, 169, 19);
		frame.getContentPane().add(txtName);

		JLabel lblClinetIp = new JLabel("Clinet IP");
		lblClinetIp.setBounds(10, 72, 115, 13);
		frame.getContentPane().add(lblClinetIp);

		JTextPane txtIP = new JTextPane();
		txtIP.setBounds(135, 66, 169, 19);
		frame.getContentPane().add(txtIP);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String routerNameString ,String pcNameString ,String routerIP,String clientip
				 pc =new PC(names.get(comboBox.getSelectedIndex()), txtName.getText(),ips.get(comboBox.getSelectedIndex()),txtIP.getText());
				for(int i = 0 ; i < routers.size(); i++)
				{
					if(routers.get(i).nameString.equals(names.get(comboBox.getSelectedIndex())))
					{
						routers.get(i).connectClient(pc);
						pcvVector.add(pc);
					}
				}
				//pcvVector.add(pc);
				frame.setVisible(false);
			}
		});
		btnCreate.setBounds(219, 99, 85, 21);
		frame.getContentPane().add(btnCreate);
	}
	public boolean created()
	{
		if(pc != null) return true;
		return false;
	}
	public PC getclient() {

		
		return this.pc;

	}
}

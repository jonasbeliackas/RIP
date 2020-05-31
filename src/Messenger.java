import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Delayed;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Messenger {

	private JFrame frame;
	private JLabel lblClient;
	private Vector<PC> pc;
	private Vector<Router> routers;
	private Vector<Object> msgStrings1 = new Vector<Object>();
	private Vector<Object> msgStrings2 = new Vector<Object>();
	private JSplitPane splitPane;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private boolean client1;
	private boolean client2;
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			if(client1) {
				
			}else if(client2) {
				
			}
			
		}
	};
	public Messenger(Vector<PC> pc, Vector<Router> routers) {
		this.routers = routers;
		this.pc=pc;
		initialize();
	}
	public void turnOn()
	{
		frame.setVisible(true);
	}
	public void turnOff()
	{
		frame.setVisible(false);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 387);
		frame.getContentPane().setLayout(null);

		lblClient = new JLabel("select Client");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClient.setBounds(10, 10, 82, 20);
		frame.getContentPane().add(lblClient);

		JTextPane txtClient1 = new JTextPane();
		txtClient1.setBounds(10, 308, 190, 19);
		frame.getContentPane().add(txtClient1);


		Vector<String> tmpStrings = new Vector<String>();
		for(int i =  0; i < pc.size() ; i++)
		{
			tmpStrings.add(pc.get(i).getPCName());
			System.out.println("works " + pc.get(i).getPCName());
		}
		

		 comboBox1 = new JComboBox(tmpStrings);
		comboBox1.setBounds(116, 11, 150, 21);
		frame.getContentPane().add(comboBox1);

		 comboBox2 = new JComboBox(tmpStrings);
		comboBox2.setBounds(393, 11, 150, 21);
		frame.getContentPane().add(comboBox2);

		JTextPane txtClient2 = new JTextPane();
		txtClient2.setBounds(318, 307, 202, 19);
		frame.getContentPane().add(txtClient2);
	

		JLabel label = new JLabel("select Client");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(318, 10, 82, 20);
		frame.getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 618, 236);
		frame.getContentPane().add(scrollPane);
		
		splitPane = new JSplitPane();
		
		scrollPane.setViewportView(splitPane);
		
		JList list = new JList();
		splitPane.setLeftComponent(list);
		
		JList list_1 = new JList();
		splitPane.setRightComponent(list_1);

		JButton btnSendClien1 = new JButton("Send");
		btnSendClien1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox1.getSelectedIndex()!=comboBox2.getSelectedIndex())
				{
					int check =send(comboBox2.getSelectedItem().toString(), comboBox1.getSelectedItem().toString()); 
					if(check == 1)
					{

						msgStrings2.add("SENDED "+ txtClient1.getText());
						JList list = new JList(msgStrings2);
							splitPane.setLeftComponent(list);
						msgStrings1.add("received "+ txtClient1.getText());
						JList list_1 = new JList(msgStrings1);
						splitPane.setRightComponent(list_1);
							
					}else {
						msgStrings2.add("Failed");
						JList list = new JList(msgStrings2);
							splitPane.setLeftComponent(list);
					}
				}

			}
		});
		btnSendClien1.setBounds(210, 306, 98, 21);
		frame.getContentPane().add(btnSendClien1);



		JButton btnSendClient2 = new JButton("Send");
		btnSendClient2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				if(comboBox1.getSelectedIndex() != comboBox2.getSelectedIndex()) {
					int check =send(comboBox2.getSelectedItem().toString(), comboBox1.getSelectedItem().toString()); 
					if(check == 1)
					{
						
						msgStrings1.add("received  "+ txtClient2.getText());
						JList list_1 = new JList(msgStrings2);
						
					
						splitPane.setRightComponent(list_1);
						
						msgStrings2.add("SENDED "+ txtClient2.getText());
						JList list = new JList(msgStrings1);
						splitPane.setLeftComponent(list);
						
						
					}else {
						msgStrings2.add("failed ");
						JList list = new JList(msgStrings1);
						splitPane.setLeftComponent(list);
					}
				}
			}
		});
		btnSendClient2.setBounds(530, 307, 98, 21);
		frame.getContentPane().add(btnSendClient2);

	}

	private int send(String Sender, String receiverString)
	{

		Router sendertmpRouter = null;
		Router receiveRouter = null;
		for(int i=0; i < routers.size(); i ++)
		{
			if(routers.get(i).haveThisClient(Sender))
			{
				sendertmpRouter = routers.get(i);
			}
		}
		for(int i=0; i < routers.size(); i ++)
		{
			if(routers.get(i).haveThisClient(receiverString))
			{
				receiveRouter = routers.get(i);
			}
		}
		if(sendertmpRouter == null)
			return -3;
		else if(receiveRouter == null)
			return -4;
		else if(!sendertmpRouter.isOnline()) {
			JOptionPane.showMessageDialog(null, sendertmpRouter.nameString + "is offline");
			return -1;
		}
		else if(!receiveRouter.isOnline()) {
			JOptionPane.showMessageDialog(null, receiveRouter.nameString + " is offline");
			return -2;
		}
		else if(receiveRouter.canReach(sendertmpRouter)) {
			
			return 1;
		}else if(!receiveRouter.canReach(sendertmpRouter)) {
			JOptionPane.showMessageDialog(null, "Router cant be reached");
		}
			

		return 0;


	}
}


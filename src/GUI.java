import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class GUI {

	private JFrame frame;
	private Vector<Router> routers = new Vector<Router>();
	private Vector<PC> pc = new Vector<PC>();
	private JTable table;
	private int pcID = 16;
	private JScrollPane scrollPane_1;
	private RouterTable routerTable;
	private Timer timer = new Timer();
	private TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			if(routerTable!=null)
			{
				updateRouterInfo();
				//routerTable.addHopsToRouters();
				//System.out.println("timer");
			}
			
		}
	};
	private void ShowGraph()
	{
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GUI window = new GUI();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public GUI() {
		initialize();
	}

	public void addRouter(Router router)
	{
		router.setID(routers.size());
		routers.add(router);
	}
	public void resetTable()
	{
		Vector<String>nameStrings = new Vector<String>();
		nameStrings.add("ID");
		nameStrings.add("Name");
		nameStrings.add("IPAdress");
		Vector<Vector<Object>>item = new Vector<Vector<Object>>();
		for(int i = 0 ; i < routers.size(); i++)
		{
			Vector<Object>vector = new Vector<Object>();
			vector.add(routers.get(i).id);
			vector.add(routers.get(i).nameString);
			vector.add(routers.get(i).iPadressString);
			item.add(vector);
		}
		DefaultTableModel dtm=new DefaultTableModel(item,nameStrings);
		table=new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane_1.setViewportView(table);

	}
	private void updateRouterInfo()
	{
		routerTable.iniGraph(routers, pc);
		
	}
	private void initialize() {
		GUI gui = this;
		routerTable = new RouterTable();
		timer.schedule(timerTask, 1500, 1500);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 584, 226);
		frame.getContentPane().add(scrollPane_1);


		Vector<String>nameStrings = new Vector<String>();
		nameStrings.add("Name");
		nameStrings.add("IPAdress");
		Vector<Vector<Object>>item = new Vector<Vector<Object>>();
		for(int i = 0 ; i < routers.size(); i++)
		{
			Vector<Object>vector = new Vector<Object>();
			vector.add(routers.get(i).nameString);
			vector.add(routers.get(i).iPadressString);
			item.add(vector);
		}
		DefaultTableModel dtm=new DefaultTableModel(item,nameStrings);
		table=new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPane_1.setViewportView(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 139, 2, 2);
		frame.getContentPane().add(scrollPane);

		JButton btnRouterTable = new JButton("Router Table");
		btnRouterTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() > -1){
				routerTable = new RouterTable(table.getValueAt(table.getSelectedRow(), 1).toString(),Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
				routerTable.iniGraph(routers, pc);
				routerTable.resetTable();
			
				routerTable.set(true);
				}
				
			}
		});
		btnRouterTable.setBounds(56, 246, 113, 40);
		frame.getContentPane().add(btnRouterTable);

		JButton btnCreateRouter = new JButton("Create Router");
		btnCreateRouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateRouter cr = new CreateRouter(gui);
				

			}
		});
		btnCreateRouter.setBounds(179, 246, 113, 40);
		frame.getContentPane().add(btnCreateRouter);

		JButton btnCreateClient = new JButton("Create Client");
		btnCreateClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateClient cleClient =new CreateClient(routers,pc);
				
				
				
			}
		});
		btnCreateClient.setBounds(302, 246, 113, 40);
		frame.getContentPane().add(btnCreateClient);

		JButton btnClientInfo = new JButton("Stop Router");
		btnClientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					routers.get(table.getSelectedRow()).turnoff();
				}
			}
		});
		btnClientInfo.setBounds(425, 246, 113, 40);
		frame.getContentPane().add(btnClientInfo);

		JButton btnMessenger = new JButton("Messenger");
		btnMessenger.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Messenger messenger = new Messenger(pc, routers);
				messenger.turnOn();
				
			}
		});
		btnMessenger.setBounds(56, 296, 113, 40);
		frame.getContentPane().add(btnMessenger);

		JButton btnAddNode = new JButton("ADD Node");
		btnAddNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() !=-1)
				{
					AddNodes addNodes = new AddNodes(routers.get(table.getSelectedRow()));

				}
			}
		});
		btnAddNode.setBounds(179, 296, 113, 40);
		frame.getContentPane().add(btnAddNode);
		
		JButton btnStartRouter = new JButton("start Router");
		btnStartRouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow()!=-1)
			{
				routers.get(table.getSelectedRow()).turnon();
			}
			}
			
		});
		btnStartRouter.setBounds(425, 296, 113, 40);
		frame.getContentPane().add(btnStartRouter);
		
		JButton btnShowGraph = new JButton("Show Graph");
		btnShowGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowGraph graph = new ShowGraph();
				graph.Show(routerTable.getDirectedSparseGraph());
			}
		});
		btnShowGraph.setBounds(302, 296, 113, 40);
		frame.getContentPane().add(btnShowGraph);
	}
}

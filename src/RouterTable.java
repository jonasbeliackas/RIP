import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

import java.awt.Font;
import javax.swing.JLabel;

public class RouterTable {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private String routerNameString;
	private int id;
	private Graph graph;
	private  UndirectedSparseGraph<String, String> g;
	private Vector<Router> routers;
	int k= 0;
	private boolean hasnode = false;
	public RouterTable(String routerNameString,int id) {
		this.routerNameString = routerNameString;
		this.id = id;
		initialize();
		
	}
	public RouterTable() {

		initialize();
		
	}
	public UndirectedSparseGraph getDirectedSparseGraph()
	{
		return g;
	}
	void set(boolean t)
	{
		frame.setVisible(t);
	}
	public void showGraph()
	{
		
		    VisualizationImageServer vs =
		      new VisualizationImageServer(
		        new CircleLayout(g), new Dimension(200, 200));
		 
		    JFrame frame = new JFrame();
		    frame.getContentPane().add(vs);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);
	}

	public void iniGraph(Vector<Router> router,Vector<PC> pc)
	{
		routers = router;
		int hasAnyNode = 0;
		   g = new UndirectedSparseGraph();
		   
		
		for(int i = 0 ; i < router.size() ; i ++)
		{
			g.addVertex(router.get(i).nameString);
			if(router.get(i).getIPNodes().size() > 0)
			{
				hasAnyNode++;
			}
		}
		if(hasAnyNode > 0)
		{
		Vector<Integer> pairs = new Vector<Integer>();
		Vector<Integer> pairs2 = new Vector<Integer>();


		
		for( int i = 0; i < router.size() ; i++)
		{
			for(int j = 0 ;  j < router.size();j++)
			{
				if(i != j)
				{
					if(router.get(i).doWeHaveEdge(router.get(j)))
					{
						pairs.add(router.get(i).id);
						pairs2.add(router.get(j).id);
						String edgeNameString = router.get(i).getnodString(router.get(i));
						////System.out.println(edgeNameString+"edges"+router.get(i).nameString+" "+router.get(j).nameString);
						if(!g.containsEdge(edgeNameString))
							g.addEdge(edgeNameString, router.get(i).nameString, router.get(j).nameString);
						
						hasnode = true;
					}
				}
			}
		}
		int newids = router.size()+1;
		Vector<Integer> routerid = new Vector<Integer>();
		Vector<Integer> pcid = new Vector<Integer>();
		for( int i = 0; i < router.size() ; i++)
		{
			if(!router.get(i).busy())
			{
				routerid.add(router.get(i).id);
				pcid.add(newids);
				
				newids++;
				break;
			}
		}
		
		
		graph = new Graph(router.size()+(pcid.size()*2), pairs.size());
		for (int i = 0 ; i < pairs.size(); i++)
		{
			createedge(i, pairs.get(i), pairs2.get(i));
			k=i;
		}
		for(int i = 0 ;i < router.size(); i ++)
		{
			router.get(i).addRoutersDistance(graph.BellmanFord(graph, i));
		}
	
		
		//int i = k; 
		
		for (int i = 0 ; i < router.size(); i++ )
		{
			
				Vector<PC> tmPcs = router.get(i).getPcs();
				//System.out.println(tmPcs.size()+"--------------------------------------------------------------------------------");
				for(int j = 0 ; j < tmPcs.size();j++)
				{
					g.addVertex(tmPcs.get(j).getPCName());
					if(!g.containsEdge(tmPcs.get(j).getclientIp()))
					g.addEdge(tmPcs.get(j).getclientIp(),router.get(i).nameString,tmPcs.get(j).getPCName());
				}
				
			
			
			
		}
	
		
		//System.out.println("-----------------"+k);
		//resetTable();
	
		}else 
		{
			int[] tmpIntegers = new int[router.size()];
			for(int i = 0 ;i < router.size(); i ++)
			{
				tmpIntegers[i]= Integer.MAX_VALUE;
			}
			
			for(int i = 0 ;i < router.size(); i ++)
			{
				router.get(i).addRoutersDistance(tmpIntegers);
			}
			////System.out.println(" ---------------------------------------------------------------------");
		}
		
	}

	private void createedge(int i, int src , int dst)
	{
		graph.edge[i].src = src;
		graph.edge[i].dest = dst;
		graph.edge[i].weight = 1;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 421);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 490, 314);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblRouter = new JLabel("Router: "+routerNameString);
		lblRouter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRouter.setBounds(128, 10, 240, 40);
		frame.getContentPane().add(lblRouter);
	}
	
	public void addHopsToRouters()
	{
		for(int i = 0 ; i < routers.size() ; i ++)
		{
		//	int[]dist=graph.BellmanFord(graph, i);
				
			//routers.get(i).addRoutersDistance( dist);
			
			
		}
	}
	
	public void resetTable()
	{
		
		Vector<String>nameStrings = new Vector<String>();
		nameStrings.add("Router name");
		nameStrings.add("Network");
		nameStrings.add("Distance");
		Vector<Vector<Object>>item = new Vector<Vector<Object>>();
		
		
		if(hasnode) {
		int[] dist=graph.BellmanFord(graph, id);

		for(int i = 0 ; i < routers.size(); i++)
		{
			
			if(id != i) {
			Vector<Object>vector = new Vector<Object>();
			////System.out.println(i+" router id" );
			vector.add(routers.get(i).nameString);
			vector.add(routers.get(i).iPadressString);
			if(dist[i] < 16)
			vector.add(String.valueOf(dist[i]));
			else vector.add("INF");
			item.add(vector);
			}
		}
		DefaultTableModel dtm=new DefaultTableModel(item,nameStrings);
		table=new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		} 
		else {
			for(int i = 0 ; i < routers.size(); i++)
			{
				if(id != i) {
				Vector<Object>vector = new Vector<Object>();
				vector.add(routers.get(i).nameString);
				vector.add(routers.get(i).iPadressString);
				vector.add("INF");
				item.add(vector);
				}
			}
			DefaultTableModel dtm=new DefaultTableModel(item,nameStrings);
			table=new JTable(dtm);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    table.setDefaultEditor(Object.class, null);
			scrollPane.setViewportView(table);
		}
	}
}

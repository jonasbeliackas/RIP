import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;




public class Router {
	public String nameString;
	public String iPadressString;
	public int id;
	private Set<String> nodeIPStrings = new HashSet(); 
	private Set<String> offIPStrings = new HashSet(); 
	private Vector<PC> pc = new Vector<PC>();
	private int[]dist;
	boolean online = true;

	public Vector<PC> getPcs()
	{
		return pc;
	}
	public void addRoutersDistance( int[] distance )
	{
		this.dist = distance;
	}
	public void printdistance()
	{
		System.out.println();
	}
	public void turnoff()
	{
		if(online) {
			offIPStrings.addAll( nodeIPStrings);
			nodeIPStrings.clear();
			online = false;
			System.out.println(offIPStrings);
		}
	}
	public void turnon()
	{
		if(!online) {
			nodeIPStrings.addAll(offIPStrings);
			offIPStrings.clear();
			online = true;
			System.out.println(nodeIPStrings);
		}
	}

	public boolean isOnline()
	{
		return online;
	}
	public boolean routerHasNode()
	{
		if(nodeIPStrings.size() > 0) return true;
		return false;
	}
	public void connectClient(PC pc)
	{
		if(!online)
			JOptionPane.showMessageDialog(null, "Router is offline");
		else {
			this.pc.add(pc);
		}

	}
	public boolean canReach(Router router)
	{
		System.out.println(this.id+" router id "+router.id);
		if(router.id == id)
			return true;
		if(dist.length < router.id)
			return false;
		if(dist[router.id] < 16)
			return true;

		return false;

	}
	public boolean haveThisClient(String name) {
		for(int i = 0; i < this.pc.size() ; i ++)
		{
			if(this.pc.get(i).getPCName() == name)
			{
				return true;
			}
		}
		return false;
	}
	public void update()
	{

	}
	public Router(String nameString , String iPadress)
	{
		this.nameString = nameString;
		this.iPadressString = iPadress;

		online = true;	
	}
	public void setID(int id)
	{
		this.id = id;
	}

	public void AddNode(String Ip)
	{
		int i = nodeIPStrings.size();

		nodeIPStrings.add(Ip);

		if(nodeIPStrings.size()== i)
			JOptionPane.showMessageDialog(null, "Warrning can not add node");
	}
	public void RemoveNode(String Ip)
	{
		nodeIPStrings.remove(Ip);

	}
	public boolean doWeHaveEdge(Router router) {

		for(String ipNode : nodeIPStrings){
			for(String ipString : router.getIPNodes())
			{
				if(ipNode.equals(ipString)) {

					return true;
				}
			}
		}

		return false;
	}
	public String getnodString(Router router)
	{
		for(String ipNode : nodeIPStrings){
			for(String ipString : router.getIPNodes())
			{
				if(ipNode.equals(ipString)) {

					return ipString;
				}
			}
		}
		return "";
	}
	public Set<String> getIPNodes()
	{
		return nodeIPStrings;
	}
	public boolean busy() {
		if(pc.size()==0)
			return false;
		return true;
	}

}

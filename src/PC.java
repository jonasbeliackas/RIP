

public class PC {

	private String routerNameString;
	private String pcNameString;
	private String clientip;
	private int id;
	public PC(String routerNameString ,String pcNameString ,String routerIP,String clientip)
	{
		this.routerNameString = routerNameString;
		this.pcNameString = pcNameString;
		
		this.clientip = clientip;
	}
	public void setid(int id)
	{
		this.id = id;
	}
	public String getPCName()
	{
		return pcNameString;
	}
	public String getclientIp()
	{
		return clientip;
	}
	public String getRouterName()
	{
		return routerNameString;
	}

}

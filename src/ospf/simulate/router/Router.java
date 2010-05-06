package ospf.simulate.router;

import java.util.Vector;

import ospf.simulate.util.InterfaceType;

public class Router {

	public Router(String name) {

		this.name = name;
		initInterfaces();
	}

	private void initInterfaces() {

		this.addInterface(InterfaceType.FastEthernet, 0);
		this.addInterface(InterfaceType.FastEthernet, 1);
		this.addInterface(InterfaceType.FastEthernet, 2);
		this.addInterface(InterfaceType.FastEthernet, 3);
		this.addInterface(InterfaceType.Serial, 0);
		this.addInterface(InterfaceType.Serial, 1);
	}
	
	public void calculateRID() {
		
		// 存储该路由器的所有IP的集合
		Vector<IP> ips = new Vector<IP>();
		// 获取所有的IP
		for (int i = 0; i < interfaces.size(); i++) {
			IP tempIp = interfaces.get(i).getIp();
			if (tempIp == null)
				continue;
			else
				ips.add(tempIp);
		}
		// 如果所有的接口都没有IP，返回
		if (ips.isEmpty()) 
			return;
		// 否则选取RID
		RID = ips.firstElement();
		for (int i = 1; i < ips.size(); i++) {
			if (ips.get(i).compare(RID) == 1) {
				RID = ips.get(i);
			}
		}
	}

	public boolean addInterface(InterfaceType type, int number) {

		Interface temp = new Interface(this, type, number);
		if (interfaces.contains(temp)) {
			return false;
		} else {
			interfaces.add(temp);
			return true;
		}
	}

	
	public String getName() {
		
		return name;
	}

	public IP getRID() {
		
		return RID;
	}

	public Vector<Interface> getInterfaces() {
		
		return interfaces;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String toString() {
		
		return this.name;
	}
	
	private String name;
	private IP RID = null;
	private Vector<Interface> interfaces = new Vector<Interface>();
}

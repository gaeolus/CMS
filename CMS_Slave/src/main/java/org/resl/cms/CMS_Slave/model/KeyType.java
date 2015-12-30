package org.resl.cms.CMS_Slave.model;

public class KeyType {
	String type;
	int prefix;
	int ref;
	
	

	public KeyType() {
		super();
	}
	public KeyType(String type, int prefix, int ref) {
		super();
		this.type = type;
		this.prefix = prefix;
		this.ref = ref;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrefix() {
		return prefix;
	}
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	

	
	

}

package org.resl.gs1.cms.interfaceback;

public class Test {

	public static void main(String[] args) {
		InterfaceBack interfaceBack=new InterfaceBack();
//		String gs1String=interfaceBack.EPCURIToGS1String("urn:epc:id:gsrn:0614141.1234567890");
//		System.out.println(gs1String);
//		String EPCURI=interfaceBack.GS1StringToEPCURI(gs1String);
//		System.out.println(EPCURI);
		
		interfaceBack.configure(112345, 2345, 0, 0);
		System.out.println("Test End");
	}

}

package org.resl.gs1.cms.test;

import org.resl.gs1.cms.backend.Persist;

public class Test {

	public static void main(String[] args) {
		Persist persist=new Persist();
		//persist.test();
		//persist.dropTbales();
		//persist.dropDatabase();
		//persist.createDatabase();
		//persist.createTables();
		persist.insertIntoKeyType("gtin", 12, 1201);
		//persist.updateIdStatus(0);
		//int x=100;
		//x=persist.selectFromIdStatus();
		//System.out.println("Result = " + x);
		System.out.println("Test End");

	}

}

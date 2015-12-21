package org.resl.cms.CMS_Slave.backend;

import org.resl.cms.CMS_Slave.model.Account;

public class Conf {

	public static void main(String[] args) {
		Persist persist=new Persist();
		//persist.dropeDatabase();
		//persist.createDatabase();
		//persist.createTbales();
		Account account=new Account();
		account.setId("Test id");
		account.setBizLocation("TestBiz");
		account.setWritePoint("TestWrie");
		persist.insertIntoSlave(account);
		System.out.println("Config End!");

	}

}

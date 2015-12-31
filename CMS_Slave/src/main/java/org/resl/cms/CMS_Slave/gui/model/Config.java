package org.resl.cms.CMS_Slave.gui.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Config {
	
	private final StringProperty configuration;
	private final StringProperty value;
	
	public Config() {
		this(null, null);
	}
	
	public Config(String configuration, String value){
		this.configuration = new SimpleStringProperty(configuration);
		this.value = new SimpleStringProperty(value);
	}
	
	public String getConfiguration(){
		return configuration.get();
	}
	
	public void setConfiguration(String configuration){
		this.configuration.set(configuration);
	}
	
	public StringProperty configurationProperty(){
		return configuration;
	}
	
	public String getValue(){
		return value.get();
	}
	
	public void setValue(String value){
		this.value.set(value);
	}
	
	public StringProperty valueProperty(){
		return value;
	}
}
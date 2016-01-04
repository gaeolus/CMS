package org.resl.gs1.cms.gui.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GS1Code {
	
	private final StringProperty codeType;
	private final StringProperty prefix;
	private final StringProperty reference;
	
	public GS1Code() {
		this(null, null, null);
	}
	
	public GS1Code(String codeType, String prefix, String reference){
		this.codeType = new SimpleStringProperty(codeType);
		this.prefix = new SimpleStringProperty(prefix);
		this.reference = new SimpleStringProperty(reference);
	}
	
	public String toString(){
		return (codeType.toString() + "," + prefix.toString() + "," + reference.toString());
	}
	
	public String getCodeType(){
		return codeType.get();
	}
	
	public void setCodeType(String codeType){
		this.codeType.set(codeType);
	}
	
	public StringProperty codeTypeProperty(){
		return codeType;
	}
	
	public String getPrefix(){
		return prefix.get();
	}
	
	public void setPrefix(String prefix){
		this.prefix.set(prefix);
	}
	
	public StringProperty prefixProperty(){
		return prefix;
	}
	
	public String getReference(){
		return reference.get();
	}
	
	public void setReference(String reference){
		this.reference.set(reference);
	}
	
	public StringProperty referenceProperty(){
		return reference;
	}
}

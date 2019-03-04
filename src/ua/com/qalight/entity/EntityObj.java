package ua.com.qalight.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntityObj implements Serializable{

	private String strfield;
	private Integer intField;
	private Boolean booleanField;

	public String getStrfield() {
		return strfield;
	}

	public void setStrfield(String strfield) {
		this.strfield = strfield;
	}

	public Integer getIntField() {
		return intField;
	}

	public void setIntField(Integer intField) {
		this.intField = intField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public EntityObj(String strfield, Integer intField, Boolean booleanField) {
		this.strfield = strfield;
		this.intField = intField;
		this.booleanField = booleanField;
	}

	public EntityObj() {
	}

	@Override
	public String toString() {
		return strfield + " - " + intField + " - " + booleanField;
	}
}

package br.com.newfitness.json;

import java.io.Serializable;

public class JsonDataTableReturn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	private Object[] aaData;

	public JsonDataTableReturn() {
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object[] getAaData() {
		return aaData;
	}

	public void setAaData(Object[] aaData) {
		this.aaData = aaData;
	}
	
}

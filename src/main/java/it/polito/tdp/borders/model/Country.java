package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {

	int ccode;
	String StateAbb; 
	String StateNme;
	public Country(int ccode, String stateAbb, String stateNme) {
		super();
		this.ccode = ccode;
		StateAbb = stateAbb;
		StateNme = stateNme;
	}
	public int getCcode() {
		return ccode;
	}
	public String getStateAbb() {
		return StateAbb;
	}
	public String getStateNme() {
		return StateNme;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ccode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return ccode == other.ccode;
	}
	
	

}

package com.aba.service.area.domain;

import java.util.ArrayList;
import java.util.List;

public class AreaResult {

	private int pn ;
	private int total ;
	private List<AreaItem> matches = new ArrayList<AreaItem>() ;
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<AreaItem> getMatches() {
		return matches;
	}
	public void setMatches(List<AreaItem> matches) {
		this.matches = matches;
	}
	
}

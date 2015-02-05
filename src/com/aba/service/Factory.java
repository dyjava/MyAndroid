package com.aba.service;

import com.aba.service.area.AreaDao;
import com.aba.service.area.AreaDaoImpl;

public class Factory {

	private static Factory factory ;
	private Factory(){}
	public static Factory getInstance(){
		if(factory==null){
			factory = new Factory() ;
		}
		return factory ;
	}
	
	public AreaDao getAreaDao(){
		return new AreaDaoImpl() ;
	}
}

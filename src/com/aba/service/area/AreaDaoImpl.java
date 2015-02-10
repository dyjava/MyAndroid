package com.aba.service.area;

import java.io.IOException;

import com.aba.main.util.JsonUtils;
import com.aba.main.util.read.ReadWebUtil;
import com.aba.service.area.domain.AreaRequest;
import com.aba.service.area.domain.AreaResult;

public class AreaDaoImpl implements AreaDao {

	private String host = "http://172.16.18.154:8180/strategyapi" ;
	private ReadWebUtil read = new ReadWebUtil() ;
	@Override
	public AreaResult findAreaById(AreaRequest request) {
		StringBuffer buf = new StringBuffer("AreaDaoImpl|findAreaById") ;
		long start = System.currentTimeMillis() ;
		
		StringBuffer url = new StringBuffer(host) ;
		url.append("/area_search.do?pid=u01&vt=1&pt=1&id=").append(request.getId()) ;
		
		String content = "" ;
		try {
			content = read.readUrlContent(url.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AreaResult result = JsonUtils.json2bean(content, AreaResult.class);
		
		buf
		.append("|").append(url)
//		.append("|").append(result.getMatches().size())
		.append("|").append(content.length())
		.append("|").append(System.currentTimeMillis()-start) ;
		
		return result;
	}

	@Override
	public AreaResult findAreaByParentId(AreaRequest request) {
		StringBuffer buf = new StringBuffer("AreaDaoImpl|findAreaById") ;
		long start = System.currentTimeMillis() ;
		
		StringBuffer url = new StringBuffer(host) ;
		url.append("/area_leve.do?pid=u01&vt=1&pt=1&leve=").append(request.getLeve()) ;
		
		String content = "{ \"pn\": \"1\", \"total\": \"362\", \"matches\": [{ \"id\": \"3579\", \"areaid\": \"12020000\", \"cname\": \"唐山市\", \"spellname\": \"tang shan \", \"alias\": \"唐山\", \"areaidp\": \"12000000\", \"cnamep\": \"河北省\", \"aliasp\": \"河北\", \"showpt\": \"1\"},{ \"id\": \"3581\", \"areaid\": \"12040000\", \"cname\": \"邯郸市\", \"spellname\": \"han dan \", \"alias\": \"邯郸\", \"areaidp\": \"12000000\", \"cnamep\": \"河北省\", \"aliasp\": \"河北\", \"showpt\": \"1\"}]}" ;
//		content = read.get(url.toString()) ;
		
		try {
			content = read.readUrlContent(url.toString()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AreaResult result = JsonUtils.json2bean(content, AreaResult.class);
		
		buf
		.append("|").append(url)
//		.append("|").append(result.getMatches().size())
		.append("|").append(content.length())
		.append("|").append(System.currentTimeMillis()-start) ;
		
		return result;
	}

}

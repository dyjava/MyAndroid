package com.aba.service.area;

import com.aba.service.area.domain.AreaRequest;
import com.aba.service.area.domain.AreaResult;

public interface AreaDao {

	public AreaResult findAreaById(AreaRequest request) ;
	
	public AreaResult findAreaByParentId(AreaRequest request) ;
}

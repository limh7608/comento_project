package com.devfun.settingweb_boot.dao;
import java.util.HashMap;
import java.util.List;

import com.devfun.settingweb_boot.dto.StatisticDto;
 
public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);

	public List<HashMap<String, Object>> departmentInfo(String year);

	public HashMap<String, Object> holidays();
	
	public HashMap<String, Object> avgLoginCnt();
 
}
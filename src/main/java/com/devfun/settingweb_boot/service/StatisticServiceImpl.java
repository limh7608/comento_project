package com.devfun.settingweb_boot.service;
 
 
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.devfun.settingweb_boot.dao.StatisticMapper;
 
@Service
public class StatisticServiceImpl implements StatisticService {
    
    
    @Autowired
    private StatisticMapper uMapper;
    
    @Override
    public HashMap<String, Object> yearloginNum (String year) {
        
        HashMap<String, Object> retVal = new HashMap<String,Object>();
        HashMap<String, Object> departmentMap = new HashMap<String,Object>();
        HashMap<String, Object> holidays = new HashMap<String,Object>();
        HashMap<String, Object> avgLogin = new HashMap<String,Object>();
   
        try {
        	// 첫번째 쿼리 실행
            retVal = uMapper.selectYearLogin(year);
            // department값이 여러개이므로 List형 객체 생성
            List<HashMap<String, Object>> departmentList = new ArrayList<>();
            // 부서별 데이터를 리턴하는 메소드 호출
            departmentList = uMapper.departmentInfo(year);
            // 휴일을 제외한 로그인 수를 리턴하는 메소드 호출
            holidays = uMapper.holidays();
            // 평균 하루 로그인 수를 리턴하는 메소드 호출
            avgLogin = uMapper.avgLoginCnt();
            
            /* department의 값을 키로, departmentCnt값을 값으로 출력하기 위해 
            deparmentMap객체에 저장함 */
            
            for (HashMap<String, Object> department : departmentList) {
            	departmentMap.put((String)department.get("department"),department.get("departmentCnt"));
            }
            retVal.put("department", departmentMap);
            
            
            retVal.put("excludHolidays", holidays);
            retVal.put("avgLogin", avgLogin);
            
            retVal.put("returnCode", "0000");
            
        }catch(Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("year", year);
            retVal.put("returnCode", "1111");
        }
        
        return retVal;
    }
 
}
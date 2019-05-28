package kr.or.ddit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CookieUtil.class);
	
	public static String cookieString;	//분석할 크키 문자열

	/**
	* Method : setCookieString
	* 작성자 : PC22
	* 변경이력 :
	* @param cookieString
	* Method 설명 : 분석할 쿠키 문자열을 받는다
	*/
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
		
		
	}

	/**
	* Method : getCookie
	* 작성자 : PC22
	* 변경이력 :
	* @param cookie
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	*/
	public static String getCookie(String cookie) {
		//"userId=brown; remember=true; test=testValue";
		//cookie = "userId"
		
		String[] cookieArray = CookieUtil.cookieString.split("; ");
		//형태 : cookieName=cookieValue
		//cookieArray[0] = "userId=brown"
		//cookieArray[1] = "remember=true"
		//cookieArray[2] = "test=testValue"
		String cookieValue="";
		for(String str : cookieArray){
			logger.debug("str : {}", str);
			
			//userId = brown
			//userI ==> "", "brown"
			
			//"userId=brown".startsWith("userI=");
//			if(str.startsWith(cookie)){
			if(str.startsWith(cookie+"=")){
				String[] cookieStr = str.split("=");
				
//				//logger확인을위한 것!! 로직 아니다
//				for(String str2 : cookieStr)
//					logger.debug("cookieStr : {}", str2);
//				//cookieStr[0] = "userId"
//				//cookieStr[1] = "true"
				
				cookieValue = cookieStr[1];
				break;
			}
		}
		return cookieValue;
		
	}
	
//	public static String getCookie2(String cookie) {
//		
//		//"userId=brown; remember=true; test=testValue";
//		//cookie = "userId"
//		String a = "userId=brown; remember=true; test=testValue";
//		String[] b = a.split("; ");
//		String c="";
//		for(int i = 0; i< b.length; i++){
//			c = b[i].substring(a.indexOf("=") );
//		}
//
//		return c;
//	}
	
	public static void main(String[] args) {
		System.out.println(CookieUtil.getCookie("userId"));
		
		
	}

}





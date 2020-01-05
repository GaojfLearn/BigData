package utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	
	// 根据Cookie的name获取响应的Cookie
	public static Cookie findCookieByName(Cookie[] cs,String name){
		
		if(cs!=null){
			
			for(Cookie c:cs){
				
				if(name.equals(c.getName())){
					return c;
				}
			}
		}
		
		return null;
	}

}

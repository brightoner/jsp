package kr.or.ddit.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.controller.Controller;
import kr.or.ddit.controller.MainController;
import kr.or.ddit.controller.UserListController;
import kr.or.ddit.model.DbVo;
import kr.or.ddit.servise.DbService;
import kr.or.ddit.servise.IDbService;

public class RequestMapping {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RequestMapping.class);

	// /main.do -> Maincontroller
	// /userList.do -> UserListcontroller

	
	//Map사용 (1)
//	private static Map<String, String> requestMappingClass;
	
	private static Map<String, Controller> requestMapping;
	
	//List사용 (2)
	private static IDbService dbService = new DbService();

	static {
		
		/*
		
		//Map 사용 (1)
		
		requestMappingClass = new HashMap<String, String>();
		requestMappingClass.put("/main.do", "kr.or.ddit.controller.MainController");
		// requestMappingClass.put("/main.do",
		// "kr.or.ddit.controller.MainController");

		requestMapping = new HashMap<String, Controller>();

		for (String key : requestMappingClass.keySet()) {
			// classInfo : "kr.or.ddit.controller.MainController"
			String classInfo = requestMappingClass.get(key);
			try {
				Class clazz = Class.forName(classInfo);
				Object obj = clazz.newInstance();
				requestMapping.put(key, (Controller) obj);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		*/
		
		//Map --> List 변경 (2)
		List<DbVo> uriClassMappingList = dbService.getUrimappingList();
		
		requestMapping = new HashMap<String, Controller>();
		
		for (DbVo dbVo : uriClassMappingList) {
			// classInfo : "kr.or.ddit.controller.MainController"
			String classInfo = dbVo.getClassname();
			try {
				Class clazz = Class.forName(classInfo);
				Object obj = clazz.newInstance();
				requestMapping.put(dbVo.getUri(), (Controller) obj);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		

		// requestMapping.put("/main.do", new MianController());
		// requestMapping.put("/userList.do", new UserListController());

	}

	public static Controller getController(String uri) {
		
		logger.debug("getController :{}",uri);

		return requestMapping.get(uri);
	}

}

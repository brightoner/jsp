package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class partTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(partTest.class);

	@Test
	public void getFileNmeTest() {
		/***Given***/
		String contentDisposition = "form-date; name=\"profile\"; filename=\"sally.png\"";

		/***When***/
		//중복되지 않을  random아이디를 만들어준다
		String fileName = PartUtil.getFileName(contentDisposition);

		/***Then***/
		assertEquals("sally.png", fileName);

	}
	
	//uuid test
	@Test
	public void uuidTest(){
		/***Given***/
		

		/***When***/
		logger.debug("UUID.randomUUID().toString() : {}", UUID.randomUUID().toString());

		/***Then***/

	}
	
	@Test
	public void getExtTest(){
		/***Given***/
		String fileName = "sally.png";
		String fileName2 = "sally.picture.png";
		String fileName3 = "sally";

		/***When***/
		String ext = PartUtil.getExt(fileName);
		String ext2 = PartUtil.getExt(fileName2);
		String ext3 = PartUtil.getExt(fileName3);
				

		/***Then***/
		assertEquals("png", ext);
		assertEquals("png", ext2);
		assertEquals("", ext3);

		
	}
	
	@Test
	public void substringTst(){
		/***Given***/
		String yyyyMM = "201906";
				

		/***When***/
		String yyyy = yyyyMM.substring(0,4);
		String mm = yyyyMM.substring(4,6);

		/***Then***/
		assertEquals("2019", yyyy);
		assertEquals("06", mm);

	}
	

}

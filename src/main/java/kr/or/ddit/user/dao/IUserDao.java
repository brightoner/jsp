package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;


/**
* Method : userList
* 작성자 : PC22
* 변경이력 :
* @return
* Method 설명 : 사용자 전체 조회
*/
public interface IUserDao {
	
	public List<UserVo> userList();

}

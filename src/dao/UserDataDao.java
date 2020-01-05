package dao;

import java.util.List;

import bean.UserData;

public interface UserDataDao {

	public UserData logining(String username,String password) ;
	
	public int insertUserData(UserData userdata);
	
	public List<UserData> findAll();
	
	public int upDate(UserData userdata);
	
	public UserData findUserDataById(int id);
	
	public int delDataById(int id);
	
	public UserData findDataByName(String username);
	
}

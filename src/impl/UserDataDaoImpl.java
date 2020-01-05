package impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.UserData;
import dao.UserDataDao;
import utils.C3P0Util;

public class UserDataDaoImpl implements UserDataDao {

	@Override
	public UserData logining(String username, String password) {

		UserData userdata = null;

		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

//		判断条件查询

		String sql = "select * from userdata where username = ? and password=?";

		try {
//			将查询结果放入userdata对象中
			userdata = qr.query(sql, new BeanHandler<>(UserData.class), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userdata;
	}

	@Override
	public int insertUserData(UserData userdata) {
		
		int result = 0;
		
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into userdata values(null,?,?,?,?,?)";
		
		try {
			result = qr.update(sql, new Object[] {userdata.getUsername(),userdata.getPassword(),userdata.getAge(),userdata.getCity(),userdata.getEmail()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UserData> findAll() {
		List<UserData> list = new ArrayList<UserData>();
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from userdata";
		try {
			list = qr.query(sql, new BeanListHandler<>(UserData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int upDate(UserData userdata) {
		int result = 0;
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update userdata set username=?,password=?,age=?,city=?,email=? where id = ?";
		
		try {
			result = qr.update(sql, new Object[] {userdata.getUsername(),userdata.getPassword(),userdata.getAge(),userdata.getCity(),userdata.getEmail(),userdata.getId()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public UserData findUserDataById(int id) {
		UserData userdata = null;
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from userdata where id = ?";
		
		try {
			userdata = qr.query(sql,new BeanHandler<>(UserData.class),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userdata;
	}

	@Override
	public int delDataById(int id) {
		int result = 0;
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from userdata where id = ?";
		
		try {
			result = qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserData findDataByName(String username) {
		UserData userdata = null;
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from userdata where username = ?";
		try {
			userdata = qr.query(sql, new BeanHandler<>(UserData.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userdata;
	}

}

package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	/*
	 * 封装c30工具类
	 * 1.获取数据源（DataSource）方法
	 * 2.获取连接方法
	 * 3.关闭资源的方法
	 * */
	public static DataSource  ds = new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
	
	public static void closeAll(ResultSet rs, Statement st, Connection conn) {
		// resultset 结果集（结果的集合）
		// Statement对象 利用这个对象的createStatement()方法，将sql语句发送到数据库
		// connection接口 提供了连接数据库方法
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

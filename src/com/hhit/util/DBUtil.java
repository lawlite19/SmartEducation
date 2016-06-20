package com.hhit.util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	static InputStream in;
	static Properties p = new Properties();
	
	/**
	 * 连接数据库
	 * */
	public static Connection getConnection(int x) throws Exception{
		in=ClassLoader.getSystemResourceAsStream("config/configuration.properties");
		p.load(in);
		Connection conn = null;
		try {
			Class.forName(p.getProperty("driver"));
			conn = DriverManager.getConnection(p.getProperty("url"+x),
					p.getProperty("username"),
					p.getProperty("password")
					);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		} 
		return conn;
	}
	
	/**
	 * 关闭数据库
	 */
	
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 回滚
	 */
	public static void rollback(Connection conn){
		if(conn!=null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

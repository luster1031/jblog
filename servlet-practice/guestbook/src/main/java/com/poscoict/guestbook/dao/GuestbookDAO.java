package com.poscoict.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.poscoict.guestbook.vo.GuestbookVO;

public class GuestbookDAO {
	public List<GuestbookVO> findAll(){
		List<GuestbookVO> list = new ArrayList<>();
		Connection conn = connectionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select no"
					+ ", name"
					+ ", password"
					+ ", date_format(reg_date, '%Y-%m-%d %H:%i:%s') as reg_date"
					+ ", message "
					+ "from guestbook order by no desc";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				GuestbookVO vo = new GuestbookVO();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setPasswd(rs.getString(3));
				vo.setReg_date(rs.getDate(4));
				vo.setMessage(rs.getString(5));
				list.add(vo);
			}
		}catch(SQLException e) {
			System.out.println("find 에러 : " + e);
		}finally{
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				System.out.println("[gustbookDAO] close error ");
			}
		}
		return list;
	}
	
	public boolean delete(Long no, String password) {
		Connection conn = connectionDB();
		PreparedStatement ps = null;
		int result = 0;
		try {
			String sql = "delete from guestbook where no=? and password=?";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, no);
			ps.setString(2, password);
			result = ps.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("delet 에러 : "+ e);
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(ps != null)
					ps.close();
			}catch (SQLException e) {
				System.out.println("[gustbookDAO] close error ");
			}	
		}
		return result==1;
	}
	
	public boolean insert(GuestbookVO vo) {
		Connection conn = connectionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql = "insert into guestbook values(null, ?, ?, ?, now())";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPasswd());
			ps.setString(3, vo.getMessage());
			
			result = ps.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("insert 에러 : " + e);
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}catch (SQLException e) {
				System.out.println("[gustbookDAO] close error ");
			}			
		}
		return result==1;
	}
	
	private Connection connectionDB(){
		Connection conn = null;
		try {
			// 1. JDBC 드라이버 로딩
//			Class.forName("com.mysql.cj.jdbc.Driver"); -> mysql
			Class.forName("org.mariadb.jdbc.Driver");
					
			
			//	2. 연결하기
			String url = "jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
//			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC"; -> mysql
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}catch(SQLException e) {
			System.out.println("error : " +e);
		}
		return conn;
	}
}

package com.poscoict.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.emaillist.vo.EmaillistVO;

public class EmaillistDAO {
	public List<EmaillistVO> findAll() {
		List<EmaillistVO> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//	3. SQL 준비
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			// 	4. 바인딩 작업 (binding)
			
			
			// 	5. sql 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				//	객체 만들기
				EmaillistVO vo = new EmaillistVO();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				result.add(vo);
			}
		}catch (SQLException e) {
			System.out.print("error : " + e);
		}finally {
			//	자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("MYSQL 연결 성공");
		}
		return result;
	}

	public boolean insert(EmaillistVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			
			//	3. SQL 준비
			String sql = "insert into emaillist values(null, ?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// 	4. 바인딩 작업 (binding)
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			// 	5. sql 실행
			int count = pstmt.executeUpdate();
			result = count ==1;
			
		}catch (SQLException e) {
			System.out.print("error : " + e);
		}finally {
			//	자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("MYSQL 연결 성공");
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException{
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
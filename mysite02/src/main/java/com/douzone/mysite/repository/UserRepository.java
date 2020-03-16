package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {
	
	public Boolean insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user values(null, ?, ?, ?, ?, sysdate())";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, vo.getName());
			pst.setString(2, vo.getEmail());
			pst.setString(3, vo.getPassword());
			pst.setString(4, vo.getGender());
			
			int count = pst.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null)
					pst.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.1.111:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select no, name  from user where email = ? and password = ?";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, vo.getEmail());
			pst.setString(2, vo.getPassword());
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				
				userVo.setNo(no);
				userVo.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if(rs != null)
					rs.close();
				if(pst != null)
					pst.close();
				if(conn !=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return userVo;
	}

	public UserVo findByNo(Long no) {
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select name, email, password, gender from user where no = ?";
			pst = conn.prepareStatement(sql);
			
			pst.setLong(1, no);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				
				userVo = new UserVo();
				
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setPassword(password);
				userVo.setGender(gender);
				userVo.setNo(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if(rs != null)
					rs.close();
				if(pst != null)
					pst.close();
				if(conn !=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return userVo;
	}

	public UserVo update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();
			
			
			//4.sql 문 실행
			String sql = "update user set name = ?, password = ? where no = ?";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, vo.getName());
			pst.setString(2, vo.getPassword());
			pst.setLong(3, vo.getNo());
			
			int count = pst.executeUpdate();
			
			//5. 성공여부
			 result = count == 1;
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if(pst != null)
					pst.close();
				if(conn !=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
}

package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {
	public List<BoardVo> findAll(int page) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date,'%Y-%m-%d %h:%i:%s'), b.user_no, b.depth\r\n"
					+ " from user a, board b\r\n" + " where a.no=b.user_no order by g_no desc, o_no asc limit ?,5";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, page*5);
			rs = pst.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String userName = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Long userNo = rs.getLong(6);
				int depth = rs.getInt(7);
				
				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setDepth(depth);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public BoardVo findbyNo(BoardVo vo) {
		BoardVo boardVo = null;

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select no, title, contents, user_no, g_no, o_no, depth from board where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, vo.getNo());

			rs = pst.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long userNo = rs.getLong(4);
				int gno = rs.getInt(5);
			    int ono = rs.getInt(6);
			    int depth = rs.getInt(7);
			    
				boardVo = new BoardVo();

				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setUserNo(userNo);
				boardVo.setGNo(gno);
				boardVo.setONo(ono);
				boardVo.setDepth(depth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return boardVo;
	}

	public Boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;

		try {
			conn = getConnection();

			String sql = "insert into board\r\n" + 
					" values(null,?,?,0,sysdate(),ifnull((select max(g_no) from board a)+1,1),1,0,?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContents());
			pst.setLong(3, vo.getUserNo());

			int count = pst.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, vo.getNo());

			int count = pst.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public BoardVo update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			String sql = "update board set title = ?, contents = ? where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContents());
			pst.setLong(3, vo.getNo());

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public void hitUpdate(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			String sql = "update board set hit = (hit + 1) where no = ?";
			pst = conn.prepareStatement(sql);

			pst.setLong(1, vo.getNo());

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void orderUpdate(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;
		try {
			conn = getConnection();

			// 4.sql 문 실행
			String sql = "update board set o_no = (o_no +1) where ? < o_no";
			pst = conn.prepareStatement(sql);

			pst.setInt(1, vo.getONo());

			int count = pst.executeUpdate();

			// 5. 성공여부
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원 정리
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void replyInsert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pst = null;
		Boolean result = false;

		try {
			conn = getConnection();

			String sql = "insert into board\r\n" + 
					" values(null,?,?,0,sysdate(),?,?+1,?+1,?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContents());
			pst.setInt(3, vo.getGNo());
			pst.setInt(4, vo.getONo());
			pst.setInt(5, vo.getDepth());
			pst.setLong(6, vo.getUserNo());

			int count = pst.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int TotlaPage() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int total = 0;
		try {
			conn = getConnection();
			String sql = "select count(no) from board";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 정리
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
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
}

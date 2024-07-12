package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.study.dvd.entity.Publisher;
import com.study.dvd.util.DBConnectionMgr;

public class PublisherDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();

	public static int save(Publisher publisher) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values(0, ?)"; // input 에서 입력 받은 값으로 만든 publisher 객체를 DB에 추가할거야!
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // INPUT 으로 입력한 애의 AI 된 KEY값 가져오기 위한 준비... ( input 에 값을 입력하면 name 은 value 이고, key 는 ai )
			pstmt.setString(1, publisher.getPublisherName()); //input 창에 입력한 값의 NAME을 DB에 INSERT!
			successCount = pstmt.executeUpdate(); // 실행 executeQuery 와 동작은 동일한데 성공 갯수를 받아옴,, executeQuery는 select 부분을 받아옴
			rs = pstmt.getGeneratedKeys(); // INPUT 으로 입력한 애의 AI 된 KEY값 가져옴
			while(rs.next()) {
				publisher.setPublisherId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return successCount;
		
	}

}

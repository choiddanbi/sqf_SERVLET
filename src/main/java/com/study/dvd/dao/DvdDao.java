package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Dvd;
import com.study.dvd.util.DBConnectionMgr;

// DB 랑 연결
public class DvdDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance(); 
	
	public static List<Dvd> searchDvdByTitle(String searchText) {
		List<Dvd> dvds = new ArrayList<>(); // dvd를 담을 list(=dvds)
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = pool.getConnection(); // db랑 연결
			StringBuilder sql = new StringBuilder(); // sql 쓸 준비
			sql.append("select * from dvd_view "); 
			sql.append("where title like ? limit 0, 50");
			pstmt = con.prepareStatement(sql.toString()); // sql 쓸 준비2
			pstmt.setString(1, "%" + searchText + "%"); // 첫번째 ?에 접근 >> title 에서 찾으려는 책 찾기 
			rs = pstmt.executeQuery(); // sql 문 실행 한 결과 저장 (select문)
			
			while(rs.next()) { // db 행 한줗한줄 가져오기
				Dvd dvd = Dvd.builder() // dvd 객체에 담기
						.dvdId(rs.getInt(1))
						.registrationNumber(rs.getString(2))
						.title(rs.getString(3))
						.producerId(rs.getInt(4))
						.producerName(rs.getString(5))
						.publisherId(rs.getInt(6))
						.publisherName(rs.getString(7))
						.publicationYear(rs.getInt(8))
						.databaseDate(rs.getDate(9).toLocalDate())
						.build();
				 
				dvds.add(dvd); // dvds(list) 에 dvd객체 추가
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return dvds;
	}
}

package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Producer;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static List<Producer> searchProducerByProducerName(String searchText) {
		List<Producer> producers = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=pool.getConnection(); // 얘로 try/catch 추가 생성
			// String sql = "select * from producer_tb where producer_name like ? limit 0, 50"; 으로 StringBuilder 안쓰고 바로 작성도 가능
			// pstmt = con.prepareStatement(sql);
			StringBuilder sql = new StringBuilder();
			sql.append("select * from producer_tb ");
			sql.append("where producer_name like ? limit 0, 50");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchText + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Producer producer = Producer.builder()
									.producerId(rs.getInt(1))
									.producerName(rs.getString(2))
									.build();
				
				producers.add(producer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return producers;
		
	}
	
	//saveProducer
	public static int save(Producer producer) {
		int successCount = 0; // autoincrement 추가용
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "insert into producer_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //autoincrement 된 key 값 가져오기
			pstmt.setString(1, producer.getProducerName());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); //ai key값 들
			while(rs.next()) {
				producer.setProducerId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		
		return successCount;
	}

}

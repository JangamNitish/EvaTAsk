package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Queries.DBQueries;
import pojo.AccDepositPojo;
import pojo.AccTransHistoryPojo;

public class AccTransHistoryData {
	DBQueries ob = new DBQueries();

	int id;
	List<Integer> list1 = new ArrayList<>();
	List<Object> list = new ArrayList<>();
	List<Object> list2 = new ArrayList<>();

	public List<Object> transactionHistory(AccDepositPojo a, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(ob.accountid());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			id = rs.getInt("accountid");
			list1.add(id);
		}
		System.out.println(list1);
		if (list1.contains(a.getAccountid())) {
			PreparedStatement pstmt1 = conn.prepareStatement(ob.acctrans());
			pstmt1.setInt(1, a.getAccountid());
			ResultSet rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				AccTransHistoryPojo ap = new AccTransHistoryPojo(rs1.getInt("transactionid"), rs1.getInt("account_id"),
						rs1.getInt("amount"), rs1.getString("transactiontype"), rs1.getDate("date"));
				list.add(ap);
			}
			return list;
		} else {
			String strt = "entered id is invalid";
			list2.add(strt);
			return list2;
		}

	}
}

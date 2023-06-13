package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Queries.DBQueries;
import pojo.AccDepositPojo;

public class AccDepositData {
	DBQueries ob = new DBQueries();

	int ac;
	int uid;
	String type = "credit";

	public String Deposits(AccDepositPojo a, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(ob.validateaccid());

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int i = rs.getInt("accountid");
			ac = rs.getInt("Accountnum");
			if (a.getAccountid() == i) {
				PreparedStatement pstmt2 = conn.prepareStatement(ob.getuesrid());
				pstmt2.setInt(1, i);
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					uid = rs2.getInt("user_id");
				}
				PreparedStatement pstmt1 = conn.prepareStatement(ob.deposit());
				pstmt1.setInt(1, a.getAmount());
				pstmt1.setInt(2, i);
				pstmt1.setInt(3, ac);
				pstmt1.setInt(4, uid);
				pstmt1.execute();
				Date date = Date.valueOf(LocalDate.now());
				PreparedStatement pstmt3 = conn.prepareStatement(ob.trans());
				pstmt3.setInt(1, i);
				pstmt3.setString(2, type);
				pstmt3.setInt(3, a.getAmount());
				pstmt3.setDate(4, date);
				pstmt3.execute();
				return "deposited sucessfully";
			} else {
				return "incorrect account id";
			}
		}
		return null;
	}
}

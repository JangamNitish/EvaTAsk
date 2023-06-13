package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Queries.DBQueries;
import pojo.AccBalancePojo;

public class AccBalanceData {

	DBQueries ob = new DBQueries();

	int i, accBalance;

	public String retriveBalance(AccBalancePojo a, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(ob.getaccId());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			i = rs.getInt("accountid");
		}
		if (i == a.getAccountid()) {
			PreparedStatement pstmt1 = conn.prepareStatement(ob.getBalance());
			pstmt1.setInt(1, a.getAccountid());
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				accBalance = rs1.getInt("balance");
			}
			String ac = Integer.toString(accBalance);
			return ac;
		} else {
			return "Entered accountid is invalid";
		}
	}
}

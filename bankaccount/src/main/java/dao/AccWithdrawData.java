package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Queries.DBQueries;
import pojo.AccWithdrawPojo;

public class AccWithdrawData {
	DBQueries ob = new DBQueries();

	int ac;
	int i;
	int x;
	String str = "debited";

	public String withdraw(AccWithdrawPojo a, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(ob.validateaccid());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			i = rs.getInt("accountid");
			ac = rs.getInt("Accountnum");
		}
		if (a.getAccountid() == i) {
			PreparedStatement pstmt1 = conn.prepareStatement(ob.balancecheck());
			pstmt1.setInt(1, i);
			ResultSet rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				x = rs1.getInt("balance");
			}

			if (x > a.getAmount() || x == a.getAmount()) {
				PreparedStatement pstmt2 = conn.prepareStatement(ob.update());
				int z = x - a.getAccountid();
				System.out.println(z);
				pstmt2.setInt(1, z);
				pstmt2.setInt(2, i);
				pstmt2.execute();
				Date date = Date.valueOf(LocalDate.now());
				PreparedStatement pstmt3 = conn.prepareStatement(ob.trans());
				pstmt3.setInt(1, i);
				pstmt3.setString(2, str);
				pstmt3.setInt(3, a.getAmount());
				pstmt3.setDate(4, date);
				pstmt3.execute();
				return "amount withdrawed";
			} else if (a.getAmount() < 0) {
				return "amount entered is invalid";
			} else if (x < a.getAmount()) {
				return "amount not sufficient";
			}
		} else {
			return null;
		}
		return null;
	}
}

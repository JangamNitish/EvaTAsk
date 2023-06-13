package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.validator.routines.EmailValidator;

import Queries.DBQueries;
import pojo.AccCreationPojo;

public class AccCreationData {
	Random rm = new Random();
	int rm1 = rm.nextInt(50);
	int rm2 = (rm1 * 100000) + 21547854;
	int bal = 0;
	int aid;
	DBQueries ob = new DBQueries();
	EmailValidator validator = EmailValidator.getInstance();

	public String creation(AccCreationPojo a, Connection conn) throws SQLException {
		if (a.getUsername() != null && a.getPassword() != null && validator.isValid(a.getEmail())) {
			PreparedStatement pstmt = conn.prepareStatement(ob.insert());
			PreparedStatement pstmt1 = conn.prepareStatement(ob.accnum());
			PreparedStatement pstmt2 = conn.prepareStatement(ob.insertacc());
			PreparedStatement pstmt3 = conn.prepareStatement(ob.selectuid());

			pstmt.setString(1, a.getUsername());
			pstmt.setString(2, a.getPassword());
			pstmt.setString(3, a.getEmail());
			if (a.getPhonenum().matches("\\d{10}")) {
				pstmt.setString(4, a.getPhonenum());
			} else {
				return "enter valid mobile number";
			}

			pstmt.setInt(5, rm1);
			pstmt.execute();
			pstmt1.setInt(1, rm2);
			pstmt1.setInt(2, rm1);
			pstmt1.execute();
			pstmt3.setInt(1, rm1);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				aid = rs.getInt("user_id");
			}
			pstmt2.setInt(1, rm1);
			pstmt2.setInt(2, aid);
			pstmt2.setInt(3, rm2);
			pstmt2.setInt(4, bal);
			pstmt2.execute();

			return "created successfully";
		} else {
			return "input type miss match";
		}
	}

}

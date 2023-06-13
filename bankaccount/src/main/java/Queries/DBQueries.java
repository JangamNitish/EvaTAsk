package Queries;

public class DBQueries {
	
	public String getaccId()
	{
		return "SELECT * FROM account";
	}
	public String getBalance()
	{
		return "SELECT balance FROM account WHERE accountid=?";
	}
	
	public String insert()
	{
		return "INSERT INTO users (username, password, email, phonenum,accountid) VALUES(?,?,?,?,?)";
	}
	public String accnum()
	{
		return "INSERT INTO accountnum VALUES(?,?)";
	}
	public String insertacc(){
	
		return "INSERT INTO account VALUES(?,?,?,?)";
	}
	
	public String selectuid(){
		
		return "SELECT user_id FROM users WHERE accountid=?";
	}
	public String validateaccid(){
		
		return "SELECT * FROM accountnum ";
	}
	public String deposit(){
		
		return "UPDATE account SET balance=? WHERE accountid=? AND Accountnum=? AND userid=?";
	}
	public String getuesrid(){
		
		return "SELECT user_id FROM users WHERE accountid=?";
	}
	public String trans(){
		
		return "INSERT INTO transaction (account_id, transactiontype, amount, date) VALUES(?,?,?,?)";
	}
	
	public String validatesid(){
		
		return "SELECT * FROM accountnum";
	}
	public String getBal(){
		
		return "SELECT balance FROM account WHERE accountid=?";
	}
	public String updatesbal(){
		
		return "UPDATE account SET balance=? WHERE accountid=?";
	}
	public String transaction()
	{
		return "INSERT INTO transaction (account_id, transactiontype, amount, date) VALUES (?,?,?,?)";
	}
	public String accountid(){
		
		return "SELECT * FROM account";
	}
	public String acctrans(){
		
		return "SELECT * FROM transaction WHERE account_id=?";
	}
	public String balancecheck(){
		
		return "SELECT balance FROM account WHERE accountid=?";
	}
	public String update ()
	{
		return "UPDATE account SET balance=? WHERE accountid=?";
	}
	
}

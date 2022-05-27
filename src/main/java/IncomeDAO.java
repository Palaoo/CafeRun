import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IncomeDAO {
	private Statement stmt;
	private Connection conn;

	public void addIncome(IncomeDTO iDTO) throws SQLException {
		try {
			connDB();
			String query = "insert into income values(sq_income.nextval,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, iDTO.getMobile());
			psmt.setInt(2, iDTO.getSeqNo());
			psmt.setInt(3, iDTO.getQty());
			psmt.setString(4, iDTO.getPrice());
			psmt.setString(5, iDTO.getIncome_date());

			System.out.println(iDTO.getMobile() + " " + iDTO.getSeqNo() + " " + iDTO.getQty() + " " + iDTO.getPrice()
					+ " " + iDTO.getIncome_date() + " ");
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchName(IncomeDTO iDTO) throws SQLException {
		try {
			connDB();
			String query = "select name from menu m, income i where m.seqno=i.seqno";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				iDTO.setName(rs.getString("name"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<IncomeDTO> listIncome() {
		ArrayList<IncomeDTO> list = new ArrayList<IncomeDTO>(); // DTO
		try {
			connDB(); // DB ����
			String query = "select * from income i,menu1 m where i.seqno=m.seqno(+) order by order_no"; // query��
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); // ResultSet class�� ������ ���̺�(2���� �迭)�� �����ϰ��ִ�.
			while (rs.next()) {
				int order_No = rs.getInt("order_no");
				String mobile = rs.getString("mobile");
				int seqNo = rs.getInt("seqno");
				int qty = rs.getInt("qty");
				String price = rs.getString("price");
				String income_Date = rs.getString("income_date");
				String name = rs.getString("name");
				IncomeDTO iDTO = new IncomeDTO();
				iDTO.setMobile(mobile);
				iDTO.setSeqNo(seqNo);
				iDTO.setQty(qty);
				iDTO.setPrice(price);
				iDTO.setIncome_date(income_Date);
				iDTO.setName(name);

				list.add(iDTO);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void connDB() { // DB�� ����
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userid = "ora_user";
		String passcode = "human123";

		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, userid, passcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

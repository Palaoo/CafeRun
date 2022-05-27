import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class menuDAO {
	private Statement stmt;
	private Connection conn;

	public int searchMenu(int seqNo) {
		int result = 0;
		try {
			connDB();
			String query = "select name from menu where seqno=" + seqNo + "";
			System.out.println(query);
			this.stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			rs.next();
			result = rs.getInt("seqno");
			System.out.println(rs.getInt("seqno"));
			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateMenu(menuDTO mDTO) {
		try {
			connDB();
			String query = "update menu set name=?,price=? where seqno=?";
			int seqno = 0;


			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDTO.getMenuName());
			pstmt.setInt(2, mDTO.getMenuPrice());
			pstmt.setInt(3, mDTO.getSeqNo());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMenu(int seqNo) {
		try {
			connDB();
			String query = "delete from menu where seqno=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seqNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addnewMenu(menuDTO mDTO) throws SQLException {
		try {
			connDB();
			String query = "insert into menu values(sq.nextval,?,?)";
			String query2 = "insert into menu1 values(sq2.nextval,?,?)";
			PreparedStatement psmt = conn.prepareStatement(query);
			PreparedStatement psmt2 = conn.prepareStatement(query2);

			psmt.setString(1, mDTO.getMenuName());
			psmt.setInt(2, mDTO.getMenuPrice());
			psmt2.setString(1, mDTO.getMenuName());
			psmt2.setInt(2, mDTO.getMenuPrice());

			psmt.executeUpdate();
			psmt2.executeUpdate();
			psmt.close();
			psmt2.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<menuDTO> listMenu() {
		ArrayList<menuDTO> list = new ArrayList<menuDTO>(); // DTO
		try {
			connDB(); // DB 연결
			String query = "select * from menu"; // query문
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); // ResultSet class는 가져온 테이블(2차원 배열)을 저장하고있다.
			while (rs.next()) {
				String menuName = rs.getString("name");
				int menuPrice = rs.getInt("price");
				int seqNo = rs.getInt("seqno");
				menuDTO mDTO = new menuDTO();
				mDTO.setMenuName(menuName);
				mDTO.setMenuPrice(menuPrice);
				mDTO.setSeqNo(seqNo);
				list.add(mDTO);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void connDB() { // DB와 연결
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

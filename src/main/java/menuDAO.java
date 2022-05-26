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

	public int searchMenu(String menuName) {
		int result = 0;
		try {
			connDB();
			String query = "select name from menu where name='" + menuName + "'";
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
			String query = "select menu set price=? where name=?";
			int seqno = 0;

			if ((seqno = searchMenu(mDTO.getMenuName())) == 0) {
				System.out.println("해당하는 메뉴가 DB에 존재하지 않아 수정이 불가합니다.");
			}

			query = "update menu set price=? where seqno=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mDTO.getMenuPrice());
			pstmt.setInt(2, seqno);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMenu(String menuName) {
		try {
			connDB();
			String query = "delete from menu where name=(?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
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
			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, mDTO.getMenuName());
			psmt.setInt(2, mDTO.getMenuPrice());

			psmt.executeUpdate();
			psmt.close();
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
				menuDTO mDTO = new menuDTO();
				mDTO.setMenuName(menuName);
				mDTO.setMenuPrice(menuPrice);
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

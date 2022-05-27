
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addIncomeServlet
 */
@WebServlet("/addIncome")
public class addIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addIncomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("addIncomeServlet ¹ßµ¿");
		String mobile = request.getParameter("mobile");
		int seqNo = Integer.parseInt(request.getParameter("seqNo"));
		int qty = Integer.parseInt(request.getParameter("menuCount"));
		String price = request.getParameter("totalPrice");
		String income_date = request.getParameter("todayDate");
		IncomeDTO iDTO = new IncomeDTO(mobile, seqNo, qty, price, income_date);
		IncomeDAO iDAO = new IncomeDAO();
		try {
			iDAO.addIncome(iDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

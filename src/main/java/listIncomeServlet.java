
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class listIncomeServlet
 */
@WebServlet("/listIncome")
public class listIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public listIncomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IncomeDAO iDAO = new IncomeDAO();
		ArrayList<IncomeDTO> list = iDAO.listIncome();
		StringBuilder outstr = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			IncomeDTO data = new IncomeDTO();
			data = list.get(i);
			outstr.append(data.getMobile() + ":" + data.getName() + ":" + data.getQty() + ":" + data.getPrice() + ":"
					+ data.getIncome_date());
			if (i != list.size() - 1)
				outstr.append(",");
		}
		System.out.println(outstr);
		response.getWriter().print(outstr);
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

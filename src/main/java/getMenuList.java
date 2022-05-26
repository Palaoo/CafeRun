
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getMenuList
 */
@WebServlet("/menuList")
public class getMenuList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getMenuList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		menuDAO mDAO = new menuDAO();
		ArrayList<menuDTO> list = mDAO.listMenu();
		StringBuilder outstr = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			menuDTO data = new menuDTO();
			data = list.get(i);
			outstr.append(data.getMenuName() + ":" + data.getMenuPrice());
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

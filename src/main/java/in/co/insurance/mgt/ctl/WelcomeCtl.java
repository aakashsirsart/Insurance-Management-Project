package in.co.insurance.mgt.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.insurance.mgt.util.ServletUtility;
/**
 * 
 * 
 * Servlet implementation class WelcomeCtl
 * 
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/welcome" })
public class WelcomeCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/*public WelcomeCtl() {
		super();
		// TODO Auto-generated constructor stub
	}*/

	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ServletUtility.forward(IMSView.WELCOME_VIEW, request, response);
	
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return IMSView.WELCOME_VIEW;
	}

}

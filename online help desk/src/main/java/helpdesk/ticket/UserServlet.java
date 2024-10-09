package helpdesk.ticket;


import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	public void init() {
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<user> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Ticket.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CreateTicket.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int ID = Integer.parseInt(request.getParameter("ID"));
		user existingUser = userDAO.selectUser(ID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CreateTicket.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
		    throws SQLException, IOException, ServletException {
		String Name = request.getParameter("Name");
		String Email = request.getParameter("Email");
		String address = request.getParameter("address");

		    if (isNumeric(Name)) {
		        // Display an error message to the user.
		        request.setAttribute("Error", "Name should not be a number.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
		        dispatcher.forward(request, response);
		    } else {
		    	user newUser = new user(Name, Email,address);
				userDAO.insertUser(newUser);
				response.sendRedirect("list");		    }
		}

		// Helper method to check if a string is numeric
		private boolean isNumeric(String str) {
		    return str.matches("-?\\d+(\\.\\d+)?");
		}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
		int ID = Integer.parseInt(request.getParameter("ID"));
		String Name = request.getParameter("Name");
		String Email = request.getParameter("Email");
		String address = request.getParameter("address");
		
		if (isNumeric(Name)) {
	        // Display an error message to the user.
	        request.setAttribute("Error", "Name should not be a number.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
	        dispatcher.forward(request, response);
	    } else {
		user book = new user(ID, Name, Email,address);
		userDAO.updateUser(book);
		response.sendRedirect("list");}
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int ID = Integer.parseInt(request.getParameter("ID"));
		userDAO.deleteUser(ID);
		response.sendRedirect("list");

	}

}

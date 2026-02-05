
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet("/models")
public class ModelsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = new PrintWriter(response.getWriter());
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/classicmodels");
			con = ds.getConnection();
			stm = con.createStatement();
			String parameter;
			if ((parameter = request.getParameter("productline")) != null) {
				rs = stm.executeQuery("select * from products where productline='" +
						request.getParameter("productline") + "'");
				
				// si no devuelve ninguna fila
					// response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				// en caso contrario generar la respuesta
				
				List<Product> productos = new ArrayList<>();
				while (rs.next()) {
					productos.add(new Product(
							rs.getString(1), // productCode
							rs.getString(2), // productName
							rs.getString(3), // productLine
							rs.getString(4), // productScale
							rs.getString(5), // productVendor
							rs.getString(6), // productDescription
							rs.getShort(7),  // quantityInStock
							rs.getFloat(8),  // buyPrice
							rs.getFloat(9)   // MSRP
							));
				}
				Gson gson = new Gson();
				out.println(gson.toJson(productos));
			} 
			else if ((parameter = request.getParameter("vendor")) != null) {
				
			}
			else if ((parameter = request.getParameter("productline")) != null) {
				
			}
			else if (request.getParameterMap().size() == 0) {
				
			}
			else
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

package product;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("html/text");
 
       int id = Integer.parseInt(request.getParameter("product_id"));
       Product pr = ProductDao.editProduct(id);
      
       request.setAttribute("edit", pr);
       
       request.getRequestDispatcher("./editProduct.jsp").forward(request, response);

          }
}
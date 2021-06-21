package ru.ash.task;

import ru.ash.task.persist.Product;
import ru.ash.task.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException{
       productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();
        PrintWriter pw = resp.getWriter();

        if(req.getPathInfo() == null){
            resp.setHeader("Content-Type", "text/html; charset=utf-8");
            resp.getWriter().println("<caption>");
            resp.getWriter().println("Products");
            resp.getWriter().println("</caption>");
            resp.getWriter().println("<table>");
            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Id</th>");
            pw.println("<th>Title</th>");
            pw.println("</tr>");
            for (Product product : products) {
                resp.getWriter().println("<tr>");
                resp.getWriter().println("<td>");
                resp.getWriter().println(product.getId());
                resp.getWriter().println("</td>");
                resp.getWriter().println("<td>");
                String message = "/servlet-app/products/" + (product.getId() - 1);
                pw.println("<a href = '" + message + "'>");
                pw.println(product.getTitle());
                pw.println("</a>");
                resp.getWriter().println("</td>");
                resp.getWriter().println("</tr>");
            }
            resp.getWriter().println("</table>");
        } else {
            int id = 0;
            if (req.getPathInfo() != null) {
                String info = req.getPathInfo().substring(1);
                try {
                    id = Integer.parseInt(info);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath()+ "/first_servlet");
                }
            }
            resp.setHeader("Content-Type", "text/html; charset=utf-8");
            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Id</th>");
            pw.println("<th>Title</th>");
            pw.println("<th>Cost</th>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>");
            pw.println(products.get(id).getId());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(products.get(id).getTitle());
            pw.println("</td>");
            pw.println("<td>");
            pw.println(products.get(id).getCost());
            pw.println("</td>");
            pw.println("</tr>");
            pw.println("</table>");
        }

    }
}

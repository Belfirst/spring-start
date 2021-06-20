package ru.ash.task;

import ru.ash.task.persist.Product;
import ru.ash.task.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException{
       productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().println("<caption>");
        resp.getWriter().println("Products");
        resp.getWriter().println("</caption>");
        resp.getWriter().println("<table>");
        for (Product product : products) {
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<td>");
            resp.getWriter().println(product.getId());
            resp.getWriter().println("</td>");
            resp.getWriter().println("<td>");
            resp.getWriter().println(product.getTitle());
            resp.getWriter().println("</td>");
            resp.getWriter().println("</tr>");
        }
        resp.getWriter().println("</table>");
    }
}

package ru.ash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // делегирует обработку запроса другому сервлету на сервере клиент при этом не задействуется
//        getServletContext().getRequestDispatcher("/first_servlet").forward(req,resp);
        // возвращает клиенту адрес, по которому нужно обратится для обработки его запроса
        resp.sendRedirect(req.getContextPath()+ "/first_servlet");
    }
}

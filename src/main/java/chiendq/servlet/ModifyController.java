package chiendq.servlet;

import chiendq.dao.ItemDAO;
import chiendq.entities.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "modify-controller", value = "/modify-controller")
public class ModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        int id = Integer.parseInt(request.getParameter("id"));
        String edit = request.getParameter("edit");
        String bool = request.getParameter("status");
        String description = request.getParameter("description");

        ItemDAO itemDAO = new ItemDAO();
        if(delete != null  && delete.equals("Delete")){
            itemDAO.delete(description);
            response.sendRedirect("http://localhost:8080/simple_todo_app_war/home");
            System.out.println("Delete successfully");
        }else if(edit != null && edit.equalsIgnoreCase("Edit")){
            itemDAO.updateDescription(id, description);
            response.sendRedirect(request.getContextPath()+"/home");
        }else if(bool!= null && bool.equalsIgnoreCase("Done") ){
            itemDAO.updateStatus(id,0);
            response.sendRedirect(request.getContextPath()+"/home");
            System.out.println("status updated");
        }else if(bool!= null && bool.equalsIgnoreCase("Undone") ){
            itemDAO.updateStatus(id,1);
            response.sendRedirect(request.getContextPath()+"/home");
            System.out.println("status updated");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

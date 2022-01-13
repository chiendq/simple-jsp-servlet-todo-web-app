package chiendq.servlet;

import chiendq.dao.ItemDAO;
import chiendq.entities.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "render", value = "/render")
public class RenderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String htmlHeader =
            "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "<title>JSP Servlet Todo List</title>" +
            "<link href=\"style.css\" rel=\"stylesheet\">"+
            "</head>" +
            "<body>" +
            "<div class=\"app-container\" id=\"taskList\">" ;

        String htmlCreateItem =
                "<h1 class=\"app-header\">TODO LIST</h1>" +
                "        <div class=\"add-task\">" +
                "            <form action=\"create-controller\" name=\"create-form\" id=\"create-form\">" +
                "                <input type=\"text\" name=\"description\" placeholder=\"Add New Task\" class=\"task-input\">" +
                "                <input id=\"btn-create\" type=\"submit\" value=\"Create\" class=\"submit-task\"></button>" +
                "            </form>" +
                "        </div>"+
                "<ul class=\"task-list\">" +
                "<li class=\"task-list-item\" v-for=\"task in tasks\">";
        // input disabled atrr
        String modifyItemHTML =
                "                <form action=\"modify-controller\" name=\"modify-form\" id=\"mod-form\">" +
                "                    <input type=\"submit\" name=\"status\" value=\"%t\" id=\"status\">" +
                "                    <input type=\"hidden\" name=\"id\" value=\"%d\" id=\"btn-status\">" +
                "                    <input class=\"item-description\"  style=\"text-decoration: line-through;\" type=\"text\" name=\"description\" value=\"%s\">" +
                "                    <input type=\"submit\" name=\"delete\" value=\"Delete\" id=\"delete\">" +
                "                    <input type=\"submit\" name=\"edit\" value=\"Edit\" id=\"edit\">" +
                "                </form>";
        String footerHTML =
                "            </li>" +
                "        </ul>" +
                "    </div>" +
                "</body>" +
                "</html>";
        String listItem = "";
        List<Item> itemList = new ItemDAO().findAll();
        for (Item i: itemList) {
            String des = i.getDescription();
            String newModifyItemHTML = "";
            newModifyItemHTML  = modifyItemHTML.replace("%s",des);
            newModifyItemHTML = newModifyItemHTML.replace("%d",String.valueOf(i.getId()));
            if(i.getStatus() == 1){
                newModifyItemHTML = newModifyItemHTML.replace("%t","Done");
                newModifyItemHTML = newModifyItemHTML.replace("style=\"\"","style=\"text-decoration: line-through;\"");
            }else{
                newModifyItemHTML = newModifyItemHTML.replace("%t","UnDone");
                newModifyItemHTML = newModifyItemHTML.replace("style=\"text-decoration: line-through;\"","style=\"\"");
            }
            listItem+= newModifyItemHTML;
        }
        String HTML  = htmlHeader + htmlCreateItem + listItem ;
        pw.print(HTML);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package transmission;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem :list){

                if(fileItem.isFormField()){
                    resp.setContentType("text/html; charset=UTF-8"); //转码
                    PrintWriter out = resp.getWriter();
                    out.flush();
                    out.println("<script>");
                    out.println("alert('请上传jpg或png格式的图片!');");
                    out.println("history.back();");
                    out.println("</script>");
                }else {
                    String fileName = fileItem.getName();
                    String fileType = fileName.substring(fileName.lastIndexOf("."));
                    if (".jpg".equals(fileType) || ".png".equals(fileType)) {
                        fileName = (String) req.getSession().getAttribute("superUser") + ".jpg";
                        InputStream inputStream = fileItem.getInputStream();
                        String path = req.getServletContext().getRealPath("file/" + fileName);
                        OutputStream outputStream = new FileOutputStream(path);
                        int temp = 0;
                        while ((temp = inputStream.read()) != -1) {
                            outputStream.write(temp);
                        }
                        outputStream.close();
                        inputStream.close();
                        resp.sendRedirect("super?operate=getSuper");
                    }else {
                        resp.setContentType("text/html; charset=UTF-8"); //转码
                        PrintWriter out = resp.getWriter();
                        out.flush();
                        out.println("<script>");
                        out.println("alert('请上传jpg或png格式的图片!');");
                        out.println("history.back();");
                        out.println("</script>");
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}

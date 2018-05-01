/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.gale;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploadFiles";
     
    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
   
        request.setAttribute("message", "Carga existosa!");
        request.setAttribute("fileName", uploadFile(request));
        request.getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
    
    private String uploadFile(HttpServletRequest request){
        String fileName = "";
        try{
            String appPath = request.getServletContext().getRealPath("");
            Part filePart = request.getPart("photo");
            fileName = extractFileName(filePart);
            String savePath = appPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(savePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex){
                ex.printStackTrace();
                fileName = "";
            } finally {
                if (outputStream != null){
                    outputStream.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }
        }catch (Exception e){
            fileName = "";
        }
      return fileName;  
    } 
    
    private String extractFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
    
}

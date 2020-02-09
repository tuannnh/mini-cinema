/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import daos.MovieDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author tuannnh
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class AddMovie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String link = request.getParameter("link");
            String image = saveImgHttpServletRequest(request, response, title);
            String category = request.getParameter("category");

            title = URLEncoder.encode(title, "ISO-8859-1");
            title = URLDecoder.decode(title, "UTF-8");

            MovieDAO dao = new MovieDAO();
            dao.addMovie(title, image, link, category);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            response.sendRedirect("ListMovie");
        }
    }

    private String saveImgHttpServletRequest(HttpServletRequest request, HttpServletResponse response, String title)
            throws ServletException, IOException {
        String url;
        Part filePart = request.getPart("upload"); // Retrieves <input type="file" name="upload">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String docBase = request.getServletContext().getRealPath("");
        File savePath = new File(docBase + File.separator + "resources");
        if (!savePath.exists()) {
            savePath.mkdir();
        }
        File upload = new File(savePath, fileName);

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, upload.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "maimien",
                "api_key", "696888872421777",
                "api_secret", "tjySCewrBowmT002nbJCCdl-12Q"));

        Map option = ObjectUtils.asMap("public_id", title, "transformation", new Transformation().width(350).height(518).crop("limit"));
        Map uploadResult = cloudinary.uploader().upload(upload, option);
        url = (String) uploadResult.get("url");
        return url.replace("http://", "https://");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package com.bluesky.training;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "/Users/bluesky/Uploads";

	public FileUploadServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		if (ServletFileUpload.isMultipartContent(request)) {
			writer.println("File upload has been done successfully");
			try {
				List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				if (formItems != null && (formItems.size()) > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
							writer.println(item.getName());
							request.setAttribute("message", "File upload has been done successfully"+item.getName()+"");							
						}
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		getServletContext().getRequestDispatcher("/FileUploadMessage.jsp").forward(request, response);

	}

}

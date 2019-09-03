package com.bluesky.training;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;

	public FileUploadServlet() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer=response.getWriter();
			 writer.println("Error: Form must has enctype=multipart/form-data.");
	         writer.flush();
	         return;
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
		 File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
		try {
			List<FileItem> formItems=upload.parseRequest(request);
			if(formItems!= null && (formItems.size())>0 ) {
				for(FileItem item:formItems) {
					if(!item.isFormField()) {
						String fileName=new File(item.getName()).getName();
						String filePath=uploadPath+File.separator+fileName;
						File storeFile=new File(filePath);
						item.write(storeFile);
						request.setAttribute("message","File upload has been done successfully");
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/FileUploadMessahe.jsp").forward(request, response);
		
	}

}

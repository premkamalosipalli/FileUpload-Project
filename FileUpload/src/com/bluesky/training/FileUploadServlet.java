package com.bluesky.training;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String fileName = null;
		Connection conn;
		Statement stmt = null;
		ResultSet resultset;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter writer = response.getWriter();
		if (ServletFileUpload.isMultipartContent(request)) {
			writer.println("File upload has been done successfully");
			try {
				List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				if (formItems != null && (formItems.size()) > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							fileName = new File(item.getName()).getName();
							item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
							writer.println(item.getName());
							request.setAttribute("message",
									"File Uploaded Successfully \n PATH:" + UPLOAD_DIRECTORY + "" + fileName + "");
						}
					}
				}
				stmt.execute(
						"CREATE TABLE IF NOT EXISTS Source(STLOC_ID INT PRIMARY KEY, IDENTIFIER VARCHAR(255), PHONE VARCHAR(255),"
								+ " FAX VARCHAR(255),ADDRESS1 VARCHAR(255), ADDRESS2 VARCHAR(255), ADDRESS3 VARCHAR(255),CITY VARCHAR(255),"
								+ " STATE VARCHAR(255), COUNTRY VARCHAR(255),ZIPCODE VARCHAR(255),ACTIVE VARCHAR(255)) "
								+ "AS SELECT * FROM CSVREAD('" + UPLOAD_DIRECTORY + "/" + fileName + "')");
				resultset = stmt.executeQuery("select * from Source");
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		getServletContext().getRequestDispatcher("/FileUploadMessage.jsp").forward(request, response);

	}

}

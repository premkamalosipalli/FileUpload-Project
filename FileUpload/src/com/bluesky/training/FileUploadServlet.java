package com.bluesky.training;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
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

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultset;
		PreparedStatement pstmt;
		Properties property = new Properties();
		InputStream input;

		String stloc_id = null;

		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		property.load(input);
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				if (formItems != null && (formItems.size()) > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							fileName = new File(item.getName()).getName();
							item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
							request.setAttribute("message", "File Uploaded Successfully \n PATH:"
									+ property.getProperty("uploadpath") + "/" + fileName + "");
						} else {
							if (item.getFieldName().equals("stloc_id")) {
								stloc_id = item.getString();
								break;
							}
						}
					}
				}
				stmt.execute(
						"CREATE TABLE IF NOT EXISTS Source(STLOC_ID INT PRIMARY KEY, IDENTIFIER VARCHAR(255), PHONE VARCHAR(255),"
								+ " FAX VARCHAR(255),ADDRESS1 VARCHAR(255), ADDRESS2 VARCHAR(255), ADDRESS3 VARCHAR(255),CITY VARCHAR(255),"
								+ " STATE VARCHAR(255), COUNTRY VARCHAR(255),ZIPCODE VARCHAR(255),ACTIVE VARCHAR(255)) "
								+ "AS SELECT * FROM CSVREAD('" + property.getProperty("uploadpath") + "/" + fileName
								+ "')");
				pstmt = conn.prepareStatement("SELECT STLOC_ID FROM  Source where STLOC_ID =?");
				pstmt.setString(1, stloc_id);
				resultset = pstmt.executeQuery();

				if (!resultset.next()) {// STLOC_ID is not present in the table then insert it in the table..
					System.out.println("stdloc value =" + stloc_id);
					pstmt = conn.prepareStatement(
							"insert into Source(STLOC_ID,IDENTIFIER,PHONE,FAX,ADDRESS1,ADDRESS2,ADDRESS3,CITY,STATE,COUNTRY,ZIPCODE,ACTIVE) values(?,?,?,?,?,?,?,?,?,?,?,?)");
					for (int i = 0; i < formItems.size(); i++) {
						FileItem item = formItems.get(i);
						if (item.isFormField()) {
							pstmt.setString(i, item.getString());
						}
					}

					pstmt.executeUpdate();
					resultset.close();
				} else {

					pstmt = conn.prepareStatement("UPDATE Source set STLOC_ID=?,IDENTIFIER=?,PHONE=?,FAX=?,ADDRESS1=?,"
							+ "ADDRESS2 =?,ADDRESS3=?,CITY=?,STATE=?,COUNTRY=?,ZIPCODE=?,ACTIVE=? WHERE STLOC_ID="
							+ stloc_id + ";");
					for (int i = 0; i < formItems.size(); i++) {
						FileItem item = formItems.get(i);
						if (item.isFormField()) {
							pstmt.setString(i, item.getString());
						}
					}
					pstmt.executeUpdate();

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

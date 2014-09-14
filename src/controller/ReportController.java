package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import list.MyArrayList;
import model.Employee;

public class ReportController {
	/**
	 * Make employees html report of certain department.
	 * @param container - container of data.
	 * @param d - department.
	 */
	public static void makeHtmlReport(MyArrayList<Employee> list){
		File f = new File("html" + File.separator + "report.html");
		try(FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new  BufferedWriter(fw)){
			bw.write("<!Doctype html>");
			bw.newLine();
			bw.write("<html>");
			bw.newLine();
			bw.write("<head>");
			bw.newLine();
			bw.write("<title>Report</title>");
			bw.newLine();
			bw.write("<meta charset = 'utf-8'>");
			bw.newLine();
			bw.write("<style>");
			bw.newLine();
			//css text
			bw.write(""
					+ "div { "
					+ "margin-left: 80px;\n"
					+ "margin-right: 80px;\n"
					+ "text-shadow: #000000 3px 0px, #000000 -3px 0px, #000000 0px 3px, "
					+ "#000000 0px -3px, #000000 3px 3px, "
					+ "#000000 -3px -3px, #000000 -3px 3px, #000000 3px -3px, #000000 0 0 10px, "
					+ "#000000 0 0 10px, #000000 0 0 10px, #000000 0 0 10px, #000000 0 0 10px,"
					+ " #000000 0 0 10px, #000000 0 0 10px, #000000 0 0 10px, #000000 0 0 10px, #000000 0 0 10px;"
					+ "font-family: Tahoma, Geneva, sans-serif;"
					+ "font-size: 120%;"
					+ "color: #FFFFFF;"
					+ "font-weight: bold;"
					+ "font-style: italic;"
					+ "line-height: 140%;"
					+ "letter-spacing: 7px;"
					+ "word-spacing: 0.15em;"
					+ "text-indent: 0.05em;"
					+ "width:250px"
					+ "height: 50px;"
					+ "border:4px solid #777;"
					+ "border-radius:30px;"
					+ "-webkit-border-radius:30px;"
					+ "-moz-border-radius:30px;"
					+ "box-shadow: 0 0 10px 2px #1A3457;"
					+ "-webkit-box-shadow: 0 0 10px 2px #1A3457;"
					+ "moz-box-shadow: 0 0 10px 2px #1A3457;"
					+ "}"
					+ "body {"
					+ "background-image:url(http://identitycourse.com/download/identitybackground.png);"
					+ "}");
			bw.write("</style>");
			bw.newLine();
			bw.write("</head>");
			bw.newLine();
			bw.write("<body>");
			bw.newLine();
			bw.write("<div>");
			bw.newLine();
			StringBuffer sb = new StringBuffer();
			Iterator<Employee> it = list.iterator();
			if(!list.isEmpty())
				bw.write("<div> <h1 align=\"center\">Department: " + list.get(0).getDepartment() + "</h1> </div>");
			while(it.hasNext()){
				sb.append("<div>");
				sb.append("<img src=\"image1.png\"/ width = \"150px\" height = \"250px\" align = \"right\">");
				sb.append(it.next());
				sb.append("</div>");
				sb.append("<br>");
			}
			//insert br for each line
			for(int i = 0; i < sb.length(); i++){
				if(sb.charAt(i) == '\n')
					sb.insert(i+1, "<br>");
			}
			bw.write(sb.toString());
			bw.newLine();
			bw.write("</div>");
			bw.newLine();
			bw.write("</body>");
			bw.newLine();
			bw.write("</html>");
			System.out.println("Html file was created successfully!");
		} catch(Exception e){
			System.out.println("Writing .html report problem!");
		}
	}
}
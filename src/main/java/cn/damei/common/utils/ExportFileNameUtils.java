package cn.damei.common.utils;

import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportFileNameUtils {
	String encodeFilename(String filename, HttpServletRequest request) {

		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}

	static HSSFCellStyle initColumnHeadStyle(HSSFWorkbook wb) {
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		HSSFFont columnHeadFont = wb.createFont();
		columnHeadFont.setFontName("宋体");
		columnHeadFont.setFontHeightInPoints((short) 10);
		columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		columnHeadStyle.setFont(columnHeadFont);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderLeft((short) 1);
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderRight((short) 1);
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);

		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		return columnHeadStyle;
	}
	
	public static HSSFCellStyle initColumnCenterstyle(HSSFWorkbook wb) {  
        HSSFFont font = wb.createFont();  
        font.setFontName("宋体");  
        font.setFontHeightInPoints((short) 10);  
        HSSFCellStyle centerstyle = wb.createCellStyle();  
        centerstyle.setFont(font);  
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        centerstyle.setWrapText(true);  
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);  
        centerstyle.setBorderLeft((short) 1);  
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);  
        centerstyle.setBorderRight((short) 1);  
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);
        return centerstyle;  
    }  
}

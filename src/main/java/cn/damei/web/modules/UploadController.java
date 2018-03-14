package cn.damei.web.modules;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 文件上传工具类
 * 
 * @author yangdc
 * @date Apr 18, 2012
 * 
 * <pre>
 * </pre>
 */
@Controller
@RequestMapping(value = "${adminPath}/upload")
public class UploadController {

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @return infos info[0] 验证文件域返回错误信息 info[1] 上传文件错误信息 info[2] savePath info[3] saveUrl info[4] fileUrl
	 */
	@RequestMapping(value = "/uploadFile")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		BufferedOutputStream bos = null;

		try{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			MultipartFile mulFile = multipartHttpServletRequest.getFile("upload");
			System.out.println(mulFile.getName());
			Properties props = new Properties();
			props.load(UploadController.class.getClassLoader().getResourceAsStream("config.properties"));
			String picUrlBase = props.getProperty("app_pic_url_base");

			String date = DateUtils.getDateTime1();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");

			File filePath = new File(request.getSession().getServletContext().getRealPath("") + "/upload/pic/pc/ueditor/notice/" + date);
			if(!filePath.exists() && !filePath.isDirectory()){
				filePath.mkdirs();
			}
			String url = request.getSession().getServletContext().getRealPath("") + ConstantUtils.UPLOAD_CKEDITOR + date +"/"+uuid + ".jpeg";
			bos = new BufferedOutputStream(new FileOutputStream(new File(url)));
			bos.write(mulFile.getBytes());
			String callback =request.getParameter("CKEditorFuncNum");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/JavaScript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" + picUrlBase + ConstantUtils.UPLOAD_CKEDITOR + date +"/" + uuid + ".jpeg" + "','')");
			out.println("</script>");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				bos.flush();
				bos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}
}

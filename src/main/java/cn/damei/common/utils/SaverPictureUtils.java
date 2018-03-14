package cn.damei.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;

public class SaverPictureUtils {

	public static List<ReportCheckDetailsPic> savePic(String[] photo, HttpServletRequest request, String inspectId) {
		List<ReportCheckDetailsPic> picList = new ArrayList<>();

		Date date = new Date();
		String rootPath = request.getSession().getServletContext().getRealPath("");
		File filePath = new File(rootPath + ConstantUtils.ORDER_COMPLINT + DateUtils.getDate1());
		for (String p : photo) {

			String uuid = UUID.randomUUID().toString().replaceAll("-", "");




			if (!filePath.exists() && !filePath.isDirectory()) {
				filePath.mkdirs();
			}
			String filepath = filePath + filePath.separator + uuid + ".jpeg";
			Base64Util.generateImage(p, filepath);

			String picpath = ConstantUtils.ORDER_COMPLINT + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

			ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
			reportCheckDetailsPic.setBusinessIdInt(Integer.valueOf(inspectId));
			reportCheckDetailsPic.setBusinessType("200");
			reportCheckDetailsPic.setPicUrl(picpath);
			reportCheckDetailsPic.setPicDatetime(date);
			picList.add(reportCheckDetailsPic);

		}
		return picList;

	}


	public static List<ReportCheckDetailsPic> savePhoto(String photoUrl, HttpServletRequest request, String inspectId) {
		List<ReportCheckDetailsPic> picList = new ArrayList<>();

		Date date = new Date();


		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(Integer.valueOf(inspectId));
		reportCheckDetailsPic.setBusinessType("200");
		reportCheckDetailsPic.setPicUrl(photoUrl);
		reportCheckDetailsPic.setPicDatetime(date);
		picList.add(reportCheckDetailsPic);

		return picList;

	}
}

package cn.damei.service.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderInstallPlanPicDao;
import cn.damei.entity.mobile.Manager.OrderInstallPlanPic;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = true)
public class OrderInstallPlanPicService extends CrudService2<OrderInstallPlanPicDao, OrderInstallPlanPic> {

	@Autowired
	private OrderInstallPlanPicDao orderInstallPlanPicDao;


	@Transactional(readOnly = false)
	public void deletePic(Integer valueOf) {
		orderInstallPlanPicDao.deletePic(valueOf);

	}


	@Transactional(readOnly = false)
	public void saveInstallPlanPicBatch(Integer orderInstallPlanId, String[] photo,HttpServletRequest request) throws IOException {

		Date date = new Date();
		List<OrderInstallPlanPic> pList = new ArrayList<OrderInstallPlanPic>();

		if (null != photo && photo.length > 0) {
			for (String pic : photo) {
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				String imgUrl = PicRootName.getConfigValue(ConstantUtils.UPLOAD_ACCEPTANCE);
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
				File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());

				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				String fullPath = filePath + filePath.separator + uuid + ".jpeg";

				Base64Util.generateImage(pic, fullPath.toString());
				OrderInstallPlanPic orderInstallPlanPic = new OrderInstallPlanPic();
				orderInstallPlanPic.setOrderInstallPlanId(orderInstallPlanId);
				orderInstallPlanPic.setPicUrl(picUrl);
				orderInstallPlanPic.setCreateDate(date);
				orderInstallPlanPic.setUpdateDate(date);
				orderInstallPlanPic.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
				pList.add(orderInstallPlanPic);
			}

			dao.saveInstallPlanPicBatch(pList);
		}
	}





}

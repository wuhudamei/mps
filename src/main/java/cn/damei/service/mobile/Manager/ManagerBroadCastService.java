package cn.damei.service.mobile.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.dao.mobile.Manager.ManagerBroadCastDao;
import cn.damei.entity.mobile.Manager.ManagerBroadCastEntity;

@Service
@Transactional(readOnly = true)
public class ManagerBroadCastService {

	private Logger logger = LoggerFactory.getLogger(ManagerBroadCastService.class);
	@Autowired
	private ManagerBroadCastDao dao;

	public List<ManagerBroadCastEntity> findBroadCastList(ManagerBroadCastEntity entity) {

		List<ManagerBroadCastEntity> list = dao.findBroadCastList(entity);
		if (null != list && list.size() > 0) {

			return list;
		} else {
			return null;
		}

	}


	public List<ManagerBroadCastEntity> findBroadCastInfoAndPic(Integer broadcastId) {
		if (broadcastId == 0) {

			return null;
		}

		List<ManagerBroadCastEntity> infoAndPic = dao.findBroadCastInfoAndPic(broadcastId);
		if (null == infoAndPic || infoAndPic.size() <= 0) {

			logger.warn("播报单id查询播报单详情 出错: 根据id查询详情 结果为空  !!  播报单id===" + broadcastId);
			return null;
		} else {

			return infoAndPic;
		}
	}


	@Transactional(readOnly = false)
	public boolean checkBroadcastPicAndStatus(String broadcastId, HttpServletRequest request, Model model,
			String[] photos, String[] picIds, String[] isShow) {
		ArrayList<ManagerBroadCastEntity> list = new ArrayList<ManagerBroadCastEntity>();

		ManagerBroadCastEntity entity = null;
		for (int v = 0; v < picIds.length; v++) {

			entity = new ManagerBroadCastEntity();

			entity.setPicId(Integer.parseInt(ManagerBroadCastService.isNum(picIds[v]) ? picIds[v] : "0"));
			entity.setIsShow(isShow.length > 0 ? isShow[v] : "0");
			if (entity.getPicId().equals(0)) {
				logger.warn("项目经理进行播报图片时: 播报id有误:  id====" + picIds[v]);
				return false;

			} else {

				list.add(entity);

			}

			
		}

					dao.updateCurrentPicStatus(list);


					ArrayList<ManagerBroadCastEntity> list2 = new ArrayList<ManagerBroadCastEntity>();
					if (null != photos && photos.length > 0) {

						for (int vr = 0; vr < photos.length; vr++) {

							String uuid = UUID.randomUUID().toString().replaceAll("-", "");


							String rootPath = request.getSession().getServletContext().getRealPath("");
							File filePath = new File(rootPath + ConstantUtils.UPLOAD_BROADCAST_MANAGER + DateUtils.getDate1());

							if (!filePath.exists() && !filePath.isDirectory()) {
								filePath.mkdirs();
							}
							String filepath = filePath + filePath.separator + uuid + ".jpeg";
							Base64Util.generateImage(photos[vr], filepath);

							String picpath = ConstantUtils.UPLOAD_BROADCAST_MANAGER + DateUtils.getDate1() + filePath.separator + uuid
									+ ".jpeg";





							entity = new ManagerBroadCastEntity();
							entity.setPicDateTime(new Date());
							entity.setPicType("501");
							entity.setPicRelatedId(Integer.parseInt(broadcastId == null ? "0" : broadcastId));
							entity.setPicPath(picpath);
							try {
								entity.setIsShow(isShow[picIds.length + vr]);

							} catch (IndexOutOfBoundsException e) {
								logger.warn("播报图片异常");

								logger.warn("上传图片数量" + photos.length);
								logger.warn("已有图片数量" + picIds.length);
								logger.warn("是否展示数量" + isShow.length);
								return false;
							}


							list2.add(entity);
			}

						dao.savePicAndIsShow(list2);
			
		}

					ManagerBroadCastEntity entity2 = new ManagerBroadCastEntity();
					entity2.setBroadcastId(Integer.parseInt(broadcastId == null ? "0" : broadcastId));
					entity2.setCheckEmployeeId(SessionUtils.getManagerSession(request).getId());
					entity2.setStatusDateTime(new Date());
					entity2.setStatus("20");
					
					entity2.setPicCount(picIds.length+(null!=photos&&photos.length>0?photos.length:0));
					dao.updateBroadCast(entity2);
		return true;
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

}

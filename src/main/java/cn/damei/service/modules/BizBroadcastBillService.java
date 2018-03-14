/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Manager.ManagerBroadCastDao;
import cn.damei.entity.mobile.Manager.ManagerBroadCastEntity;
import cn.damei.service.mobile.Manager.ManagerBroadCastService;
import cn.damei.entity.modules.BizBroadcastBill;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizBroadcastBillDao;

/**
 * 播报信息类Service
 * @author meihao
 * @version 2017-01-10
 */
@Service
@Transactional(readOnly = true)
public class BizBroadcastBillService extends CrudService<BizBroadcastBillDao, BizBroadcastBill> {

	public BizBroadcastBill get(String id) {
		return super.get(id);
	}
	
	public List<BizBroadcastBill> findList(BizBroadcastBill bizBroadcastBill) {
		return super.findList(bizBroadcastBill);
	}
	
	public Page<BizBroadcastBill> findPage(Page<BizBroadcastBill> page, BizBroadcastBill bizBroadcastBill) {
		return super.findPage(page, bizBroadcastBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBroadcastBill bizBroadcastBill) {
		super.save(bizBroadcastBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBroadcastBill bizBroadcastBill) {
		super.delete(bizBroadcastBill);
	}
	
	public List<String> findPackageTempleteList(){
		
		return dao.findPackageTempleteList();
		
	}
	public List<BizBroadcastBill>findBroadCastInfoAndPic(Integer broadcastId){
		
		
		return dao.findBroadCastInfoAndPic(broadcastId);
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
		// 1:批量更新播报图片是否展示 picids isShow
		broadcastDao.updateCurrentPicStatus(list);
					// 2:如果项目经理再次上传图片 , 进行批量保存 photos isShow[picIds.length]

					ArrayList<ManagerBroadCastEntity> list2 = new ArrayList<ManagerBroadCastEntity>();
					if (null != photos && photos.length > 0) {

						for (int vr = 0; vr < photos.length; vr++) {

							String uuid = UUID.randomUUID().toString().replaceAll("-", "");

							// String rootPath = RootName.SystemEnvironment(request);
							String rootPath = request.getSession().getServletContext().getRealPath("");
							File filePath = new File(rootPath + ConstantUtils.UPLOAD_BROADCAST_MANAGER + DateUtils.getDate1());
							// 判断该文件是否存在
							if (!filePath.exists() && !filePath.isDirectory()) {
								filePath.mkdirs();
							}
							String filepath = filePath + filePath.separator + uuid + ".jpeg";
							Base64Util.generateImage(photos[vr], filepath);

							String picpath = ConstantUtils.UPLOAD_BROADCAST_MANAGER + DateUtils.getDate1() + filePath.separator + uuid
									+ ".jpeg";
							// 保存图片到数据库
							// 图片主键(自动生成),图片类型:501 播报图片 图片关联id : broadcastId
							// pucUrl : picpath pic_datetime : new Date()
							// remarks : isShow

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

							// 添加图片到集合中
							list2.add(entity);
			}
						// 如果有图片
						broadcastDao.savePicAndIsShow(list2);
			
		}
					// 3: 更新播报单状态 为20 项目经理已播报 ---> broadcastId
					ManagerBroadCastEntity entity2 = new ManagerBroadCastEntity();
					entity2.setBroadcastId(Integer.parseInt(broadcastId == null ? "0" : broadcastId));
					entity2.setCheckEmployeeId(Integer.parseInt(UserUtils.getUser().getId()));
					entity2.setStatusDateTime(new Date());
					entity2.setStatus("20");
					
					entity2.setPicCount(picIds.length+(null!=photos&&photos.length>0?photos.length:0));
					broadcastDao.updateBroadCast(entity2);
		return true;
	}

	public static boolean isNum(String str) { 
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	@Autowired
	private ManagerBroadCastDao broadcastDao;
}
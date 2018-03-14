
package cn.damei.service.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.Scrap;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizOrderScrapDao;
import cn.damei.entity.modules.BizOrderScrap;
import cn.damei.entity.modules.PublicPic;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizOrderScrapService extends CrudService2<BizOrderScrapDao, BizOrderScrap> {

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

	public BizOrderScrap get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderScrap> findList(BizOrderScrap bizOrderScrap) {
		return super.findList(bizOrderScrap);
	}


	public Page<BizOrderScrap> findPage(Page<BizOrderScrap> page, BizOrderScrap bizOrderScrap) {

		return super.findPage(page, bizOrderScrap);
	}


	public Page<BizOrderScrap> findRecoveryPage(Page<BizOrderScrap> page, BizOrderScrap bizOrderScrap) {
		bizOrderScrap.setPage(page);
		page.setList(dao.findRecoveryList(bizOrderScrap));
		return page;
	}


	@Transactional(readOnly = false)
	public void scrapUpdate(BizOrderScrap bizOrderScrap, String[] photo, HttpServletResponse response, HttpServletRequest request) {
		bizOrderScrap.setScrapOperatorEmployeeId(Integer.parseInt(UserUtils.getUser().getEmpId() == null ? "0" : UserUtils.getUser().getEmpId()));
		bizOrderScrap.setIsScrap(Scrap.IS_SCRAP1);

		savePic(Integer.parseInt(bizOrderScrap.getOrderId()), photo, request);
		dao.scrapUpdate(bizOrderScrap);
	}

	@Transactional(readOnly = false)
	public boolean savePic(Integer purchaseId, String[] photo, HttpServletRequest request) {

		List<PublicPic> pList = new ArrayList<PublicPic>();
		boolean flag = false;

		if (null != photo && photo.length > 0) {
			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_SCRAP + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = ConstantUtils.UPLOAD_SCRAP + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";


				PublicPic purchasePic = new PublicPic();
				purchasePic.setPurchaseId(purchaseId);
				purchasePic.setPicType(ConstantUtils.UPLOAD_SCRAP_209);
				purchasePic.setPicUrl(picpath);
				purchasePic.preInsert();
				pList.add(purchasePic);
			}

			if (null != pList && pList.size() > 0) {
				dao.savePicAll(pList);
			}
		}

		return flag;

	}


	public void storeAndProjectMode(BizOrderScrap bizOrderScrap, Model model) {
		User user = UserUtils.getUser();


		if (null == bizOrderScrap.getStoreId()) {
			if (StringUtils.isNotBlank(user.getStoreId())) {
				bizOrderScrap.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderScrap.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderScrap.setProjectMode(user.getProjectMode());
			}
		}
	}


	@Transactional(readOnly=false)
	public void orderNumberRecovery(BizOrderScrap bizOrderScrap) {
		String orderNumber = bizOrderScrap.getOrderNumber();
		Random random =new java.util.Random();
		int nextInt = random.nextInt(10);
		orderNumber = ConstantUtils.ORDERNUMBER_RECOVERY + orderNumber + nextInt;
		bizOrderScrap.setOrderNumber("'"+orderNumber+"'");
		dao.updateOrderNumber(bizOrderScrap);


		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.ORDERNUMBER_RECOVERY_STATUS_6000);
		statusLog.setBusinessOnlyMarkInt(Integer.parseInt(bizOrderScrap.getOrderId()));
		statusLog.setStatusDatetime(new Date());
		String empId = UserUtils.getUser1().getId();
		if (null != empId) {
			statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
		}
		statusLog.setBusinessRemarks("订单回收");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
	}

}
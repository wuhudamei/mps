
package cn.damei.web.modules;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.entity.modules.QualityControlFineUpdateEntity;
import cn.damei.service.modules.QualityControlFineUpdateService;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "${adminPath}/qualityControlFineUpdate")
public class QualityControlFineUpdateController extends BaseController {
	@Autowired
	private QualityControlFineUpdateService qualityControlFineUpdateService;
	
	@ModelAttribute
	public QualityControlFineUpdateEntity get(@RequestParam(required=false) String id) {
		QualityControlFineUpdateEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qualityControlFineUpdateService.get(id);
		}
		if (entity == null){
			entity = new QualityControlFineUpdateEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = {"list", ""})
	public String list(QualityControlFineUpdateEntity qualityControlFineUpdateEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        if(null==qualityControlFineUpdateEntity.getStoreId()){
            if(null!=user.getStoreId()){
                qualityControlFineUpdateEntity.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		model.addAttribute("page", page);
		model.addAttribute("qualityControlFineUpdateEntity", qualityControlFineUpdateEntity);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateList";
	}
	@RequiresPermissions("qualityControlFineUpdateLog:qualityControlFineUpdateLog:view")
	@RequestMapping(value = {"loglist", ""})
	public String loglist(QualityControlFineUpdateEntity qualityControlFineUpdateEntity, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        if(null==qualityControlFineUpdateEntity.getStoreId()){
            if(null!=user.getStoreId()){
                qualityControlFineUpdateEntity.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		for(QualityControlFineUpdateEntity qc:page.getList()){
			int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qc.getCheckItemId().toString());
			qc.setPicCount(picCount);
		}
		model.addAttribute("page", page);
		model.addAttribute("qualityControlFineUpdateEntity", qualityControlFineUpdateEntity);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLogList";
	}
	
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = "listInfo")
	public String inspectSignListCheck(QualityControlFineUpdateEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		int x = 0;

		if (!UserUtils.getUser().getOffice().getId().equals("1")) {

			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService
				.findPage(new Page<QualityControlFineUpdateEntity>(request, response), checkEntity);

		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", checkEntity);

		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateList";
	}
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = "loglistInfo")
	public String inspectSignListLogCheck(QualityControlFineUpdateEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (null != checkEntity.getBeginModifyDatetime()) {
			model.addAttribute("beginModifyDatetime", checkEntity.getBeginModifyDatetime());
		}
		if (null != checkEntity.getEndModifyDatetime()) {

			model.addAttribute("endModifyDatetime", checkEntity.getEndModifyDatetime());
		}
		int x = 0;

		if (!UserUtils.getUser().getOffice().getId().equals("1")) {

			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService
				.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), checkEntity);

		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		for(QualityControlFineUpdateEntity qc:page.getList()){
			int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qc.getCheckItemId().toString());
			qc.setPicCount(picCount);
		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", checkEntity);

		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLogList";
	}
	@RequestMapping(value = {"fineUpdate", ""})
	public String fineUpdate(String  qcBillCode,String qcCheckItemId, HttpServletRequest request, HttpServletResponse response, Model model){
		QualityControlFineUpdateEntity qualityControlFineUpdateEntity = new QualityControlFineUpdateEntity();
		qualityControlFineUpdateEntity.setReportCode(qcBillCode);
		qualityControlFineUpdateEntity.setQcCheckItemId(Integer.valueOf(qcCheckItemId));
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		QualityControlFineUpdateEntity qf = page.getList().get(0);
		BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
		
		bps.setOrderId(Integer.valueOf(qf.getOrderId()));
		bps.setRelatedBusinessId(qf.getCheckItemId());
		bps.setSettleRole("1");
		bps.setSettleCategory("4");
		
		

		String managerSettleStatus = qualityControlFineUpdateService.findSettleStatus(bps);
		if(null ==managerSettleStatus){
			managerSettleStatus = "";
		}else if(managerSettleStatus.equals("null")){
			managerSettleStatus = "";
		}
		bps.setSettleRole("2");

		String qcSettleStatus = qualityControlFineUpdateService.findSettleStatus(bps);
		
		boolean managerCanUpdate = true;
		boolean qcCanUpdate = true;
		boolean workerCanUpdate = true;
		if(qf.getPunishMoney()!=null&&equal(qf.getPunishMoney(), 0)){
			managerCanUpdate = false; 
		}else	if(managerSettleStatus!=null&&!managerSettleStatus.equals("10")){
			managerCanUpdate = false; 
		}
		if(qf.getInspectorMoney()!=null&&equal(qf.getInspectorMoney(),0)){
			qcCanUpdate = false; 
		}else	if(qcSettleStatus!=null&&!qcSettleStatus.equals("10")){
			qcCanUpdate = false; 
		}
		if(qf.getWorkerMoney()==null){
			
			
		}
		String[] workerCanUpdateStatus = {"80","90","95","110","130"};
		if(qf.getPackageStateId()!=null&&Arrays.asList(workerCanUpdateStatus).contains(qf.getPackageStateId())&&(qf.getWorkerMoney()!=null&&!equal(qf.getWorkerMoney(), 0))){
			workerCanUpdate = true;
		}else{
			workerCanUpdate = false;
		}

		List<BusinessPic> pList = qualityControlFineUpdateService.findPicListByCheckItemId(qf.getCheckItemId().toString());
		model.addAttribute("pList",pList);
		model.addAttribute("managerCanUpdate",managerCanUpdate);
		model.addAttribute("qcCanUpdate",qcCanUpdate);
		model.addAttribute("workerCanUpdate",workerCanUpdate);
		model.addAttribute("report", qf);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdate";
	}
	@RequestMapping(value = {"fineUpdateLog", ""})
	public String fineUpdateLog(String  qcBillCode,String qcCheckItemId, HttpServletRequest request, HttpServletResponse response, Model model){
		QualityControlFineUpdateEntity qualityControlFineUpdateEntity = new QualityControlFineUpdateEntity();
		qualityControlFineUpdateEntity.setReportCode(qcBillCode);
		qualityControlFineUpdateEntity.setQcCheckItemId(Integer.valueOf(qcCheckItemId));
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		QualityControlFineUpdateEntity qf = page.getList().get(0);
		int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qf.getCheckItemId().toString());
		qf.setPicCount(picCount);

		model.addAttribute("report", qf);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLog";
	}
	public static boolean equal(double num1,double num2)
	{
		if((num1-num2>-0.000001)&&(num1-num2)<0.000001){
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping(value = {"saveData", ""})
	public  String saveData(QualityControlFineUpdateEntity report,String[] photo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		QualityControlFineUpdateEntity reportOld = qualityControlFineUpdateService.getBizQcBillCheckItemById(report.getCheckItemId().toString());
		String managerCanUpdate = request.getParameter("managerCanUpdate");
		String qcCanUpdate = request.getParameter("qcCanUpdate");
		String workerCanUpdate = request.getParameter("workerCanUpdate");

		if(managerCanUpdate.equals("false")){
			report.setManagerScore(reportOld.getManagerScore());
			report.setPunishMoney(report.getPunishMoney());
		}
		if(qcCanUpdate.equals("false")){
			report.setInspectorMoney(reportOld.getInspectorMoney());
			report.setInspectorScore(reportOld.getInspectorScore());
		}
		if(workerCanUpdate.equals("false")){
			report.setWorkerMoney(reportOld.getWorkerMoney());
			report.setWorkerScore(reportOld.getWorkerScore());
		}

		if(!equal((double)report.getPunishMoney(),(double)reportOld.getPunishMoney())||(double)report.getManagerScore()!=(double)reportOld.getManagerScore()||
			!equal((double)report.getWorkerMoney(),(double)reportOld.getWorkerMoney())||(double)report.getWorkerScore()!=(double)reportOld.getWorkerScore()||
			!equal((double)report.getInspectorMoney(),(double)reportOld.getInspectorMoney())||(double)report.getInspectorScore()!=(double)reportOld.getInspectorScore()){
			String no = UserUtils.getUser().getNo(); 
			Map<String,String> map = new HashMap<String,String>();
			map.put("checkedId", report.getCheckItemId().toString());
			map.put("no", no);
			qualityControlFineUpdateService.insertBizQcBillCheckItemLog(map);
			qualityControlFineUpdateService.updateBizQcBillCheckItem(report);
		}

		Double punishMoney = report.getPunishMoney();
		if(!equal(report.getPunishMoney(),reportOld.getPunishMoney())){
			BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
			bps.setOrderId(Integer.valueOf(report.getOrderId()));
			bps.setRelatedBusinessId(report.getCheckItemId());
			bps.setSettleRole("1");
			bps.setSettleCategory("4");
			bps.setSettleAmount(punishMoney*(-1));
			if(punishMoney>-0.000001 && punishMoney < 0.000001){
				bps.setSettleStatus("18");
			}else{
				bps.setSettleStatus("10"); 
			}
			qualityControlFineUpdateService.updateBizPmSettleCategoryDetail(bps);
		}

		Double inspectorMoney = report.getInspectorMoney();
		if(!equal(report.getInspectorMoney(),reportOld.getInspectorMoney())){
			BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
			bps.setOrderId(Integer.valueOf(report.getOrderId()));
			bps.setRelatedBusinessId(report.getCheckItemId());
			bps.setSettleRole("2");
			bps.setSettleCategory("4");
			bps.setSettleAmount(inspectorMoney*(-1));
			if(inspectorMoney>-0.000001 && inspectorMoney < 0.000001){
				bps.setSettleStatus("18");
			}else{
				bps.setSettleStatus("10"); 
			}
			qualityControlFineUpdateService.updateBizPmSettleCategoryDetail(bps);
		}
		if(null != photo && photo.length>0){
			List<BusinessPic> pList = new ArrayList<BusinessPic>();
			for(String p : photo){
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				String picpath = ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				BusinessPic businessPic = new BusinessPic();
				businessPic.setId(null);
				businessPic.setBusinessIdInt(report.getCheckItemId());
				businessPic.setPicUrl(picpath);
				businessPic.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_2);
				businessPic.setPicDatetime(new Date());

				pList.add(businessPic);
			}
			
			qualityControlFineUpdateService.saveCheckitemPicAll(pList);
		}
		return "redirect:" + Global.getAdminPath() + "/qualityControlFineUpdate/list";

	}
	@RequestMapping(value = {"delImgById", ""})
	public void delImgById(String id,String picUrl,HttpServletRequest request, HttpServletResponse response) throws IOException{
		qualityControlFineUpdateService.delImgById(id);
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath().split("WEB-INF")[0];
		
		String url = (rootPath+picUrl).replace("/", File.separator).replace("\\", File.separator);
		File file = new File(url);

		JSONObject json = new JSONObject();
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + picUrl + "成功！");
                json.put("status", "success");
                json.put("message", "删除成功！");
            } else {
            	json.put("status", "fail");
                json.put("message", "删除失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + picUrl + "不存在！");
            json.put("status", "fail");
            json.put("message", "删除失败，文件不存在！");
        }
        PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
	}



	@RequestMapping(value="/ajaxphoto")
	@ResponseBody
	public Map<Object, Object> ajaxphoto(Integer checkItemId, String qcBillCode,String qcCheckItemId, Model model) throws IOException{


		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		if(null!=checkItemId) {
			List<BusinessPic> pictures = qualityControlFineUpdateService.findPicListByCheckItemId(checkItemId.toString());
			model.addAttribute("pictures", pictures);
			mapObject.put("mapObject", pictures);
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("qcBillCode", qcBillCode);
		model.addAttribute("qcCheckItemId", qcCheckItemId);
		return mapObject;
	}
	public static void main(String[] args) {
		String[] workerCanUpdateStatus = {"80","90","95","110","120","130"};
		String s ="120";
		System.out.println(!Arrays.asList(workerCanUpdateStatus).contains(s));
		
	}
	
}
package cn.damei.service.modules;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.mdni.commons.file.UploadCategory;
import cn.mdni.commons.view.ViewDownLoad;
import com.google.common.collect.Maps;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallPlanDao;
import cn.damei.entity.modules.BizOrderInstallPlan;
import cn.damei.entity.modules.BizProjectInstallItem;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallPlanService extends CrudService2<BizOrderInstallPlanDao, BizOrderInstallPlan>{
	
	@Autowired
	private BizOrderInstallPlanDao bizOrderInstallPlanDao;
	
	public BizOrderInstallPlan get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallPlan> findList(BizOrderInstallPlan bizOrderInstallPlan) {
		return super.findList(bizOrderInstallPlan);
	}
	
	public Page<BizOrderInstallPlan> findPage(Page<BizOrderInstallPlan> page, BizOrderInstallPlan bizOrderInstallPlan) {
		return super.findPage(page, bizOrderInstallPlan);
	}


	public List<BizOrderInstallPlan> getByList() {
		return bizOrderInstallPlanDao.getByList();
	}


	public BizOrderInstallPlan selectByInstallID(Integer id) {
		return bizOrderInstallPlanDao.selectByInstallID(id);
	}


	@Transactional(readOnly = false)
	public String updateByIdAndStatus(Integer id, String status ,String date, String supplierConfirmRemarks) {
		return bizOrderInstallPlanDao.updateByIdAndStatus(id,status, date,supplierConfirmRemarks) ? "0" : "1";
	}
	
	
	public  List<BizProjectInstallItem> findInstallNameByStoreIdAndProjectModeId(String storeId ,String projectModeId, String isOn){
		
		BizProjectInstallItem bizProjectInstallItem = new BizProjectInstallItem();
		bizProjectInstallItem.setStoreId(storeId);
		bizProjectInstallItem.setProjectMode(projectModeId);
		bizProjectInstallItem.setIsOn(isOn);
		
		return bizOrderInstallPlanDao.findInstallNameByStoreIdAndProjectModeId(bizProjectInstallItem);
		
		
}

    public Page<BizOrderInstallPlan> findUnqualifiedLogPage(Page<BizOrderInstallPlan> bizOrderInstallPlanPage, BizOrderInstallPlan bizOrderInstallPlan,Model model) {

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }

        storeIdAndProjectMode(bizOrderInstallPlan,model);

        bizOrderInstallPlan.setPage(bizOrderInstallPlanPage);
        bizOrderInstallPlanPage.setList(dao.findUnqualifiedLogList(bizOrderInstallPlan));
        return bizOrderInstallPlanPage;
    }

    public List<BizOrderInstallPlan> findUnqualifiedLogList(BizOrderInstallPlan bizOrderInstallPlan){
        return dao.findUnqualifiedLogList(bizOrderInstallPlan);
    }
    public List<String> findUnPhone(String id, String s) {
        return dao.findUnPhone(id,s);
    }

    public List<String> findPhone(String id) {
        return dao.findPhone(id);
    }

    public Page<BizOrderInstallPlan> findOrderInstallAccept(Page<BizOrderInstallPlan> bizOrderInstallPlanPage, BizOrderInstallPlan bizOrderInstallPlan,Model model) {

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }
        storeIdAndProjectMode(bizOrderInstallPlan,model);

        if(StringUtils.isBlank(bizOrderInstallPlan.getStatus())){
            bizOrderInstallPlan.setStatus("4");
        }
        bizOrderInstallPlan.setPage(bizOrderInstallPlanPage);
        bizOrderInstallPlanPage.setList(dao.findOrderInstallAccept(bizOrderInstallPlan));
        return bizOrderInstallPlanPage;
    }

    public BizOrderInstallPlan getOrderDetail(BizOrderInstallPlan bizOrderInstallPlan) {
        return dao.getOrderDetail(bizOrderInstallPlan);
    }

    public List<BizOrderInstallPlan> findItemPlanLog(BizOrderInstallPlan bizOrderInstallPlan) {
        return dao.findItemPlanLog(bizOrderInstallPlan);
    }

    public Page<BizOrderInstallPlan> findUnqualifiedInstallItemCountPage(Page<BizOrderInstallPlan> bizOrderInstallPlanPage, BizOrderInstallPlan bizOrderInstallPlan,Model model) {

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }
        storeIdAndProjectMode(bizOrderInstallPlan,model);
        bizOrderInstallPlan.setPage(bizOrderInstallPlanPage);
        bizOrderInstallPlanPage.setList(dao.findUnqualifiedInstallItemCount(bizOrderInstallPlan));
        return bizOrderInstallPlanPage;
    }

    public Page<BizOrderInstallPlan> findUnqualifiedResonCount(Page<BizOrderInstallPlan> bizOrderInstallPlanPage, BizOrderInstallPlan bizOrderInstallPlan,Model model) {

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }
        storeIdAndProjectMode(bizOrderInstallPlan,model);
        bizOrderInstallPlan.setPage(bizOrderInstallPlanPage);
        bizOrderInstallPlanPage.setList(dao.findUnqualifiedResonCount(bizOrderInstallPlan));
        return bizOrderInstallPlanPage;
    }

    public ModelAndView exportList(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request) throws IOException {
        UploadCategory uploadCategory = UploadCategory.EXCLE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "主材安装验收不合格明细（"+sdf.format(new Date())+"）.xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root+ PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);

        LinkedHashMap<String, String> headerMapper = Maps.newLinkedHashMap();
        headerMapper.put("storeName", "门店");
        headerMapper.put("projectModeName", "工程模式");
        headerMapper.put("orderNumber", "订单编号");
        headerMapper.put("customerName", "客户姓名");
        headerMapper.put("customerAddressDetaill", "客户地址");
        headerMapper.put("itemManager", "项目经理");
        headerMapper.put("installItemName", "安装项名称");
        headerMapper.put("createDate", "提交时间");
        headerMapper.put("unqualifiedReasonConfigure", "订单不合格原因");
        headerMapper.put("acceptRemarks", "备注");

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }
        List<BizOrderInstallPlan> list = dao.findUnqualifiedLogList(bizOrderInstallPlan);
        ExportSingleSheetHelper exportSingleSheetHelper = new ExportSingleSheetHelper(filePath, headerMapper, list);
        exportSingleSheetHelper.build();
        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
        return new ModelAndView(viewDownLoad);
    }

    public ModelAndView exportOrderInstallAccept(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request) throws IOException {
        UploadCategory uploadCategory = UploadCategory.EXCLE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "主材安装验收不合格（"+sdf.format(new Date())+"）.xls";
        String root = request.getSession().getServletContext().getRealPath("/");
        String uploadUrl = root+ PicRootName.getConfigValue(ConstantUtils.UPLOAD);
        String filePath = cn.mdni.commons.file.FileUtils.saveFilePath(uploadCategory, uploadUrl, fileName);

        LinkedHashMap<String, String> headerMapper = Maps.newLinkedHashMap();
        headerMapper.put("storeName", "门店");
        headerMapper.put("orderNumber", "订单编号");
        headerMapper.put("customerName", "客户姓名");
        headerMapper.put("customerAddressDetaill", "客户地址");
        headerMapper.put("itemManager", "项目经理");
        headerMapper.put("installItemName", "安装项名称");
        headerMapper.put("status", "安装项状态");
        headerMapper.put("unqualifiedTimes", "安装不合格次数");
        headerMapper.put("firstPassRate", "一次合格率");

        List<String> projectInstallItemIdList = new ArrayList<String>();
        if(null != bizOrderInstallPlan.getOrderInstallItemId()){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemId()));
        }
        if(StringUtils.isNoneBlank(bizOrderInstallPlan.getOrderInstallItemIdStop())){
            projectInstallItemIdList.add(String.valueOf(bizOrderInstallPlan.getOrderInstallItemIdStop()));
        }
        if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
            bizOrderInstallPlan.setProjectInstallItemIdList(projectInstallItemIdList);
        }
        if(StringUtils.isBlank(bizOrderInstallPlan.getStatus())){
            bizOrderInstallPlan.setStatus("4");
        }
        List<BizOrderInstallPlan> list = dao.findOrderInstallAccept(bizOrderInstallPlan);
        ExportSingleSheetHelper exportSingleSheetHelper = new ExportSingleSheetHelper(filePath, headerMapper, list);
        exportSingleSheetHelper.build();
        ViewDownLoad viewDownLoad = new ViewDownLoad(new File(filePath),null);
        return new ModelAndView(viewDownLoad);
    }

    private void storeIdAndProjectMode(BizOrderInstallPlan bizOrderInstallPlan, Model model) {
        User user = UserUtils.getUser();

        if(null==bizOrderInstallPlan.getStoreId()){
            if(null!=user.getStoreId()){
                bizOrderInstallPlan.setStoreId(user.getStoreId());
            }
        }


        if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals(ConstantUtils.EMPLOYEE_PROJECT_MODE_3)){
            model.addAttribute("gongcheng", true);
        }else{
            bizOrderInstallPlan.setProjectMode(user.getProjectMode());
        }
    }
}

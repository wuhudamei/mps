package cn.damei.web.modules;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderService;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.WorkgroupVo;
import cn.damei.service.modules.AllotWorkerGroupService;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 2017-2-3 加入特殊任务包分配,没有类型限制.只有时间限制
 */
@Controller
@RequestMapping(value = "${adminPath}/AllotWorkerGroup/allotWorkerGroup")
public class AllotWorkerGroupController extends BaseController {

    @Autowired
    private AllotWorkerGroupService allotWorkerGroupService;


    @ModelAttribute(value = "WorkgroupVo")
    public WorkgroupVo get(@RequestParam(required = false) String id) {
        WorkgroupVo entity = null;

        entity = new WorkgroupVo();

        return entity;
    }

    /**
     * 17-2-3加入特殊任务包和地图分配 进行时间复杂度和空间复杂度的优化性能分析
     *
     * @param packageId
     * @param workgroupVo
     * @param request
     * @param turnpageflag
     * @param response
     * @param model
     * @param planStartdate
     * @param planEnddate
     * @param orderBy
     * @return
     * @throws ParseException
     */
    @Autowired
    private OrderService orderService;

    @RequiresPermissions("AllotWorkerGroup:WorkerGroup:view")
    @RequestMapping(value = {"list", ""})
    public String list(String packageId, String packageCode, WorkgroupVo workgroupVo, String itemManagerId, HttpServletRequest request, String turnpageflag,
                       HttpServletResponse response, Model model, String startDate, String endDate, String isRedistribute, String itemManageName, String projectMode, String flag) throws ParseException {


        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }
        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
       
        /*if(projectMode.equals("1")&&"1".equals(flag)){
            workgroupVo.setItemManagerId("");
        	workgroupVo.setItemManageName("");
        	taskpackage.setItemManagerId("");
        	taskpackage.setItemManagerId("");
        	itemManageName = "";
        	itemManagerId = "";
        }else */
        if (projectMode.equals("1")) {
            workgroupVo.setItemManagerId("");
            workgroupVo.setItemManageName("");
            taskpackage.setItemManagerId("");
            taskpackage.setItemManagerId("");
            itemManageName = "";
            itemManagerId = "";
        }
        Order order = new Order();
        String loginUserEmpId = UserUtils.getUser().getEmpId();
        order.setEmpId(loginUserEmpId);
        order.setStoreId(taskpackage.getStoreId());
        String projectMode1 = UserUtils.getUser().getProjectMode();

        if (!"3".equals(projectMode1)) {

            order.setProjectMode(projectMode1);
        }
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());

           /* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPage(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);


        // workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);


      /*  // (判断是否为特殊任务包)
        if (null != taskpackage && taskpackage.getIsSpecialPack().equals("0")) {
            // 特殊任务包
            // 直接过滤时间即可 函数(过滤时间) (传入参数: 总工人集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);

        } else {
            // 不是特殊任务包
            // 过滤掉类型 函数(过滤类型) (传入参数: 总工人集合 , 任务包对象)
            Page<WorkgroupVo> groupWithPlainPack = getWorkerGroupWithPlainPack(page, taskpackage);
            // 过滤掉时间 函数(过滤时间) (传入参数: 过滤类型后的集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(groupWithPlainPack, taskpackage);
        }

        // 最后剩下的就是满足 特殊任务包和普通任务包的工人组


        //通过任务包对象包含的 任务包id和工人组集合 查询相应得信息
*/

        //当前的工人组id
        model.addAttribute("groupId", taskpackage.getEmpGroupid());
        //model.addAttribute("managerName",itemManagerId);
        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroup";
    }

    //待派工人组 分派工人
    @RequiresPermissions("AllotWorkerGroup:WorkerGroup:view")
    @RequestMapping(value = {"listNew", ""})
    public String listNew(String packageId, String packageCode, WorkgroupVo workgroupVo, String itemManagerId, HttpServletRequest request, String turnpageflag,
                          HttpServletResponse response, Model model, String startDate, String endDate, String isRedistribute, String itemManageName, String projectMode, String flag) throws ParseException {


        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }
        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));

    	/*if(projectMode.equals("1")&&"1".equals(flag)){
    		workgroupVo.setItemManagerId("");
    		workgroupVo.setItemManageName("");
    		taskpackage.setItemManagerId("");
    		taskpackage.setItemManagerId("");
    		itemManageName = "";
    		itemManagerId = "";
    	}else*/
        if (projectMode.equals("1")) {
            workgroupVo.setItemManagerId("");
            workgroupVo.setItemManageName("");
            taskpackage.setItemManagerId("");
            taskpackage.setItemManagerId("");
            itemManageName = "";
            itemManagerId = "";
        }
        Order order = new Order();
        String loginUserEmpId = UserUtils.getUser().getEmpId();
        order.setEmpId(loginUserEmpId);
        order.setStoreId(taskpackage.getStoreId());
        String projectMode1 = UserUtils.getUser().getProjectMode();

        if (!"3".equals(projectMode1)) {

            order.setProjectMode(projectMode1);
        }
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		
    		/* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPageNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);


        // workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);
    	
    	
    	/*  // (判断是否为特殊任务包)
        if (null != taskpackage && taskpackage.getIsSpecialPack().equals("0")) {
            // 特殊任务包
            // 直接过滤时间即可 函数(过滤时间) (传入参数: 总工人集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);

        } else {
            // 不是特殊任务包
            // 过滤掉类型 函数(过滤类型) (传入参数: 总工人集合 , 任务包对象)
            Page<WorkgroupVo> groupWithPlainPack = getWorkerGroupWithPlainPack(page, taskpackage);
            // 过滤掉时间 函数(过滤时间) (传入参数: 过滤类型后的集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(groupWithPlainPack, taskpackage);
        }

        // 最后剩下的就是满足 特殊任务包和普通任务包的工人组


        //通过任务包对象包含的 任务包id和工人组集合 查询相应得信息
    	 */

        //当前的工人组id
        model.addAttribute("groupId", taskpackage.getEmpGroupid());
        //model.addAttribute("managerName",itemManagerId);
        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroupNew";
    }

    //待派工人组 分派工人 --特殊
    @RequiresPermissions("AllotWorkerGroup:WorkerGroup:view")
    @RequestMapping(value = {"specialListNew", ""})
    public String specialListNew(String packageId, String packageCode, WorkgroupVo workgroupVo, String itemManagerId, HttpServletRequest request, String turnpageflag,
                                 HttpServletResponse response, Model model, String startDate, String endDate, String isRedistribute, String itemManageName, String projectMode, String flag) throws ParseException {


        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }
        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
    	
    	/*if(projectMode.equals("1")&&"1".equals(flag)){
    		workgroupVo.setItemManagerId("");
    		workgroupVo.setItemManageName("");
    		taskpackage.setItemManagerId("");
    		taskpackage.setItemManagerId("");
    		itemManageName = "";
    		itemManagerId = "";
    	}else*/
        if ("1".equals(projectMode) && !StringUtils.isEmpty(itemManageName)) {
            workgroupVo.setItemManagerId("");
            workgroupVo.setItemManageName("");
            taskpackage.setItemManagerId("");
            taskpackage.setItemManagerId("");
            itemManageName = "";
            itemManagerId = "";
        }
        Order order = new Order();
        String loginUserEmpId = UserUtils.getUser().getEmpId();
        order.setEmpId(loginUserEmpId);
        order.setStoreId(taskpackage.getStoreId());
        String projectMode1 = UserUtils.getUser().getProjectMode();

        if (!"3".equals(projectMode1)) {

            order.setProjectMode(projectMode1);
        }
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		
    		/* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.findPageSpecialListNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);


        // workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);
    	
    	
    	/*  // (判断是否为特殊任务包)
        if (null != taskpackage && taskpackage.getIsSpecialPack().equals("0")) {
            // 特殊任务包
            // 直接过滤时间即可 函数(过滤时间) (传入参数: 总工人集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);

        } else {
            // 不是特殊任务包
            // 过滤掉类型 函数(过滤类型) (传入参数: 总工人集合 , 任务包对象)
            Page<WorkgroupVo> groupWithPlainPack = getWorkerGroupWithPlainPack(page, taskpackage);
            // 过滤掉时间 函数(过滤时间) (传入参数: 过滤类型后的集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(groupWithPlainPack, taskpackage);
        }

        // 最后剩下的就是满足 特殊任务包和普通任务包的工人组


        //通过任务包对象包含的 任务包id和工人组集合 查询相应得信息
    	 */

        //当前的工人组id
        model.addAttribute("groupId", taskpackage.getEmpGroupid());
        //model.addAttribute("managerName",itemManagerId);
        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroupSpecialNew";
    }

    //待派工人组 分派工人
    @RequiresPermissions("AllotWorkerGroup:WorkerGroup:view")
    @RequestMapping(value = {"specialList", ""})
    public String specialList(String packageId, String packageCode, WorkgroupVo workgroupVo, String itemManagerId, HttpServletRequest request, String turnpageflag,
                              HttpServletResponse response, Model model, String startDate, String endDate, String isRedistribute, String itemManageName, String projectMode, String flag) throws ParseException {


        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }
        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
    	
    	/*if(projectMode.equals("1")&&"1".equals(flag)){
    		workgroupVo.setItemManagerId("");
    		workgroupVo.setItemManageName("");
    		taskpackage.setItemManagerId("");
    		taskpackage.setItemManagerId("");
    		itemManageName = "";
    		itemManagerId = "";
    	}else*/
        if (projectMode.equals("1") && !StringUtils.isEmpty(itemManageName)) {
            workgroupVo.setItemManagerId("");
            workgroupVo.setItemManageName("");
            taskpackage.setItemManagerId("");
            taskpackage.setItemManagerId("");
            itemManageName = "";
            itemManagerId = "";
        }
        Order order = new Order();
        String loginUserEmpId = UserUtils.getUser().getEmpId();
        order.setEmpId(loginUserEmpId);
        order.setStoreId(taskpackage.getStoreId());
        String projectMode1 = UserUtils.getUser().getProjectMode();

        if (!"3".equals(projectMode1)) {

            order.setProjectMode(projectMode1);
        }
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		
    		/* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPageNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);


        // workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);
    	
    	
    	/*  // (判断是否为特殊任务包)
        if (null != taskpackage && taskpackage.getIsSpecialPack().equals("0")) {
            // 特殊任务包
            // 直接过滤时间即可 函数(过滤时间) (传入参数: 总工人集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);

        } else {
            // 不是特殊任务包
            // 过滤掉类型 函数(过滤类型) (传入参数: 总工人集合 , 任务包对象)
            Page<WorkgroupVo> groupWithPlainPack = getWorkerGroupWithPlainPack(page, taskpackage);
            // 过滤掉时间 函数(过滤时间) (传入参数: 过滤类型后的集合 , 任务包对象)
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(groupWithPlainPack, taskpackage);
        }

        // 最后剩下的就是满足 特殊任务包和普通任务包的工人组


        //通过任务包对象包含的 任务包id和工人组集合 查询相应得信息
    	 */

        //当前的工人组id
        model.addAttribute("groupId", taskpackage.getEmpGroupid());
        //model.addAttribute("managerName",itemManagerId);
        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroupNew";
    }

    @RequiresPermissions("AllotWorkerGroup:WorkerGroup:edit")
    @RequestMapping(value = {"allotWorker", ""})
    public String allotWorker(String packageId, WorkgroupVo work, HttpServletRequest request, String turnpageflag,
                              HttpServletResponse response, Model model, String startDate, String endDate, String isReDistribute,
                              RedirectAttributes redirectAttributes, String reasonRemarks, String reasonType, String flag) throws IOException, ParseException {
        // 前端 按钮 url 追加 任务包id (形参也接收了 工人组的id)

        // 1: 工人组信息 根据工人组的id
        WorkgroupVo workerGroup = allotWorkerGroupService.get(work.getId());
        workerGroup.setId(work.getId());
        try {
            Boolean isSpecial = null;
            //是否是特殊分派工人组
            if ("1".equals(flag)) {
                isSpecial = true;
            } else {
                isSpecial = false;
            }
            allotWorkerGroupService.updatePackage(packageId, workerGroup, startDate, endDate, isReDistribute, reasonRemarks, reasonType, isSpecial);
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "分配任务包失败了~");
        }
        // 返回到任务包分配页面
        if (null != isReDistribute && "1".equals(isReDistribute)) {//工人接单监控

            addMessage(redirectAttributes, "再分配任务包成功");

            return "redirect:" + Global.getAdminPath() + "/scheduling/orderTaskpackage/workerList";
        } else if ("waitAllot".equals(turnpageflag)) {//待派工查询
            addMessage(redirectAttributes, "分配任务包成功");
            return "redirect:" + Global.getAdminPath()
                    + "/ordertaskpackageaudit/orderTaskpackageAudit/waitAllotTaskpackage";
        } else {
            //分派工人组
            addMessage(redirectAttributes, "分配任务包成功");
            return "redirect:" + Global.getAdminPath() + "/scheduling/orderTaskpackage/listNew";
        }


    }


    /**
     * 高德地图marker工人组和任务包地址,直观分配工人
     *
     * @return
     */
    @RequestMapping(value = "mapAllotCenterNew")
    public String mapAllotCenterNew(String packageId, Model model, WorkgroupVo workgroupVo, String isRedistribute, String turnpageflag, String projectMode) throws IOException {

        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));

        model.addAttribute("taskpakcage", taskpackage);
        model.addAttribute("workgroupVo", workgroupVo);
        model.addAttribute("projectMode", projectMode);
        return "modules/workerGroup/mapAllotWorkerGroupNew";
    }

    /**
     * 高德地图marker工人组和任务包地址,直观分配工人
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "mapAllotCenter")
    public String mapAllotCenter(String packageId, Model model, WorkgroupVo workgroupVo, String isRedistribute, String turnpageflag, String projectMode) throws IOException {

        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));

        model.addAttribute("taskpakcage", taskpackage);
        model.addAttribute("workgroupVo", workgroupVo);
        model.addAttribute("projectMode", projectMode);
        return "modules/workerGroup/mapAllotWorkerGroup";
    }

    @RequestMapping(value = "mapAllot")
    public @ResponseBody
    List<WorkgroupVo> mapAllot(String packageId, Model model, WorkgroupVo workgroupVo,
                               String startDate, String endDate, Integer scopeDistance, String isRedistribute) throws ParseException {


        if (null != isRedistribute && !"".equals(isRedistribute) && "1".equals(isRedistribute)) {

            model.addAttribute("isRedistribute", isRedistribute);
        }

        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());

           /* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
        }
        if (null != scopeDistance) {

            workgroupVo.setAddressToWorkerAddressDistance(String.valueOf(scopeDistance));
        }

        List<WorkgroupVo> list = allotWorkerGroupService.findList(workgroupVo);
        model.addAttribute("taskpakcage", taskpackage);
        if (list.size() > 0) {
            Page page = new Page<WorkgroupVo>();
            page.setList(list);
            workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);
            return workerGroupFilterTimeConflict.getList();
        } else {

            return null;
        }


    }

    @RequestMapping(value = "mapAllotNew")
    public @ResponseBody
    List<WorkgroupVo> mapAllotNew(String packageId, Model model, WorkgroupVo workgroupVo,
                                  String startDate, String endDate, Integer scopeDistance, String isRedistribute) throws ParseException {


        if (null != isRedistribute && !"".equals(isRedistribute) && "1".equals(isRedistribute)) {

            model.addAttribute("isRedistribute", isRedistribute);
        }

        // 声明集合过滤后的工人组
        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;

        // :根据需要派送的任务包id查询任务包对象
        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
        if (null != startDate && !startDate.trim().equals("")) {
            // 开始时间
            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {
            // 结束时间
            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        if (null != taskpackage) {
            // 所有该订单工程区域区域内的工人组
            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));

            //任务包经纬度
            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		
    		/* //开始时间和结束时间
            workgroupVo.setPlanStartDate(taskpackage.getPlanStartdate());
            workgroupVo.setPlanEndDate(taskpackage.getPlanEnddate());*/
            // 该对象加入特殊任务包的标识符 isSpecialPack(为0时 是特殊任务包)
            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
        }
        if (null != scopeDistance) {

            workgroupVo.setAddressToWorkerAddressDistance(String.valueOf(scopeDistance));
        }

        List<WorkgroupVo> list = allotWorkerGroupService.findList(workgroupVo);
    	/*model.addAttribute("taskpakcage", taskpackage);
    	if(list.size()>0) {
    		Page page = new Page<WorkgroupVo>();
    		page.setList(list);
    		workerGroupFilterTimeConflict = getWorkerGroupFilterTimeConflict(page, taskpackage);
    		return workerGroupFilterTimeConflict.getList();
    	}else{
    		
    		return null;
    	}*/
        return list;

    }

	/*
     * @RequestMapping(value = "findWorkerInfoById") public @ResponseBody
	 * List<WorkgroupVo> findWorkerInfoById(String[] ids) {
	 * 
	 * LinkedList<WorkgroupVo> linkedList = new LinkedList<WorkgroupVo>();
	 * 
	 * for (String id : ids) { String str = ""; for (int i = 0; i < id.length();
	 * i++) { if (id.charAt(i) >= 48 && id.charAt(i) <= 57) { str +=
	 * id.charAt(i); } }
	 * 
	 * if (id.equals(",") || id.equals("[") || id.equals('"') || id.equals("0"))
	 * {
	 * 
	 * } else { WorkgroupVo findWorkerGroupInfoById = allotWorkerGroupService
	 * .findWorkerGroupInfoById(Integer.valueOf(str.trim())); // 通过任务包的详细地址得到经纬度
	 * Map<String, Double> map =
	 * LngAndLatUtils.getLngAndLat(findWorkerGroupInfoById.getAddress()); //
	 * 工地的经度 Double lng = map.get("lng"); // 工地的纬度 Double lat = map.get("lat");
	 * findWorkerGroupInfoById.setLng(lng); findWorkerGroupInfoById.setLat(lat);
	 * linkedList.add(findWorkerGroupInfoById); }
	 * 
	 * }
	 * 
	 * return linkedList; }
	 */

    /**
     * 17-2-3分离出function
     *
     * @return 过滤后的工人组
     * @params 工人组list(加入工程部id)
     * @params 任务包对象(普通任务包-->时间和类型的过滤)
     */
    public Page<WorkgroupVo> getWorkerGroupWithPlainPack(Page<WorkgroupVo> page, OrderTaskpackage taskPack) {


        // 1-2: 遍历所有的工人组 比较类型
        Iterator<WorkgroupVo> iterator = page.getList().iterator();
        while (iterator.hasNext()) {
            WorkgroupVo next = iterator.next();
            String[] taskId = next.getTargetPackageId().split(",");
            // 如果不为空
            for (String string : taskId) {
                // 如果不为,
                if (null != string && !",".equals(string)) {

                    // 如果类型一致
                    if (string.equals(String.valueOf(taskPack.getTaskTackageTempId()))) {
                        // 添加到集合中, 遍历下个工人组

                        iterator.remove();
                        // 只要一个类型一致即可
                        break;
                    }

                }

            }

        }


        return page;
    }

    /**
     * 过滤时间不符合要求的工人组
     *
     * @return List<WorkgroupVo>
     */
    public Page<WorkgroupVo> getWorkerGroupFilterTimeConflict(Page<WorkgroupVo> page,
                                                              OrderTaskpackage taskPack) {
        // 类型一致的工人组

        // 如果该任务包没有时间或者没有工人组 就直接不做时间上的剔除

        if (null == taskPack.getPlanEnddate() || page.getList().size() == 0) {
            return page;

        }

        // 根据工人组list 去 任务包表 查询 已接的 任务包
        List<OrderTaskpackage> list2 = allotWorkerGroupService
                .findAllPackageWhomHasEmpGroup(page.getList());

        //需要删除的工人组集合
        List<Integer> wantedDeleteWorkerId = new ArrayList<>();
        // 遍历任务包 比较时间是否冲突
        for (OrderTaskpackage orderTaskpackage : list2) {

            if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate()
                    && null != taskPack.getPlanEnddate() && null != taskPack.getPlanEnddate()) {

                // 判断时间是否冲突
                // 取集合的交集 为不可分配时间
                //即 已分配的任务包 要么开始时间比 要分配的任务包的结束时间大, 要么已分配的任务包的结束时间比要分配的任务包的开始时间

                if ((orderTaskpackage.getPlanStartdate().getTime() > taskPack.getPlanEnddate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < taskPack.getPlanStartdate().getTime())) {
                } else {
                    // 如果冲突就删除
                    wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
                }

            }

        }


        //迭代删除
        Iterator<WorkgroupVo> iterator = page.getList().iterator();
        while (iterator.hasNext()) {
            WorkgroupVo workgroupVo2 = iterator.next();

            if (wantedDeleteWorkerId.contains(workgroupVo2.getWorkerGroupId())) {
                iterator.remove();


            }


        }
        page.setCount(page.getList().size());
        return page;
    }


    public static synchronized void getThreadSleep(Thread thread) {

        try {
            thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

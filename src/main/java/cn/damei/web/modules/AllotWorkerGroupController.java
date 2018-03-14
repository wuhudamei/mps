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

        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
       

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

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());



            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPage(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);








        model.addAttribute("groupId", taskpackage.getEmpGroupid());

        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroup";
    }


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

        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));


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

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		


            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPageNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);



    	
    	



        model.addAttribute("groupId", taskpackage.getEmpGroupid());

        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroupNew";
    }


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

        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
    	

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

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		


            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.findPageSpecialListNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);



    	
    	



        model.addAttribute("groupId", taskpackage.getEmpGroupid());

        model.addAttribute("page", page);
        model.addAttribute("taskPackage", taskpackage);
        model.addAttribute("packageCode", packageCode);
        model.addAttribute("projectMode", projectMode);
        model.addAttribute("itemManagerId", itemManagerId);
        model.addAttribute("itemManageName", itemManageName);
        model.addAttribute("groupName", taskpackage.getGroupRealname());

        return "modules/workerGroup/AllotWorkerGroupSpecialNew";
    }


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

        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
    	

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

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        List<Order> engineList = orderService.findEngineDepartByStoreIdProjectModeIdAndEmpId(order);

        model.addAttribute("engineList", engineList);


        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		


            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
            workgroupVo.setTargetPackageId(packageId);
        }
        workgroupVo.setPackageCode(packageCode);
        Page<WorkgroupVo> page = allotWorkerGroupService.getFindPageNew(new Page<WorkgroupVo>(request, response), workgroupVo, taskpackage);



    	
    	



        model.addAttribute("groupId", taskpackage.getEmpGroupid());

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



        WorkgroupVo workerGroup = allotWorkerGroupService.get(work.getId());
        workerGroup.setId(work.getId());
        try {
            Boolean isSpecial = null;

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

        if (null != isReDistribute && "1".equals(isReDistribute)) {

            addMessage(redirectAttributes, "再分配任务包成功");

            return "redirect:" + Global.getAdminPath() + "/scheduling/orderTaskpackage/workerList";
        } else if ("waitAllot".equals(turnpageflag)) {
            addMessage(redirectAttributes, "分配任务包成功");
            return "redirect:" + Global.getAdminPath()
                    + "/ordertaskpackageaudit/orderTaskpackageAudit/waitAllotTaskpackage";
        } else {

            addMessage(redirectAttributes, "分配任务包成功");
            return "redirect:" + Global.getAdminPath() + "/scheduling/orderTaskpackage/listNew";
        }


    }



    @RequestMapping(value = "mapAllotCenterNew")
    public String mapAllotCenterNew(String packageId, Model model, WorkgroupVo workgroupVo, String isRedistribute, String turnpageflag, String projectMode) throws IOException {

        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));

        model.addAttribute("taskpakcage", taskpackage);
        model.addAttribute("workgroupVo", workgroupVo);
        model.addAttribute("projectMode", projectMode);
        return "modules/workerGroup/mapAllotWorkerGroupNew";
    }


    @RequestMapping(value = "mapAllotCenter")
    public String mapAllotCenter(String packageId, Model model, WorkgroupVo workgroupVo, String isRedistribute, String turnpageflag, String projectMode) throws IOException {

        if (null != isRedistribute) {

            model.addAttribute("isRedistribute", isRedistribute);
        }
        if (null != turnpageflag) {

            model.addAttribute("turnpageflag", turnpageflag);
        }


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


        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
        if (null != startDate && !startDate.trim().equals("")) {

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());



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


        Page<WorkgroupVo> workerGroupFilterTimeConflict = null;


        OrderTaskpackage taskpackage = allotWorkerGroupService.findTargetPackageById(Integer.valueOf(packageId));
        if (null != startDate && !startDate.trim().equals("")) {

            taskpackage.setPlanStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));

        }

        if (null != endDate && !endDate.trim().equals("")) {

            taskpackage.setPlanEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }
        if (null != taskpackage) {

            workgroupVo.setElactricationId(String.valueOf(taskpackage.getEngineDepartId()));


            workgroupVo.setPackLat(taskpackage.getLat());
            workgroupVo.setPackLng(taskpackage.getLng());
    		


            workgroupVo.setIsSpecialPack(taskpackage.getIsSpecialPack());
            workgroupVo.setPackTempId(taskpackage.getTaskTackageTempId());
        }
        if (null != scopeDistance) {

            workgroupVo.setAddressToWorkerAddressDistance(String.valueOf(scopeDistance));
        }

        List<WorkgroupVo> list = allotWorkerGroupService.findList(workgroupVo);

        return list;

    }




    public Page<WorkgroupVo> getWorkerGroupWithPlainPack(Page<WorkgroupVo> page, OrderTaskpackage taskPack) {



        Iterator<WorkgroupVo> iterator = page.getList().iterator();
        while (iterator.hasNext()) {
            WorkgroupVo next = iterator.next();
            String[] taskId = next.getTargetPackageId().split(",");

            for (String string : taskId) {

                if (null != string && !",".equals(string)) {


                    if (string.equals(String.valueOf(taskPack.getTaskTackageTempId()))) {


                        iterator.remove();

                        break;
                    }

                }

            }

        }


        return page;
    }


    public Page<WorkgroupVo> getWorkerGroupFilterTimeConflict(Page<WorkgroupVo> page,
                                                              OrderTaskpackage taskPack) {




        if (null == taskPack.getPlanEnddate() || page.getList().size() == 0) {
            return page;

        }


        List<OrderTaskpackage> list2 = allotWorkerGroupService
                .findAllPackageWhomHasEmpGroup(page.getList());


        List<Integer> wantedDeleteWorkerId = new ArrayList<>();

        for (OrderTaskpackage orderTaskpackage : list2) {

            if (null != orderTaskpackage.getPlanStartdate() && null != orderTaskpackage.getPlanEnddate()
                    && null != taskPack.getPlanEnddate() && null != taskPack.getPlanEnddate()) {





                if ((orderTaskpackage.getPlanStartdate().getTime() > taskPack.getPlanEnddate().getTime()) || (orderTaskpackage.getPlanEnddate().getTime() < taskPack.getPlanStartdate().getTime())) {
                } else {

                    wantedDeleteWorkerId.add(Integer.parseInt(orderTaskpackage.getEmpGroupid()));
                }

            }

        }



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

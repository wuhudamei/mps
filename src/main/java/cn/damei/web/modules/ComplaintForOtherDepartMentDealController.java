/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ComplaintForOtherDepartMentDeal;
import cn.damei.service.modules.ComplaintForOtherDepartMentDealService;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 其他部门投诉Controller
 *
 * @author mh
 * @version 2017-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal")
public class ComplaintForOtherDepartMentDealController extends BaseController {

    @Autowired
    private ComplaintForOtherDepartMentDealService complaintForOtherDepartMentDealService;

    @ModelAttribute
    public ComplaintForOtherDepartMentDeal get(@RequestParam(required = false) String id) {
        ComplaintForOtherDepartMentDeal entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = complaintForOtherDepartMentDealService.get(id);
        }
        if (entity == null) {
            entity = new ComplaintForOtherDepartMentDeal();
        }
        return entity;
    }

    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "")
    public String preList() {

        return "modules/complaintForOtherDepartmentDeal/complaintForOtherDepartMentList";
    }

    /**
     * 其他部门上报, 有权限list
     *
     * @param complaintForOtherDepartMent
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintforotherdepartmentDeal:view")
    @RequestMapping(value = "list")
    public String list(ComplaintForOtherDepartMentDeal complaintForOtherDepartMent, HttpServletRequest request, HttpServletResponse response, Model model) {
        complaintForOtherDepartMent.setStoreId(UserUtils.getUser().getStoreId());

        Page<ComplaintForOtherDepartMentDeal> page = complaintForOtherDepartMentDealService.findPage(new Page<ComplaintForOtherDepartMentDeal>(request, response), complaintForOtherDepartMent);
        model.addAttribute("page", page);
        return "modules/complaintForOtherDepartmentDeal/complaintForOtherDepartMentList";
    }


    @RequiresPermissions("complaintforotherdepartmentDeal:complaintforotherdepartmentDeal:edit")
    @ResponseBody
    @RequestMapping(value = "updatePreComplaintStatus", method = RequestMethod.POST)
    public String updatePreComplaintStatus(String id, String text, String status) {

        String resultStatus = "1";
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(text) || StringUtils.isEmpty(status)) {
            resultStatus = "0";


        } else {

            complaintForOtherDepartMentDealService.updatePreComplaintStatus(id, text, status);

        }

        return resultStatus;


    }


    /**
     * 接收投诉信息
     *
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "add")
    public String add(@RequestParam String id, Model model) {
        //根据预投诉id 查询订单信息,和投诉的来源 投诉人和手机号包括对应的照片问题等


        Map<String, Object> map = complaintForOtherDepartMentDealService.findOrderInfoByPreComplaintId(id);
        List<Map<String, String>> list = complaintForOtherDepartMentDealService.selectPicByPreId(id);
        model.addAttribute("map", map);
        model.addAttribute("list", list);
        model.addAttribute("preComplaintId", id);
        return "modules/complaintForOtherDepartmentDeal/complain";
    }


    /**
     * 删除图片
     *
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit")
    @RequestMapping(value = "deletePic")
    @ResponseBody
    public void deletePic(@RequestParam String picId) {


        complaintForOtherDepartMentDealService.deletePrePic(picId);

    }


    /**
     * 根据门店查找投诉类型
     *
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "findComplaintTypeByStoreId")
    @ResponseBody
    public List<Map<String, String>> findComplaintTypeByStoreId(@RequestParam String storeId) {


        List<Map<String, String>> mapList = complaintForOtherDepartMentDealService.findComplaintTypeByStoreId(storeId);

        return mapList;

    }

    /**
     * 根据投诉类型查找投诉项
     *
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "findComplaintItemByTypeId")
    @ResponseBody
    public List<Map<String, String>> findComplaintItemByTypeId(@RequestParam String typeId) {


        List<Map<String, String>> mapList = complaintForOtherDepartMentDealService.findComplaintItemByTypeId(typeId);

        return mapList;

    }


    /**
     * 接收预投诉
     *
     * @return
     */
    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit")
    @RequestMapping(value = "savePreComplaintInfo")
    public String savePreComplaintInfo(ComplaintForOtherDepartMentDeal vo, HttpServletRequest request) {

        complaintForOtherDepartMentDealService.saveComplaintData(request, vo);
        return "redirect:" + Global.getAdminPath() + "/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal";
    }


}
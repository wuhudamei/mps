
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



    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "add")
    public String add(@RequestParam String id, Model model) {



        Map<String, Object> map = complaintForOtherDepartMentDealService.findOrderInfoByPreComplaintId(id);
        List<Map<String, String>> list = complaintForOtherDepartMentDealService.selectPicByPreId(id);
        model.addAttribute("map", map);
        model.addAttribute("list", list);
        model.addAttribute("preComplaintId", id);
        return "modules/complaintForOtherDepartmentDeal/complain";
    }



    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit")
    @RequestMapping(value = "deletePic")
    @ResponseBody
    public void deletePic(@RequestParam String picId) {


        complaintForOtherDepartMentDealService.deletePrePic(picId);

    }



    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "findComplaintTypeByStoreId")
    @ResponseBody
    public List<Map<String, String>> findComplaintTypeByStoreId(@RequestParam String storeId) {


        List<Map<String, String>> mapList = complaintForOtherDepartMentDealService.findComplaintTypeByStoreId(storeId);

        return mapList;

    }


    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:view")
    @RequestMapping(value = "findComplaintItemByTypeId")
    @ResponseBody
    public List<Map<String, String>> findComplaintItemByTypeId(@RequestParam String typeId) {


        List<Map<String, String>> mapList = complaintForOtherDepartMentDealService.findComplaintItemByTypeId(typeId);

        return mapList;

    }



    @RequiresPermissions("complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit")
    @RequestMapping(value = "savePreComplaintInfo")
    public String savePreComplaintInfo(ComplaintForOtherDepartMentDeal vo, HttpServletRequest request) {

        complaintForOtherDepartMentDealService.saveComplaintData(request, vo);
        return "redirect:" + Global.getAdminPath() + "/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal";
    }


}
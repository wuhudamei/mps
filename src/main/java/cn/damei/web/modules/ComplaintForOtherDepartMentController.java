
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.savePhoto.GetBusinessPhoto;
import cn.damei.common.utils.UserUtils;
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
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.ComplaintForOtherDepartMent;
import cn.damei.service.modules.ComplaintForOtherDepartMentService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/complaintforotherdepartment/complaintForOtherDepartMent")
public class ComplaintForOtherDepartMentController extends BaseController {

    @Autowired
    private ComplaintForOtherDepartMentService complaintForOtherDepartMentService;

    @ModelAttribute
    public ComplaintForOtherDepartMent get(@RequestParam(required = false) String id) {
        ComplaintForOtherDepartMent entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = complaintForOtherDepartMentService.get(id);
        }
        if (entity == null) {
            entity = new ComplaintForOtherDepartMent();
        }
        return entity;
    }

    @RequiresPermissions("complaintforotherdepartment:complaintForOtherDepartMent:view")
    @RequestMapping(value = "")
    public String preList() {

        return "modules/complaintforotherdepartment/complaintForOtherDepartMentList";
    }


    @RequiresPermissions("complaintforotherdepartment:complaintForOtherDepartMent:view")
    @RequestMapping(value = "list")
    public String list(ComplaintForOtherDepartMent complaintForOtherDepartMent, HttpServletRequest request, HttpServletResponse response, Model model) {


        Page<ComplaintForOtherDepartMent> page = complaintForOtherDepartMentService.findPage(new Page<ComplaintForOtherDepartMent>(request, response), complaintForOtherDepartMent);
        model.addAttribute("page", page);
        return "modules/complaintforotherdepartment/complaintForOtherDepartMentList";
    }


    @Autowired
    private GetBusinessPhoto photo;



    @RequestMapping(value = "detailLog")
    public String detailLog(ComplaintForOtherDepartMent complaintForOtherDepartMent, HttpServletRequest request, HttpServletResponse response, Model model) {




        Map<String, Object> map = complaintForOtherDepartMentService.findDetailById(complaintForOtherDepartMent.getId());

        model.addAttribute("preComlaint", map);

        return "modules/complaintforotherdepartment/log";
    }

    @RequiresPermissions("complaintforotherdepartment:complaintForOtherDepartMent:view")
    @RequestMapping(value = "photo")
    public String photo(ComplaintForOtherDepartMent complaintForOtherDepartMent, HttpServletRequest request, HttpServletResponse response, Model model) {



        List<String> pic = photo.getBusinessPhoto(complaintForOtherDepartMent.getId(), PictureTypeContantUtil.PICTURE_TYPE_113);

        model.addAttribute("list", pic);
        model.addAttribute("title", "预投诉图片");

        return "common/photo/photo";
    }


    @RequiresPermissions("complaintforotherdepartment:complaintForOtherDepartMent:edit")
    @RequestMapping(value = "add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/complaintforotherdepartment/complain";
    }


    @RequiresPermissions("complaintforotherdepartment:complaintForOtherDepartMent:edit")
    @RequestMapping(value = "saveComplaintInfo")
    public String saveComplaintInfo(RedirectAttributes redirectAttributes, String[] photos, String orderId, String complaintText, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (null == orderId) {

            redirectAttributes.addFlashAttribute("请选择关联订单");
            return "modules/complaintforotherdepartment/complain";
        } else {


            complaintForOtherDepartMentService.saveComplaintInfo(request, orderId, complaintText, photos);

            redirectAttributes.addFlashAttribute("保存预投诉问题成功");
            return "redirect:" + Global.getAdminPath() + "/complaintforotherdepartment/complaintForOtherDepartMent";
        }

    }


    @RequestMapping(value = "findOrderByText")
    @ResponseBody
    public List<Map<String, String>> findOrderByText(String text, String orderId) {

        try {
            if (!StringUtils.isEmpty (text))
            text = URLDecoder.decode (text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        if (StringUtils.isEmpty(text) && StringUtils.isEmpty(orderId)) {
            return null;
        } else {
            String storeId = UserUtils.getUser().getStoreId();
            List<Map<String, String>> mapList = complaintForOtherDepartMentService.findOrderInfoByText(text, orderId,storeId);
            return mapList;

        }

    }


}
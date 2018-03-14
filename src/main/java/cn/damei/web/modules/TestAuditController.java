
package cn.damei.web.modules;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.TestAudit;
import cn.damei.service.modules.TestAuditService;


@Controller
@RequestMapping(value = "${adminPath}/oa/testAudit")
public class TestAuditController extends BaseController {

	@Autowired
	private TestAuditService testAuditService;
	
	@ModelAttribute
	public TestAudit get(@RequestParam(required=false) String id){

		TestAudit testAudit = null;
		if (StringUtils.isNotBlank(id)){
			testAudit = testAuditService.get(id);


		}
		if (testAudit == null){
			testAudit = new TestAudit();
		}
		return testAudit;
	}
	
	@RequiresPermissions("oa:testAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestAudit testAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			testAudit.setCreateBy(user);
		}
        Page<TestAudit> page = testAuditService.findPage(new Page<TestAudit>(request, response), testAudit); 
        model.addAttribute("page", page);
		return "modules/oa/testAuditList";
	}
	

	@RequiresPermissions("oa:testAudit:view")
	@RequestMapping(value = "form")
	public String form(TestAudit testAudit, Model model) {
		
		String view = "testAuditForm";
		

		if (StringUtils.isNotBlank(testAudit.getId())){


			String taskDefKey = testAudit.getAct().getTaskDefKey();
			

			if(testAudit.getAct().isFinishTask()){
				view = "testAuditView";
			}

			else if ("modify".equals(taskDefKey)){
				view = "testAuditForm";
			}

			else if ("audit".equals(taskDefKey)){
				view = "testAuditAudit";


			}

			else if ("audit2".equals(taskDefKey)){
				view = "testAuditAudit";
			}

			else if ("audit3".equals(taskDefKey)){
				view = "testAuditAudit";
			}

			else if ("audit4".equals(taskDefKey)){
				view = "testAuditAudit";
			}

			else if ("apply_end".equals(taskDefKey)){
				view = "testAuditAudit";
			}
		}

		model.addAttribute("testAudit", testAudit);
		return "modules/oa/" + view;
	}
	

	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "save")
	public String save(TestAudit testAudit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testAudit)){
			return form(testAudit, model);
		}
		testAuditService.save(testAudit);
		addMessage(redirectAttributes, "提交审批'" + testAudit.getUser().getName() + "'成功");
		return "redirect:" + adminPath + "/act/task/todo/";
	}


	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(TestAudit testAudit, Model model) {
		if (StringUtils.isBlank(testAudit.getAct().getFlag())
				|| StringUtils.isBlank(testAudit.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(testAudit, model);
		}
		testAuditService.auditSave(testAudit);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	

	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(TestAudit testAudit, RedirectAttributes redirectAttributes) {
		testAuditService.delete(testAudit);
		addMessage(redirectAttributes, "删除审批成功");
		return "redirect:" + adminPath + "/oa/testAudit/?repage";
	}

}

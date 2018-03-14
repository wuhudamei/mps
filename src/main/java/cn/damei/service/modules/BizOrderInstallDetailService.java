package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.StringUtils;
import cn.damei.web.modules.BizOrderInstallDetailController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallDetailDao;
import cn.damei.entity.modules.BizOrderInstallDetail;
import org.springframework.ui.Model;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallDetailService extends CrudService2<BizOrderInstallDetailDao, BizOrderInstallDetail> {

	@Autowired
	private BizOrderInstallDetailDao bizOrderInstallDetailDao;
	@Autowired
    private BizEmployeeService2 bizEmployeeService2;

	public BizOrderInstallDetail get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderInstallDetail> findList(BizOrderInstallDetail bizOrderInstallDetail) {
		return super.findList(bizOrderInstallDetail);
	}

	public Page<BizOrderInstallDetail> findPagezlist(Page<BizOrderInstallDetail> page, BizOrderInstallDetail bizOrderInstallDetail) {
		bizOrderInstallDetail.setPage(page);
		List<BizOrderInstallDetail> findList = dao.findListz(bizOrderInstallDetail);
		page.setList(findList);
		return page;
	}

    public void projectModeAndStore(BizOrderInstallDetail bizOrderInstallDetail, Model model, BizOrderInstallDetailController bizOrderInstallDetailController) {
        User user = UserUtils.getUser();

        if (null == bizOrderInstallDetail.getStoreId()) {
            if (null != user.getStoreId()) {
                bizOrderInstallDetail.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        if (StringUtils.isBlank(user.getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        if (null != user.getEmpId()) {
            BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
            if (StringUtils.isBlank(be.getProjectMode())) {
                model.addAttribute("gongcheng", true);
            } else {
                if (be.getProjectMode().equals("3")) {
                    model.addAttribute("gongcheng", true);
                } else {
                    bizOrderInstallDetail.setProjectMode(be.getProjectMode());
                }
            }
        } else {
            model.addAttribute("gongcheng", true);
        }
    }
}

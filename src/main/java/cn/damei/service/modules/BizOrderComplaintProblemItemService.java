
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderComplaintProblemItemDao;
import cn.damei.entity.modules.BizOrderComplaintProblemItem;


@Service
@Transactional(readOnly = true)
public class BizOrderComplaintProblemItemService extends CrudService<BizOrderComplaintProblemItemDao, BizOrderComplaintProblemItem>
{

	@Autowired
	private BizOrderComplaintProblemItemDao bizOrderComplaintProblemItemDao;

	public BizOrderComplaintProblemItem get(String id)
	{
		return super.get(id);
	}

	public List<BizOrderComplaintProblemItem> findList(BizOrderComplaintProblemItem bizOrderComplaintProblemItem)
	{
		return super.findList(bizOrderComplaintProblemItem);
	}

	public Page<BizOrderComplaintProblemItem> findPage(Page<BizOrderComplaintProblemItem> page, BizOrderComplaintProblemItem bizOrderComplaintProblemItem)
	{
		return super.findPage(page, bizOrderComplaintProblemItem);
	}

	@Transactional(readOnly = false)
	public void save(BizOrderComplaintProblemItem bizOrderComplaintProblemItem)
	{
		super.save(bizOrderComplaintProblemItem);
	}

	@Transactional(readOnly = false)
	public void delete(BizOrderComplaintProblemItem bizOrderComplaintProblemItem)
	{
		super.delete(bizOrderComplaintProblemItem);
	}

	@Transactional(readOnly = false)
	public void Insert(BizOrderComplaintProblemItem bizOrderComplaintProblemItem)
	{
		int insert = bizOrderComplaintProblemItemDao.insert(bizOrderComplaintProblemItem);
		System.err.println(insert);

	}

}
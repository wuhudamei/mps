
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.CacheUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.LinkDao;
import cn.damei.entity.modules.Link;


@Service
@Transactional(readOnly = true)
public class LinkService extends CrudService<LinkDao, Link> {

	@Transactional(readOnly = false)
	public Page<Link> findPage(Page<Link> page, Link link, boolean isDataScopeFilter) {

		Date updateExpiredWeightDate =  (Date)CacheUtils.get("updateExpiredWeightDateByLink");
		if (updateExpiredWeightDate == null || (updateExpiredWeightDate != null 
				&& updateExpiredWeightDate.getTime() < new Date().getTime())){
			dao.updateExpiredWeight(link);
			CacheUtils.put("updateExpiredWeightDateByLink", DateUtils.addHours(new Date(), 6));
		}
		link.getSqlMap().put("dsf", dataScopeFilter(link.getCurrentUser(), "o", "u"));
		
		return super.findPage(page, link);
	}
	
	@Transactional(readOnly = false)
	public void delete(Link link, Boolean isRe) {

		link.setDelFlag(isRe!=null&&isRe?Link.DEL_FLAG_NORMAL:Link.DEL_FLAG_DELETE);
		dao.delete(link);
	}
	

	public List<Object[]> findByIds(String ids) {
		List<Object[]> list = Lists.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		if (idss.length>0){
			List<Link> l = dao.findByIdIn(idss);
			for (Link e : l){
				list.add(new Object[]{e.getId(),StringUtils.abbr(e.getTitle(),50)});
			}
		}
		return list;
	}

}

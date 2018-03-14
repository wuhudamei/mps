/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BreakProblemDao;
import cn.damei.entity.modules.BreakProblem;

/**
 * 违规问题统计Service
 * @author wyb
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class BreakProblemService extends CrudService<BreakProblemDao, BreakProblem> {

	@Autowired
	private BreakProblemDao breakProblemDao;


	public BreakProblem get(String id) {
		return super.get(id);
	}
	
	public List<BreakProblem> findList(BreakProblem breakProblem) {
		return super.findList(breakProblem);
	}
	
	public Page<BreakProblem> findPage(Page<BreakProblem> page, BreakProblem breakProblem) {
		return super.findPage(page, breakProblem);
	}

    public Page<BreakProblem>  queryBreakProblemList( Page<BreakProblem> page, BreakProblem breakProblem) {
        breakProblem.setPage(page);
        List<BreakProblem> breakProblems = dao.queryBreakProblemListbreakProblem(breakProblem);
        page.setList(breakProblems);
        return page;
    }
    public List<BreakProblem>  queryBreakProblemListbreakProblem(  BreakProblem breakProblem , BreakProblem breakProblemzuzhuang) {
        List<BreakProblem> breakProblems = dao.queryBreakProblemListbreakProblem(breakProblem);
        return breakProblems;
    }
}
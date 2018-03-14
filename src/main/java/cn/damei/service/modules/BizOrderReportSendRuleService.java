/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderReportSendRuleDao;
import cn.damei.dao.modules.BizOrderReportSendRuleServiceDao;
import cn.damei.entity.modules.BizOrderReportSendRule;
import cn.damei.entity.modules.BizOrderReportSendRuleServiceMember;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 返单客服轮训规则业务处理Service
 * @author Liwancai
 * @version 2017-05-06
 */
@Service
public class BizOrderReportSendRuleService extends CrudService<BizOrderReportSendRuleDao, BizOrderReportSendRule> {
	
	@Autowired
	private BizOrderReportSendRuleDao orderReportSendRuleDao;
	
	@Autowired
	private BizOrderReportSendRuleServiceDao orderReportSendRuleServiceDao;
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	
	private final static String START_TIME = " 00:00:00";	//开始时间
	private final static String PEDDING_EXECUTION = "0";	//待执行
	private final static String ON_EXECUTION = "1";		//在执行
	private final static String EXPIRED = "2";				//已过期
	
	public BizOrderReportSendRule get(String id) {
		return super.get(id);
	}
	
	/**
	 * 返单客服轮循规则保存
	 * @param sendRuleId,规则ID
	 * @param startDateTime，规则执行起始时间
	 * @param serviceMembers，规则制定客服人员
	 * @throws ParseException 
	 */
	@Transactional
	public int save(Integer sendRuleId,String startDateTime,String[] serviceMembers) throws ParseException {
		int result = 0;
		boolean del_flag = false;
		
			//新增或者更新规则信息
			BizOrderReportSendRule rule = new BizOrderReportSendRule();
			rule.setStartDatetime(  sdf.parse( startDateTime + START_TIME ) );
			
			if( sendRuleId == null  ){
				
				User user = UserUtils.getUser();
				if (StringUtils.isNotBlank(user.getId())){
					rule.setCreateBy( user );
				}
				Date date = new Date();
				rule.setCreateDate( date );
				rule.setStatus(PEDDING_EXECUTION);
				orderReportSendRuleDao.insert(rule);
				sendRuleId = Integer.parseInt( rule.getId() ) ;
			}else{
				rule.setId(sendRuleId.toString());
				orderReportSendRuleDao.update(rule);
				del_flag = true;
			}
			
			//插入客服人员信息
			if( serviceMembers != null && serviceMembers.length > 0   ){
				
				List<BizOrderReportSendRuleServiceMember> serviceMemberList = new ArrayList<BizOrderReportSendRuleServiceMember>();
				int index = 1;
				for( String str : serviceMembers ){
					BizOrderReportSendRuleServiceMember member = new BizOrderReportSendRuleServiceMember();
					member.setSendRuleId(sendRuleId);
					member.setServiceIndex( index );
					member.setServiceEmployeeId( Integer.parseInt( str ) );
					member.setSendCount(0);
					member.setSendEnd(0);
					serviceMemberList.add( member );
					index ++;
				}
				
				if(del_flag){
					orderReportSendRuleServiceDao.deleteBySendRuleId(sendRuleId);
				}
				
				orderReportSendRuleServiceDao.batchInsert(serviceMemberList);
			}
			
			result = sendRuleId;
		
		return result;
	}
	
	/**
	 * 查询当前未执行的规则信息
	 * @return
	 */
	public BizOrderReportSendRule selectNoExecution(){
		List<BizOrderReportSendRule> list = orderReportSendRuleDao.selectByStatus( PEDDING_EXECUTION );
		if( list != null && list.size() > 0 ){
			return list.get(0); 
		}else{
			return null;
		}
	}
	
	/**
	 * 根据规则id获取对呀的客服人员信息
	 * @param sendRuleId
	 * @return
	 */
	public List<BizOrderReportSendRuleServiceMember> selectServiceMembersBySendRuleId(Integer sendRuleId){
		return orderReportSendRuleServiceDao.selectServiceMembersBySendRuleId(sendRuleId);
	}
	
	/**
	 * 查询当前未过期规则涉及的客服人员信息
	 * @return	Map结构如下：id:1,realName:xxx,phone:111111111,addFlag:Y
	 */
	public List<Map<String,String>> selectServiceMembersForPendingRule(){
		return orderReportSendRuleServiceDao.selectServiceMembersForPendingRule();
	}
	
	/**
	 * 查询轮循规则执行记录
	 * @return
	 */
	public List<BizOrderReportSendRule> selectRuleExecuteRecord(){
		return orderReportSendRuleDao.selectByStatus( ON_EXECUTION + "," + EXPIRED );
	}
	
}
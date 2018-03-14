
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


@Service
public class BizOrderReportSendRuleService extends CrudService<BizOrderReportSendRuleDao, BizOrderReportSendRule> {
	
	@Autowired
	private BizOrderReportSendRuleDao orderReportSendRuleDao;
	
	@Autowired
	private BizOrderReportSendRuleServiceDao orderReportSendRuleServiceDao;
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	
	private final static String START_TIME = " 00:00:00";
	private final static String PEDDING_EXECUTION = "0";
	private final static String ON_EXECUTION = "1";
	private final static String EXPIRED = "2";
	
	public BizOrderReportSendRule get(String id) {
		return super.get(id);
	}
	

	@Transactional
	public int save(Integer sendRuleId,String startDateTime,String[] serviceMembers) throws ParseException {
		int result = 0;
		boolean del_flag = false;
		

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
	

	public BizOrderReportSendRule selectNoExecution(){
		List<BizOrderReportSendRule> list = orderReportSendRuleDao.selectByStatus( PEDDING_EXECUTION );
		if( list != null && list.size() > 0 ){
			return list.get(0); 
		}else{
			return null;
		}
	}
	

	public List<BizOrderReportSendRuleServiceMember> selectServiceMembersBySendRuleId(Integer sendRuleId){
		return orderReportSendRuleServiceDao.selectServiceMembersBySendRuleId(sendRuleId);
	}
	

	public List<Map<String,String>> selectServiceMembersForPendingRule(){
		return orderReportSendRuleServiceDao.selectServiceMembersForPendingRule();
	}
	

	public List<BizOrderReportSendRule> selectRuleExecuteRecord(){
		return orderReportSendRuleDao.selectByStatus( ON_EXECUTION + "," + EXPIRED );
	}
	
}
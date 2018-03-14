
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.CheckNodeDao;
import cn.damei.dao.modules.BizCustomerReturnVisitContentDao;
import cn.damei.dao.modules.BizCustomerReturnVisitDao;
import cn.damei.entity.modules.BizCustomerReturnVisit;
import cn.damei.entity.modules.BizCustomerReturnVisitContent;
import cn.damei.dao.modules.BizCustomerReturnVisitInvalidRecordDao;
import cn.damei.entity.modules.BizCustomerReturnVisitInvalidRecord;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONObject;


@Service
public class BizCustomerReturnVisitService extends CrudService<BizCustomerReturnVisitDao, BizCustomerReturnVisit> {
	@Autowired
	BizCustomerReturnVisitDao customerReturnVisitDao;
	@Autowired
	BizCustomerReturnVisitInvalidRecordDao invalidRecordDao;
	@Autowired
	BizCustomerReturnVisitContentDao customerReturnVisitContentDao;
	@Autowired
	CheckNodeDao checkNodeDao;
	
	public BizCustomerReturnVisit getById(String id) {

		BizCustomerReturnVisit customerReturnVisit = super.get(id);
		

		List<BizCustomerReturnVisitContent> questions = customerReturnVisitContentDao.getByReturnVisitId( id ); 
		

		for(BizCustomerReturnVisitContent customerReturnVisitContent :  questions ){
			if( !StringUtils.isBlank( customerReturnVisitContent.getItemContent() ) ){
				JSONObject jsonobject = JSONObject.fromObject( customerReturnVisitContent.getItemContent() );
				Map<String, Object> mapJson = JSONObject.fromObject(jsonobject); 
				customerReturnVisitContent.setItemContentList( (List<Map<String,String>>)mapJson.get("item") );
			}
		}
		
		customerReturnVisit.setQuestions( questions );
		return customerReturnVisit;
	}
	
	public List<BizCustomerReturnVisit> findList(BizCustomerReturnVisit bizCustomerReturnVisit) {
		return super.findList(bizCustomerReturnVisit);
	}
	
	public Page<BizCustomerReturnVisit> findPage(Page<BizCustomerReturnVisit> page, BizCustomerReturnVisit bizCustomerReturnVisit) {
		
		Page<BizCustomerReturnVisit> pageList = super.findPage(page, bizCustomerReturnVisit);
		for(BizCustomerReturnVisit customerReturnVisit : pageList.getList()){
			customerReturnVisit.setQuestions( customerReturnVisitContentDao.getByReturnVisitId( customerReturnVisit.getId() ) );
		}
		
		return super.findPage(page, bizCustomerReturnVisit);
	}
	

	public List<Map<String,Object>>  getByProjectNode(String projectNode,String storeId,String projectMode){
		List<BizCustomerReturnVisitContent> list = customerReturnVisitContentDao.getByProjectNode(projectNode,storeId,projectMode);
		

		if( list != null && list.size() > 0 ){
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			for(BizCustomerReturnVisitContent customerReturnVisitContent :  list ){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("questionContent", customerReturnVisitContent.getQuestionContent() );
				map.put("statisticsDepartment", customerReturnVisitContent.getStatisticsDepartment() );
				map.put("replyMode", customerReturnVisitContent.getReplyMode() );
				if( !"3".equals( customerReturnVisitContent.getReplyMode() ) && !"4".equals( customerReturnVisitContent.getReplyMode() )){
					JSONObject jsonobject = JSONObject.fromObject( customerReturnVisitContent.getItemContent() );
					Map<String, Object> mapJson = JSONObject.fromObject(jsonobject);
					map.put("questionItems", (List<Map<String,String>>)mapJson.get("item") );
				}
				resultList.add( map );
			}
			return resultList;
		}else{
			return Collections.emptyList();
		}
	}
	

	public List<BizCustomerReturnVisitInvalidRecord> getInvalidRecordList(String returnVisitNode,Integer orderId){
		List<BizCustomerReturnVisitInvalidRecord> list = invalidRecordDao.findByOrderIdAndVisitNode(orderId,returnVisitNode);
		if( list != null && list.size() > 0 ){
			return list;
		}else{
			return Collections.EMPTY_LIST;
		}
	}
	

	public List<BizCustomerReturnVisitContent>  getByProjectNodeForExport(String projectNode,String storeId,String projectMode){
		return customerReturnVisitContentDao.getByProjectNode(projectNode,storeId,projectMode);
	}
	
	
	public String getReturnVisitName(String projectNode,String storeId,String projectMode){
		return customerReturnVisitDao.getReturnVisitName(projectNode,storeId,projectMode);
	}
	

	@Transactional
	public void saveOrUpdate(BizCustomerReturnVisit bizCustomerReturnVisit,String[] questionContent,String[] statisticsDepartment,String[] replyMode,String[] itemContent) {
		
		User user = UserUtils.getUser();
		Date date = new Date();
		

		if( StringUtils.isBlank(bizCustomerReturnVisit.getId()) ){
			if (StringUtils.isNotBlank(user.getId())){
				bizCustomerReturnVisit.setCreateBy( user );
			}
			bizCustomerReturnVisit.setCreateDate( date );
			customerReturnVisitDao.insert(bizCustomerReturnVisit);
		}else{
			if (StringUtils.isNotBlank(user.getId())){
				bizCustomerReturnVisit.setUpdateBy(user);
			}
			bizCustomerReturnVisit.setUpdateDate(date);
			customerReturnVisitDao.update(bizCustomerReturnVisit);
		}
		

		customerReturnVisitContentDao.deleteByReturnVisitId(bizCustomerReturnVisit.getId());

		if( questionContent != null && questionContent.length > 0 ){
			List<BizCustomerReturnVisitContent> contentList = new ArrayList<BizCustomerReturnVisitContent>();
			for(int i = 0 ; i < questionContent.length; i++){
				BizCustomerReturnVisitContent content = new BizCustomerReturnVisitContent();
				content.setReturnVisitId( bizCustomerReturnVisit.getId() );
				content.setStatisticsDepartment( Integer.parseInt( statisticsDepartment[i] ));
				content.setReplyMode(replyMode[i]);
				content.setQuestionContent(questionContent[i]);
				content.setItemContent(itemContent[i]);
				
				if (StringUtils.isNotBlank(user.getId())){
					content.setCreateBy( user );
				}
				content.setCreateDate( date );
				contentList.add(content);
			}
			customerReturnVisitContentDao.insertBatch(contentList);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(BizCustomerReturnVisit bizCustomerReturnVisit) {
		super.delete(bizCustomerReturnVisit);
	}
	

	public List<DropModel> queryProjectNodeListByStoreId(Integer storeId,String projectMode){
		Map<String, Object> map = new HashMap<>();
		map.put("storeid", storeId);
		map.put("projectMode", projectMode);
		
		return checkNodeDao.queryNodeListByStoreId(map);
	}
	

	public List<String> queryReturnVisitBadType(){
		
		List<Map<String,String>> list = customerReturnVisitDao.queryReturnVisitBadType();
		if( list != null && list.size() > 0 ){
			List<String> result = new ArrayList<String>();
			for( Map<String,String> map : list ){
				result.add(map.get("label"));
			}
			return result;
		}else{
			return Collections.emptyList();
		}
	}

	public List<Map<String,Object>> queryReturnVisitNodeByStoreId(String storeId,String projectMode){
		List<Map<String,Object>> result = customerReturnVisitDao.queryReturnVisitNodeByStoreId(storeId,projectMode);
		if( result != null && result.size() > 0 ){
			return result;
		}else{
			return Collections.emptyList();
		}
	}
}
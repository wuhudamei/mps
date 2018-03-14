package cn.damei.Quartz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.service.modules.BizOrderInstallItemReportQuartzService;
import org.apache.commons.collections.CollectionUtils;

/**
 * 工程部主材工期统计表
 * WYB
 */
public class OrderInstallItemReportQuartz {

    private static Logger logger = LoggerFactory.getLogger(OrderInstallItemReportQuartz.class);

    @Autowired
	private BizOrderInstallItemReportQuartzService bizOrderInstallItemReportQuartzService;
	
    public void execute() {
    	
    	//1.查询所有的订单
        List<BizOrderInstallItemReport> list = bizOrderInstallItemReportQuartzService.findOrderMessage();
        
        //2.建立空白的List--更新
        List<BizOrderInstallItemReport> updateList = new ArrayList<BizOrderInstallItemReport>();
      
        //3.建立空白的List--新增
        List<BizOrderInstallItemReport> insertList = new ArrayList<BizOrderInstallItemReport>();
        
        //4.循环遍历订单
        if(CollectionUtils.isNotEmpty(list)){
        	for(BizOrderInstallItemReport report:list){
        		
        		if(null != report.getBizOrderInstallItemReportId()){
        			//4.1：不为空时，更新
        			report = bizOrderInstallItemReportQuartzService.findOrderInstallReportMessage(report);
        			//4.1.1：将对象放入updateList中
        			updateList.add(report);
        			
        		}else{
        			//4.2:为空，插入
        			report = bizOrderInstallItemReportQuartzService.findOrderInstallReportMessage(report);
        			//4.2.1：将对象放入insertList中
        			insertList.add(report);
        		}
        		
        	}
        	
        }
        
        //5.批量更新操作
        if(CollectionUtils.isNotEmpty(updateList)){
        	for(int i=0; i<updateList.size();i+=100){
        		
        		//5.1：从集合中取出100条数据
        		List<BizOrderInstallItemReport> mixUpdateList = null;
        		if((i+100)<updateList.size()){
        			mixUpdateList = updateList.subList(i, i+100);
        		}else{
        			mixUpdateList = updateList.subList(i, updateList.size());
        		}
        		
        		//5.2: 批量更新100条数据
        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixUpdateList)){
        			flag = bizOrderInstallItemReportQuartzService.batchUpdateList(mixUpdateList);
        		}
        		
        		//5.3：更新是否成功
        		if(flag){
        			logger.debug("第"+i+"到"+(i+99)+"更新成功");
        		}else{
        			logger.error("第"+i+"到"+(i+99)+"更新失败");
        		}
        		
        	}
        }
        
        //6.批量插入操作
        if(CollectionUtils.isNotEmpty(insertList)){
        	for(int i=0; i<insertList.size();i+=100){
	        		
        		//6.1：从集合中取出100条数据
        		List<BizOrderInstallItemReport> mixInsertListList = null;
        		if((i+100)<insertList.size()){
        			mixInsertListList = insertList.subList(i, i+100);
        		}else{
        			mixInsertListList = insertList.subList(i, insertList.size());
        		}
        	
        		//6.2: 批量插入100条数据
        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixInsertListList)){
        			flag = bizOrderInstallItemReportQuartzService.batchInsertList(mixInsertListList);
        		}
        		
        		//6.3：插入是否成功
        		if(flag){
        			logger.debug("第"+i+"到"+(i+99)+"插入成功");
        		}else{
        			logger.error("第"+i+"到"+(i+99)+"插入失败");
        		}
        		
        	}
        }
      
    }

}

package cn.damei.Quartz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.service.modules.BizOrderInstallItemReportQuartzService;
import org.apache.commons.collections.CollectionUtils;


public class OrderInstallItemReportQuartz {

    private static Logger logger = LoggerFactory.getLogger(OrderInstallItemReportQuartz.class);

    @Autowired
	private BizOrderInstallItemReportQuartzService bizOrderInstallItemReportQuartzService;
	
    public void execute() {
    	

        List<BizOrderInstallItemReport> list = bizOrderInstallItemReportQuartzService.findOrderMessage();
        

        List<BizOrderInstallItemReport> updateList = new ArrayList<BizOrderInstallItemReport>();
      

        List<BizOrderInstallItemReport> insertList = new ArrayList<BizOrderInstallItemReport>();
        

        if(CollectionUtils.isNotEmpty(list)){
        	for(BizOrderInstallItemReport report:list){
        		
        		if(null != report.getBizOrderInstallItemReportId()){

        			report = bizOrderInstallItemReportQuartzService.findOrderInstallReportMessage(report);

        			updateList.add(report);
        			
        		}else{

        			report = bizOrderInstallItemReportQuartzService.findOrderInstallReportMessage(report);

        			insertList.add(report);
        		}
        		
        	}
        	
        }
        

        if(CollectionUtils.isNotEmpty(updateList)){
        	for(int i=0; i<updateList.size();i+=100){
        		

        		List<BizOrderInstallItemReport> mixUpdateList = null;
        		if((i+100)<updateList.size()){
        			mixUpdateList = updateList.subList(i, i+100);
        		}else{
        			mixUpdateList = updateList.subList(i, updateList.size());
        		}
        		

        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixUpdateList)){
        			flag = bizOrderInstallItemReportQuartzService.batchUpdateList(mixUpdateList);
        		}
        		

        		if(flag){
        			logger.debug("第"+i+"到"+(i+99)+"更新成功");
        		}else{
        			logger.error("第"+i+"到"+(i+99)+"更新失败");
        		}
        		
        	}
        }
        

        if(CollectionUtils.isNotEmpty(insertList)){
        	for(int i=0; i<insertList.size();i+=100){
	        		

        		List<BizOrderInstallItemReport> mixInsertListList = null;
        		if((i+100)<insertList.size()){
        			mixInsertListList = insertList.subList(i, i+100);
        		}else{
        			mixInsertListList = insertList.subList(i, insertList.size());
        		}
        	

        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixInsertListList)){
        			flag = bizOrderInstallItemReportQuartzService.batchInsertList(mixInsertListList);
        		}
        		

        		if(flag){
        			logger.debug("第"+i+"到"+(i+99)+"插入成功");
        		}else{
        			logger.error("第"+i+"到"+(i+99)+"插入失败");
        		}
        		
        	}
        }
      
    }

}

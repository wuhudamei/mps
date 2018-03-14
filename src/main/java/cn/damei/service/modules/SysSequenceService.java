/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.SysSequence;
import cn.damei.dao.modules.SysSequenceDao;

/**
 * 编号序列管理Service
 * @author 魏建勇
 * @version 2016-08-21
 */
@Service
@Transactional(readOnly = true)
public class SysSequenceService extends CrudService<SysSequenceDao, SysSequence> {
    public static  enum ProMessage{
        PRO_ONE(1,"00000"),
        PRO_TWO(2,"0000"),
        PRO_THIRD(3,"000"),
        PRO_FOUR(4,"00"),
        PRO_FIVE(5,"0"),
        PRO_SIX(6,"");
        private int code;
        private String msg;
        private ProMessage(int code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public static ProMessage getProMessage(int code){
            if(code == 1){
                return PRO_ONE;
            }if(code == 2){
                return PRO_TWO;
            }if(code == 3){
                return PRO_THIRD;
            }if(code == 4){
                return PRO_FOUR;
            }if(code == 5){
                return PRO_FIVE;
            }else{
                return PRO_SIX;
            }            
            
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
	public SysSequence get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	 public synchronized String getSequence(String orderNo){
	     String res = "";
	     SysSequence sspra = new SysSequence();
	     sspra.setName(orderNo);
	     List<SysSequence> list = super.findList(sspra);
	     if(list != null && list.size()>0){
	         SysSequence resBean = list.get(0);
	         String resNo = dao.getSequence(orderNo);
	         if(resNo!=null && !"".equals(resNo)){
	             if(resNo.length()>Integer.parseInt(resBean.getMaxlength())){
	                 res =resBean.getPrefix()+ resNo.substring(resNo.length()-Integer.parseInt(resBean.getMaxlength())-1,resNo.length()-1 );
	             }
	             if(resNo.length()==Integer.parseInt(resBean.getMaxlength())){
	                 res =resBean.getPrefix()+ resNo;
	             }
	             if(resNo.length()<Integer.parseInt(resBean.getMaxlength())){
	                 int code = Integer.parseInt(resBean.getMaxlength()) - resNo.length();    
                     res = resBean.getPrefix()+getStringPlaceholder(code,"0")+ resNo;
                 }
	         }
	     }
	     return res;
	 }
	public String getStringPlaceholder(int k,String Placeholder){
	    String res = "";
	    for(int i = 0;i<k;i++){
	        res = Placeholder+res;
	    }
	    return res;
	} 
	public List<SysSequence> findList(SysSequence sysSequence) {
		return super.findList(sysSequence);
	}
	
	public Page<SysSequence> findPage(Page<SysSequence> page, SysSequence sysSequence) {
		return super.findPage(page, sysSequence);
	}
	
	@Transactional(readOnly = false)
	public void save(SysSequence sysSequence) {
		super.save(sysSequence);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysSequence sysSequence) {
		super.delete(sysSequence);
	}
	
}
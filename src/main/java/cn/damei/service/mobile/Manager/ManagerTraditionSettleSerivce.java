package cn.damei.service.mobile.Manager;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.SettleStatusConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.ManagerTraditionSettleDao;
import cn.damei.entity.mobile.Manager.ManagerNormalSettle;
import cn.damei.entity.mobile.Manager.ManagerTraditionSettleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by joseph on 2017/4/14.
 */
@Service
@Transactional(readOnly = true)
public class ManagerTraditionSettleSerivce {
    @Autowired
    private ManagerTraditionSettleDao dao;


    /**
     * 根据经理id查询该经理下的所有订单
     * @param request
     * @return ManagerTraditionSettleEntity
     */
    public List<ManagerTraditionSettleEntity> findSettleOrderList(HttpServletRequest request,ManagerTraditionSettleEntity entity){
       Integer managerId= SessionUtils.getManagerSession(request).getId();
    entity.setItemManagerId(managerId);
        return dao.findSettleOrderList(entity);
    }

    /**
     * 根据订单id,查询结算模板节点及对应结算状态 和一些限制条件
     * @param orderId
     * @return ManagerTraditionSettleEntity
     */
    public ManagerTraditionSettleEntity findSettleInfoByOrderId(Integer orderId){


        return dao.findSettleInfoByOrderId(orderId);

    }
    /**
     * 第一次申请
     * @param orderId
     * @return
     */

    public ManagerTraditionSettleEntity findSettleInfoDetailByIndexAndOrderId(String orderId,String index){


        return dao.findSettleInfoDetailByIndexAndOrderId(orderId,index);
    }

    /**
     * 重新业务梳理, 验收bug修复sql
     */
    public  List<Integer> findIsCheckDoneInfoByOrderId(Integer orderId){


        return dao.findIsCheckDoneInfoByOrderId(orderId);

    }


    @Transactional(readOnly = false)
    public String  saveSettleInfo(HttpServletRequest request, ManagerTraditionSettleEntity entity, String [] photos){

     Date date =   new Date();


      Manager manager = (SessionUtils.getManagerSession(request));
        ManagerNormalSettle settle=null;
        if(null!=entity ){
//防止重复提交
            Integer count =dao.checkIsSettleExist(entity.getOrderId(),entity.getSettleNodeId());

            if(count>0){
                //防止重复提交
                return "3";

            }

         settle= new ManagerNormalSettle();
            //有了orderId,settleNodeId ,remarks,storeId
            settle.setOrderEntity(entity);
            settle.setApplyEmpId(manager.getId());
            settle.setApplyTime(date);
            settle.setSettleStatus(SettleStatusConstantUtil.SETTLE_STATUS_1);
            settle.setStatusDateTime(date);
            settle.setCreateBy(String.valueOf(manager.getId()));
            settle.setCreateDate(date);
            try{
                dao.saveSettleApplyInfo(settle);
            }catch(NullPointerException e){

                return "1";
            }


        }else{
            return "1";

        }


        if (null != photos && photos.length > 0) {

            for (int vr = 0; vr < photos.length; vr++) {

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");

                // String rootPath = RootName.SystemEnvironment(request);
                String rootPath = request.getSession().getServletContext().getRealPath("");
                File filePath = new File(rootPath + PicturePathContantUtil.UPLOAD_MANAGER_SETTLE_APPLY_UPLOAD_PHOTO + DateUtils.getDate1());
                // 判断该文件是否存在
                if (!filePath.exists() && !filePath.isDirectory()) {
                    filePath.mkdirs();
                }
                String filepath = filePath + filePath.separator + uuid + ".jpeg";
                Base64Util.generateImage(photos[vr], filepath);

                String picpath = PicturePathContantUtil.UPLOAD_MANAGER_SETTLE_APPLY_UPLOAD_PHOTO + DateUtils.getDate1() + filePath.separator + uuid
                        + ".jpeg";
                // 保存图片到数据库

                businessPicService.insertPhotos(photos[vr],SettleStatusConstantUtil.SETTLE_PIC_TYPE_666,settle==null?0:settle.getSettleId(),picpath);


            }

    }

        return "2";
    }
    @Autowired
    private BusinessPicService businessPicService;



    /**
     * 之后状态查看结算单的详情
     * @param settleId
     * @return 子类映射
     */
    public List<ManagerNormalSettle> findSettleInfoDetailBySettleId(String settleId){



        return dao.findSettleInfoDetailBySettleId(settleId);
    }
    
    
    public ManagerNormalSettle queryLastNormalSettleNode(Integer orderId){
    	return dao.queryLastNormalSettleNode(orderId);
    }
}

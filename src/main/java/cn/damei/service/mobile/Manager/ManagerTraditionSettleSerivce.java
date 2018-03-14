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


@Service
@Transactional(readOnly = true)
public class ManagerTraditionSettleSerivce {
    @Autowired
    private ManagerTraditionSettleDao dao;



    public List<ManagerTraditionSettleEntity> findSettleOrderList(HttpServletRequest request,ManagerTraditionSettleEntity entity){
       Integer managerId= SessionUtils.getManagerSession(request).getId();
    entity.setItemManagerId(managerId);
        return dao.findSettleOrderList(entity);
    }


    public ManagerTraditionSettleEntity findSettleInfoByOrderId(Integer orderId){


        return dao.findSettleInfoByOrderId(orderId);

    }


    public ManagerTraditionSettleEntity findSettleInfoDetailByIndexAndOrderId(String orderId,String index){


        return dao.findSettleInfoDetailByIndexAndOrderId(orderId,index);
    }


    public  List<Integer> findIsCheckDoneInfoByOrderId(Integer orderId){


        return dao.findIsCheckDoneInfoByOrderId(orderId);

    }


    @Transactional(readOnly = false)
    public String  saveSettleInfo(HttpServletRequest request, ManagerTraditionSettleEntity entity, String [] photos){

     Date date =   new Date();


      Manager manager = (SessionUtils.getManagerSession(request));
        ManagerNormalSettle settle=null;
        if(null!=entity ){

            Integer count =dao.checkIsSettleExist(entity.getOrderId(),entity.getSettleNodeId());

            if(count>0){

                return "3";

            }

         settle= new ManagerNormalSettle();

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


                String rootPath = request.getSession().getServletContext().getRealPath("");
                File filePath = new File(rootPath + PicturePathContantUtil.UPLOAD_MANAGER_SETTLE_APPLY_UPLOAD_PHOTO + DateUtils.getDate1());

                if (!filePath.exists() && !filePath.isDirectory()) {
                    filePath.mkdirs();
                }
                String filepath = filePath + filePath.separator + uuid + ".jpeg";
                Base64Util.generateImage(photos[vr], filepath);

                String picpath = PicturePathContantUtil.UPLOAD_MANAGER_SETTLE_APPLY_UPLOAD_PHOTO + DateUtils.getDate1() + filePath.separator + uuid
                        + ".jpeg";


                businessPicService.insertPhotos(photos[vr],SettleStatusConstantUtil.SETTLE_PIC_TYPE_666,settle==null?0:settle.getSettleId(),picpath);


            }

    }

        return "2";
    }
    @Autowired
    private BusinessPicService businessPicService;




    public List<ManagerNormalSettle> findSettleInfoDetailBySettleId(String settleId){



        return dao.findSettleInfoDetailBySettleId(settleId);
    }
    
    
    public ManagerNormalSettle queryLastNormalSettleNode(Integer orderId){
    	return dao.queryLastNormalSettleNode(orderId);
    }
}

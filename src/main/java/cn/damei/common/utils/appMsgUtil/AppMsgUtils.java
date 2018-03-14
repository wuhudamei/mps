package cn.damei.common.utils.appMsgUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppMsgUtils {






    @Autowired
    private AppMsgUtilDao msgUtilDao;



    public  Integer saveMsgContent(AppMsgEntity entity){


        return  msgUtilDao.saveAppMsgContent(entity);

    }





      public  int checkIsExistByTypeAndId(final String relatedBusinessType,final String relatedBusinessId){

        return msgUtilDao.checkIsExistByTypeAndId(relatedBusinessType,relatedBusinessId);

    }


    public    void batchSaveAppMsgContent(List<AppMsgEntity> list){


          msgUtilDao.batchSaveAppMsgContent(list);
    }


}

package cn.damei.common.utils.appMsgUtil;

import cn.damei.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Created by joseph on 2017/7/31.
 */

@MyBatisDao
public interface AppMsgUtilDao {


    /**
     * 保存消息内容,并返回主键
     * @param entity
     * @return
     */
    Integer saveAppMsgContent(AppMsgEntity entity);


    /**
     * 根据业务id和类型查询是否插入 (1为插入,0为没插入)
     * @param relatedBusinessType
     * @param relatedBusinessId
     * @return
     */
    int checkIsExistByTypeAndId(final String relatedBusinessType,final String relatedBusinessId);





    void batchSaveAppMsgContent(List<AppMsgEntity> list);
}

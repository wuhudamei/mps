/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizOrderChecksizePic;

/**
 * 上传复尺DAO接口
 * @author wyb
 * @version 2016-10-20
 */
@MyBatisDao
public interface BizOrderChecksizeDao extends CrudDao2<BizOrderChecksizeEntity> {

	//通过复尺表id查询复尺图片
	List<BizOrderChecksizePic> selectPicByOrderChecksizeId(Integer orderChecksizeId);

	//通过复尺表id查询详情
	BizOrderChecksizeEntity selectDetailsByOrderChecksizeId(Integer orderChecksizeId);

	/**
	 * 更新厂家复尺
	 * @param bizOrderChecksize
	 * @return
	 */
	boolean updateOrderChecksize(BizOrderChecksizeEntity bizOrderChecksize);
	/**
	 * 动态加载复尺项
	 * @param storeId
	 * @param projectModeValue
	 * @return
	 */
	List<BizOrderChecksizeEntity> findMainItem(String storeId, String projectModeValue);
	
}
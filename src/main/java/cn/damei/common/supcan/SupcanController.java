
package cn.damei.common.supcan;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.damei.common.config.Global;
import cn.damei.common.supcan.annotation.treelist.SupTreeList;
import cn.damei.common.supcan.annotation.treelist.cols.SupCol;
import cn.damei.common.supcan.annotation.treelist.cols.SupGroup;
import cn.damei.common.supcan.treelist.TreeList;
import cn.damei.common.supcan.treelist.cols.Col;
import cn.damei.common.supcan.treelist.cols.Group;
import cn.damei.common.utils.CacheUtils;
import cn.damei.common.utils.SpringContextHolder;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;


@Controller
@RequestMapping(value = "${adminPath}/supcan")
public class SupcanController extends BaseController {

	private static final String SUPCAN_CACHE = "supcanCache";
	

	@RequestMapping(value = "treeList/{typeAlias}.xml")
	@ResponseBody
	public TreeList treeList(@PathVariable("typeAlias") String typeAlias) {
		

		boolean useCache = Global.getConfig("supcan.useCache") == "true";
		if (useCache){
			Object object = CacheUtils.get(SUPCAN_CACHE, typeAlias);
			if (object != null){
				return (TreeList)object;
			}
		}
		

		Class<?> clazz;
		
		try{

			SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
			clazz = sqlSessionFactory.getConfiguration().getTypeAliasRegistry().resolveAlias(typeAlias);
		}catch (Exception e) {

			return null;
		}
		

		SupTreeList supTreeList = clazz.getAnnotation(SupTreeList.class);


		if (supTreeList == null){
			return null;
		}
		

		TreeList treeList = new TreeList(supTreeList);
		

		Map<String, Group> groupMap = Maps.newHashMap();
		if (supTreeList !=null && supTreeList.groups() != null){
			for (SupGroup supGroup : supTreeList.groups()){
				groupMap.put(supGroup.id(), new Group(supGroup));
			}
		}
		

		List<Object> cols = treeList.getCols();
		for (Method m : clazz.getMethods()){
			SupCol supCol = m.getAnnotation(SupCol.class);
			if (supCol != null){
				

				Col col = new Col(supCol);
				if (StringUtils.isBlank(col.getName())){
					col.setName(StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3)));
				}
				

				if (StringUtils.isBlank(supCol.groupId())){
					cols.add(col);
				}

				else{
					Group group = groupMap.get(supCol.groupId());
					if (group != null){
						group.getCols().add(col);
					}
				}
			}
		}
		

		Comparator<Object> comparator = new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				int sort1 = 0, sort2 = 0;
				if (o1 instanceof Group){
					sort1 = ((Group)o1).getSort();
				}else if (o1 instanceof Col){
					sort1 = ((Col)o1).getSort();
				}
				if (o2 instanceof Group){
					sort2 = ((Group)o2).getSort();
				}else if (o2 instanceof Col){
					sort2 = ((Col)o2).getSort();
				}
				return new Integer(sort1).compareTo(new Integer(sort2));
			}
		};


		listToTree(cols, groupMap, null, comparator);
		

		Collections.sort(cols, comparator);


		if (useCache){
			CacheUtils.put(SUPCAN_CACHE, typeAlias, treeList);
		}
		
		return treeList;
	}
	

	private void listToTree(List<Object> colList, Map<String, Group> groupMap, String parentId, Comparator<Object> comparator){
		for (Map.Entry<String, Group> e : groupMap.entrySet()){
			Group g = e.getValue();
			if (StringUtils.equals(parentId, g.getParentId())){
				colList.add(g);

				for (Map.Entry<String, Group> ec : groupMap.entrySet()){
					Group gc = ec.getValue();
					if (g.getId() != null && g.getId().equals(gc.getParentId())){
						List<Object> childrenList = Lists.newArrayList();
						listToTree(childrenList, groupMap, gc.getParentId(), comparator);
						g.getCols().addAll(childrenList);
						break;
					}
				}

				Collections.sort(g.getCols(), comparator);
			}
		}
	}
	

	@RequestMapping(value = "treeList/test/test.xml")
	@ResponseBody
	public TreeList treeListTest() {


		TreeList treeList = new TreeList();
		

		List<Object> cols = treeList.getCols();
		cols.add(new Col("id", "编号"));
		cols.add(new Col("office", "归属部门"));
		cols.add(new Col("loginName", "登录名"));
		cols.add(new Col("name", "名称"));
		cols.add(new Col("remarks", "备注"));
		

		

		Group group = new Group("时间");
		List<Object> groupCol = group.getCols();
		groupCol.add(new Col("createDate", "创建时间"));
		groupCol.add(new Col("updateDate", "更新时间"));
		

		Group group2 = new Group("时间2");
		List<Object> group2Col = group2.getCols();
		group2Col.add(new Col("createDate2", "创建时间2"));
		group2Col.add(new Col("updateDate2", "更新时间2"));
		

		groupCol.add(group2);


		cols.add(group);
		

		return treeList;
	}
}

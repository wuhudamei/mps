
package cn.damei.entity.modules;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;
import cn.damei.common.utils.UserUtils;


public class Site extends DataEntity<Site> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String title;
	private String logo;
	private String description;
	private String keywords;
	private String theme;
	private String copyright;
	private String customIndexView;
	private String domain;

	public Site() {
		super();
	}
	
	public Site(String id){
		this();
		this.id = id;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=1, max=100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Length(min=0, max=255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=255)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Length(min=1, max=255)
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCustomIndexView() {
		return customIndexView;
	}

	public void setCustomIndexView(String customIndexView) {
		this.customIndexView = customIndexView;
	}


	public static String defaultSiteId(){
		return "1";
	}
	

	public static boolean isDefault(String id){
		return id != null && id.equals(defaultSiteId());
	}
	

	public static String getCurrentSiteId(){
		String siteId = (String)UserUtils.getCache("siteId");
		return StringUtils.isNotBlank(siteId)?siteId:defaultSiteId();
	}


   	public static final String TPL_BASE = "/WEB-INF/views/modules/cms/front/themes";


   	public String getSolutionPath() {
   		return TPL_BASE + "/" + getTheme();
   	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
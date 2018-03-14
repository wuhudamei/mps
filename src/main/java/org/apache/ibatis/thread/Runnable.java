package org.apache.ibatis.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.Configuration;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;


public class Runnable implements java.lang.Runnable {

	public static Logger log = Logger.getLogger(Runnable.class);

	private String location;
	private Configuration configuration;

	private Long beforeTime = 0L;
	private static boolean refresh = false;

	private static String mappingPath = "mappings";
	private static int delaySeconds = 10;
	private static int sleepSeconds = 1;
	
	private static boolean enabled = false;

	static {
		delaySeconds = PropertiesUtil.getInt("delaySeconds");
		sleepSeconds = PropertiesUtil.getInt("sleepSeconds");
		mappingPath = PropertiesUtil.getString("mappingPath");
		enabled = "true".equals(PropertiesUtil.getString("enabled"));
		
		delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;
		sleepSeconds = sleepSeconds == 0 ? 1 : sleepSeconds;
		mappingPath = StringUtils.isBlank(mappingPath) ? "mappings"
				: mappingPath;

		log.debug("[delaySeconds] " + delaySeconds);
		log.debug("[sleepSeconds] " + sleepSeconds);
		log.debug("[mappingPath] " + mappingPath);

	}

	public static boolean isRefresh() {
		return refresh;
	}

	public Runnable(String location, Configuration configuration) {
		this.location = location.replaceAll("\\\\", "/");
		this.configuration = configuration;
	}

	@Override
	public void run() {
		location = location.substring("file [".length(),
				location.lastIndexOf(mappingPath) + mappingPath.length());
		beforeTime = System.currentTimeMillis();

		log.debug("[location] " + location);
		log.debug("[configuration] " + configuration);
		
		if (enabled){
			start(this);
		}
	}

	public void start(final Runnable runnable) {

		new Thread(new java.lang.Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(delaySeconds * 1000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				refresh = true;

				System.out.println("========= Enabled refresh mybatis mapper =========");

				while (true) {
					try {
						runnable.refresh(location, beforeTime);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						Thread.sleep(sleepSeconds * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}


	public void refresh(String filePath, Long beforeTime) throws Exception {


		Long refrehTime = System.currentTimeMillis();

		List<File> refreshs = this.getRefreshFile(new File(filePath),
				beforeTime);
		if (refreshs.size() > 0) {
			log.debug("refresh files:" + refreshs.size());
		}
		for (int i = 0; i < refreshs.size(); i++) {
			System.out.println("Refresh file: "
					+ mappingPath
					+ StringUtils.substringAfterLast(refreshs.get(i)
							.getAbsolutePath(), mappingPath));
			log.debug("refresh file:" + refreshs.get(i).getAbsolutePath());
			log.debug("refresh filename:" + refreshs.get(i).getName());
			SqlSessionFactoryBean.refresh(new FileInputStream(refreshs.get(i)),
					refreshs.get(i).getAbsolutePath(), configuration);
		}

		if (refreshs.size() > 0) {
			this.beforeTime = refrehTime;
		}
	}


	public List<File> getRefreshFile(File dir, Long beforeTime) {
		List<File> refreshs = new ArrayList<File>();

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				refreshs.addAll(this.getRefreshFile(file, beforeTime));
			} else if (file.isFile()) {
				if (this.check(file, beforeTime)) {
					refreshs.add(file);
				}
			} else {
				System.out.println("error file." + file.getName());
			}
		}

		return refreshs;
	}


	public boolean check(File file, Long beforeTime) {
		if (file.lastModified() > beforeTime) {
			return true;
		}
		return false;
	}

}

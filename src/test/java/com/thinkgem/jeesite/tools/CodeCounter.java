package com.thinkgem.jeesite.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CodeCounter {


	public static void main(String[] args) {
		String file = CodeCounter.class.getResource("/").getFile();
		String path = file.replace("target/test-classes", "src");

		ArrayList<File> al = getFile(new File(path));
		for (File f : al) {
			if (f.getName().matches(".*\\.java$")){
				count(f);
				System.out.println(f);
			}
		}
		System.out.println("统计文件：" + files);
		System.out.println("代码行数：" + codeLines);
		System.out.println("注释行数：" + commentLines);
		System.out.println("空白行数：" + blankLines);
	}
	
	static long files = 0;
	static long codeLines = 0;
	static long commentLines = 0;
	static long blankLines = 0;
	static ArrayList<File> fileArray = new ArrayList<File>();
	

	public static ArrayList<File> getFile(File f) {
		File[] ff = f.listFiles();
		for (File child : ff) {
			if (child.isDirectory()) {
				getFile(child);
			} else
				fileArray.add(child);
		}
		return fileArray;

	}


	private static void count(File f) {
		BufferedReader br = null;
		boolean flag = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.matches("^[ ]*$")) {
					blankLines++;
				} else if (line.startsWith("//")) {
					commentLines++;
				} else if (line.startsWith("/*") && !line.endsWith("*/")) {
					commentLines++;
					flag = true;
				} else if (line.startsWith("/*") && line.endsWith("*/")) {
					commentLines++;
				} else if (flag == true) {
					commentLines++;
					if (line.endsWith("*/")) {
						flag = false;
					}
				} else {
					codeLines++;
				}
			}
			files++;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
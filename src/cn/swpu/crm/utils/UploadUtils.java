package cn.swpu.crm.utils;

import java.util.UUID;

/**
 *
 * @author zhangbo
 *
 */
public class UploadUtils {
	/**
	 * 解决文件重复问题
	 */
	public static String getUuidFileName(String filename){
		int index = filename.lastIndexOf(".");
		String extions = filename.substring(index);
		return UUID.randomUUID().toString()+extions;
	}
	
	/**
	 * 目录分离
	 */
	
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = code1>>>4;
		int d2 = code2  &0xf;
		return "/"+d1+"/"+d2;
	}
	
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().hashCode());
	}
}

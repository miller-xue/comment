package com.miller.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码缓存，存放用户手机号与所下发的验证码
 */
public class CodeCache {
	private static CodeCache instance;
	
	private Map<Long,String> codeMap;
	
	private CodeCache() {
		codeMap = new HashMap<Long,String>();
	}
	
	public static CodeCache getInstance() {
		if(instance == null) {
			synchronized(CodeCache.class){
				if (instance == null) {
					instance = new CodeCache();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 保存手机号与验证码
	 * @param phone 手机号
	 * @param code 验证码
	 * @return true：保存成功，false：保存失败，手机号已存在
	 */
	public boolean save(Long phone,String code) {
		if (codeMap.containsKey(phone)) {
			//方法在这里，被锁
			synchronized (CodeCache.class) {
				//1个人进去,设置了KEy
				if (!codeMap.containsKey(phone)) {
					codeMap.put(phone, code);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 根据手机号获取验证码
	 * @param phone 手机号
	 * @return 验证码
	 */
	public String getCode(Long phone) {
		return codeMap.get(phone);
	}
}
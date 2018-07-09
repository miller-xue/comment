package com.miller.util;


import java.util.UUID;



/**
 * 共通工具类.
 */
public class CommonUtil {

	/**
	 * 生成指定位数的随机整数
	 * 
	 * @param number
	 *            位数
	 * @return 随机整数
	 */
	public static int random(int number) {
		return (int) ((Math.random() * 9 + 1) * Math.pow(10, number - 1));
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 判断session中存放的动作dto列表中是否包含指定的url
	 * @param session
	 * @param url 
	 * @param method http动作
	 * @return true:包含，false：不包含

	public static boolean contains(HttpSession session,String url,String method) {
		Object obj = session.getAttribute(SessionKeyConst.ACTION_INFO);
		if(obj != null) {
			@SuppressWarnings("unchecked")
			List<ActionDto> dtoList = (List<ActionDto>)obj;
			for(ActionDto actionDto : dtoList) {
				if(!isEmpty(actionDto.getMethod()) && !actionDto.getMethod().equals(method)) {
					continue;
				}
				if(!url.matches(actionDto.getUrl())) {
					continue;
				}
				return true;
			}
		}
		return false;
	}*/
}

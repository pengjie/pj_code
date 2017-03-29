package com.result;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants
{
	
	/**
	* 产品分类的顶层父类
	*/
	public static final long PRODUCT_CATEGORY_PARENT_TOP = 0;
	
	/**
	 * 产地行情/市场行情
	 * @author sq
	 *
	 * 2016年12月9日
	 */
	public class KEY{
		/** 产地行情***/
		public final static String AREA = "area";
		
		/** 市场行情***/
		public final static String MARKET = "market";
	}
	public class Facet{
		public static final String CATEID1= "cateId1";
		public static final String CATEID3= "cateId3";
		public static final String BREEDID= "breedId";
		public static final String AREAID= "areaId";
		public static final String PROVINCEID= "provinceId";
		public static final String CITYID= "cityId";
		public static final String MARKETID= "marketId";
	}
	public static Map<String, Set<String>> retentionHeaders = new HashMap<String, Set<String>>();

	public static final String HN_SSO_TICKET_COOKIE_KEY = "hn_sso_ticket_cookie_key";

	public final static String COOKIE_HNUSER = "HNUSER_LOGIN";

	public final static String CART_COOKIE_NAME = "hn.b2b.shop";

	/** 成功 */
	public static String STATUS_SUCCESS = "SUCCESS";
	/** 失败 */
	public static String STATUS_ERROR = "ERROR";
	static {
		// 请求时候保留用户的头部信息-cookie
		Set<String> cookieNames = new HashSet<String>();
		cookieNames.add(HN_SSO_TICKET_COOKIE_KEY);
		cookieNames.add(CART_COOKIE_NAME);
		retentionHeaders.put("Cookie", cookieNames);
		retentionHeaders.put("x-forwarded-for", null);
		retentionHeaders.put("X-Real-IP", null);
	}

	public static final String MARKET_COMMENT_GROUP_ID = "MARKET_COMMENT_LABEL";//行情评论在数据字典中的组ID
	public static final int EXCEL_SHEET_SIZE = 5000;//导出excel文件每个工作薄的大小
	
	public static final String TIME_FORM_BEFORE_WEEK = "before_week";//7天以内
	public static final String TIME_FORM_AFTER_WEEK = "after_week";//7天以前
	
	public static final String INVALID_REGIX = "[`~!@#$^&*|'\\[\\]<>~]";//输入的非法字符
	
}

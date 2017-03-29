/**create by liuhua at 2016年7月7日 上午10:01:49**/
package com.huinong.commerce.specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huinong.commerce.specification.result.PageInfo;
import com.huinong.commerce.specification.result.ResultMessage;
import com.huinong.commerce.specification.utils.MessageUtils;
import com.huinong.commerce.specification.utils.ParamHandler;
import com.huinong.commerce.specification.utils.SignUtils;

public class JsonTest {

	public static void main(String[] args) {
		Gson gson  = new GsonBuilder().serializeNulls().create();
		
		User user = new User();
		Map<String, User> userMap = new HashMap<String, User>();
		userMap.put("user1", new User());
		user.setUserMap(userMap);
		System.out.println(gson.toJson(user));
		
		ResultMessage rMessage1 = new ResultMessage(user, "");
		ResultMessage rMessage4 = new ResultMessage(user, new PageInfo(1, 10, 100), "");
		System.out.println(MessageUtils.toJson(rMessage4));
		System.out.println(MessageUtils.toJsonWithoutNull(rMessage1));
		ResultMessage rMessage2 = new ResultMessage("看透了2", FailureCode.ERR_001);
		System.out.println(gson.toJson(rMessage2));
		ResultMessage rMessage3 = new ResultMessage("看透了3", FailureCode.ERR_002, 10, 3);
		System.out.println(gson.toJson(rMessage3));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "liuhua");
		map.put("hnUserId", 277364);
		map.put("mebile", "18641254752");
		String sign = SignUtils.createSign(map, Constants.SIGN_KEY);
		map.put("sign", sign);
		ParamHandler paramHandler = new ParamHandler(map);
		System.out.println(paramHandler.checkParam(Constants.SIGN_KEY));
	}
	
}

class User{
	private Long userId;
	private String name;
	private Integer sex;
	private Double dou;
	private Boolean bool;
	private Short sho;
	private List<User> users;
	private Map<String, User> userMap;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Double getDou() {
		return dou;
	}
	public void setDou(Double dou) {
		this.dou = dou;
	}
	public Boolean getBool() {
		return bool;
	}
	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	public Short getSho() {
		return sho;
	}
	public void setSho(Short sho) {
		this.sho = sho;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Map<String, User> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}
}
package com.inter.specification.version;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class InterfaceVesrsionCondition implements RequestCondition<InterfaceVesrsionCondition> {
	private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/(\\d+(\\.\\d+)+)/");
	private String[] version;

	public InterfaceVesrsionCondition(String[] apiVersion) {
		this.version = apiVersion;
	}

	public InterfaceVesrsionCondition combine(InterfaceVesrsionCondition other) {
		return new InterfaceVesrsionCondition(other.getVersion());

	}

	public InterfaceVesrsionCondition getMatchingCondition(HttpServletRequest request) {
		Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
		if (m.find()) {//版本校验
			List<String> vs = Arrays.asList(this.version);
			String version = m.group(1);
			if (vs.contains(version)){//存在当前版本.必须使用存在版本(如:http://product.cnhnb.com/msupply/4.5/stop)
				return this;
			}else{//版本校验,没有当前版本
				return null;
			}
		}//表示可以不用版本(如:http://product.cnhnb.com/msupply/stop)
		return this;
	}

	public int compareTo(InterfaceVesrsionCondition other, HttpServletRequest request) {
		String[] version1 = other.getVersion();
		String[] version2 = this.version;
		
		return version1.length - version2.length;
	}
	
	public String[] getVersion(){
		return version;
	}
}

package com.service.gateway.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class GatewayPreFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(GatewayPreFilter.class);
	@Autowired
	private Environment env;

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();

	    log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    List<String> ignoreUrlList = fetchIgnoreUrlList();
	    String reqUrl = request.getRequestURL().toString();
	    if (!ignoreUrlList.isEmpty() & ignoreUrlList.contains(reqUrl)) {
	    	log.info("Ignore url is "+reqUrl);
	    	ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
	    	ctx.setSendZuulResponse(false);
	    }

	    return null;
	}

	
	private List<String> fetchIgnoreUrlList() {
		// TODO Auto-generated method stub
		List<String> ignoreUrlList = new ArrayList<String>();
		String ignoreUrlCount = env.getProperty("ignore.url.count");
		log.info("Ignore Url count is "+ignoreUrlCount);
		
		for (int count =1; count<=Integer.parseInt(ignoreUrlCount);count++) {
			String ignoreUrlProp = "ignore.url."+count;
			ignoreUrlList.add(env.getProperty(ignoreUrlProp));
		}	
		return ignoreUrlList;
	}



	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	// pre/post/route/error
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}

package com.spring.microservices.zuul1.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulSimpleFilter1 extends ZuulFilter {
	
	private static final Logger log = LoggerFactory.getLogger(ZuulSimpleFilter1.class);
	
	private RestTemplate template;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    HttpServletResponse response = ctx.getResponse();
	    
	    
	    
	    log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
}
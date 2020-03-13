package com.demo.cn.zuul.fliter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/10/28
 * Time: 16:19
 * Description: No Description
 */
@Component
public class TestZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext cxk = RequestContext.getCurrentContext();
        HttpServletRequest request = cxk.getRequest();
        //todo

        String id = request.getSession().getId();
        cxk.setSendZuulResponse(false);
        cxk.setResponseStatusCode(511);
        try {
            cxk.getResponse().getWriter().write(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

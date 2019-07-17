package org.agent.interceptor;

import org.agent.common.Constants;
import org.agent.pojo.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 管理员权限验证
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class AdminInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// 验证是否登录
		User user = (User) arg0.getInvocationContext().getSession().get(Constants.SESSION_USER);
		if (null != user && null != user.getUserCode() && null != user.getUserPassword() && user.getIsStart() == 1) {
			return arg0.invoke(); // 继续执行代码
		} else
			return Action.LOGIN; // 停止执行Action，直接返回给具体的Action处理
	}

}

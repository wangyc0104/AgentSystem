package org.agent.service.user;

import java.util.List;

import org.agent.pojo.User;

/**
 * 用户Service
 * @author Yicheng Wang
 */
public interface UserService {

	/**
	 * 获取用户列表
	 * @param user
	 * @return
	 */
	public List<User> getUserList(User user);

	/**
	 * 根据查询条件获取特定用户
	 * @param user
	 * @return
	 */
	public User getUser(User user);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int modifyUser(User user);

	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public int deleteUser(User user);

	/**
	 * 获取用户数量
	 * @param user
	 * @return
	 */
	public int count(User user);

	/**
	 * 用户是否已登录
	 * @param user
	 * @return
	 */
	public int isExitLoginUser(User user);

	/**
	 * 获取当前登录的用户
	 * @param user
	 * @return
	 */
	public User getLoginUser(User user);
	
	/**
	 * 需要事务处理的添加用户方式
	 * @param user
	 */
	public void tx_AddUser(User user);

}

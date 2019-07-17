package org.agent.dao.user;

import java.util.List;

import org.agent.pojo.User;

public interface UserMapper {

	// 查询用户列表
	public List<User> getUserList(User user);
	
	// 查询特定用户
	public User getUser(User user);
	
	/**
	 * 添加用户
	 * user新生成的ID放在生成的指定属性中
	 * @param user
	 * @return 返回影响的行数
	 */
	public int addUser(User user);
	
	// 修改用户
	public int modifyUser(User user);
	
	// 删除用户
	public int deleteUser(User user);
	
	// 统计用户总数
	public int count(User user);
	
	// 按用户名userCode查询是否存在某用户，大于等于1则存在，0则不存在
	public int isExitLoginUser(User user);

	public User getLoginUser(User user);
}

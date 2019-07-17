package org.agent.service.user;

import java.math.BigDecimal;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.user.UserMapper;
import org.agent.pojo.Account;
import org.agent.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired // 按类型寻找（默认找一个UserMapper）
	// @Qualifier("userMapper") // 按名称寻找（自定义一个）
	private UserMapper mapper; // Autowired自动按照byType装备
	
	@Autowired
	private AccountMapper accountMapper;

	public UserMapper getMapper() {
		return mapper;
	}

	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

	public AccountMapper getAccountMapper() {
		return accountMapper;
	}

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public List<User> getUserList(User user) {
		return mapper.getUserList(user);
	}

	@Override
	public User getUser(User user) {
		return mapper.getUser(user);
	}

	@Override
	public int addUser(User user) {
		return mapper.addUser(user);
	}

	@Override
	public int modifyUser(User user) {
		return mapper.modifyUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return mapper.deleteUser(user);
	}

	@Override
	public int count(User user) {
		return mapper.count(user);
	}

	@Override
	public int isExitLoginUser(User user) {
		return mapper.isExitLoginUser(user);
	}

	@Override
	public User getLoginUser(User user) {
		return mapper.getLoginUser(user);
	}

	// 需要事务处理的添加用户方式
	@Override
	public void tx_AddUser(User user) {
		// 将用户添加到用户列表中
		mapper.addUser(user); // 添加后，用户ID生成，可以使用user.getId()来获取
		// 为用户增加一个账户
		Account account = new Account();
		account.setUserId(user.getId());
		account.setMoney(new BigDecimal(0));
		account.setMoneyBak(new BigDecimal(0));
		accountMapper.addAccount(account);
		//
	}

}

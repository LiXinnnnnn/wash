package com.laiba.wash.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.OperatorMapper;
import com.laiba.wash.core.po.Operator;
import com.laiba.wash.core.po.OperatorExample;
import com.laiba.wash.core.po.OperatorExample.Criteria;
import com.laiba.wash.core.service.OperatorService;

/**
 * 操作员接口实现类
 * 
 * @author jerry
 *
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	private OperatorMapper operatorDAO;

	@Override
	public Operator login(String loginName, String password) {

		OperatorExample operatorExample = new OperatorExample();
		Criteria criteria = operatorExample.createCriteria();
		criteria.andNameEqualTo(loginName);
		criteria.andPasswordEqualTo(password);
		List<Operator> opList = operatorDAO.selectByExample(operatorExample);
		if (opList == null || opList.size() == 0) {
			return null;
		}
		return opList.get(0);
	}

	@Override
	public int updatePassword(Integer id, String newPassword) {
		Operator operatorPO = new Operator();
		operatorPO.setId(id);
		operatorPO.setPassword(newPassword);
		int i = operatorDAO.updateByPrimaryKeySelective(operatorPO);
		if (i > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}

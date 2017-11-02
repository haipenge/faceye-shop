package com.faceye.component.order.repository.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.faceye.component.order.repository.mongo.CartInfoRepository;
import com.faceye.component.order.service.model.CartInfo;

@Repository
public class CartInfoRepositoryImpl implements CartInfoRepository {
	@Autowired
	private MongoOperations mongoOps = null;

	@Override
	public CartInfo getCartInfo(String cartId) {
//		this.mongoOps.executeCommand(arg0)
		return null;
	}

}

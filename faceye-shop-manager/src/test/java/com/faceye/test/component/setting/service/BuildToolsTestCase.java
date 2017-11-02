package com.faceye.test.component.setting.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.faceye.component.setting.service.BuildTools;
import com.faceye.test.feature.service.BaseServiceTestCase;
@TransactionConfiguration(defaultRollback=false)
public class BuildToolsTestCase extends BaseServiceTestCase {
	@Autowired
	private BuildTools buildTools = null;

	@Test
	@Rollback(false)
	public void testInit() throws Exception {
		buildTools.init();
	}
}

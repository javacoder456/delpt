package com.zh.common.dbmanger.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;


public class MultiDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		return MultiDataSourceHolder.getDataSource();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {

		MultiDataSourceHolder.DB_NUM = targetDataSources.size();
		super.setTargetDataSources(targetDataSources);
	}

}

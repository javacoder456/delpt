package com.zh.common.dbmanger.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: DynamicDataSourceHolder
 * Function: 数据源持有者，用于数据源切换.
 */
public class MultiDataSourceHolder {

	private static Logger logger = LoggerFactory.getLogger(MultiDataSourceHolder.class);

	/**
	 * 数据源前缀
	 */
	public static final String DATA_SOURCE_PREFIX = "dataSource";

	/**
	 * 数据源数量
	 */
	public static int DB_NUM;

	/**
	 * 默认数据源随机分配
	 */
	private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();

	/**
	 * 修改数据库连接
	 *
	 * @param index 1/2
	 */
	public static void changeDataSource(int index) {

		String datasourceKey = DATA_SOURCE_PREFIX + index;
		setDataSource(datasourceKey);
	}

	public static void changeDataSource(String datasourceKey) {
		setDataSource(datasourceKey);
	}
	/**
	 * 获取数据库默认连接
	 *
	 * @return
	 */
	public static String getDataSource() {
		// 获取当前线程连接
		String datasourceKey = THREAD_DATA_SOURCE.get();
		return datasourceKey;
	}

	/**
	 * 设置线程数据库连接
	 *
	 * @param dataSource
	 */
	private static void setDataSource(String dataSource) {

		THREAD_DATA_SOURCE.set(dataSource);
		logger.info("切换后数据源：" + THREAD_DATA_SOURCE.get());

	}

}

package com.hhit.util;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return CustomerContextHolder.getCustomerType();
	}
	
}

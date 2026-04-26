package com.jdc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql")
public class ApplicationSqlProperties {

	private EmployeeSql employee = new EmployeeSql();
	private SupplierSql supplier = new SupplierSql();
	private ProductSql product = new ProductSql();
	private ProductSupplierSql productSupplier = new ProductSupplierSql();
	private PurchaseProductSql purchaseProduct = new PurchaseProductSql();
	private PurchaseSql purchase = new PurchaseSql();
	private StockSql stock = new StockSql();
	private StockHistorySql stockHistory = new StockHistorySql();
	
	@Data
	public static class EmployeeSql {
		private String isPresent;
	}
	
	@Data
	public static class SupplierSql {
		private String isPresent;
	}

	@Data
	public static class ProductSql {
		private String isPresent;
	}
	
	@Data
	public static class ProductSupplierSql {
		private String isPresent;
		private String create;
	}
	
	@Data
	public static class PurchaseProductSql {
		private String create;
	}

	@Data
	public static class PurchaseSql {
		private String create;
	}
	
	@Data
	public static class StockHistorySql {
		private String create;
	}

	@Data
	public static class StockSql {
		private String findById;
		private String update;
	}
	
}

package com.jdc.shop.model;

import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {
	private Pager pager;
	private List<T> contents;
	
	public static<T> Builder<T> builder() {
		return new Builder<T>();
	}
	
	public static class Builder<T> {
		private List<T> contents;
		private long count;
		private int page;
		private int size;
		
		public Builder<T> contents(List<T> contents) {
			this.contents = contents;
			return this;
		}
		
		public Builder<T> count(long count) {
			this.count = count;
			return this;
		}
		
		public Builder<T> page(int page) {
			this.page = page;
			return this;
		}
		
		public Builder<T>  size(int size) {
			this.size = size;
			return this;
		}
		
		public PageResult<T> build() {
			var result = new PageResult<T>();
			
			var pager = new Pager();
			pager.setCount(count);
			pager.setPage(page);
			pager.setSize(size);
			
			result.setPager(pager);
			result.setContents(contents);
			
			return result;
		}
	}
}

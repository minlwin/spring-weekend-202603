package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Pager {

	private long count;
	private int size;
	private int page;

	public List<Integer> getLinks() {
		if (count <= 0 || size <= 0) {
			return List.of();
		}

		var totalPages = getTotalPages();

		var start = Math.max(0, page - 2);
		var end = Math.min(totalPages, start + 5);
		start = Math.max(0, end - 5);

		var list = new ArrayList<Integer>(end - start);
		for (var i = start; i < end; i++) {
			list.add(i);
		}

		return list;
	}

	public int getTotalPages() {
		if (count <= 0 || size <= 0) {
			return 0;
		}

		return (int) ((count + size - 1) / size);
	}

	public boolean isFirst() {
		return page <= 0;
	}

	public boolean isLast() {
		return page >= getTotalPages() - 1;
	}

}

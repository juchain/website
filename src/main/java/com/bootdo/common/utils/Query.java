package com.bootdo.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 
	private int offset;
	// 每页条数
	private int limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		this.offset = Optional.ofNullable(params.get("offset")).isPresent()?Integer.parseInt(params.get("offset").toString()):0;
		this.limit = Optional.ofNullable(params.get("limit")).isPresent()?Integer.parseInt(params.get("limit").toString()):10;
		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

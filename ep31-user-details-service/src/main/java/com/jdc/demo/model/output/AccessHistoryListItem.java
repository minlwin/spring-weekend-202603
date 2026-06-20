package com.jdc.demo.model.output;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.demo.model.entity.AccessHistory;
import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.entity.AccessHistory.Status;
import com.jdc.demo.model.entity.AccessHistory_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessHistoryListItem {

	private UUID id;
	private String username;
	private AccessType type;
	private Status status;
	private LocalDateTime accessAt;
	private String remark;
	
	public static void select(CriteriaQuery<AccessHistoryListItem> cq, Root<AccessHistory> root, CriteriaBuilder cb) {
		cq.select(cb.construct(
				AccessHistoryListItem.class, 
				root.get(AccessHistory_.id),
				root.get(AccessHistory_.username),
				root.get(AccessHistory_.type),
				root.get(AccessHistory_.status),
				root.get(AccessHistory_.accessAt),
				root.get(AccessHistory_.remark)
		));
	}
}

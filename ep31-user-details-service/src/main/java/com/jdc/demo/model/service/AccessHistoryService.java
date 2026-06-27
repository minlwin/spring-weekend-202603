package com.jdc.demo.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.entity.AccessHistory;
import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.entity.AccessHistory.Status;
import com.jdc.demo.model.entity.AccessHistory_;
import com.jdc.demo.model.input.AccessSearch;
import com.jdc.demo.model.output.AccessHistoryListItem;
import com.jdc.demo.model.repo.AccessHistoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessHistoryService {
	
	private final AccessHistoryRepo repo;
	
	public void success(String name, AccessType type) {
		var entity = new AccessHistory();
		entity.setUsername(name);
		entity.setType(type);
		entity.setStatus(Status.Success);
		entity.setAccessAt(LocalDateTime.now());
		repo.save(entity);
	}

	public void fails(String name, AuthenticationException exception) {
		
		var message = switch (exception) {
		case UsernameNotFoundException _ -> "There is no user in account.";
		case BadCredentialsException _ -> "Mismatch Password.";
		case DisabledException _ -> "Account is disabled.";
		default -> "Other Failures";
		};
		
		var entity = new AccessHistory();
		entity.setUsername(name);
		entity.setType(AccessType.Login);
		entity.setStatus(Status.Fails);
		entity.setAccessAt(LocalDateTime.now());
		entity.setRemark(message);
		repo.save(entity);
	}

	@Transactional(readOnly = true)
	public List<AccessHistoryListItem> search(AccessSearch form) {
		
		Function<CriteriaBuilder, CriteriaQuery<AccessHistoryListItem>> queryFunc = cb -> {
			var cq = cb.createQuery(AccessHistoryListItem.class);
			var root = cq.from(AccessHistory.class);
			
			AccessHistoryListItem.select(cq, root, cb);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(AccessHistory_.accessAt)));
			
			return cq;
		};
		
		return repo.search(queryFunc);
	}

}

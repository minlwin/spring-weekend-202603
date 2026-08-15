package com.jdc.spring.demo.model.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.jdc.spring.demo.model.BaseRepository;
import com.jdc.spring.demo.model.entity.VerificationHistory;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.entity.VerificationHistory.Status;

public interface VerificationHistoryRepo extends BaseRepository<VerificationHistory, UUID>{

	@Query("select h from VerificationHistory h where h.account.email = :email and h.verifiedAt = null order by h.sendAt desc")
	List<VerificationHistory> findForVerification(String email);

	@Query("select count(h.id) from VerificationHistory h where h.account.email = :email and h.action = :action and h.sendAt >= :timeFrom and h:status = :status")
	Long findFialsCount(String email, Action action, LocalDateTime timeFrom, Status status);
}

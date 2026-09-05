package com.jdc.product.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.product.api.input.ProductForm;
import com.jdc.product.api.input.ProductSearch;
import com.jdc.product.api.output.ModificationResult;
import com.jdc.product.api.output.ProductDetails;
import com.jdc.product.api.output.ProductListItem;
import com.jdc.product.model.entity.Product;
import com.jdc.product.model.entity.Product_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

	private final EntityManager em;

	public List<ProductListItem> search(ProductSearch form) {
		
		var criteriaBuilder = em.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(ProductListItem.class);
		
		var root = criteriaQuery.from(Product.class);
		
		criteriaQuery.select(criteriaBuilder.construct(ProductListItem.class, 
				root.get(Product_.id),
				root.get(Product_.name),
				root.get(Product_.category),
				root.get(Product_.unitPrice),
				root.get(Product_.status)
		));
		
		var params = new ArrayList<Predicate>();
		
		if(null != form.status()) {
			params.add(criteriaBuilder.equal(root.get(Product_.status), form.status()));
		}
		
		if(StringUtils.hasLength(form.keyword())) {
			var value = form.keyword().toLowerCase().concat("%");
			params.add(criteriaBuilder.or(
				criteriaBuilder.like(root.get(Product_.name), value),
				criteriaBuilder.like(root.get(Product_.category), value)
			));
		}
		
		criteriaQuery.where(params);
		
		var query = em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

	public ProductDetails findById(int id) {
		var entity = findOne(id);
		return ProductDetails.from(entity);
	}

	@Transactional
	public ModificationResult<Integer> create(ProductForm form) {
		var entity = new Product();
		entity.setName(form.name());
		entity.setCategory(form.category());
		entity.setUnitPrice(form.unitPrice());
		entity.setStatus(form.status());
		entity.setDescription(form.description());
		
		em.persist(entity);
		
		return new ModificationResult<Integer>(entity.getId());
	}
	
	@Transactional
	public ModificationResult<Integer> update(int id, ProductForm form) {
		var entity = findOne(id);
		entity.setName(form.name());
		entity.setCategory(form.category());
		entity.setUnitPrice(form.unitPrice());
		entity.setStatus(form.status());
		entity.setDescription(form.description());
		return new ModificationResult<Integer>(id);
	}

	private Product findOne(int id) {
		return Optional.ofNullable(em.find(Product.class, id))
				.orElseThrow();
	}

}

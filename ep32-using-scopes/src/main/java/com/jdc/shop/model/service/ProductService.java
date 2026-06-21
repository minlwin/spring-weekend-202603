package com.jdc.shop.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.anonymous.output.ProductDto;
import com.jdc.shop.controller.management.input.ProductSearch;
import com.jdc.shop.model.PageResult;
import com.jdc.shop.model.entity.Category;
import com.jdc.shop.model.entity.Category_;
import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.Product_;
import com.jdc.shop.model.repository.CategoryRepo;
import com.jdc.shop.model.repository.ProductRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
@Transactional(readOnly = true)
public class ProductService {

	@Autowired
	private ProductRepo repo;
	@Autowired
	private CategoryRepo categoryRepo;

	public PageResult<ProductDto> search(String category, String keyword, int page, int size) {
		return repo.search(queryFunc(null, category, keyword), countFunc(null, category, keyword), page, size);
	}

	public PageResult<ProductDto> search(ProductSearch form, int page, int size) {
		return repo.search(
			queryFunc(form.getCategoryId(), null, form.getKeyword()),
			countFunc(form.getCategoryId(), null, form.getKeyword()),
			page, size
		);
	}

	public Optional<ProductDto> findById(UUID id) {
		return repo.findById(id)
			.map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()));
	}

	@Transactional
	@PreAuthorize("hasAnyAuthority('Admin', 'Employee')")
	public void upload(MultipartFile file) {
		try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			reader.lines()
				.filter(StringUtils::hasLength)
				.forEach(line -> {
					var parts = line.split("\t");
					var product = new Product();
					product.setName(parts[0].trim());
					product.setPrice(Integer.parseInt(parts[1].trim()));
					product.setDescription(parts[2].trim());
					product.setCategory(parseCategories(parts[3]));
					repo.save(product);
				});
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload products", e);
		}
	}

	private List<Category> parseCategories(String csv) {
		return Arrays.stream(csv.split(","))
			.map(String::trim)
			.filter(StringUtils::hasLength)
			.map(name -> categoryRepo.findByName(name).orElseGet(() -> {
				var cat = new Category();
				cat.setName(name);
				return categoryRepo.save(cat);
			}))
			.toList();
	}

	private Function<CriteriaBuilder, CriteriaQuery<ProductDto>> queryFunc(
			Integer categoryId, String categoryName, String keyword) {
		return cb -> {
			var cq = cb.createQuery(ProductDto.class);
			var root = cq.from(Product.class);
			ProductDto.select(cq, cb, root);
			cq.distinct(true);
			var predicates = predicates(cb, root, categoryId, categoryName, keyword);
			if (!predicates.isEmpty()) {
				cq.where(predicates.toArray(Predicate[]::new));
			}
			cq.orderBy(cb.asc(root.get(Product_.name)));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(
			Integer categoryId, String categoryName, String keyword) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Product.class);
			cq.select(cb.countDistinct(root));
			var predicates = predicates(cb, root, categoryId, categoryName, keyword);
			if (!predicates.isEmpty()) {
				cq.where(predicates.toArray(Predicate[]::new));
			}
			return cq;
		};
	}

	private List<Predicate> predicates(CriteriaBuilder cb, Root<Product> root,
			Integer categoryId, String categoryName, String keyword) {
		var params = new ArrayList<Predicate>();

		if (categoryId != null) {
			var cat = root.join(Product_.category, JoinType.INNER);
			params.add(cb.equal(cat.get(Category_.id), categoryId));
		} else if (StringUtils.hasLength(categoryName)) {
			var cat = root.join(Product_.category, JoinType.INNER);
			params.add(cb.equal(cat.get(Category_.name), categoryName));
		}

		if (StringUtils.hasLength(keyword)) {
			var param = keyword.toLowerCase().concat("%");
			params.add(cb.or(
				cb.like(cb.lower(root.get(Product_.name)), param),
				cb.like(cb.lower(root.get(Product_.description)), param)
			));
		}

		return params;
	}
}

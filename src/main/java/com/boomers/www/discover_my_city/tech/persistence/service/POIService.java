package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.POIStatus;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.POIEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserEntityRepository;
import com.boomers.www.discover_my_city.utils.mapper.Mapper;
import jakarta.persistence.criteria.Join;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class POIService implements POIRepository {
  private final POIEntityRepository poiEntityRepository;
  private final UserEntityRepository userRepository;
  private final Mapper<POI, POIEntity> poiToPoiEntityMapper;

  public POIService(
      POIEntityRepository poiEntityRepository,
      UserEntityRepository userRepository,
      Mapper<POI, POIEntity> poiToPoiEntityMapper) {
    this.poiEntityRepository = poiEntityRepository;
    this.userRepository = userRepository;
    this.poiToPoiEntityMapper = poiToPoiEntityMapper;
  }

  @Override
  public POI save(POI poi) {
    POIEntity entity = poiToPoiEntityMapper.to(poi);
    return poiToPoiEntityMapper.from(poiEntityRepository.save(attachCreatorAndApprover(entity)));
  }

  @Override
  public Paged<POI> getAllApprovedPoiAndUserInApproval(POIRequest request) {
    Pageable pagination = PageRequest.of(request.getPageNumber(), request.getPageSize());
    Specification<POIEntity> specification =
        hasStatus(POIStatus.APPROVED)
            .or(
                hasCreator(request.getCreator().getUsername())
                    .and(hasStatus(POIStatus.IN_APPROVAL)));
    Page<POIEntity> page = poiEntityRepository.findAll(specification, pagination);
    Paged<POI> result = new Paged<>();
    result.setTotalSize(page.getTotalElements());
    result.setTotalPages(page.getTotalPages());
    result.setPageSize(page.getSize());
    result.setPageNumber(page.getNumber());
    result.setList(page.stream().map(poiToPoiEntityMapper::from).collect(Collectors.toList()));
    return result;
  }

  @Override
  public Paged<POI> findAll(POIRequest request) {
    Pageable pagination = PageRequest.of(request.getPageNumber(), request.getPageSize());
    Specification<POIEntity> specification = isTrue();
    if (!Objects.isNull(request.getStatus())) {
      specification =
          specification.and(hasStatus(POIStatus.valueOf(request.getStatus().toString())));
    }
    if (!Objects.isNull(request.getId())) {
      specification = specification.and(hasId(request.getId()));
    }
    Page<POIEntity> page = poiEntityRepository.findAll(specification, pagination);
    // TODO gestire creazione
    Paged<POI> result = new Paged<>();
    result.setTotalSize(page.getTotalElements());
    result.setTotalPages(page.getTotalPages());
    result.setPageSize(page.getSize());
    result.setPageNumber(page.getNumber());
    result.setList(page.stream().map(poiToPoiEntityMapper::from).collect(Collectors.toList()));
    return result;
  }

  @Override
  public POI update(POI poi) {
    POIEntity entity = attachCreatorAndApprover(poiEntityRepository.getReferenceById(poi.getId()));
    entity.setDescription(poi.getDescription());
    entity.setStatus(POIStatus.valueOf(poi.getStatus().toString()));
    entity.setLatitude(poi.getCoordinate().getLatitude());
    entity.setLongitude(poi.getCoordinate().getLongitude());
    entity.setName(poi.getName());
    return poiToPoiEntityMapper.from(poiEntityRepository.save(entity));
  }

  private POIEntity attachCreatorAndApprover(POIEntity entity) {
    UserEntity creator =
        userRepository
            .findByUsernameOrEmail(
                entity.getCreator().getUsername(), entity.getCreator().getEmail())
            .orElse(null);
    UserEntity approver = null;
    if (!Objects.isNull(entity.getApprover())) {
      approver =
          userRepository
              .findByUsernameOrEmail(
                  entity.getApprover().getUsername(), entity.getApprover().getEmail())
              .orElse(null);
    }
    entity.setCreator(creator);
    entity.setApprover(approver);
    return entity;
  }

  static Specification<POIEntity> isTrue() {
    return (poi, cq, cb) -> cb.isTrue(cb.literal(true));
  }

  static Specification<POIEntity> hasStatus(POIStatus status) {
    return (poi, cq, cb) -> cb.equal(poi.get("status"), status);
  }

  static Specification<POIEntity> hasId(Integer id) {
    return (poi, cq, cb) -> cb.equal(poi.get("id"), id);
  }

  public static Specification<POIEntity> hasCreator(String username) {
    return (root, query, criteriaBuilder) -> {
      Join<POIEntity, UserEntity> poi = root.join("creator");
      return criteriaBuilder.equal(poi.get("username"), username);
    };
  }
}

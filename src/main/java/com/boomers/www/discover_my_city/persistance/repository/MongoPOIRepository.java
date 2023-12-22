package com.boomers.www.discover_my_city.persistance.repository;

import com.boomers.www.discover_my_city.persistance.model.POI;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPOIRepository extends MongoRepository<POI, String> {}

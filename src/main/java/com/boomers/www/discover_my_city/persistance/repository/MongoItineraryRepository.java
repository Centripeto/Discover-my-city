package com.boomers.www.discover_my_city.persistance.repository;

import com.boomers.www.discover_my_city.persistance.model.Itinerary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoItineraryRepository extends MongoRepository<Itinerary, String> {
  @Aggregation(
      pipeline = {
        "{$lookup: { from: \"poi\", localField: \"pois\", foreignField: \"_id\", as: \"pois\"}}",
      })
  List<com.boomers.www.discover_my_city.model.Itinerary> findAllDeep();
}

package com.boomers.www.discover_my_city.persistance.repository;

import com.boomers.www.discover_my_city.persistance.model.Itinerary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoItineraryRepository extends MongoRepository<Itinerary, String> {
  @Aggregation(
      pipeline = {
        "{$project: { _id: 1, name: 1, description: 1, temporary: 1, status: 1, endDate: 1, startDate:1, pois: { $map: { input: \"$pois\", as: \"poi\", in: { $convert: { input: \"$$poi\", to: \"objectId\"}}}}}}",
        "{$lookup: { from: \"poi\", localField: \"pois\", foreignField: \"_id\", as: \"pois\"}}",
      })
  List<com.boomers.www.discover_my_city.model.Itinerary> findAllDeep();
}

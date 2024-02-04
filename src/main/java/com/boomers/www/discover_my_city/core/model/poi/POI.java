package com.boomers.www.discover_my_city.core.model.poi;

import com.boomers.www.discover_my_city.core.model.user.Coordinate;
import com.boomers.www.discover_my_city.core.model.user.Status;
import com.boomers.www.discover_my_city.core.model.user.User;

public class POI {
    private Integer id;
    private String name;
    private String description;
    private Coordinate coordinate;
    private Status status;
    private User creator;
    private User approver;

    public void setId(Integer id) { this.id = id; }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) { this.status = status; }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    @Override
    public String toString() {
        String json = """
        {
            "id": "%s",
            "name": "%s",
            "description": "%s",
            "status": "%s",
            "coordinate": %s,
        }
      """;
        return String.format(json, this.id, this.name, this.description, this.status, this.coordinate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private POI poi;

        public Builder() {
            reset();
        }

        public Builder addId(Integer id) {
            poi.setId(id);
            return this;
        }


        public Builder addName(String name) {
            poi.setName(name);
            return this;
        }


        public Builder addDescription(String description) {
            poi.setDescription(description);
            return this;
        }


        public Builder addCoordinate(Coordinate coordinate) {
            poi.setCoordinate(coordinate);
            return this;
        }


        public Builder addStatus(Status status) {
            poi.setStatus(status);
            return this;
        }

        public Builder addCreator(User creator) {
            poi.setCreator(creator);
            return this;
        }

        public Builder addApprover(User approver) {
            poi.setApprover(approver);
            return this;
        }

        public Builder reset() {
            poi = new POI();
            return this;
        }

        public POI build(){
            return poi;
        }
    }
}


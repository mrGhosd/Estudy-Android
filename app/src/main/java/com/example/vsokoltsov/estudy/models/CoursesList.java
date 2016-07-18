package com.example.vsokoltsov.estudy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vsokoltsov on 15.07.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursesList {
    @JsonProperty("courses")
    private List<Course> courses;

    @JsonProperty("search")
    private List<Course> searchedCourses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getSearchedCourses() {
        return searchedCourses;
    }

    public void setSearchedCourses(List<Course> searchedCourses) {
        this.searchedCourses = searchedCourses;
    }
}

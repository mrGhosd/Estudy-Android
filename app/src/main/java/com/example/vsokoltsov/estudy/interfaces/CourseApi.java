package com.example.vsokoltsov.estudy.interfaces;

import com.example.vsokoltsov.estudy.models.CoursesList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vsokoltsov on 15.07.16.
 */
public interface CourseApi {
    @GET("courses")
    Observable<CoursesList> loadCourses(@Query("page") int pageNumber);

    @GET("search")
    Observable<CoursesList> searchCourses(
            @Query("object") String object,
            @Query("query") String query);
}

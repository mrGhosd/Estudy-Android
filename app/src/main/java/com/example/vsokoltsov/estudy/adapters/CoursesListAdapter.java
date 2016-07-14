package com.example.vsokoltsov.estudy.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.models.Course;
import com.example.vsokoltsov.estudy.view_holders.CourseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsokoltsov on 15.07.16.
 */
public class CoursesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Course> courses = new ArrayList<Course>();
    private Activity activity;

    public CoursesListAdapter(List<Course> courses, Activity activity) {
        this.courses = courses;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_row, parent, false);
        CourseViewHolder cvh = new CourseViewHolder(v, this);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
        Course course = courses.get(position);
        Drawable emptyUser = activity.getResources().getDrawable(R.drawable.empty_course);

        courseViewHolder.setCourse(course);
        courseViewHolder.courseTitle.setText(course.getTitle());
        courseViewHolder.courseShortDescription.setText(course.getShortDescription());

        if (course.getImage() != null) {
            String fullURL = course.getImage().getUrl();
            Picasso.with(this.activity.getApplicationContext())
                    .load(fullURL)
                    .placeholder(emptyUser)
                    .into(courseViewHolder.courseImage);
        }
        else {
            courseViewHolder.courseImage.setImageDrawable(emptyUser);
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}

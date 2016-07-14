package com.example.vsokoltsov.estudy.view_holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.CoursesListAdapter;
import com.example.vsokoltsov.estudy.models.Course;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vsokoltsov on 15.07.16.
 */
public class CourseViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    CoursesListAdapter adapter;
    private Course course;
    public TextView courseTitle;
    public TextView courseShortDescription;
    public CircleImageView courseImage;

    public CourseViewHolder(View itemView) {
        super(itemView);
    }

    public CourseViewHolder(View itemView, CoursesListAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        courseTitle = (TextView) itemView.findViewById(R.id.courseTitle);
        courseShortDescription = (TextView) itemView.findViewById(R.id.courseShortDescription);
        courseImage = (CircleImageView) itemView.findViewById(R.id.courseImage);

    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

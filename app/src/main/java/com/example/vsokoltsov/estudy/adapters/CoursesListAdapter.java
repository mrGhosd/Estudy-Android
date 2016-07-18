package com.example.vsokoltsov.estudy.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.OnLoadMoreListener;
import com.example.vsokoltsov.estudy.models.Course;
import com.example.vsokoltsov.estudy.view_holders.CourseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsokoltsov on 15.07.16.
 */
public class CoursesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    public List<Course> courses = new ArrayList<Course>();
    private List<Course> orig;
    private Activity activity;

    private final int VISIBLE_THRESHOLD = 5;

    private final int ITEM_VIEW_TYPE_BASIC = 0;
    private final int ITEM_VIEW_TYPE_FOOTER = 1;

    private int firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    private boolean loading = true;

    public CoursesListAdapter(List<Course> courses, Activity activity) {
        this.courses = courses;
        this.activity = activity;
    }

    public CoursesListAdapter(List<Course> courses, Activity activity, RecyclerView recyclerView,
                              final OnLoadMoreListener onLoadMoreListener) {
        this.courses = courses;
        this.activity = activity;

        onListenerHandler(recyclerView, onLoadMoreListener);
    }

    private void onListenerHandler(RecyclerView recyclerView,  final OnLoadMoreListener onLoadMoreListener) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    totalItemCount = linearLayoutManager.getItemCount();
                    visibleItemCount = linearLayoutManager.getChildCount();
                    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem + VISIBLE_THRESHOLD) && (totalItemCount != visibleItemCount)) {
                        // End has been reached

                        addCourse(null);
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            notifyItemInserted(courses.size() - 1);
        }
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Course> results = new ArrayList<Course>();
                if (orig == null)
                    orig  = courses;
                if (constraint != null){
                    if(orig !=null & orig.size()>0 ){
                        for ( final Course g :orig) {
                            if (g.getTitle().toLowerCase().contains(constraint.toString()))results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                courses = (ArrayList<Course>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

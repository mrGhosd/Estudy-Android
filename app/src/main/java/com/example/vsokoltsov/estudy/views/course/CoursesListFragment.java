package com.example.vsokoltsov.estudy.views.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.CoursesListAdapter;
import com.example.vsokoltsov.estudy.interfaces.CourseApi;
import com.example.vsokoltsov.estudy.models.Course;
import com.example.vsokoltsov.estudy.models.CoursesList;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;

import org.solovyev.android.views.llm.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 10.07.16.
 */
public class CoursesListFragment extends Fragment {
    private ApplicationBaseActivity activity;
    private View fragmentView;
    private List<Course> courses = new ArrayList<Course>();
    private RecyclerView rv;
    private CoursesListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (ApplicationBaseActivity) getActivity();

        fragmentView = inflater.inflate(R.layout.course_list_fragment, container, false);
        adapter = new CoursesListAdapter(courses, getActivity());
        rv = (RecyclerView) fragmentView.findViewById(R.id.coursesList);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        loadCoursesList();
        return fragmentView;
    }

    private void loadCoursesList() {
        Retrofit retrofit = api.getRestAdapter();
        CourseApi service = retrofit.create(CourseApi.class);
        service.loadCourses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CoursesList>() {
                    @Override
                    public void onCompleted() {
                        activity.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onNext(CoursesList coursesList) {
                        setCoursesList(coursesList);
                    }
                });
    }

    private void setCoursesList(CoursesList courses) {
        adapter.courses = courses.getCourses();
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable error) {
        activity.dismissProgress();
    }
}

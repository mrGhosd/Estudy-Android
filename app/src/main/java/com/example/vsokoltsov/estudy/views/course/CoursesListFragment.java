package com.example.vsokoltsov.estudy.views.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class CoursesListFragment extends Fragment implements SearchView.OnQueryTextListener {
    private ApplicationBaseActivity activity;
    private View fragmentView;
    private List<Course> courses = new ArrayList<Course>();
    private RecyclerView rv;
    private CoursesListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();
    private Menu mainMenu;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (ApplicationBaseActivity) getActivity();
        fragmentView = inflater.inflate(R.layout.course_list_fragment, container, false);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mainMenu = menu;
        inflater.inflate(R.menu.courses_list_menu, mainMenu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = (MenuItem) mainMenu.findItem(R.id.action_search);
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    private void setCoursesList(CoursesList courses) {
        adapter.courses = courses.getCourses();
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable error) {
        activity.dismissProgress();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}

package com.example.vsokoltsov.estudy.views.authorization;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.ViewPagerAdapter;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.messages.UserMessage;
import com.example.vsokoltsov.estudy.models.authorization.AuthorizationService;
import com.example.vsokoltsov.estudy.models.authorization.CurrentUser;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.util.SlidingTabLayout;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.vsokoltsov.estudy.R.color.highlighted_text_material_light;

/**
 * Created by vsokoltsov on 03.07.16.
 */
public class AuthorizationBaseFragment extends Fragment {
    private ApplicationBaseActivity activity;
    private View fragmentView;


    private android.support.v4.app.FragmentManager fragmentManager;
    private String action;

    private FragmentTabHost mTabHost;

    //Sliding tab elements
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[];
    private List<String> titles = new ArrayList<String>();
    private int Numboftabs =2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (ApplicationBaseActivity) getActivity();

        fragmentView = inflater.inflate(R.layout.authorization_base_fragment, container, false);
        fragmentManager = getFragmentManager();
        setTitles();
        defineCurrentTab();
        setSlidingTabs();
        return fragmentView;
    }

    private void setTitles() {
        Resources resources = getResources();
        String signIn = resources.getString(R.string.nav_sign_in);
        String signUp = resources.getString(R.string.nav_sign_up);
        titles.add(signIn);
        titles.add(signUp);
    }

    private void defineCurrentTab() {
        Bundle extras = getArguments();
        action = extras.getString("action");
    }

    private void setSlidingTabs() {
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getFragmentManager(), titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) fragmentView.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        Resources res = getResources();

        if (action.equals("sign_in")) {
            pager.setCurrentItem(0);
            activity.setTitle(titles.get(0));
        }
        else if (action.equals("sign_up")) {
            pager.setCurrentItem(1);
            activity.setTitle(titles.get(1));
        }

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) fragmentView.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabs.setBackground(new ColorDrawable(highlighted_text_material_light));
        tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                String title = titles.get(position);
                activity.getSupportActionBar().setTitle(title);
            }
        });

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    public void currentUserRequest() {
        Retrofit retrofit = ApiRequester.getInstance().getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.getCurrentUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentUser>() {
                    @Override
                    public void onCompleted() {
                        activity.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CurrentUser user) {
                        currentUserReceived(user);
                    }
                });
    }

    public void currentUserReceived(CurrentUser user) {
        AuthorizationService auth = AuthorizationService.getInstance();
        auth.setCurrentUser(user.getUser());
        EventBus.getDefault().post(new UserMessage("currentUser", user.getUser()));
    }
}

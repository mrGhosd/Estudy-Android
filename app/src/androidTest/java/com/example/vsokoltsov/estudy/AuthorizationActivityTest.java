package com.example.vsokoltsov.estudy;

import android.test.ActivityInstrumentationTestCase2;

import com.example.vsokoltsov.estudy.views.authorization.AuthorizationActivity;
import com.example.vsokoltsov.estudy.views.authorization.AuthorizationBaseFragment;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;

/**
 * Created by vsokoltsov on 03.07.16.
 */
public class AuthorizationActivityTest extends ActivityInstrumentationTestCase2<AuthorizationActivity> {
    ApplicationBaseActivity applicationBaseActivity;
    AuthorizationActivity authorizationActivity;
    AuthorizationBaseFragment authorizationBaseFragment;

    public AuthorizationActivityTest() {
        super(AuthorizationActivity.class);
    }

    public AuthorizationActivityTest(String pkg, Class<AuthorizationActivity> activityClass) {
        super(pkg, activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testPreConditions() {
        assertTrue(true);
//        assertNotNull(authorizationActivity);
//        assertNotNull(authorizationBaseFragment);
    }
}

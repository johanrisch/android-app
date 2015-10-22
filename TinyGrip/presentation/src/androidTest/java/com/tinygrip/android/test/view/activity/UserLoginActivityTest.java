package com.tinygrip.android.test.view.activity;

import android.content.Intent;
import android.support.test.espresso.action.TypeTextAction;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.user.activity.UserLoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class UserLoginActivityTest extends ActivityInstrumentationTestCase2<UserLoginActivity> {

    private static final String FAKE_USER_NAME = "fake";
    private static final String FAKE_PASSWORD = "fake";

    private UserLoginActivity userLoginActivity;

    public UserLoginActivityTest() {
        super(UserLoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(createTargetIntent());
        this.userLoginActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsUserLoginFragment() {
        Fragment userLoginFragment = userLoginActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_user_login);
        assertThat(userLoginFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = this.userLoginActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("User Login"));
    }

    public void testLoadUserHappyCaseViews() {
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.et_username)).check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_register_link)).check(matches(isDisplayed()));
    }

    public void testLoadUserHappyCaseData() {
        onView(withId(R.id.et_username)).perform(clearText(), typeText(FAKE_USER_NAME));
        onView(withId(R.id.et_password)).perform(clearText(), typeText(FAKE_PASSWORD));

        onView(withId(R.id.et_username)).check(matches(withText(FAKE_PASSWORD)));
        onView(withId(R.id.et_password)).check(matches(withText(FAKE_PASSWORD)));
    }

    private Intent createTargetIntent() {
        return UserLoginActivity.getCallingIntent(getInstrumentation().getTargetContext());
    }
}

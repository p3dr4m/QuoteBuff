package com.project.quotebuff.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.project.quotebuff.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
            ViewInteraction materialButton = onView(
                    allOf(withId(R.id.save_quote_btn), withText("Save"), withTagValue(is((Object) "btn0")),
                            childAtPosition(
                                    withParent(withId(R.id.quotes_list)),
                                    1),
                            isDisplayed()));
            materialButton.perform(click());
            materialButton = onView(
                    allOf(withId(R.id.save_quote_btn), withText("Save"), withTagValue(is((Object) "btn1")),
                            childAtPosition(
                                    withParent(withId(R.id.quotes_list)),
                                    1),
                            isDisplayed()));
            materialButton.perform(click());
            materialButton = onView(
                    allOf(withId(R.id.save_quote_btn), withText("Save"), withTagValue(is((Object) "btn2")),
                            childAtPosition(
                                    withParent(withId(R.id.quotes_list)),
                                    1),
                            isDisplayed()));
            materialButton.perform(click());
            materialButton = onView(
                    allOf(withId(R.id.save_quote_btn), withText("Save"), withTagValue(is((Object) "btn3")),
                            childAtPosition(
                                    withParent(withId(R.id.quotes_list)),
                                    1),
                            isDisplayed()));
            materialButton.perform(click());
            materialButton = onView(
                    allOf(withId(R.id.save_quote_btn), withText("Save"), withTagValue(is((Object) "btn4")),
                            childAtPosition(
                                    withParent(withId(R.id.quotes_list)),
                                    1),
                            isDisplayed()));
            materialButton.perform(click());
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_saved), withContentDescription("Saved Quotes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        materialButton = onView(
                allOf(withId(R.id.delete_quote_btn), withText("Delete"), withTagValue(is((Object) "btn0")),
                        childAtPosition(
                                withParent(withId(R.id.quotes_list)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());
        materialButton = onView(
                allOf(withId(R.id.delete_quote_btn), withText("Delete"), withTagValue(is((Object) "btn1")),
                        childAtPosition(
                                withParent(withId(R.id.quotes_list)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());
        materialButton = onView(
                allOf(withId(R.id.delete_quote_btn), withText("Delete"), withTagValue(is((Object) "btn2")),
                        childAtPosition(
                                withParent(withId(R.id.quotes_list)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

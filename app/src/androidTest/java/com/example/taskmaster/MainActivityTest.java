package com.example.taskmaster;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MainActivity(){
        onView(withId(R.id.button2)).check(matches(withText("ALL TASKS")));
    }
    @Test
    public void settingsActivety(){
        onView(withId(R.id.settings)).perform(click());
        onView((withId(R.id.textView6))).check(matches(withText("Settings")));
    }

    @Test
    public void addTask(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button4)).check(matches(withText("ADD TASK")));
    }
    @Test
    public void allTasks(){
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.textView4)).check(matches(withText("All Tasks")));
    }
    @Test
    public void addNewTask(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.taskTitleInput)).perform(typeText("Amazon"),closeSoftKeyboard());
        onView(withId(R.id.taskDescriptionInput)).perform(typeText("Learn Amplify and DynamicDB"),closeSoftKeyboard());
        onView(withId(R.id.taskStateInput)).perform(typeText("New"),closeSoftKeyboard());

        onView(withId(R.id.button4)).perform(click());
    }
    @Test
    public void openTaskDetail() throws InterruptedException {
        onView(ViewMatchers.withId(R.id.RecyclerView)).check(matches(isDisplayed()));
        Thread.sleep(5000);

        onView(withId(R.id.RecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.textView5)).check(matches(withText("Learn Amplify and DynamicDB")));
    }

    @Test
    public void settingsTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.settings),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Abdelqader"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.submit), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.user), withText("Abdelqader's Tasks"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Abdelqader's Tasks")));
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

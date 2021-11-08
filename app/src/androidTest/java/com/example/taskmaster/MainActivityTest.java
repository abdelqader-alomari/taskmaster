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
        onView(withId(R.id.button2)).check(matches(withText("ALL TASKS"))); // check if "All TASKS" appear on main screen
        onView(withId(R.id.button3)).check(matches(withText("ADD TASK")));  // check if "ADD TASK" appear on main screen
        onView(withId(R.id.settings)).perform(click()); // Click on settings button
        onView(withText("Settings")).check(matches(isDisplayed())); // Check if "Settings" appear in settings activity
        onView(withId(R.id.username)).perform(typeText("Abdelqader")); // Type "Abdelqader" as username input in settings activity
        onView(withId(R.id.submit)).perform(click()); // click on save button to save username
        onView(withText("Abdelqader's Tasks")).check(matches(isDisplayed())); // check if username + "'s" + "Tasks" Appear on main activity after save username
        onView(withId(R.id.button3)).perform(click()); // click on add Task button from main activity
        onView(withId(R.id.taskTitleInput)).perform(typeText("Amazon"),closeSoftKeyboard()); // type title in add task activity and close keyboard
        onView(withId(R.id.taskDescriptionInput)).perform(typeText("Learn Amplify and DynamoDB"),closeSoftKeyboard()); // type description in add task activity and close keyboard
        onView(withId(R.id.taskStateInput)).perform(typeText("New"),closeSoftKeyboard()); // // type state in add task activity and close keyboard

        onView(withId(R.id.button4)).perform(click()); // click add Task button in add task activity to save typed information
        onView(withText("Amazon")).check(matches(isDisplayed())); // check of the title typed in add Task matches appeared title in main activity
        onView(withText("Learn Amplify and DynamoDB")).check(matches(isDisplayed())); // check of the description typed in add Task matches appeared description in main activity
        onView(withText("New")).check(matches(isDisplayed())); // // check of the status typed in add Task matches appeared status in main activity

        onView(ViewMatchers.withId(R.id.RecyclerView)).check(matches(isDisplayed())); // check the matchers of recycle view on main activity and delay an action for 2 seconds.
        try {
            Thread.sleep(2000); // delay for 2 sec
        } catch (InterruptedException e) {
            e.printStackTrace(); // handle exception
        }
        onView(withId(R.id.RecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click())); // click and show recycle view item (display at TaskDetail activity)
        onView(withId(R.id.textView5)).check(matches(withText("Learn Amplify and DynamoDB"))); // check if the description of task display on TaskDetail activity from recycle view.
    }
}

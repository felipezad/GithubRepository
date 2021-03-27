package com.example.lookatxing.ui.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.lookatxing.R
import io.mockk.mockk
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityWithRecyclerView() {
        Thread.sleep(3000)
        val viewGroup = onView(
            allOf(
                withId(R.id.homeGithubRecyclerView),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
        viewGroup.perform(swipeUp())
    }

    @Test
    fun mainActivityWithRecyclerViewPerformClickOnFirstItem() {
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.homeGithubRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GithubItemAdapter.ViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getDescription(): String {
                            return "Click on button in the first element of recycler view"
                        }

                        override fun getConstraints(): Matcher<View> {
                            return mockk()
                        }

                        override fun perform(uiController: UiController?, view: View?) {
                            view?.findViewById<View>(R.id.itemContainer)?.performClick()
                        }
                    }
                )
            )
    }

}

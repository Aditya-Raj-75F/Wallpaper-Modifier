package com.example.wallpaperchanger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import de.mannodermaus.junit5.ActivityScenarioExtension
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

class MainActivityTest {
    @JvmField
    @RegisterExtension
    var activityScenario= ActivityScenarioExtension.launch<MainActivity>()

//    @Nested
//    inner class TestVisibility {
        @Test
        fun test_isTextViewVisible() {
            onView(withId(R.id.textView)).check(matches(isDisplayed()))
        }

        @Test
        fun test_isButtonVisible() {
            onView(withId(R.id.changeWallpaperButton)).check((matches(isDisplayed())))
        }

        @Test
        fun test_isScrollLayoutVisible() {
            onView(withId(R.id.scroller)).check((matches(isDisplayed())))
        }

        @Test
        fun test_isLinearLayoutVisible() {
            onView(withId(R.id.imageContainer)).check(matches(isDisplayed()))
        }

        @Test
        fun test_isImageViewsVisible() {
            onView(withTagValue(`is`("img1"))).check(matches(isDisplayed()))
        }

//        @Test
//        fun test_isVariableUpdatedOnImageViewClick() {
//            onView(withTagValue(`is`("img1"))).perform(click())
//            activityScenario.scenario.onActivity { assertEquals(it.selectedImgId, R.drawable.img1) }
//        }
//    }
}
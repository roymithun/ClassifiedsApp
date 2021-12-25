package com.inhouse.classifiedsapp.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.inhouse.classifiedsapp.R
import com.inhouse.classifiedsapp.ui.adapter.ClassifiedsAdListAdapter
import com.inhouse.classifiedsapp.ui.fragment.HomeFragment
import com.inhouse.classifiedsapp.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Test
    fun launchHome_checkIfRecyclerView_isDisplayed() {
        launchHomeFragment()
        onView(withId(R.id.rv_classifieds_ad_list)).check(matches(isDisplayed()))
    }

    @Test
    fun launchHome_scrollToPosition_isDisplayed() {
        launchHomeFragment()
        runBlocking {
            delay(5000)
        }
        onView(withId(R.id.rv_classifieds_ad_list)).perform(
            scrollToPosition<ClassifiedsAdListAdapter.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun launchHome_clickOnItem() {
        launchHomeFragment()
        runBlocking {
            delay(10000)
        }
        onView(withId(R.id.rv_classifieds_ad_list)).perform(
            actionOnItemAtPosition<ClassifiedsAdListAdapter.ViewHolder>(
                1,
                click()
            )
        )
    }

    private fun launchHomeFragment() {
        launchFragmentInHiltContainer<HomeFragment> {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.homeFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}
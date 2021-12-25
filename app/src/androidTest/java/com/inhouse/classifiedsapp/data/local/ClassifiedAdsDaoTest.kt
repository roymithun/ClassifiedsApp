package com.inhouse.classifiedsapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.inhouse.classifiedsapp.core.data.local.ClassifiedAdDao
import com.inhouse.classifiedsapp.utils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ClassifiedAdsDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dao: ClassifiedAdDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun insert_and_fetch_all_classified_ads() = runBlocking {
        val classifiedAdList = FakeClassifiedsAdsList.classifiedAdList
        dao.insertAllClassifiedAds(classifiedAdList)

        dao.getAllClassifiedAds().test {
            assertThat(awaitItem()).isEqualTo(classifiedAdList)
        }
    }

    @Test
    fun insert_and_fetch_classified_ad_by_uid() = runBlocking {
        val classifiedAdList = FakeClassifiedsAdsList.classifiedAdList
        dao.insertAllClassifiedAds(classifiedAdList)

        val secondClassifiedAd = dao.getClassifiedAdById("bdf455e89f3b49f484d2a181b0268eab").getOrAwaitValue()
        assertThat(secondClassifiedAd).isEqualTo(FakeClassifiedsAdsList.classifiedAdList[1])
    }
}
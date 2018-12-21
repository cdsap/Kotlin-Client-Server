package com.kotlin.client

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.kotlin.client.view.pairscreen.PairScreenActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PairScreenActivityTest {

    @Test
    fun listOfPairsWithElements() {
        val scenario = ActivityScenario.launch(PairScreenActivity::class.java)
        // WHEN
      //  onView(withId(R.id.recycler)).perform(typeText(“ test_user ”))
      //  onView(withId(R.id.password))
      //          .perform(typeText(“ correct_password ”))
      //  onView(withId(R.id.button)).perform(click())
        // THEN
       // assertThat(getIntents().first())
       //         .hasComponentClass(HomeActivity::class.java)
    }

}
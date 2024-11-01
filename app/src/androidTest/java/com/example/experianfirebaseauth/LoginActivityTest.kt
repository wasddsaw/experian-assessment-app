package com.example.experianfirebaseauth

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val mockAuth = mock(FirebaseAuth::class.java)
    private val mockAuthResult = mock(Task::class.java) as Task<AuthResult>

    @Test
    fun login_withValidCredentials_navigatesToMainActivity() {
        // Setup successful login
        `when`(mockAuth.signInWithEmailAndPassword(any(), any())).thenReturn(mockAuthResult)
        `when`(mockAuthResult.isSuccessful).thenReturn(true)

        // Start LoginActivity with mocked FirebaseAuth
        val scenario = ActivityScenario.launch(LoginActivity::class.java)
        scenario.onActivity { activity ->
            activity.auth = mockAuth
        }

        // Perform login action
        onView(withId(R.id.editTextEmail)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        Thread.sleep(1000)

        // Verify that the login status is displayed
        onView(withId(R.id.textViewStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewStatus)).check(matches(withText("Login Successful")))
    }

    @Test
    fun login_withInvalidCredentials_showsErrorMessage() {
        // Setup failed login
        `when`(mockAuth.signInWithEmailAndPassword(any(), any())).thenReturn(mockAuthResult)
        `when`(mockAuthResult.isSuccessful).thenReturn(false)

        // Start LoginActivity with mocked FirebaseAuth
        val scenario = ActivityScenario.launch(LoginActivity::class.java)
        scenario.onActivity { activity ->
            activity.auth = mockAuth
        }

        // Perform login action with incorrect credentials
        onView(withId(R.id.editTextEmail)).perform(typeText("wronguser@example.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText("wrongpassword"), closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        Thread.sleep(1000)

        // Verify error message is displayed
        onView(withId(R.id.textViewStatus)).check(matches(isDisplayed()))
    }
}
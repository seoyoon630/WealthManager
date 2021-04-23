package com.bri.wealthmanager

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.bri.wealthmanager.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class DataBaseTest {

    @Test
    fun start_mainActivity() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity { activity ->
            val toasts: List<Toast> = Shadows.shadowOf(application).shownToasts
            toasts.forEach { println(it) }
        }
    }
}
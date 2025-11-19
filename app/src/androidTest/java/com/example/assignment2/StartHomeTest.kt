package com.example.assignment2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartHomeTest {

    private lateinit var device: UiDevice
    private val PACKAGE = "com.example.assignment2"

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)

        // Go to home screen
        device.pressHome()

        device.wait(Until.hasObject(By.pkg(device.launcherPackageName)), 3000)
    }

    @Test
    fun testStartFromHomeScreen() {
        // Makes sure the icon is on the home screen
        val appIcon = device.findObject(By.desc("Assignment2"))
            ?: device.findObject(By.text("Assignment2"))
        requireNotNull(appIcon) { "App icon not on home screen" }

        // Opens the app
        appIcon.click()

        // Wait for app to load up
        device.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), 3000)

        // Click on start activity explicitly
        val startButton = device.findObject(By.text("Start Activity Explicitly"))
        requireNotNull(startButton) { "Start Activity Explicitly not found" }
        startButton.click()

        // Searching for challenges with contains so its not the whole string.
        val challengeText =
            device.findObject(By.textContains("battery")) ?:
            device.findObject(By.textContains("Fragmentation")) ?:
            device.findObject(By.textContains("Security")) ?:
            device.findObject(By.textContains("Oversaturation")) ?:
            device.findObject(By.textContains("Ensuring"))

        requireNotNull(challengeText) {
            "Expected challenge text not found"
        }
    }
}

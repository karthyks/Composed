package com.karthyks.composed.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karthyks.composed.samples.test.TestTag
import io.github.karthyks.composed.OVERLAY_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun shouldShowOverlayAndDismissOverlay() {
        composeTestRule.onNodeWithTag(OVERLAY_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTag.showDialog).performClick()
        composeTestRule.onNodeWithTag(TestTag.fullScreenDialog).assertExists()
        composeTestRule.onNodeWithTag(TestTag.toastMessage).assertExists()
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(TestTag.toastMessage).fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onNodeWithTag(TestTag.toastMessage).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTag.closeDialog).performClick()
        composeTestRule.onNodeWithTag(OVERLAY_TEST_TAG).assertDoesNotExist()
    }
}

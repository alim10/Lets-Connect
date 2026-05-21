<<<<<<<< HEAD:features/profile/src/androidTest/java/org/alimapps/letsconnect/profile/ExampleInstrumentedTest.kt
package org.alimapps.letsconnect.profile
========
package org.alimapps.letsconnect.core.network
>>>>>>>> origin/development:Core/Network/src/androidTest/java/org/alimapps/letsconnect/core/network/ExampleInstrumentedTest.kt

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
<<<<<<<< HEAD:features/profile/src/androidTest/java/org/alimapps/letsconnect/profile/ExampleInstrumentedTest.kt
        assertEquals("org.alimapps.letsconnect.Core.test", appContext.packageName)
========
        assertEquals("org.alimapps.letsconnect.core.network.test", appContext.packageName)
>>>>>>>> origin/development:Core/Network/src/androidTest/java/org/alimapps/letsconnect/core/network/ExampleInstrumentedTest.kt
    }
}
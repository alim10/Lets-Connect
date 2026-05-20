package org.alimapps.letsconnect.core.common.navigation
import android.util.Log
import androidx.annotation.IdRes
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import org.alimapps.letsconnect.core.common.R

/**
 * Use this extension function to navigate between multi-modules using deep-links
 */
fun NavController.navigateToDeepLink(
    deepLinkDestination: DeepLinkDestination
) = runCatching {
    val deeplink = deepLinkDestination.address.toUri()
    val hasDeepLink = graph.hasDeepLink(deeplink)
    Log.d("Navigation", "isDeepLinkExistedOnGraph? -> $hasDeepLink")
    navigate(
        deepLink = deeplink,
        navOptions = NavOptions.Builder()
            .setExitAnim(R.anim.exit_to_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .setEnterAnim(R.anim.enter_from_right)
            .setPopEnterAnim(R.anim.enter_from_left)
            .build()
    )
}.getOrElse { Log.e("Navigation: ", "navigateToDeepLink: ${it.localizedMessage}" ) }

/**
 * Pop nav graph from back stack until a specific [startDestinationId] and if it can not find the startDestination it will navigateUp
 */
fun NavController.popGraphBackStack(@IdRes startDestinationId: Int) = runCatching {
    if (!popBackStack(startDestinationId, true)) {
        navigateUp()
    }
}.getOrNull()
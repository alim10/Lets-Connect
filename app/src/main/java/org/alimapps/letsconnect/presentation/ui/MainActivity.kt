package org.alimapps.letsconnect.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.alimapps.letsconnect.R
import org.alimapps.letsconnect.apps.AppHeight
import org.alimapps.letsconnect.apps.AppPadding
import org.alimapps.letsconnect.core.navigations.Activities
import org.alimapps.letsconnect.core.navigations.Navigator
import org.alimapps.letsconnect.core.theme.ColorDefaultIcon
import org.alimapps.letsconnect.core.theme.ColorGrayLight
import org.alimapps.letsconnect.core.theme.ColorWhite
import org.alimapps.letsconnect.core.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var provider: Navigator.Provider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ContentUI(this, provider)
            }
//            }
        }
    }

    companion object {
        fun launchActivity(activity: Activity) {
            val landingIntent = Intent(activity, MainActivity::class.java)
            startActivity(landingIntent)
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ContentUI(context: Activity, provider: Navigator.Provider) {

    Column {
        Text(
            text = "Hello !",
        )

        Button(
            onClick = {
                moveToNextActivity(context, provider)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Submit",
                style = TextStyle(
                    color = ColorWhite, textAlign = TextAlign.Center
                )
            )
        }
//        LargeSpacer()
//        LargeSpacer()
//        LargeSpacer()
//        LargeSpacer()
//        LargeSpacer()
        Button(
            onClick = {
                moveToNextActivity(context, provider)
            },
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                text = "Submit",
                style = TextStyle(
                    color = ColorWhite, textAlign = TextAlign.Center
                )
            )
        }

//        LargeSpacer()
//        LargeSpacer()
//        LargeSpacer()

        Button(
            onClick = {
                moveToNextActivity(context, provider)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Submit",
                style = TextStyle(
                    color = ColorWhite, textAlign = TextAlign.Center
                )
            )

        }

        Box(
            Modifier
                .align(Alignment.CenterHorizontally)) {
            Canvas(
                modifier = Modifier.size(AppHeight.h_150, AppHeight.h_150))
             {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawCircle(
                    color = ColorDefaultIcon,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = size.minDimension / 2,
                    style = Stroke(
                        width = 1f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    )
                )
            }

            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(AppHeight.h_120, AppHeight.h_120)
                    .padding(all = AppPadding.pLarge)
                    .border(AppPadding.pRegular, ColorGrayLight, CircleShape)
                    .clip(CircleShape),

                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.mipmap.ic_placeholder),
                contentDescription = null
            )
        }
//
//        LargeSpacer()
//        LargeSpacer()
//        LargeSpacer()

    }
}

fun startActivity(intent: Intent) {

}

fun moveToNextActivity(context: Activity, provider: Navigator.Provider) {
//    provider.getActivities(Activities.AuthActivity).navigate(context)
    provider.getActivities(Activities.AuthActivity1).navigate(context)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}


object GoToMainActivity : Navigator {

    override fun navigate(activity: Activity) {
        MainActivity.launchActivity(activity)
    }
}



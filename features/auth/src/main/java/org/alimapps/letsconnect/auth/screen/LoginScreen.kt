package org.alimapps.letsconnect.auth.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.alimapps.letsconnect.auth.components.CustomButton
import org.alimapps.letsconnect.auth.components.CustomOutlinedTextField
import org.alimapps.letsconnect.auth.components.CustomTextTitle
import org.alimapps.letsconnect.auth.components.CustomTextTitleMedium

import org.alimapps.letsconnect.core.R
import org.alimapps.letsconnect.core.theme.AppHeight
import org.alimapps.letsconnect.core.theme.AppPadding
import org.alimapps.letsconnect.core.theme.ColorDefaultIcon
import org.alimapps.letsconnect.core.theme.ColorGrayLight
import org.alimapps.letsconnect.core.theme.ColorWhite


@Composable
fun LoginScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite)
            .padding(AppPadding.large)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


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
                        .padding(all = AppPadding.large)
                        .border(AppPadding.regular, ColorGrayLight, CircleShape)
                        .clip(CircleShape),

                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null
                )
            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )


            CustomTextTitle(value = stringResource(id = R.string.txt_login))
            CustomTextTitleMedium(value = stringResource(id = R.string.txt_login))

            CustomOutlinedTextField(labelValue = stringResource(id = R.string.txt_user_name),
                painterResource(id = R.drawable.ic_profile))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            )

            CustomOutlinedTextField(labelValue = stringResource(id = R.string.txt_password),
                painterResource(id = R.drawable.ic_profile))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            )

            CustomButton(textButton = stringResource(id = R.string.txt_login),
                onClick = {

                })

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            )
//            CustomOutlinedTextField(labelValue = stringResource(id = R.string.txt_password))
        }
    }
}

@Preview
@Composable
fun DefaultPreviewLogin() {
    LoginScreen()
}


package org.alimapps.letsconnect.presentation.ui


import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.Card
//import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.alimapps.letsconnect.R
import org.alimapps.letsconnect.app_reviews.domain.entities.ReviewEntity
import org.alimapps.letsconnect.apps.AppBtnPadding
import org.alimapps.letsconnect.apps.AppBtnWidth
import org.alimapps.letsconnect.apps.AppButtonSize
import org.alimapps.letsconnect.apps.AppCornerPercentage
import org.alimapps.letsconnect.apps.AppCornerRadius
import org.alimapps.letsconnect.apps.AppIconSize
import org.alimapps.letsconnect.apps.AppImageSize
import org.alimapps.letsconnect.apps.AppPadding
import org.alimapps.letsconnect.common.component.TwoLineTextField
import org.alimapps.letsconnect.core.theme.ColorPrimary
import org.alimapps.letsconnect.core.theme.ColorWhite
import org.alimapps.letsconnect.core.theme.MyAppTheme
import org.alimapps.letsconnect.core.theme.Typography
import org.alimapps.letsconnect.presentation.widgets.AppRatingBar
import org.alimapps.letsconnect.presentation.widgets.AppRatingBarClickable
import org.alimapps.letsconnect.presentation.widgets.AppSpacer


@Composable
fun ContentReviewScreen() {
    MyAppTheme {

        var rating by remember { mutableStateOf(0f) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppPadding.pLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorPrimary)
                    .clip(RoundedCornerShape(AppCornerPercentage.percentage_50))
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.mipmap.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(AppImageSize.sizeImage3)
                        .clip(RoundedCornerShape(AppCornerPercentage.percentage_50)),
                )
            }

            AppSpacer(
                modifier = Modifier,
                AppPadding.pLarge
            )

            AppRatingBarClickable(
                rating = rating,
                maxStar = 5,
                AppIconSize.largest,
                onRatingChanged = { newRating ->
                    rating = newRating
                }
            )
            AppSpacer(
                modifier = Modifier,
                AppPadding.pLarge
            )

            var text by remember {
                mutableStateOf("")
            }
//            CustomMultiLineTextField(
//                text = text,
//                onTextChanged = { text = it },
//                hint = "Enter the message here!",
//                maxLines = 5,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(AppCornerRadius.medium))
//                    .padding(8.dp),
//            )

            TwoLineTextField(
                text = text,
                onTextChanged = { text = it },
                hint = "Enter the message here!",
                maxLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppCornerRadius.medium)),
            )

            AppSpacer(
                modifier = Modifier,
                AppPadding.pLarge
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .widthIn(AppBtnWidth.regular, AppBtnWidth.large)
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        horizontal = AppBtnPadding.horizontalRegular,
                        vertical = AppBtnPadding.verticalRegular
                    ),
                shape = RoundedCornerShape(
                    percent = AppCornerPercentage.percentage_10
                )
            ) {
                Text(
                    text = "Submit",
                    fontSize = AppButtonSize.appTextRegular,
                    color = ColorWhite.copy(0.8f),
                )
            }

            AppSpacer(
                modifier = Modifier,
                AppPadding.pLarge
            )

            AppRatingBar(
                rating = 3.8f, AppIconSize.regular
            )
            Text(
                text = "Total users review: 1258 ",
                modifier = Modifier
                    .padding(start = AppPadding.pLarge),
                style = Typography.labelSmall
            )

            AppSpacer(
                modifier = Modifier,
                AppPadding.pLarge
            )

            LazyColumn(
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
            ) {
                items(getFindReview().size) { index ->
                    ItemReview(
                        getFindReview()[index],
                    )
                }
            }
        }
    }
}

fun getFindReview(): List<ReviewEntity> {
    val list = mutableListOf<ReviewEntity>()
    for (index in 1..20) {
        list.add(
            ReviewEntity(
                id = "$index",
                image = "https://images.unsplash.com/photo-1613323593608-abc90fec84ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
                name = "user - $index",
                desc = "Description -$index:- In the ConstraintLayout example, constraints are specified inline, with a modifier in the composable they're applied to. However, there are situations when it's preferable to decouple the constraints from the layouts they apply to. For example, you might want to change the constraints based on the screen configuration, or animate between two constraint sets.",
                date = "$index Aug 2023",
                rating = (2.5f + (index * 0.1f))
            )
        )
    }
    return list
}


@Composable
fun ItemReview(
    item: ReviewEntity,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .height(AppHeight.h_72)
        .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(ColorWhite)
            ,
//        elevation = CardElevation(AppCard.)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_launcher_background),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .padding(AppPadding.pMedium)
                    .size(AppImageSize.sizeImage0)
                    .clip(CircleShape)
                    .fillMaxSize(), contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(AppPadding.pSmall),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = AppPadding.pMedium,
                        end = AppPadding.pMedium,
                        bottom = AppPadding.pMedium
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        style = Typography.labelSmall
                    )
                    Text(
                        text = item.date,
                        modifier = Modifier
                            .padding(start = AppPadding.pLarge),
                        style = Typography.labelSmall
                    )
                }

                AppRatingBar(
                    rating = item.rating, AppIconSize.small
                )

                Text(
                    text = item.desc,
                    maxLines = 3, // Set the maximum number of lines
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = Typography.labelSmall
                )
            }
        }
    }
}


@Composable
fun ScreenTotalRating(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = AppPadding.pMedium,
                bottom = AppPadding.pMedium
            ),
            style = Typography.headlineMedium
        )
        AppRatingBar(
            rating = 3.5f, AppIconSize.regular
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = AppPadding.pMedium,
                bottom = AppPadding.pMedium
            ),
            style = Typography.headlineSmall
        )

    }
}

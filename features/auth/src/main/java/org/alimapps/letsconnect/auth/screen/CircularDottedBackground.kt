//package org.alimapps.letsconnect.auth.screen
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.widget.ConstraintLayout
//import kotlin.math.cos
//import kotlin.math.sin
//
//@Composable
//fun CircularDottedBackground() {
//
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//
//        val (fLayLogo, ivLogo) = createGuidelineFromTop(0.3f)
//            .let { createGuidelineFromBottom(0.7f) }
//            .let { createGuidelineFromStart(0.0f) }
//            .let { createGuidelineFromEnd(1.0f) }
//            .createGuidelineFromStart(0.0f)
//            .let { createGuidelineFromEnd(1.0f) }
//            .let { createGuidelineFromTop(0.3f) }
//            .let { createGuidelineFromBottom(0.7f) }
//            .let { createGuidelineFromStart(0.0f) }
//            .let { createGuidelineFromEnd(1.0f) }
//
//    }
//
//
//    Frame() {
//
//
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your image resource
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(120.dp)
//                .align(Alignment.Center)
//                .border(
//                    shape = CircleShape,
//                    border = BorderStroke(
//                        width = 8.dp,
//                        color = Color.Gray
//                    )
//                ),
//        )
//    }
//
//}
//
////@Composable
////fun CircularDottedBackground() {
////    ConstraintLayout (
////        modifier = Modifier.fillMaxSize()
////    ) {
//////        val (fLayLogo, ivLogo) = createGuidelineFromTop(0.3f)
//////            .let { createGuidelineFromBottom(0.7f) }
//////            .let { createGuidelineFromStart(0.0f) }
//////            .let { createGuidelineFromEnd(1.0f) }
//////            .createGuidelineFromStart(0.0f)
//////            .let { createGuidelineFromEnd(1.0f) }
//////            .let { createGuidelineFromTop(0.3f) }
//////            .let { createGuidelineFromBottom(0.7f) }
//////            .let { createGuidelineFromStart(0.0f) }
//////            .let { createGuidelineFromEnd(1.0f) }
////
////        Frame(
////            modifier = Modifier.constrainAs(fLayLogo) {
////                top.linkTo(parent.top)
////                start.linkTo(parent.start)
////                end.linkTo(parent.end)
////            },
////        ) {
//////            Canvas(
//////                modifier = Modifier
//////                    .fillMaxSize()
//////                    .background(color = Color.Transparent),
//////            ) {
//////                // Drawing the circular dotted background
//////                val radius = size.minDimension / 2
//////                val dotRadius = 8f
//////                val dotSpacing = 8f
//////                val center = Offset(size.width / 2, size.height / 2)
//////
//////                for (angle in 0 until 360 step 12) {
//////                    val startX = center.x + radius * cos(Math.toRadians(angle.toDouble()))
//////                    val startY = center.y + radius * sin(Math.toRadians(angle.toDouble()))
//////
//////                    val endX = center.x + (radius - dotSpacing) * cos(Math.toRadians(angle.toDouble()))
//////                    val endY = center.y + (radius - dotSpacing) * sin(Math.toRadians(angle.toDouble()))
//////
//////                    drawLine(
//////                        color = Color.Gray,
//////                        start = Offset(startX.toFloat(), startY.toFloat()),
//////                        end = Offset(endX.toFloat(), endY.toFloat()),
//////                        strokeWidth = 2f
//////                    )
//////                }
//////            }
////
////            Image(
////                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your image resource
////                contentDescription = null,
////                contentScale = ContentScale.Crop,
////                modifier = Modifier
////                    .size(120.dp)
////                    .align(Alignment.Center)
////                    .border(
////                        shape = CircleShape,
////                        border = BorderStroke(
////                            width = 8.dp,
////                            color = Color.Gray
////                        )
////                    ),
////            )
////        }
////    }
////}

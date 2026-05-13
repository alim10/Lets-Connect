package org.alimapps.letsconnect.auth.presentation

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReviewScreen() {
    val customerReviews = listOf(
        "This is a great product. I love it!",
        "This product is not what I expected. I am disappointed.",
        "This product is amazing! I would definitely recommend it to others."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Review Screen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Product details
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile image"
                    )
                }

                Text(
                    text = "John Doe",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    RatingBar(
                        value = 3.5f,
                        numStars = 5,
                        onRatingChanged = {}
                    )
                }

                Text(
                    text = "Product ID: 123456",
                    fontSize = 14.sp,
                    color = Color.Red
                )

                // Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    RatingBar(
                        value = 3.5f,
                        numStars = 5,
                        onRatingChanged = {}
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Review") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "0/300 characters",
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                // Customer reviews
                Text(
                    text = "Customer Reviews",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                RatingBar(
                    value = 3.5f,
                    numStars = 5,
                    onRatingChanged = {}
                )

                Text(
                    text = "5 out of 5",
                    fontSize = 12.sp,
                    color = Color.LightGray
                )

                // List of reviews
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(customerReviews) { review ->
                        Text(
                            text = review,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    ReviewScreen()
}
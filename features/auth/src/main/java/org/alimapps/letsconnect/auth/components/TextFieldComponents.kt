package org.alimapps.letsconnect.auth.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.alimapps.letsconnect.core.common.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(labelValue: String, icon: Painter) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        value = textValue,
        shape = RoundedCornerShape(AppCornerRadius.large),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },

        leadingIcon = {
            Icon(painter = icon, contentDescription = "")
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField1(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        label = { Text(text = labelValue) },
        value = textValue,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
        modifier = Modifier.fillMaxWidth(),

        )
}
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        label = { Text(text = labelValue) },
        value = textValue,
        shape = RoundedCornerShape(AppCornerRadius.large),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordTextField2(labelValue: String, icon: Painter) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(AppPadding.regular, ColorGrayLight),
                shape = RoundedCornerShape(AppCornerRadius.large_I)
            ),
        placeholder = { Text(text = labelValue) },
        value = textValue,
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
//            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent

        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },

        trailingIcon = {
            Icon(painter = icon, contentDescription = "")
        }

    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordTextField1(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(AppPadding.regular, ColorGrayLight),
                shape = RoundedCornerShape(AppCornerRadius.large_I)
            ),
        label = { Text(text = labelValue) },
        value = textValue,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            focusedTextColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
//        trailingIcon = Icon(
//            painter = painterResource(id = R.drawable.ic_password_visible),
//            contentDescription = ""
//        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordTextField(labelValue: String, icon: Painter) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        value = textValue,
        shape = RoundedCornerShape(AppCornerRadius.large),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },

        trailingIcon = {
            Icon(painter = icon, contentDescription = "")
        }

    )
}

@Preview
@Composable
fun DefaultPreviewSignup() {
}


@Composable
fun CustomOutlinedTextField1() {
    var text by remember { mutableStateOf("Outlined Text") }

    BasicTextField(
        value = text,
        onValueChange = { str ->
            text = str
        },

        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background color of the text field
            .padding(16.dp), // Add padding for spacing
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(Color.Gray) // Outline color
                    .padding(1.dp) // Outline thickness
            ) {
                innerTextField()
            }
        }
    )
}

/*

@Composable
fun TwoLineTextField(
    text: String,
    onTextChanged: (String) -> Unit,


    modifier: Modifier = Modifier,
    hint: String = "",
    minLine: Int = 1,
    maxLines: Int = 1,
) {

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = Typography.bodyMedium,
//        singleLine = maxLines == minLine,
        singleLine = false,
        maxLines = maxLines,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ColorGrayExtraLight,
                shape = RoundedCornerShape(AppCornerRadius.large)
            )
            .heightIn(AppHeight.h_65)
            .padding(AppPadding.regular), // Adjust padding according to your layout
        decorationBox = { innerTextField ->
            Box(modifier = Modifier) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
//                        color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    )
                }
                innerTextField()
            }
        }
    )

}


package org.alimapps.letsconnect.auth.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.heightIn



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        label = { Text(text = labelValue) },
        value = textValue,
        shape = RoundedCornerShape(AppCornerRadius.large),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
        modifier = Modifier.fillMaxWidth()

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField1(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        label = { Text(text = labelValue) },
        value = textValue,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
        modifier = Modifier.fillMaxWidth(),

        )
}
//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        label = { Text(text = labelValue) },
        value = textValue,
        shape = RoundedCornerShape(AppCornerRadius.large),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,
        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
        modifier = Modifier.fillMaxWidth(),
//        trailingIcon = Icon(painter = painterResource(id = R.drawable.ic_password_visible), contentDescription = "")

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordTextField(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(AppPadding.pRegular, ColorGrayLight),
                shape = RoundedCornerShape(AppCornerRadius.large_I)
            ),
        placeholder = { Text(text = labelValue) },
        value = textValue,
        colors = TextFieldDefaults.textFieldColors(
//            focusedLabelColor = ColorPrimary,
//            unfocusedLabelColor = ColorPrimary,
//            cursorColor = ColorPrimary,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,


//            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
//            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent

        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
//        trailingIcon = Icon(
//            painter = painterResource(id = R.drawable.ic_password_visible),
//            contentDescription = ""
//        )
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordTextField1(labelValue: String) {
    var textValue by remember { mutableStateOf("") }
    var isPasswordShow by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(AppPadding.pRegular, ColorGrayLight),
                shape = RoundedCornerShape(AppCornerRadius.large_I)
            ),
        label = { Text(text = labelValue) },
        value = textValue,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ColorPrimary,
            focusedLabelColor = ColorPrimary,
            cursorColor = ColorPrimary,

        ),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions(
            onDone = {
                // Handle the "Done" action
            }
        ),
        onValueChange = {
            textValue = it
        },
//        trailingIcon = Icon(
//            painter = painterResource(id = R.drawable.ic_password_visible),
//            contentDescription = ""
//        )
    )

}

@Preview
@Composable
fun DefaultPreviewSignup() {
}


@Composable
fun CustomOutlinedTextField() {
    var text by remember { mutableStateOf("Outlined Text") }

    BasicTextField(
        value = text,
        onValueChange = { str ->
            text = str
        },

        textStyle = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background color of the text field
            .padding(16.dp), // Add padding for spacing
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(Color.Gray) // Outline color
                    .padding(1.dp) // Outline thickness
            ) {
                innerTextField()
            }
        }
    )
}



@Composable
fun TwoLineTextField(
    text: String,
    onTextChanged: (String) -> Unit,


    modifier: Modifier = Modifier,
    hint: String = "",
    minLine: Int = 1,
    maxLines: Int = 1,
) {

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = Typography.bodyMedium,
//        singleLine = maxLines == minLine,
        singleLine = false,
        maxLines = maxLines,
        modifier = modifier
            .fillMaxWidth()
            .background(color = ColorGrayExtraLight,
                shape = RoundedCornerShape(AppCornerRadius.large) )
            .heightIn(AppHeight.h_65)
            .padding(AppPadding.pRegular), // Adjust padding according to your layout
        decorationBox = { innerTextField ->
            Box(modifier = Modifier) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
//                        color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    )
                }
                innerTextField()
            }
        }
    )

}





*/

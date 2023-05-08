package com.example.wallpapercompose.screens.home.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.wallpapercompose.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBoxHome(navigator: DestinationsNavigator) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    Surface(
        shape = RoundedCornerShape(15.dp),
        contentColor=MaterialTheme.colorScheme.onSurface,
        shadowElevation = 0.dp,
        //tonalElevation = 5.dp,
        color=MaterialTheme.colorScheme.surface,

    ) {
        OutlinedTextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            leadingIcon = {Icon(imageVector = Icons.Rounded.Search, contentDescription = "")},
            trailingIcon =  { if(text.text.isEmpty())Unit else IconButton(onClick = {focusManager.clearFocus();text=TextFieldValue("") }) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
            }},
            onValueChange = { newText ->
                text = newText
            },
            placeholder = { Text(
                modifier=Modifier.wrapContentSize(),
                text = stringResource(id = R.string.search_wallpaper),
                style = MaterialTheme.typography.labelLarge
            )},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions {
                    focusManager.clearFocus();
                    navigator.navigate(
                    com.example.wallpapercompose.screens.destinations.CategoryScreenDestination(
                        text.text
                    )
                )
            },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent
           ),

        )
//        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
//
//
//        }
    }
}
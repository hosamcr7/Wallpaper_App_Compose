package com.example.wallpapercompose.screens.home.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileDescription(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {

        ProfileContent(
            displayName = "Hosam Abufasha",
            accountName = "@hosrm10",
            description = "3 years of coding experience!",
            url = "www.linkedin.com/in/hosam-al-deen-abufasha-9184631b4",
            followedBy = listOf("cristiano", "selena_gomez"),
            otherCount = 17
        )
        StatSection(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 10.dp)
        )

    }
}


@Composable
private  fun StatSection(modifier: Modifier = Modifier) {
    Surface(modifier, tonalElevation = 3.dp, shape = RoundedCornerShape(10.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            ProfileStat(numberText = "250", text = "Posts")
            ProfileStat(numberText = "10K", text = "Followers")
            ProfileStat(numberText = "7K", text = "Following")
        }
    }
}

@Composable
private  fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}


@Composable
private fun ProfileContent(
    displayName: String,
    accountName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = accountName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            style = TextStyle(color = MaterialTheme.colorScheme.secondary)
        )

        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            style = TextStyle(color = MaterialTheme.colorScheme.tertiary)

        )
        if(followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if(index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    if(otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}
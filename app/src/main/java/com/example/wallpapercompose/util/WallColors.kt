package com.example.wallpapercompose.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray

enum class WallColors {
    Blue,
    DarkGray,
    Yellow,
    Cyan,
    Orange,
    Magenta,
    Purple,
     Black ,
    Red,
    Green,
    Lime,
    Pink,
    Gray,
    ;


    fun color(): Color {
       return when(this){
            Black -> Color(0xFF000000)
            DarkGray -> Color(0xFF444444)
            Gray -> Color(0xFF888888)
            Red -> Color(0xFFF44336)
            Green -> Color(0xFF4CAF50)
            Lime -> Color(0xFFCDDC39)
            Pink -> Color(0xFFE91E63)
            Blue -> Color(0xFF2196F3)
            Yellow -> Color(0xFFFFEB3B)
            Cyan -> Color(0xFF00BCD4)
            Orange -> Color(0xFFFF9800)
            Purple -> Color(0xFF673AB7)
            Magenta -> Color(0xFFFF00FF)
        }
    }


    //   val Black = Color(0xFF000000)
//    val DarkGray = Color(0xFF444444)
//    val Gray = Color(0xFF888888)
//    val LightGray = Color(0xFFCCCCCC)
//    val White = Color(0xFFFFFFFF)
//    val Red = Color(0xFFFF0000)
//    val Green = Color(0xFF00FF00)
//    val Blue = Color(0xFF0000FF)
//    val Yellow = Color(0xFFFFFF00)
//    val Cyan = Color(0xFF00FFFF)
//    val Magenta = Color(0xFFFF00FF)
}
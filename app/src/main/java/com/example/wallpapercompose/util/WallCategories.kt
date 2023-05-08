package com.example.wallpapercompose.util

import com.example.wallpapercompose.R

enum class WallCategories {
    Animals,
    Art,
    Dark,
    City,
    Flowers,
    Nature;

    fun imageSrc(): Int {
        return when(this){
            Animals -> R.drawable.animals
            Art -> R.drawable.art
            Dark -> R.drawable.dark
            City -> R.drawable.city
            Flowers -> R.drawable.flowers
            Nature -> R.drawable.nature
        }
    }


}
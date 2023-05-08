package com.example.wallpapercompose.screens.wallpaper
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.wallpapercompose.R
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutWallpaperDialog(isOpen:Boolean, onDismiss: () -> Unit,wallpaperModel: WallpaperModel) {
    val context = LocalContext.current

    if (isOpen) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            icon = { Icon(Icons.Filled.Info, modifier = Modifier.size(40.dp), contentDescription = null) },
            title = {
                Text(text = stringResource(id = R.string.about_wallpaper))
            },
            text = {
                Text(stringResource(id = R.string.about_photographer, wallpaperModel.photographer) )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val defaultBrowser = Intent(Intent.ACTION_VIEW)
                        defaultBrowser.data = Uri.parse(wallpaperModel.url)
                        startActivity(context,defaultBrowser, Bundle.EMPTY)
                    }
                ) {
                    Text(stringResource(id = R.string.more))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(stringResource(id = R.string.dismiss))
                }
            }
        )
    }
}


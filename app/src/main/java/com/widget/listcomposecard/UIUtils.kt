package com.widget.listcomposecard

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun backPressSupport(itemsList : SnapshotStateList<CardData>) {
    var showDialog by remember { mutableStateOf(false) }

    val activity = when (val owner = LocalLifecycleOwner.current) {
        is MainActivity -> owner
        else -> {
            val context = LocalContext.current
            if (context is MainActivity) {
                context
            } else {
                throw IllegalStateException("LocalLifecycleOwner is not MainActivity or Fragment")
            }
        }
    }

    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialog = true
            }
        }
    }

    activity.onBackPressedDispatcher.addCallback(callback)

    DisposableEffect(activity.lifecycle, activity.onBackPressedDispatcher) {
        Log.d("Back", "DisposableEffect")
        onDispose {
            //printItemsList(itemsList);
            callback.remove()
            Log.d("Back", "callback.remove()")
        }
    }

    if (showDialog) {

        showAlertDialog(
            title = "Exit Application",
            text = "Are you sure leave app?",
            onDismiss = { showDialog = false },
            onOkClick = {
                Log.d("Back", "activity.finish()")
                activity.finish()
            }
        )
    }
}

fun printItemsList(itemsList : SnapshotStateList<CardData>) {
    itemsList.forEach { item ->
        if (item.start) {
            item.start = false;
        }
        println(item)
    }
}

@Composable
fun showAlertDialog(title: String, text: String, onDismiss: () -> Unit, onOkClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },

        title = { Text(text = title, color = Color.Black,
            style = TextStyle(
                fontSize = 18.sp),
                    fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic)
        },

        text = { Text(text = text, color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp),
                    fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic)
        },

        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray, // Change background color of the button
                    contentColor = Color.White // Change text color of the button
                ),
                onClick = {
                    onOkClick()
                    onDismiss()
                }
            ) {
                Text(text = "Ok",
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray, // Change background color of the button
                    contentColor = Color.White // Change text color of the button
                ),
                onClick = {
                    onDismiss()
                }
            ) {
                Text(text = "Cancel",
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic)
            }
        },
        containerColor = Color.LightGray
    )
}

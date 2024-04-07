package com.widget.listcomposecard.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GraphEcgWidget(width: Dp, height: Dp, seriesLength: Int, mode: GraphMode, toggle: Boolean) {

    println(" [GraphEcgWidget] $seriesLength $mode [$toggle]")

    var currentCounter  by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    val storeWrapper = StoreWrapper(seriesLength, 5, mode)

    val density = Density(LocalContext.current)
    val screenWidth = convertDpToFloat(density, width)
    val screenHeight = convertDpToFloat(density, height)

    fun DrawScope.drawScene() {

        val topLeft = Offset(0f, 0f)
        val width = screenWidth
        val height = screenHeight

        currentCounter

        fun drawFlowingGraph() {

            val path = if (storeWrapper.path == null) Path() else storeWrapper.path!!

            drawPath(
                path = path,
                color = Color.Gray,
                style = Stroke(width = 2f), // Stroke width
            )

            if (storeWrapper.point == null) {
                return
            }

            if (storeWrapper.isFull()) {
                return
            }
            drawCircle(
                center = Offset(
                    storeWrapper.point!!.x,
                    storeWrapper.point!!.y),
                radius = 6f,
                color = Color.Red
            )
        }

        fun drawOverlayGraph() {

            val pathBefore = if (storeWrapper.pathBefore == null) Path() else storeWrapper.pathBefore!!
            val pathAfter = if (storeWrapper.pathAfter == null) Path() else storeWrapper.pathAfter!!

            if (storeWrapper.point != null) {
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(storeWrapper.point!!.x, 0f),
                    size = Size(width, height),
                    style = Fill
                )
            }

            drawPath(
                path = pathBefore,
                color = Color.Gray,
                style = Stroke(width = 2f), // Stroke width
            )

            drawPath(
                path = pathAfter,
                color = Color.LightGray,
                style = Stroke(width = 2f), // Stroke width
            )

            if (storeWrapper.point == null) {
                return
            }

            drawCircle(
                center = Offset(
                    storeWrapper.point!!.x,
                    storeWrapper.point!!.y),
                radius = 6f,
                color = Color.Red
            )
        }

        // Draw the rectangle
        drawRect(
            color = Color.LightGray,
            topLeft = topLeft,
            size = Size(width, height),
            style = Fill
        )

        if (storeWrapper.mode() == GraphMode.Flowing) {
            drawFlowingGraph()
        }
        else {
            drawOverlayGraph()
        }
    }

    fun runWidgetEngine(isCoroutineRunning: Boolean, cycles: Int) {

        val width = screenWidth
        val height = screenHeight

        toggleCoroutine(isCoroutineRunning, coroutineScope, cycles) { counter ->
            //println("Coroutine is running [$mode] [$counter] ... [$cycles]")
            currentCounter = counter
            storeWrapper.updateBuffer(counter)
            if (counter > 0) {
                storeWrapper.prepareDrawing(android.util.Size(width.toInt(), height.toInt()), 32.toDouble())
            }
        }
    }

    Canvas(
        modifier = Modifier
            .size(width, height)
    ) {
        drawScene()
    }

    runWidgetEngine(toggle, storeWrapper.drawingFrequency())

}

fun convertDpToFloat(density: Density, dpValue: Dp): Float {
    return dpValue.value * density.density
}

fun toggleCoroutine(isRunning: Boolean, coroutineScope: CoroutineScope, cycles: Int, callback: (Int) -> Unit) {

    coroutineScope.launch {
        if (isRunning) {
            val channel = Channel<Int>()
            launch {
                var counter = 0
                while (true) {
                    channel.send(counter++)
                    if (counter >= cycles) {
                        counter = 1
                    }
                    delay(24)
                }
            }

            for (value in channel) {
                callback(value)
            }
        } else {
            coroutineScope.coroutineContext.cancelChildren()
        }
    }
}



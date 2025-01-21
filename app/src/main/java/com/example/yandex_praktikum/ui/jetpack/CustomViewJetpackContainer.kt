package com.example.yandex_praktikum.ui.jetpack

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


private const val DURATION = 5000

@Composable
fun CustomViewJetpackContainer(
    modifier: Modifier = Modifier,
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?,
) {
    val density = LocalDensity.current.density

    val firstAlpha = remember { Animatable(0f) }
    val firstOffsetY = remember { Animatable(0f) }

    val secondAlpha = remember { Animatable(0f) }
    val secondOffsetY = remember { Animatable(0f) }

    var parentHeight by remember { mutableFloatStateOf(0f) }
    var firstHeight by remember { mutableFloatStateOf(0f) }
    var secondHeight by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        launch {
            launch {
                firstAlpha.animateTo(targetValue = 1f, tween(DURATION))
            }
            launch {
                firstOffsetY.animateTo(
                    targetValue = -parentHeight / 2 + firstHeight / 2,
                    tween(DURATION)
                )
            }
        }

        launch {
            launch {
                secondAlpha.animateTo(targetValue = 1f, tween(DURATION))
            }
            launch {
                secondOffsetY.animateTo(
                    targetValue = parentHeight / 2f - secondHeight / 2,
                    tween(DURATION)
                )
            }
        }
    }

    Box(
        modifier = modifier
            .onPlaced { layoutCoordinates ->
                parentHeight = layoutCoordinates.size.height / density
            }
            .fillMaxSize()
    ) {
        firstChild?.let {
            Box(
                modifier = Modifier
                    .offset(y = firstOffsetY.value.dp)
                    .align(Alignment.Center)
                    .onPlaced { layoutCoordinates ->
                        firstHeight = layoutCoordinates.size.height / density
                    }
                    .alpha(firstAlpha.value)
            ) {
                it()
            }
        }

        secondChild?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = secondOffsetY.value.dp)
                    .onPlaced { layoutCoordinates ->
                        secondHeight = layoutCoordinates.size.height / density
                    }
                    .alpha(secondAlpha.value)
            ) {
                it()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomViewJetpackContainerPreview() {
    CustomViewJetpackContainer(
        modifier = Modifier.size(300.dp).background(Color.Gray),
        firstChild = { Box(modifier = Modifier.size(100.dp).background(Color.Red)) },
        secondChild = { Box(modifier = Modifier.size(100.dp).background(Color.Blue)) }
    )
}
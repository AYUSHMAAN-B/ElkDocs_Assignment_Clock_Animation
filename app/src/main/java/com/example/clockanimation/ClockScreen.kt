package com.example.clockanimation

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.*

@Composable
fun ClockScreen() {
    val orangeColor = Color(0xffeb5e28).copy(0.8f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.95f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        val fixedTime = remember { mutableStateOf(Calendar.getInstance()) }
        fixedTime.value.set(Calendar.HOUR, 6)
        fixedTime.value.set(Calendar.MINUTE, 28)

        val hours = fixedTime.value.get(Calendar.HOUR)
        val minutes = fixedTime.value.get(Calendar.MINUTE)
        val seconds = fixedTime.value.get(Calendar.SECOND)

        Text(
            text = "Monday 15",
            color = orangeColor
        )

        Spacer(modifier = Modifier.height(150.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {
            ClockFace(hours, minutes, seconds)
            MultiBorderCircle()
        }

        Spacer(modifier = Modifier.height(120.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "That Cool Song",
                    color = orangeColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    "It's Artist",
                    color = orangeColor
                )
            }

            Row {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = orangeColor,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    Icons.Default.SkipNext,
                    contentDescription = null,
                    tint = orangeColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Divider(thickness = 2.dp, modifier = Modifier.padding(start = 36.dp, end = 36.dp))

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            "34%",
            color = orangeColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .background(Color(0xff131219))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .background(Color(0xff2a2932))
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.Center)
                    )
                }

                Row {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(Color(0xff2a2932))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp)
                            )

                            Text(
                                "EDIT",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(Color(0xff2a2932))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(25.dp)
                            )

                            Text(
                                "APPLY",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val listItems = listOf("Backgrounds", "Music Controls", "Notifications", "Calendar")

            LazyRow {
                items(listItems) {
                    Text(
                        text = it,
                        color = when (it) {
                            "Music Controls" -> Color.White
                            else -> Color.Gray
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ClockFace(
    hours: Int,
    minutes: Int,
    seconds: Int
) {
    Canvas(modifier = Modifier.size(250.dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = size.minDimension / 2

        // Draw the clock face
        val orangeColor = Color(0xffeb5e28)
        val blueColor = Color(0xff003566)

        drawCircle(
            color = orangeColor,
            radius = radius,
            center = Offset(centerX, centerY),
            style = Stroke(width = 5f)
        )

        // Draw the hour hand
        val hourAngle = (hours + minutes / 60f) * 30
        drawLine(
            color = orangeColor,
            start = Offset(centerX, centerY),
            end = Offset(
                x = centerX + radius * 0.4f * cos(Math.toRadians(hourAngle.toDouble() - 90)).toFloat(),
                y = centerY + radius * 0.4f * sin(Math.toRadians(hourAngle.toDouble() - 90)).toFloat()
            ),
            strokeWidth = 8f,
            cap = StrokeCap.Round
        )

        // Draw the minute hand
        val minuteAngle = (minutes + seconds / 60f) * 6
        drawLine(
            color = blueColor,
            start = Offset(centerX, centerY),
            end = Offset(
                x = centerX + radius * 0.7f * cos(Math.toRadians(minuteAngle.toDouble() - 90)).toFloat(),
                y = centerY + radius * 0.7f * sin(Math.toRadians(minuteAngle.toDouble() - 90)).toFloat()
            ),
            strokeWidth = 6f,
            cap = StrokeCap.Round
        )

        // Draw the second hand
//        val secondAngle = seconds * 6
//        drawLine(
//            color = Color.Red,
//            start = Offset(centerX, centerY),
//            end = Offset(
//                x = centerX + radius * 0.9f * cos(Math.toRadians(secondAngle.toDouble() - 90)).toFloat(),
//                y = centerY + radius * 0.9f * sin(Math.toRadians(secondAngle.toDouble() - 90)).toFloat()
//            ),
//            strokeWidth = 4f,
//            cap = StrokeCap.Round
//        )
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun MultiBorderCircle(
    colors: List<Triple<Color, Float, Dp>> = listOf(
        Triple(Color.Blue, 90f, 20.dp),
        Triple(Color.White, 60f, 10.dp),
        Triple(Color.Red, 120f, 20.dp),
        Triple(Color.Green, 90f, 10.dp)
    ),
    animationSpeed: Int = 2000,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = animationSpeed, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(
        modifier = modifier
            .size(250.dp) // adjust the size as needed
//            .clip(CircleShape) // clip the canvas to a circle
    ) {
        val circleCenter = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2

        // Draw the circle with multiple colored borders
        var startAngle = 0f

        for ((color, sweepAngle, maxStrokeWidth) in colors) {
            val midAngle = startAngle + sweepAngle / 2
            for (angle in 0..sweepAngle.toInt()) {
                val strokeWidth = if (angle < sweepAngle / 2) {
                    (angle / (sweepAngle / 2)) * maxStrokeWidth.toPx()
                } else {
                    ((sweepAngle - angle) / (sweepAngle / 2)) * maxStrokeWidth.toPx()
                }
                drawArc(
                    color = color,
                    startAngle = (startAngle + angle + rotation.value) % 360,
                    sweepAngle = 1f,
                    useCenter = false,
                    topLeft = Offset(circleCenter.x - radius, circleCenter.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokeWidth)
                )
            }
            startAngle += sweepAngle
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClockScreenPreview() {
    ClockScreen()
}


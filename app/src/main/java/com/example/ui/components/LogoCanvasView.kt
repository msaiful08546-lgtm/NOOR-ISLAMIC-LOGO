package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.example.data.LogoStyle
import com.example.ui.theme.AmoledBackground
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldSecondary
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LogoCanvasView(
    title: String,
    styleName: String,
    modifier: Modifier = Modifier,
    isTransparentBg: Boolean = false,
    show4KBadge: Boolean = true
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, GlassBorder, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (isTransparentBg) {
            // Draw Checkered Transparency Pattern
            Canvas(modifier = Modifier.fillMaxSize()) {
                val squareSize = 24.dp.toPx()
                val cols = (size.width / squareSize).toInt() + 1
                val rows = (size.height / squareSize).toInt() + 1

                for (r in 0 until rows) {
                    for (c in 0 until cols) {
                        val color = if ((r + c) % 2 == 0) Color(0xFF181818) else Color(0xFF101010)
                        drawRect(
                            color = color,
                            topLeft = Offset(c * squareSize, r * squareSize),
                            size = Size(squareSize, squareSize)
                        )
                    }
                }
            }
        } else {
            // Dark Velvet / AMOLED Background
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF0C1B14),
                            AmoledBackground,
                            Color.Black
                        ),
                        center = Offset(size.width / 2, size.height / 2),
                        radius = size.width * 0.8f
                    )
                )
            }
        }

        // Draw Vector Islamic Geometry Logo Emblem
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            val cx = size.width / 2
            val cy = size.height / 2
            val radius = size.width * 0.38f

            // 1. Outer 8-Pointed Rub el Hizb Star Frame
            val outerPath = Path()
            val points = 8
            for (i in 0 until points * 2) {
                val r = if (i % 2 == 0) radius else radius * 0.72f
                val angle = Math.toRadians((i * 360.0 / (points * 2)) - 90.0)
                val x = (cx + r * cos(angle)).toFloat()
                val y = (cy + r * sin(angle)).toFloat()
                if (i == 0) outerPath.moveTo(x, y) else outerPath.lineTo(x, y)
            }
            outerPath.close()

            val goldBrush = Brush.linearGradient(
                colors = listOf(GoldPrimary, GoldSecondary, Color(0xFFFFE082), GoldPrimary),
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height)
            )

            val emeraldBrush = Brush.linearGradient(
                colors = listOf(EmeraldPrimary, Color(0xFF00FF87), EmeraldDark),
                start = Offset(0f, size.height),
                end = Offset(size.width, 0f)
            )

            // Draw Outer Star Border
            drawPath(
                path = outerPath,
                brush = goldBrush,
                style = Stroke(width = 6.dp.toPx())
            )

            // Inner Decorative Ring
            drawCircle(
                brush = emeraldBrush,
                radius = radius * 0.65f,
                center = Offset(cx, cy),
                style = Stroke(width = 3.dp.toPx())
            )

            // Crescent Moon Motif
            val crescentRadius = radius * 0.45f
            drawCircle(
                brush = goldBrush,
                radius = crescentRadius,
                center = Offset(cx - crescentRadius * 0.1f, cy - crescentRadius * 0.1f)
            )
            // Cutout Circle
            drawCircle(
                color = if (isTransparentBg) Color.Transparent else Color(0xFF0A1510),
                radius = crescentRadius * 0.85f,
                center = Offset(cx + crescentRadius * 0.25f, cy - crescentRadius * 0.25f)
            )

            // Center Minaret Dome Pinnacle
            val domePath = Path().apply {
                moveTo(cx, cy - crescentRadius * 0.9f)
                lineTo(cx + 8.dp.toPx(), cy - crescentRadius * 0.4f)
                lineTo(cx - 8.dp.toPx(), cy - crescentRadius * 0.4f)
                close()
            }
            drawPath(path = domePath, brush = goldBrush)

            // Decorative Geometric Rays
            for (deg in 0 until 360 step 45) {
                rotate(deg.toFloat(), pivot = Offset(cx, cy)) {
                    drawLine(
                        brush = goldBrush,
                        start = Offset(cx, cy - radius * 0.72f),
                        end = Offset(cx, cy - radius * 0.88f),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }
    }
}

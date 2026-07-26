package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldSecondary

@Composable
fun GoldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    isEmerald: Boolean = false,
    enabled: Boolean = true
) {
    val gradientColors = if (isEmerald) {
        listOf(
            Color(0xFF00FF87),
            EmeraldPrimary,
            Color(0xFF00B0FF)
        )
    } else {
        listOf(
            GoldPrimary,
            GoldSecondary,
            Color(0xFFFF9100)
        )
    }

    val textColor = Color.Black

    Row(
        modifier = modifier
            .shadow(
                elevation = if (enabled) 12.dp else 2.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = if (isEmerald) EmeraldPrimary else GoldPrimary
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = if (enabled) Brush.horizontalGradient(gradientColors) else Brush.horizontalGradient(
                    listOf(Color.Gray, Color.DarkGray)
                )
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 24.dp, vertical = 14.dp)
            .defaultMinSize(minHeight = 48.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}

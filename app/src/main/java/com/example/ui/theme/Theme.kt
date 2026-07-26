package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val IslamicAmoledColorScheme = darkColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.Black,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldGlow,
    secondary = GoldPrimary,
    onSecondary = Color.Black,
    secondaryContainer = GoldContainer,
    onSecondaryContainer = GoldText,
    tertiary = GoldSecondary,
    onTertiary = Color.Black,
    background = AmoledBackground,
    onBackground = TextPrimary,
    surface = AmoledSurface,
    onSurface = TextPrimary,
    surfaceVariant = GlassSurface,
    onSurfaceVariant = TextSecondary,
    outline = GlassBorder,
    outlineVariant = DividerColor
)

@Composable
fun NoorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = IslamicAmoledColorScheme,
        typography = Typography,
        content = content
    )
}

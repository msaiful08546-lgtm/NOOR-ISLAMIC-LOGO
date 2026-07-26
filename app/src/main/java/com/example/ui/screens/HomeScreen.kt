package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicPrompt
import com.example.data.PromptCategory
import com.example.data.PromptLibraryData
import com.example.ui.components.EmeraldChip
import com.example.ui.components.GlassCard
import com.example.ui.components.GoldButton
import com.example.ui.components.LogoCanvasView
import com.example.ui.theme.AmoledBackground
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldGlow
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.AppNavTab
import com.example.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current
    val dailyPrompt = PromptLibraryData.allPrompts.firstOrNull { it.isDaily } ?: PromptLibraryData.allPrompts.first()
    val trendingPrompts = PromptLibraryData.allPrompts.filter { it.isTrending }
    val featuredPrompts = PromptLibraryData.allPrompts.filter { it.isFeatured }
    val favouritePrompts by viewModel.favouritePrompts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AmoledBackground)
            .verticalScroll(scrollState)
            .padding(bottom = 90.dp)
    ) {
        // Top App Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(EmeraldContainer)
                    .border(1.dp, GoldPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "NOOR ISLAMIC LOGO",
                    color = GoldPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "AI Logo Studio",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Hero Banner Card
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            glowColor = GoldPrimary
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(GoldPrimary.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.FlashOn,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "AI GENERATOR v3.5",
                                color = GoldPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Craft Luxury Islamic Logos in Seconds",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Generate 4K calligraphic, geometric, and 3D Islamic brand emblems with custom text prompts and AI prompt tools.",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                GoldButton(
                    text = "Create AI Logo Now",
                    onClick = { viewModel.selectTab(AppNavTab.GENERATE) },
                    icon = Icons.Default.AutoAwesome,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Daily Prompt Featured Section
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "DAILY FEATURED PROMPT",
                    color = GoldSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = GoldPrimary.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = dailyPrompt.title,
                        color = GoldPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = dailyPrompt.promptText,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GoldButton(
                            text = "Use This Prompt",
                            onClick = { viewModel.quickGenerateWithPrompt(dailyPrompt) },
                            isEmerald = true,
                            icon = Icons.Default.PlayArrow,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { clipboardManager.setText(AnnotatedString(dailyPrompt.promptText)) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy Prompt",
                                tint = GoldPrimary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categories Grid Preview
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PROMPT CATEGORIES",
                    color = TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "View All (30+)",
                    color = EmeraldPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { viewModel.selectTab(AppNavTab.PROMPT_LIBRARY) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            val popularCategories = listOf(
                PromptCategory.MOSQUE,
                PromptCategory.MAKKAH,
                PromptCategory.ARABIC_CALLIGRAPHY,
                PromptCategory.KAABA,
                PromptCategory.ISLAMIC_BUSINESS,
                PromptCategory.CRESCENT_MOON,
                PromptCategory.HALAL_FOOD,
                PromptCategory.LUXURY_ISLAMIC
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(popularCategories) { cat ->
                    EmeraldChip(
                        text = cat.title,
                        isSelected = false,
                        onClick = {
                            viewModel.setSelectedCategory(cat)
                            viewModel.selectTab(AppNavTab.PROMPT_LIBRARY)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Trending Prompts Carousel
        Column {
            Text(
                text = "TRENDING LOGO PROMPTS",
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(trendingPrompts) { prompt ->
                    val isFav = favouritePrompts.any { it.promptId == prompt.id }
                    GlassCard(
                        modifier = Modifier.width(260.dp),
                        onClick = { viewModel.quickGenerateWithPrompt(prompt) }
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(EmeraldContainer)
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = prompt.category.title,
                                        color = EmeraldGlow,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = { viewModel.toggleFavouritePrompt(prompt) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = if (isFav) Color.Red else TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = prompt.title,
                                color = GoldPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = prompt.promptText,
                                color = TextSecondary,
                                fontSize = 12.sp,
                                maxLines = 3,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Logo Templates Showcase
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "FEATURED LOGO TEMPLATES",
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                featuredPrompts.take(2).forEach { feat ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { viewModel.quickGenerateWithPrompt(feat) }
                    ) {
                        LogoCanvasView(
                            title = feat.title,
                            styleName = feat.style.displayName
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = feat.title,
                            color = GoldPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

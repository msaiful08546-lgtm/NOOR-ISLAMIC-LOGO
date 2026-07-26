package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.EmeraldChip
import com.example.ui.components.GlassCard
import com.example.ui.theme.AmoledBackground
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.MainViewModel

@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val savedLogos by viewModel.savedLogos.collectAsState()
    val favouriteLogos by viewModel.favouriteLogos.collectAsState()
    val favouritePrompts by viewModel.favouritePrompts.collectAsState()
    val appLanguage by viewModel.appLanguage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AmoledBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 90.dp)
    ) {
        Text(
            text = "PROFILE & SETTINGS",
            color = GoldPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Text(
            text = "Noor Studio Account",
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Avatar Card
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            glowColor = GoldPrimary
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(EmeraldContainer)
                        .border(1.5.dp, GoldPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Islamic AI Creator",
                        color = GoldPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "4K Unlimited License Active",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            GlassCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${savedLogos.size}",
                        color = GoldPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Created Logos",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }
            }

            GlassCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${favouriteLogos.size + favouritePrompts.size}",
                        color = GoldPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Saved Favourites",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Settings Section
        Text(
            text = "PREFERENCES & SETTINGS",
            color = TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                // Dark Mode
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = null,
                        tint = GoldPrimary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "AMOLED Dark Mode",
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Pitch Black (Active)",
                        color = GoldPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Language
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = GoldPrimary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "App Language",
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Row {
                        EmeraldChip(
                            text = "English",
                            isSelected = appLanguage == "English",
                            onClick = { viewModel.setAppLanguage("English") }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        EmeraldChip(
                            text = "Bangla",
                            isSelected = appLanguage == "Bangla",
                            onClick = { viewModel.setAppLanguage("Bangla") }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // About & Legal
        Text(
            text = "ABOUT & SUPPORT",
            color = TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = GoldPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "About Noor Islamic Logo v1.0", color = TextPrimary, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Policy, contentDescription = null, tint = GoldPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Privacy Policy & Terms", color = TextPrimary, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = GoldPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Rate App 5 Stars", color = TextPrimary, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = GoldPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Share Noor Logo App", color = TextPrimary, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }
            }
        }
    }
}

package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ui.screens.FavouritesScreen
import com.example.ui.screens.GenerateScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.PromptLibraryScreen
import com.example.ui.theme.AmoledBackground
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GlassSurface
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NoorTheme
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.AppNavTab
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NoorTheme {
                val currentTab by viewModel.currentTab.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = AmoledBackground,
                    bottomBar = {
                        NoorBottomNavigation(
                            currentTab = currentTab,
                            onTabSelected = { viewModel.selectTab(it) }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                    ) {
                        AnimatedContent(
                            targetState = currentTab,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "TabTransition"
                        ) { tab ->
                            when (tab) {
                                AppNavTab.HOME -> HomeScreen(viewModel = viewModel)
                                AppNavTab.PROMPT_LIBRARY -> PromptLibraryScreen(viewModel = viewModel)
                                AppNavTab.GENERATE -> GenerateScreen(viewModel = viewModel)
                                AppNavTab.FAVOURITES -> FavouritesScreen(viewModel = viewModel)
                                AppNavTab.PROFILE -> ProfileScreen(viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoorBottomNavigation(
    currentTab: AppNavTab,
    onTabSelected: (AppNavTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(28.dp),
                    ambientColor = Color.Black,
                    spotColor = GoldPrimary.copy(alpha = 0.4f)
                )
                .clip(RoundedCornerShape(28.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            GlassSurface.copy(alpha = 0.98f),
                            Color(0xFF09120D)
                        )
                    )
                )
                .border(
                    BorderStroke(1.dp, Brush.horizontalGradient(listOf(GlassBorder, GoldPrimary.copy(alpha = 0.4f), GlassBorder))),
                    RoundedCornerShape(28.dp)
                )
                .padding(vertical = 10.dp, horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavNavItem(
                    title = "Home",
                    icon = Icons.Default.Home,
                    isSelected = currentTab == AppNavTab.HOME,
                    onClick = { onTabSelected(AppNavTab.HOME) }
                )
                NavNavItem(
                    title = "Library",
                    icon = Icons.Default.MenuBook,
                    isSelected = currentTab == AppNavTab.PROMPT_LIBRARY,
                    onClick = { onTabSelected(AppNavTab.PROMPT_LIBRARY) }
                )
                NavNavItem(
                    title = "Generate",
                    icon = Icons.Default.AutoAwesome,
                    isSelected = currentTab == AppNavTab.GENERATE,
                    isCenterHero = true,
                    onClick = { onTabSelected(AppNavTab.GENERATE) }
                )
                NavNavItem(
                    title = "Favourites",
                    icon = Icons.Default.Favorite,
                    isSelected = currentTab == AppNavTab.FAVOURITES,
                    onClick = { onTabSelected(AppNavTab.FAVOURITES) }
                )
                NavNavItem(
                    title = "Profile",
                    icon = Icons.Default.Person,
                    isSelected = currentTab == AppNavTab.PROFILE,
                    onClick = { onTabSelected(AppNavTab.PROFILE) }
                )
            }
        }
    }
}

@Composable
fun NavNavItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    isCenterHero: Boolean = false,
    onClick: () -> Unit
) {
    if (isCenterHero) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = if (isSelected) listOf(GoldPrimary, Color(0xFFFF9100)) else listOf(EmeraldPrimary, Color(0xFF00B0FF))
                    )
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.Black,
                modifier = Modifier.size(26.dp)
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isSelected) GoldPrimary else TextSecondary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                color = if (isSelected) GoldPrimary else TextSecondary,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

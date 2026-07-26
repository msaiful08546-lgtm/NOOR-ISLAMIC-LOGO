package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LogoStyle
import com.example.ui.components.AiPromptAssistantDialog
import com.example.ui.components.EmeraldChip
import com.example.ui.components.GlassCard
import com.example.ui.components.GoldButton
import com.example.ui.components.LogoCanvasView
import com.example.ui.theme.AmoledBackground
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.MainViewModel

@Composable
fun GenerateScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val clipboardManager = LocalClipboardManager.current

    val generatorPrompt by viewModel.generatorPrompt.collectAsState()
    val generatorStyle by viewModel.generatorStyle.collectAsState()
    val generatorQuality by viewModel.generatorQuality.collectAsState()
    val isTransparentBg by viewModel.isTransparentBg.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val generationStepText by viewModel.generationStepText.collectAsState()
    val lastGeneratedLogo by viewModel.lastGeneratedLogo.collectAsState()

    val aiAssistantLoading by viewModel.aiAssistantLoading.collectAsState()
    val aiRewriteVariations by viewModel.aiRewriteVariations.collectAsState()

    var showAiDialog by remember { mutableStateOf(false) }

    if (showAiDialog) {
        AiPromptAssistantDialog(
            currentPrompt = generatorPrompt,
            isLoading = aiAssistantLoading,
            variations = aiRewriteVariations,
            onDismiss = { showAiDialog = false },
            onGenerateFromKeyword = { keyword ->
                viewModel.generatePromptFromKeyword(keyword)
            },
            onImprovePrompt = {
                viewModel.improveCurrentPrompt()
            },
            onRewritePrompt = {
                viewModel.rewriteCurrentPromptVariations()
            },
            onTranslate = { targetLang ->
                viewModel.translateCurrentPrompt(targetLang)
            },
            onSelectVariation = { variation ->
                viewModel.setGeneratorPrompt(variation)
                showAiDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AmoledBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 90.dp)
    ) {
        Text(
            text = "AI LOGO STUDIO",
            color = GoldPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Text(
            text = "Generate Islamic Logo",
            color = TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Prompt Input Area
        GlassCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Logo Concept / Prompt",
                        color = GoldPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { showAiDialog = true },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = "AI Assistant Suite",
                            tint = EmeraldPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = generatorPrompt,
                    onValueChange = { viewModel.setGeneratorPrompt(it) },
                    placeholder = { Text("Describe your Islamic logo prompt...", color = TextSecondary, fontSize = 13.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = GlassBorder,
                        focusedContainerColor = AmoledBackground,
                        unfocusedContainerColor = AmoledBackground,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // AI Prompt Suite Quick Action Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    EmeraldChip(
                        text = "AI Tools Suite",
                        isSelected = false,
                        onClick = { showAiDialog = true },
                        icon = Icons.Default.AutoAwesome
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Style Selector (15 Styles)
        Text(
            text = "SELECT LOGO STYLE (15 STYLES)",
            color = TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(LogoStyle.values()) { style ->
                EmeraldChip(
                    text = style.displayName,
                    isSelected = generatorStyle == style,
                    onClick = { viewModel.setGeneratorStyle(style) }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Options: Resolution Quality & Transparent Background
        GlassCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4K Resolution Quality",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    EmeraldChip(
                        text = generatorQuality,
                        isSelected = true,
                        onClick = {
                            val newQual = if (generatorQuality == "4K Quality") "Standard HD" else "4K Quality"
                            viewModel.setGeneratorQuality(newQual)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transparent Background (PNG)",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = isTransparentBg,
                        onCheckedChange = { viewModel.toggleTransparentBg(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = GoldPrimary,
                            checkedTrackColor = EmeraldContainer
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Generate Button or Progress
        if (isGenerating) {
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = GoldPrimary
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = GoldPrimary)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = generationStepText,
                        color = GoldSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            GoldButton(
                text = "Generate 4K Logo",
                onClick = { viewModel.startLogoGeneration() },
                icon = Icons.Default.AutoAwesome,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Generated Output Canvas Result
        lastGeneratedLogo?.let { logo ->
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "GENERATED LOGO RESULT (4K)",
                color = GoldPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = GoldPrimary
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LogoCanvasView(
                        title = logo.title,
                        styleName = logo.style,
                        isTransparentBg = logo.isTransparentBg
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = logo.title,
                        color = GoldPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = logo.prompt,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GoldButton(
                            text = "Export 4K PNG",
                            onClick = {
                                clipboardManager.setText(AnnotatedString("Exported Logo: ${logo.title}"))
                            },
                            isEmerald = true,
                            icon = Icons.Default.Download,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { viewModel.toggleFavouriteLogo(logo) }
                        ) {
                            Icon(
                                imageVector = if (logo.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (logo.isFavourite) Color.Red else TextSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

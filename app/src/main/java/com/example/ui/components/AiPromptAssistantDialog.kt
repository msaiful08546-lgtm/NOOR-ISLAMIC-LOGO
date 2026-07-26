package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.GTranslate
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.AmoledSurface
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GlassBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun AiPromptAssistantDialog(
    currentPrompt: String,
    isLoading: Boolean,
    variations: List<String>,
    onDismiss: () -> Unit,
    onGenerateFromKeyword: (String) -> Unit,
    onImprovePrompt: () -> Unit,
    onRewritePrompt: () -> Unit,
    onTranslate: (String) -> Unit,
    onSelectVariation: (String) -> Unit
) {
    var keywordInput by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, GoldPrimary, RoundedCornerShape(24.dp)),
            color = AmoledSurface
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = GoldPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AI Prompt Suite",
                        color = GoldPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = EmeraldPrimary)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Gemini AI processing prompt...",
                            color = TextSecondary,
                            fontSize = 14.sp
                        )
                    }
                } else {
                    // Tool 1: AI Prompt Generator from Key Phrase
                    Text(
                        text = "1. AI Prompt Generator (Keyphrase)",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = keywordInput,
                            onValueChange = { keywordInput = it },
                            placeholder = { Text("e.g. Halal Honey, Quran App", fontSize = 12.sp, color = TextSecondary) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmeraldPrimary,
                                unfocusedBorderColor = GlassBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        GoldButton(
                            text = "Generate",
                            onClick = { onGenerateFromKeyword(keywordInput) },
                            isEmerald = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tool 2 & 3: Improver, Rewriter & Translator
                    Text(
                        text = "2. Quick AI Prompt Transformations",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        EmeraldChip(
                            text = "Magic Improve",
                            isSelected = false,
                            onClick = onImprovePrompt,
                            icon = Icons.Default.AutoAwesome,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        EmeraldChip(
                            text = "3 Variations",
                            isSelected = false,
                            onClick = onRewritePrompt,
                            icon = Icons.Default.Refresh,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        EmeraldChip(
                            text = "Translate to Bangla",
                            isSelected = false,
                            onClick = { onTranslate("Bangla") },
                            icon = Icons.Default.GTranslate,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        EmeraldChip(
                            text = "Translate to English",
                            isSelected = false,
                            onClick = { onTranslate("English") },
                            icon = Icons.Default.GTranslate,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Display Rewrite Variations if available
                    if (variations.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "AI Generated Prompt Variations:",
                            color = GoldSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        variations.forEachIndexed { idx, varText ->
                            GlassCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                onClick = { onSelectVariation(varText) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${idx + 1}. $varText",
                                        color = TextPrimary,
                                        fontSize = 12.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = { clipboardManager.setText(AnnotatedString(varText)) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy",
                                            tint = GoldPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

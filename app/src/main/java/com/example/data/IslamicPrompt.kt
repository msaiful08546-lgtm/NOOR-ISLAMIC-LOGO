package com.example.data

data class IslamicPrompt(
    val id: String,
    val title: String,
    val category: PromptCategory,
    val style: LogoStyle,
    val promptText: String,
    val tags: List<String>,
    val rating: Float = 4.9f,
    val usageCount: Int = 1250,
    val isFeatured: Boolean = false,
    val isTrending: Boolean = false,
    val isDaily: Boolean = false
)

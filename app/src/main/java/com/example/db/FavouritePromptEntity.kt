package com.example.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_prompts")
data class FavouritePromptEntity(
    @PrimaryKey
    val promptId: String,
    val title: String,
    val categoryId: String,
    val styleId: String,
    val promptText: String,
    val dateAdded: Long = System.currentTimeMillis()
)

package com.example.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_logos")
data class SavedLogoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val prompt: String,
    val style: String,
    val category: String,
    val imageUri: String,
    val dateTimestamp: Long = System.currentTimeMillis(),
    val resolution: String = "4K Quality",
    val format: String = "PNG",
    val isFavourite: Boolean = false,
    val isTransparentBg: Boolean = false
)

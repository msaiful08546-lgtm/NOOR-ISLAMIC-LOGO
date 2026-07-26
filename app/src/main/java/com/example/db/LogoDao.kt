package com.example.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LogoDao {

    // Saved Logos
    @Query("SELECT * FROM saved_logos ORDER BY dateTimestamp DESC")
    fun getAllSavedLogos(): Flow<List<SavedLogoEntity>>

    @Query("SELECT * FROM saved_logos WHERE isFavourite = 1 ORDER BY dateTimestamp DESC")
    fun getFavouriteLogos(): Flow<List<SavedLogoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogo(logo: SavedLogoEntity): Long

    @Update
    suspend fun updateLogo(logo: SavedLogoEntity)

    @Delete
    suspend fun deleteLogo(logo: SavedLogoEntity)

    @Query("DELETE FROM saved_logos WHERE id = :id")
    suspend fun deleteLogoById(id: Long)

    // Favourite Prompts
    @Query("SELECT * FROM favourite_prompts ORDER BY dateAdded DESC")
    fun getAllFavouritePrompts(): Flow<List<FavouritePromptEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_prompts WHERE promptId = :promptId)")
    suspend fun isPromptFavourite(promptId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritePrompt(prompt: FavouritePromptEntity)

    @Query("DELETE FROM favourite_prompts WHERE promptId = :promptId")
    suspend fun deleteFavouritePromptById(promptId: String)
}

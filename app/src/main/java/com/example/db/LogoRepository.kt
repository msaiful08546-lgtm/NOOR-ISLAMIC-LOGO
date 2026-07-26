package com.example.db

import kotlinx.coroutines.flow.Flow

class LogoRepository(private val logoDao: LogoDao) {

    val allSavedLogos: Flow<List<SavedLogoEntity>> = logoDao.getAllSavedLogos()
    val favouriteLogos: Flow<List<SavedLogoEntity>> = logoDao.getFavouriteLogos()
    val favouritePrompts: Flow<List<FavouritePromptEntity>> = logoDao.getAllFavouritePrompts()

    suspend fun saveLogo(logo: SavedLogoEntity): Long {
        return logoDao.insertLogo(logo)
    }

    suspend fun updateLogo(logo: SavedLogoEntity) {
        logoDao.updateLogo(logo)
    }

    suspend fun deleteLogo(logo: SavedLogoEntity) {
        logoDao.deleteLogo(logo)
    }

    suspend fun deleteLogoById(id: Long) {
        logoDao.deleteLogoById(id)
    }

    suspend fun isPromptFavourite(promptId: String): Boolean {
        return logoDao.isPromptFavourite(promptId)
    }

    suspend fun toggleFavouritePrompt(
        promptId: String,
        title: String,
        categoryId: String,
        styleId: String,
        promptText: String
    ): Boolean {
        val exists = logoDao.isPromptFavourite(promptId)
        if (exists) {
            logoDao.deleteFavouritePromptById(promptId)
            return false
        } else {
            logoDao.insertFavouritePrompt(
                FavouritePromptEntity(
                    promptId = promptId,
                    title = title,
                    categoryId = categoryId,
                    styleId = styleId,
                    promptText = promptText
                )
            )
            return true
        }
    }
}

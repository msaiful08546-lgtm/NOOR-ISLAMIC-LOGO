package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.IslamicPrompt
import com.example.data.LogoStyle
import com.example.data.PromptCategory
import com.example.data.PromptLibraryData
import com.example.db.AppDatabase
import com.example.db.FavouritePromptEntity
import com.example.db.LogoRepository
import com.example.db.SavedLogoEntity
import com.example.services.GeminiApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavTab {
    HOME,
    PROMPT_LIBRARY,
    GENERATE,
    FAVOURITES,
    PROFILE
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LogoRepository

    init {
        val dao = AppDatabase.getDatabase(application).logoDao()
        repository = LogoRepository(dao)
    }

    // Navigation Tab
    private val _currentTab = MutableStateFlow(AppNavTab.HOME)
    val currentTab: StateFlow<AppNavTab> = _currentTab.asStateFlow()

    fun selectTab(tab: AppNavTab) {
        _currentTab.value = tab
    }

    // Prompt Library Filtering
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(PromptCategory.ALL)
    val selectedCategory: StateFlow<PromptCategory> = _selectedCategory.asStateFlow()

    private val _selectedStyleFilter = MutableStateFlow<LogoStyle?>(null)
    val selectedStyleFilter: StateFlow<LogoStyle?> = _selectedStyleFilter.asStateFlow()

    val filteredPrompts: StateFlow<List<IslamicPrompt>> = combine(
        _searchQuery,
        _selectedCategory,
        _selectedStyleFilter
    ) { query, category, style ->
        var list = PromptLibraryData.searchPrompts(query)
        if (category != PromptCategory.ALL) {
            list = list.filter { it.category == category }
        }
        if (style != null) {
            list = list.filter { it.style == style }
        }
        list
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PromptLibraryData.allPrompts)

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(category: PromptCategory) {
        _selectedCategory.value = category
    }

    fun setSelectedStyleFilter(style: LogoStyle?) {
        _selectedStyleFilter.value = style
    }

    // Generator State
    private val _generatorPrompt = MutableStateFlow(PromptLibraryData.allPrompts.first().promptText)
    val generatorPrompt: StateFlow<String> = _generatorPrompt.asStateFlow()

    private val _generatorStyle = MutableStateFlow(LogoStyle.LUXURY)
    val generatorStyle: StateFlow<LogoStyle> = _generatorStyle.asStateFlow()

    private val _generatorCategory = MutableStateFlow(PromptCategory.LUXURY_ISLAMIC)
    val generatorCategory: StateFlow<PromptCategory> = _generatorCategory.asStateFlow()

    private val _generatorQuality = MutableStateFlow("4K Quality")
    val generatorQuality: StateFlow<String> = _generatorQuality.asStateFlow()

    private val _generatorFormat = MutableStateFlow("PNG")
    val generatorFormat: StateFlow<String> = _generatorFormat.asStateFlow()

    private val _isTransparentBg = MutableStateFlow(false)
    val isTransparentBg: StateFlow<Boolean> = _isTransparentBg.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val _generationStepText = MutableStateFlow("")
    val generationStepText: StateFlow<String> = _generationStepText.asStateFlow()

    private val _lastGeneratedLogo = MutableStateFlow<SavedLogoEntity?>(null)
    val lastGeneratedLogo: StateFlow<SavedLogoEntity?> = _lastGeneratedLogo.asStateFlow()

    fun setGeneratorPrompt(promptText: String) {
        _generatorPrompt.value = promptText
    }

    fun setGeneratorStyle(style: LogoStyle) {
        _generatorStyle.value = style
    }

    fun setGeneratorCategory(category: PromptCategory) {
        _generatorCategory.value = category
    }

    fun setGeneratorQuality(quality: String) {
        _generatorQuality.value = quality
    }

    fun setGeneratorFormat(format: String) {
        _generatorFormat.value = format
    }

    fun toggleTransparentBg(transparent: Boolean) {
        _isTransparentBg.value = transparent
    }

    fun quickGenerateWithPrompt(prompt: IslamicPrompt) {
        _generatorPrompt.value = prompt.promptText
        _generatorStyle.value = prompt.style
        _generatorCategory.value = prompt.category
        _currentTab.value = AppNavTab.GENERATE
    }

    // AI Prompt Assistant Suite
    private val _aiAssistantLoading = MutableStateFlow(false)
    val aiAssistantLoading: StateFlow<Boolean> = _aiAssistantLoading.asStateFlow()

    private val _aiRewriteVariations = MutableStateFlow<List<String>>(emptyList())
    val aiRewriteVariations: StateFlow<List<String>> = _aiRewriteVariations.asStateFlow()

    fun generatePromptFromKeyword(keyword: String) {
        if (keyword.isBlank()) return
        viewModelScope.launch {
            _aiAssistantLoading.value = true
            val prompt = GeminiApiService.generateIslamicPrompt(keyword, _generatorStyle.value.displayName)
            _generatorPrompt.value = prompt
            _aiAssistantLoading.value = false
        }
    }

    fun improveCurrentPrompt() {
        if (_generatorPrompt.value.isBlank()) return
        viewModelScope.launch {
            _aiAssistantLoading.value = true
            val improved = GeminiApiService.improvePrompt(_generatorPrompt.value)
            _generatorPrompt.value = improved
            _aiAssistantLoading.value = false
        }
    }

    fun rewriteCurrentPromptVariations() {
        if (_generatorPrompt.value.isBlank()) return
        viewModelScope.launch {
            _aiAssistantLoading.value = true
            val variations = GeminiApiService.rewritePromptVariations(_generatorPrompt.value)
            _aiRewriteVariations.value = variations
            _aiAssistantLoading.value = false
        }
    }

    fun translateCurrentPrompt(targetLang: String) {
        if (_generatorPrompt.value.isBlank()) return
        viewModelScope.launch {
            _aiAssistantLoading.value = true
            val translated = GeminiApiService.translatePrompt(_generatorPrompt.value, targetLang)
            _generatorPrompt.value = translated
            _aiAssistantLoading.value = false
        }
    }

    // AI Logo Generation Trigger
    fun startLogoGeneration() {
        if (_generatorPrompt.value.isBlank()) return
        viewModelScope.launch {
            _isGenerating.value = true
            _generationStepText.value = "Analyzing prompt & Islamic geometry..."
            delay(800)

            _generationStepText.value = "Generating gold & emerald calligraphy vectors..."
            delay(1000)

            _generationStepText.value = "Applying 3D volumetric lighting & 4K textures..."
            delay(1200)

            _generationStepText.value = "Finalizing 4K PNG render..."
            delay(800)

            val title = if (_generatorPrompt.value.length > 25) {
                _generatorPrompt.value.substring(0, 25) + "..."
            } else {
                _generatorPrompt.value
            }

            val newLogo = SavedLogoEntity(
                title = title,
                prompt = _generatorPrompt.value,
                style = _generatorStyle.value.displayName,
                category = _generatorCategory.value.title,
                imageUri = "generated_logo_${System.currentTimeMillis()}",
                resolution = _generatorQuality.value,
                format = _generatorFormat.value,
                isTransparentBg = _isTransparentBg.value
            )

            val savedId = repository.saveLogo(newLogo)
            _lastGeneratedLogo.value = newLogo.copy(id = savedId)

            _isGenerating.value = false
            _generationStepText.value = ""
        }
    }

    // Database Flows
    val savedLogos: StateFlow<List<SavedLogoEntity>> = repository.allSavedLogos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favouriteLogos: StateFlow<List<SavedLogoEntity>> = repository.favouriteLogos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favouritePrompts: StateFlow<List<FavouritePromptEntity>> = repository.favouritePrompts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavouriteLogo(logo: SavedLogoEntity) {
        viewModelScope.launch {
            repository.updateLogo(logo.copy(isFavourite = !logo.isFavourite))
        }
    }

    fun deleteLogo(logo: SavedLogoEntity) {
        viewModelScope.launch {
            repository.deleteLogo(logo)
        }
    }

    fun toggleFavouritePrompt(prompt: IslamicPrompt) {
        viewModelScope.launch {
            repository.toggleFavouritePrompt(
                promptId = prompt.id,
                title = prompt.title,
                categoryId = prompt.category.id,
                styleId = prompt.style.id,
                promptText = prompt.promptText
            )
        }
    }

    // Preferences & Settings
    private val _appLanguage = MutableStateFlow("English") // "English" or "Bangla"
    val appLanguage: StateFlow<String> = _appLanguage.asStateFlow()

    fun setAppLanguage(lang: String) {
        _appLanguage.value = lang
    }
}

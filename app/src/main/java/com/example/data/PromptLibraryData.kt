package com.example.data

object PromptLibraryData {

    val allPrompts: List<IslamicPrompt> = listOf(
        // Daily Prompt
        IslamicPrompt(
            id = "p_daily_1",
            title = "Royal Golden Kaaba Emblem",
            category = PromptCategory.KAABA,
            style = LogoStyle.LUXURY,
            promptText = "A luxury 3D geometric Kaaba logo emblem crafted from 24k polished gold, encircled by elegant Arabic Thuluth calligraphy, set against an AMOLED pitch black background with glowing emerald rim light, 8k resolution, vector finish.",
            tags = listOf("Kaaba", "Golden", "Luxury", "Calligraphy"),
            rating = 5.0f,
            usageCount = 4820,
            isFeatured = true,
            isTrending = true,
            isDaily = true
        ),

        // Mosque
        IslamicPrompt(
            id = "p_mosque_1",
            title = "Minimalist Mosque Dome & Crescent",
            category = PromptCategory.MOSQUE,
            style = LogoStyle.MINIMAL,
            promptText = "A clean minimalist logo mark of a mosque dome seamlessly merging with a crescent moon, golden ratio geometry, sleek emerald green vector lines on dark background.",
            tags = listOf("Dome", "Crescent", "Minimal", "Emerald"),
            isTrending = true
        ),
        IslamicPrompt(
            id = "p_mosque_2",
            title = "Grand Minaret Architectural Seal",
            category = PromptCategory.MOSQUE,
            style = LogoStyle.GOLDEN,
            promptText = "A majestic towering minaret silhouette enclosed inside an octagonal Islamic geometric star frame, 3D metallic gold bevel, dark velvet backdrop, high definition branding logo.",
            tags = listOf("Minaret", "Octagon", "Gold", "Architecture")
        ),

        // Makkah
        IslamicPrompt(
            id = "p_makkah_1",
            title = "Sacred Makkah Al-Haram Crown",
            category = PromptCategory.MAKKAH,
            style = LogoStyle.PREMIUM,
            promptText = "A high-end corporate Islamic logo featuring a stylized Kaaba silhouette surrounded by radiant golden rays and Quranic calligraphy, dark emerald foil background, pristine luxury branding mark.",
            tags = listOf("Makkah", "Al-Haram", "Kaaba", "Sacred"),
            isFeatured = true
        ),

        // Madina
        IslamicPrompt(
            id = "p_madina_1",
            title = "Al-Madina Green Dome Radiance",
            category = PromptCategory.MADINA,
            style = LogoStyle.EMERALD,
            promptText = "A glowing emerald green dome of Al-Masjid an-Nabawi in Madina, with subtle gold starburst accents, modern vector circle badge, spiritual elegance.",
            tags = listOf("Madina", "Green Dome", "Nabawi", "Radiance"),
            isTrending = true
        ),

        // Arabic Calligraphy
        IslamicPrompt(
            id = "p_calli_1",
            title = "Royal Thuluth Bismillah Seal",
            category = PromptCategory.ARABIC_CALLIGRAPHY,
            style = LogoStyle.ARABIC,
            promptText = "A circular Bismillah written in master-level Thuluth Arabic script, forming an intricate circular medallion, 3D golden embossed texture on dark marble background.",
            tags = listOf("Bismillah", "Thuluth", "Script", "Medallion"),
            isFeatured = true
        ),
        IslamicPrompt(
            id = "p_calli_2",
            title = "Modern Diwani Calligraphy Monogram",
            category = PromptCategory.ARABIC_CALLIGRAPHY,
            style = LogoStyle.MODERN,
            promptText = "An abstract modern monogram composed of fluid Arabic letters, flowing golden metallic ribbon style, isolated on pitch black, high-end design logo.",
            tags = listOf("Diwani", "Monogram", "Ribbon", "Gold")
        ),

        // Crescent Moon
        IslamicPrompt(
            id = "p_moon_1",
            title = "Celestial Crescent & Star Geometry",
            category = PromptCategory.CRESCENT_MOON,
            style = LogoStyle.GLASS,
            promptText = "A 3D glassmorphism crescent moon featuring an embedded 8-pointed Rub el Hizb Islamic star, translucent frosted emerald glass with golden light reflections.",
            tags = listOf("Crescent", "Star", "Glass", "Geometry")
        ),

        // Islamic Business
        IslamicPrompt(
            id = "p_biz_1",
            title = "Halal Global Trade Crest",
            category = PromptCategory.ISLAMIC_BUSINESS,
            style = LogoStyle.PREMIUM,
            promptText = "A luxury corporate emblem for an Islamic financial group, combining a geometric shield, golden grain wheat, and a subtle crescent, dark green background, professional seal.",
            tags = listOf("Finance", "Trade", "Corporate", "Shield")
        ),

        // Islamic School
        IslamicPrompt(
            id = "p_school_1",
            title = "Academy of Quranic Knowledge",
            category = PromptCategory.ISLAMIC_SCHOOL,
            style = LogoStyle.ELEGANT,
            promptText = "An educational emblem depicting an open Quran on a wooden Rahlah stand, illuminated by a glowing golden lamp of knowledge, enclosed in a geometric wreath.",
            tags = listOf("School", "Academy", "Knowledge", "Quran")
        ),

        // Islamic Foundation
        IslamicPrompt(
            id = "p_found_1",
            title = "Charity Unity Shield",
            category = PromptCategory.ISLAMIC_FOUNDATION,
            style = LogoStyle.FLAT,
            promptText = "A warm, trustworthy foundation logo featuring two hands cradling a crescent moon and a heart, clean flat vector style, vibrant emerald and teal tones.",
            tags = listOf("Charity", "Foundation", "Unity", "Hands")
        ),

        // Islamic Clothing
        IslamicPrompt(
            id = "p_cloth_1",
            title = "Noor Luxury Modest Fashion",
            category = PromptCategory.ISLAMIC_CLOTHING,
            style = LogoStyle.ELEGANT,
            promptText = "An ultra-chic luxury fashion logo mark, minimalist golden needle and thread weaving a graceful Arabic calligraphy curve, dark matte background, high fashion brand.",
            tags = listOf("Fashion", "Clothing", "Modest", "Minimal")
        ),

        // Halal Food & Restaurant
        IslamicPrompt(
            id = "p_food_1",
            title = "Halal Certified Gourmet Seal",
            category = PromptCategory.HALAL_FOOD,
            style = LogoStyle.GOLDEN,
            promptText = "A premium Halal Certified stamp logo, 24k golden round medallion with Arabic 'Halal' typography in the center, encircled by olive leaves, isolated vector.",
            tags = listOf("Halal", "Food", "Certified", "Stamp")
        ),
        IslamicPrompt(
            id = "p_rest_1",
            title = "Sultan Gourmet Dining Monogram",
            category = PromptCategory.ISLAMIC_RESTAURANT,
            style = LogoStyle.LUXURY,
            promptText = "An exclusive Arabian restaurant logo featuring crossed golden knives inside an intricate Arabesque arch, dark mahogany texture, opulent dining emblem.",
            tags = listOf("Restaurant", "Gourmet", "Arabian", "Dining")
        ),

        // Islamic Media & YouTube
        IslamicPrompt(
            id = "p_yt_1",
            title = "Islamic Dawah Media Studio",
            category = PromptCategory.ISLAMIC_YOUTUBE,
            style = LogoStyle.FUTURISTIC,
            promptText = "A modern digital media logo featuring a neon emerald play button integrated with a crescent moon and soundwaves, glowing sci-fi dark style, high engagement thumbnail icon.",
            tags = listOf("YouTube", "Media", "Digital", "Play")
        ),

        // Ramadan & Eid
        IslamicPrompt(
            id = "p_ramadan_1",
            title = "Ramadan Kareem Fanoos Light",
            category = PromptCategory.RAMADAN,
            style = LogoStyle.GLASS,
            promptText = "A glowing glass Fanoos lantern with intricate filigree casting golden warm shadows, crescent moon background, festive luxury Ramadan logo mark.",
            tags = listOf("Ramadan", "Fanoos", "Lantern", "Kareem")
        ),
        IslamicPrompt(
            id = "p_eid_1",
            title = "Eid Mubarak Celebration Crest",
            category = PromptCategory.EID,
            style = LogoStyle.ROYAL,
            promptText = "A festive royal emblem celebrating Eid Mubarak, gold ribbon, 8-pointed starburst, crescent moon and fireworks silhouettes, dark sapphire and gold contrast.",
            tags = listOf("Eid", "Mubarak", "Celebration", "Gold")
        ),

        // Tasbih
        IslamicPrompt(
            id = "p_tasbih_1",
            title = "Circle of Light Prayer Beads",
            category = PromptCategory.TASBIH,
            style = LogoStyle.EMERALD,
            promptText = "A circular chain of glowing emerald and gold prayer beads (Tasbih) forming an infinite loop, centered around an Arabic Allah calligraphy glyph, peaceful spiritual energy.",
            tags = listOf("Tasbih", "Dhikr", "Beads", "Circle")
        ),

        // Minaret & Lantern
        IslamicPrompt(
            id = "p_minaret_1",
            title = "Apex Minaret Geometry",
            category = PromptCategory.MINARET,
            style = LogoStyle.MODERN,
            promptText = "A sharp geometric modern vector of a minaret pinnacle pointing towards a golden crescent, dark theme, sleek corporate logo.",
            tags = listOf("Minaret", "Pinnacle", "Geometric", "Modern")
        ),
        IslamicPrompt(
            id = "p_lantern_1",
            title = "Traditional Filigree Lantern",
            category = PromptCategory.LANTERN,
            style = LogoStyle.VINTAGE,
            promptText = "A vintage hand-drawn brass lantern vector with amber glass panels, intricate geometric patterns, warm ambient illumination, antique seal style.",
            tags = listOf("Lantern", "Filigree", "Vintage", "Brass")
        ),

        // Style Prompts
        IslamicPrompt(
            id = "p_style_gold_1",
            title = "Golden Arabic Monogram Crown",
            category = PromptCategory.GOLDEN_STYLE,
            style = LogoStyle.GOLDEN,
            promptText = "A 24k molten liquid gold monogram logo, shiny metallic reflections, crowned with a subtle crescent, luxury 3D render on AMOLED background.",
            tags = listOf("Gold", "Liquid", "Monogram", "Glossy")
        ),
        IslamicPrompt(
            id = "p_style_green_1",
            title = "Emerald Geometric Mandala",
            category = PromptCategory.GREEN_STYLE,
            style = LogoStyle.EMERALD,
            promptText = "An intricate Islamic geometric mandala pattern in deep emerald green with neon highlight edges, sacred geometry vector logo.",
            tags = listOf("Emerald", "Mandala", "Geometry", "Sacred")
        ),
        IslamicPrompt(
            id = "p_style_bg_1",
            title = "Black & Gold Arabic Monogram",
            category = PromptCategory.BLACK_GOLD,
            style = LogoStyle.LUXURY,
            promptText = "A stark high-contrast black and gold logo, pure obsidian black background with gleaming gold foil typography and thin geometric border lines.",
            tags = listOf("Black Gold", "Obsidian", "Foil", "Contrast")
        ),
        IslamicPrompt(
            id = "p_style_white_1",
            title = "Pristine White Pearl Calligraphy",
            category = PromptCategory.WHITE_ELEGANT,
            style = LogoStyle.ELEGANT,
            promptText = "A clean white pearl calligraphy emblem, soft subtle shadows, silver outline, pristine minimalist luxury aesthetic on soft dark background.",
            tags = listOf("White", "Pearl", "Pristine", "Silver")
        ),
        IslamicPrompt(
            id = "p_style_silver_1",
            title = "Platinum Silver Crescent Shield",
            category = PromptCategory.SILVER_STYLE,
            style = LogoStyle.METALLIC,
            promptText = "A polished platinum silver metallic shield logo featuring an embossed crescent and star, brushed chrome finish, modern sleek emblem.",
            tags = listOf("Silver", "Platinum", "Chrome", "Shield")
        )
    )

    fun getPromptsByCategory(category: PromptCategory): List<IslamicPrompt> {
        return if (category == PromptCategory.ALL) {
            allPrompts
        } else {
            allPrompts.filter { it.category == category }
        }
    }

    fun searchPrompts(query: String): List<IslamicPrompt> {
        if (query.isBlank()) return allPrompts
        val lq = query.lowercase()
        return allPrompts.filter {
            it.title.lowercase().contains(lq) ||
                    it.promptText.lowercase().contains(lq) ||
                    it.category.title.lowercase().contains(lq) ||
                    it.tags.any { tag -> tag.lowercase().contains(lq) }
        }
    }
}

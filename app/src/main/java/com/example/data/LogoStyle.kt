package com.example.data

enum class LogoStyle(
    val id: String,
    val displayName: String,
    val promptModifier: String,
    val sampleColorHex: String
) {
    MODERN("modern", "Modern", "vector graphic style, modern clean lines, sleek geometric icon, minimalist balance, isolated on dark background, 8k", "#00E676"),
    LUXURY("luxury", "Luxury", "ultra luxury 3D metallic gold emblem, dark velvet background, intricate arabesque details, premium rim lighting, 8k render", "#FFD700"),
    PREMIUM("premium", "Premium", "premium corporate logo, gold leaf foil texture, dark emerald satin background, elegant typography, symmetrical, high definition", "#FFC107"),
    ELEGANT("elegant", "Elegant", "refined elegant calligraphy icon, smooth fluid lines, golden ratio geometry, subtle gradient glow, polished finish", "#FFF5C0"),
    ARABIC("arabic", "Arabic", "traditional Arabic calligraphy logo mark, intricate Diwani script, golden ornamental border, authentic cultural aesthetic", "#00E676"),
    MINIMAL("minimal", "Minimal", "ultra minimalist single-line logo icon, negative space visual concept, clean flat geometric silhouette, vector art", "#A0B2A6"),
    GLASS("glass", "Glass", "glassmorphism 3D translucent frosted glass icon, emerald green and gold glowing light reflections, soft drop shadow", "#26A69A"),
    METALLIC("metallic", "Metallic", "polished chrome and gold metal logo badge, 3D metallic bevel, reflection highlights, dark isolated background", "#ECEFF1"),
    GOLDEN("golden", "Golden", "24k molten gold logo emblem, gleaming metallic reflection, golden rays, opulent royal seal finish", "#FFD700"),
    EMERALD("emerald", "Emerald", "deep emerald green gem texture, glowing golden accents, vibrant neon green highlights, sacred geometry pattern", "#00C853"),
    FLAT("flat", "Flat", "flat design vector logo, sharp clean edges, solid bold colors, modern icon graphic, minimalist composition", "#4CAF50"),
    THREE_D("three_d", "3D", "3D volumetric rendered logo icon, smooth ambient occlusion, subtle depth, cinematic studio lighting", "#81C784"),
    FUTURISTIC("futuristic", "Futuristic", "cyberpunk futuristic Islamic logo, glowing emerald neon wireframe lines, dark sci-fi dark background, holographic glow", "#00E5FF"),
    ROYAL("royal", "Royal", "royal coat of arms Islamic emblem, golden crown crest motif, ornate symmetry, noble crest seal", "#FF9800"),
    VINTAGE("vintage", "Vintage", "vintage traditional wax seal logo stamp, distressed bronze golden patina, antique parchment vector art", "#D7CCC8")
}

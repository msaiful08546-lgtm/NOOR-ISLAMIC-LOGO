package com.example.services

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object GeminiApiService {

    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun getApiKey(): String {
        return try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Calls Gemini 3.5 Flash for text transformations (Generate Prompt, Improve Prompt, Rewrite, Translate EN/BN)
     */
    suspend fun generateText(systemInstruction: String, userPrompt: String): Result<String> = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext Result.failure(Exception("Gemini API key is missing or placeholder."))
        }

        try {
            val url = "$BASE_URL/gemini-3.5-flash:generateContent?key=$apiKey"

            val jsonBody = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().put("text", userPrompt))
                        })
                    })
                })
                put("systemInstruction", JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().put("text", systemInstruction))
                    })
                })
            }

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@withContext Result.failure(Exception("API Error ${response.code}: ${response.message}"))
                }
                val bodyStr = response.body?.string() ?: ""
                val jsonResponse = JSONObject(bodyStr)
                val candidates = jsonResponse.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val content = candidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        val text = parts.getJSONObject(0).optString("text", "")
                        return@withContext Result.success(text)
                    }
                }
                Result.failure(Exception("No content returned from Gemini."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * AI Prompt Generator: Transforms a simple phrase (e.g. "Halal Bakery") into a rich Islamic logo prompt.
     */
    suspend fun generateIslamicPrompt(concept: String, styleName: String): String {
        val systemInstruction = "You are an expert Islamic Logo Prompt Architect. Generate 1 highly detailed, creative, professional image generation prompt for an Islamic logo based on the user's concept and requested style. Focus on calligraphy, geometric motifs, gold/emerald accents, 3D renders, vector art, and high resolution. Output ONLY the generated prompt text, no intro or chatter."
        val userPrompt = "Concept: '$concept', Style: '$styleName'. Create a detailed Islamic logo prompt."
        
        val result = generateText(systemInstruction, userPrompt)
        return result.getOrElse {
            "A luxury $styleName Islamic logo for '$concept', featuring gold and emerald arabesque calligraphy, crescent moon emblem, dark background, 8k render."
        }
    }

    /**
     * AI Prompt Improver: Enhances any user prompt with professional lighting and geometric symmetry keywords.
     */
    suspend fun improvePrompt(originalPrompt: String): String {
        val systemInstruction = "You are a senior AI Prompt Improver for Islamic Logos. Take the user's prompt and rewrite it to be 2x richer, adding professional lighting, golden ratio proportions, Diwani/Thuluth calligraphy details, isometric/vector quality, and AMOLED dark background. Output ONLY the improved prompt text."
        val userPrompt = "Improve this Islamic logo prompt: '$originalPrompt'"
        
        val result = generateText(systemInstruction, userPrompt)
        return result.getOrElse {
            "$originalPrompt, embellished with 24k golden leaf accents, intricate Islamic geometry, 3D volumetric lighting, ultra crisp vector definition."
        }
    }

    /**
     * AI Prompt Rewrite: Rewrites prompt into 3 artistic variations.
     */
    suspend fun rewritePromptVariations(originalPrompt: String): List<String> {
        val systemInstruction = "You are a creative Islamic branding designer. Rewrite the following Islamic logo prompt into 3 distinct artistic variations (1. Minimalist Vector, 2. Royal 3D Gold, 3. Traditional Calligraphy). Separate each variation with '---'."
        val userPrompt = "Prompt to vary: '$originalPrompt'"
        
        val result = generateText(systemInstruction, userPrompt)
        val text = result.getOrNull() ?: ""
        return if (text.contains("---")) {
            text.split("---").map { it.trim() }.filter { it.isNotBlank() }
        } else {
            listOf(
                "$originalPrompt, modern minimalist vector style with sharp geometric lines.",
                "$originalPrompt, 3D royal golden embossed seal with glowing emerald aura.",
                "$originalPrompt, traditional Master Diwani Arabic calligraphy medallion."
            )
        }
    }

    /**
     * Prompt Translator: English <-> Bangla translation.
     */
    suspend fun translatePrompt(promptText: String, targetLanguage: String): String {
        val systemInstruction = if (targetLanguage.equals("Bangla", ignoreCase = true) || targetLanguage.equals("bn", ignoreCase = true)) {
            "You are a professional translator between English and Bangla (Bengali). Translate the given Islamic logo prompt into fluent, natural Bangla language. Keep Islamic technical terms intact. Output ONLY the translation."
        } else {
            "You are a professional translator. Translate the given Bangla Islamic prompt into clear, expressive English optimized for AI image generation. Output ONLY the translation."
        }
        
        val result = generateText(systemInstruction, promptText)
        return result.getOrElse { promptText }
    }
}

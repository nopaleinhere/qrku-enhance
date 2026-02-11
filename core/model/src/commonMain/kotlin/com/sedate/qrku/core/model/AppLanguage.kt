package com.sedate.qrku.core.model

enum class AppLanguage(val code: String, val countryCode: String) {
    ENGLISH("en", "US"),
	INDONESIA("id", "ID");

    companion object{
        fun getAppLanguageByCode(languageCode: String): AppLanguage{
            return entries.firstOrNull { it.code == languageCode }?: ENGLISH
        }
    }
}
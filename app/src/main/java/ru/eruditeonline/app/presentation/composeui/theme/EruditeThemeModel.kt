package ru.eruditeonline.app.presentation.composeui.theme

enum class EruditeThemeModel(val title: String, val isDarkSchema: Boolean) {
    STANDARD_LIGHT(title = "Стандартная светлая", isDarkSchema = false),
    STANDARD_DARK(title = "Стандартная темная", isDarkSchema = true),
    AUTUMN_LIGHT(title = "Осенняя светлая", isDarkSchema = false),
    AUTUMN_DARK(title = "Осенняя темная", isDarkSchema = true),
}
package ru.eruditeonline.core.di

import ru.eruditeonline.core.domain.repository.AppInfoRepository

interface CoreDeps {
    fun appInfoRepository(): AppInfoRepository
}
package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.ProfileMapper
import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.data.remote.ApiService
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val profileMapper: ProfileMapper,
) : ProfileRepository {
    override suspend fun getProfile(): Profile {
        return apiService.getProfile()
            .data
            .profile
            ?.let { profileMapper.fromApiToModel(it) }
            ?: throw IllegalStateException("profile null from api")
    }
}

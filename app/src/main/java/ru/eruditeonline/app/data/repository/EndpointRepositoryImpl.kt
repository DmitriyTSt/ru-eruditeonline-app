package ru.eruditeonline.app.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

private const val ENDPOINT_PREFS_STORAGE = "endpoint_prefs"
private const val KEY_ENDPOINT_ADDRESS = "key_endpoint_address"

class EndpointRepositoryImpl @Inject constructor(
    context: Context,
    private val appInfoRepository: AppInfoRepository,
) : EndpointRepository {

    private val pref: SharedPreferences = context.getSharedPreferences(ENDPOINT_PREFS_STORAGE, Context.MODE_PRIVATE)

    override fun provideEndpoint(): String {
        return if (appInfoRepository.isRelease) {
            provideProdEndpoint()
        } else {
            getSavedEndpoint() ?: provideDevEndpoint()
        }
    }

    @SuppressLint("ApplySharedPref")
    override fun updateEndpoint(newEndpoint: String) {
        pref.edit()
            .putString(KEY_ENDPOINT_ADDRESS, newEndpoint)
            .commit()
    }

    private fun getSavedEndpoint(): String? {
        val address = pref.getString(KEY_ENDPOINT_ADDRESS, null)
        return address?.takeIf { it.isNotEmpty() }
    }
}

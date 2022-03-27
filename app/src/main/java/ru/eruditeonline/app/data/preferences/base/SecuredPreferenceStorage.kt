package ru.eruditeonline.app.data.preferences.base

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.KeyPairGeneratorSpec
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
import javax.security.auth.x500.X500Principal
import kotlin.math.abs

private const val PREF_SECURE_FILE_NAME = "secured_preferences_storage"
private const val LOW_API_KEY_ALIAS = "pref_secure_low_api_key_alias"
private const val LOW_API_ALGORITHM = "RSA"
private const val LOW_API_PROVIDER = "AndroidKeyStore"
private const val LOW_API_KEY_LIFETIME_YEARS = 30L

/**
 * Шифрованные префсы
 */
@Singleton
class SecuredPreferenceStorage @Inject constructor(
    context: Context,
) : BasePreferencesStorage(context) {

    override fun createPreferences(): SharedPreferences {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPreferences()
        } else {
            getPreferencesApiLevel21()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getPreferences(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            PREF_SECURE_FILE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getPreferencesApiLevel21(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            PREF_SECURE_FILE_NAME,
            generateLowApiMasterKey(context),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun generateLowApiMasterKey(context: Context): String {
        val start = LocalDate.now()
        val end = start.plusYears(LOW_API_KEY_LIFETIME_YEARS)

        val spec = KeyPairGeneratorSpec.Builder(context)
            .setAlias(LOW_API_KEY_ALIAS)
            .setSubject(X500Principal("CN=$LOW_API_KEY_ALIAS"))
            .setSerialNumber(
                BigInteger.valueOf(
                    abs(LOW_API_KEY_ALIAS.hashCode()).toLong()
                )
            )
            .setStartDate(Date(start.toMillis()))
            .setEndDate(Date(end.toMillis()))
            .build()

        val generator = KeyPairGenerator.getInstance(
            LOW_API_ALGORITHM,
            LOW_API_PROVIDER
        )
        generator.initialize(spec)
        return generator.generateKeyPair().public.toString()
    }

    private fun LocalDate.toMillis(): Long {
        return this.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    }
}

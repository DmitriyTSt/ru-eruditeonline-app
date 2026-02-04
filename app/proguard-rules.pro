#Okhttp
-dontwarn okhttp3.**
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn javax.lang.model.element.Modifier

##---------------Begin: proguard configuration for kotlinx-serialization  ----------
# Keep serialization annotations
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# Keep @Serializable classes
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class * {
    *** Companion;
}

# Keep serializers
-keepclassmembers class * implements kotlinx.serialization.KSerializer {
    *** INSTANCE;
}

# Preserves all members of Enum classes
-keep enum * { *; }

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
    @kotlinx.serialization.SerialName <fields>;
}

##---------------End: proguard configuration for kotlinx-serialization  ----------

# Navigation
-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable
-keep class androidx.fragment.app.FragmentContainerView
-keepnames class androidx.navigation.fragment.NavHostFragment

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Crashlytics
-keepattributes SourceFile, LineNumberTable       # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.
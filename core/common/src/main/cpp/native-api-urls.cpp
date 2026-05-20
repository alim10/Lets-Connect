#include <jni.h>
#include <string>


extern "C" {
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getBaseUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string baseUrl = "http://213.136.192.12/";
    return env->NewStringUTF(baseUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getBannerUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string bannerUrl = "api/utilities/bannerList";
    return env->NewStringUTF(bannerUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getLoginOtpUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string loginOtpUrl = "api/signin/OtpSignIn";
    return env->NewStringUTF(loginOtpUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getLoginUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string loginUrl = "api/signin";
    return env->NewStringUTF(loginUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getSeriesUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string seriesUrl = "api/signin/OtpSignIn";
    return env->NewStringUTF(seriesUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getAllMatchUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string allMatchUrl = "api/signin/OtpSignIn";
    return env->NewStringUTF(allMatchUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getRoundsUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string roundsUrl = "api/signin/OtpSignIn";
    return env->NewStringUTF(roundsUrl.c_str());
}
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getWinnersUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string winnersUrl = "api/signin/OtpSignIn";
    return env->NewStringUTF(winnersUrl.c_str());
}

// NativeApiImpl methods
JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_NativeApiImpl_getLoginUrl(
        JNIEnv *env,
        jobject /* this */) {
    return Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getLoginUrl(env, nullptr);
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_NativeApiImpl_getWinnersUrl(
        JNIEnv *env,
        jobject /* this */) {
    return Java_org_alimapps_letsconnect_core_common_nativeLib_ApiUrls_getWinnersUrl(env, nullptr);
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_NativeApiImpl_getNativeMessage(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("Hello from Native!");
}
}
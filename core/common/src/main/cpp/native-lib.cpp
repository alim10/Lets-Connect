#include <jni.h>
#include <string>


extern "C" {

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_baseUrl(
        JNIEnv *env,
        jobject /* this */) {
    std::string baseUrl = "http://213.136.192.12/";
    return env->NewStringUTF(baseUrl.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_apiKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string apiKey = "";
    return env->NewStringUTF(apiKey.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_xApplicationHeader(
        JNIEnv *env,
        jobject /* this */) {
    std::string xApplicationHeader = "";
    return env->NewStringUTF(xApplicationHeader.c_str());
}


JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_xApplicationClientHeader(
        JNIEnv *env,
        jobject /* this */) {
    std::string xApplicationClientHeader = "H7LXJK2495";
    return env->NewStringUTF(xApplicationClientHeader.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_encryptionKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string encryptionKey = "";
    return env->NewStringUTF(encryptionKey.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_certificatePinning(
        JNIEnv *env,
        jobject /* this */) {
    std::string certificatePinning = "s";
    return env->NewStringUTF(certificatePinning.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_databaseEncryptionKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string databaseEncryptionKey = "";
    return env->NewStringUTF(databaseEncryptionKey.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_firebaseApiKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string firebaseApiKey = "";
    return env->NewStringUTF(firebaseApiKey.c_str());
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_encryptionIVKey(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("");
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_userAgentKey(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("");
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_telehealthSecretKey(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("");
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_amplitudeApiKey(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("");
}

JNIEXPORT jstring
JNICALL
Java_org_alimapps_letsconnect_core_common_nativeLib_Secrets_certificatePinner(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF("");
}
}
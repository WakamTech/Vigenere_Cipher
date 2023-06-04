#include <jni.h>
#include <iostream>
#include <string>
#include <cctype>

std::string encryptAlphaNumeric(const std::string& message, const std::string& key) {
    std::string encrypted;
    int keyIndex = 0;
    int keyLength = key.length();

    for (char c : message) {
        if (std::isalnum(c)) {
            char base = std::isupper(c) ? 'A' : std::islower(c) ? 'a' : '0';
            char shift = std::toupper(key[keyIndex]) - 'A';

            char encryptedChar = ((c - base + shift) % 26) + base;
            encrypted += encryptedChar;

            keyIndex = (keyIndex + 1) % keyLength;
        } else {
            encrypted += c;
        }
    }

    return encrypted;
}

std::string decryptAlphaNumeric(const std::string& encrypted, const std::string& key) {
    std::string decrypted;
    int keyIndex = 0;
    int keyLength = key.length();

    for (char c : encrypted) {
        if (std::isalnum(c)) {
            char base = std::isupper(c) ? 'A' : std::islower(c) ? 'a' : '0';
            char shift = std::toupper(key[keyIndex]) - 'A';

            char decryptedChar = ((c - base - shift + 26) % 26) + base;
            decrypted += decryptedChar;

            keyIndex = (keyIndex + 1) % keyLength;
        } else {
            decrypted += c;
        }
    }

    return decrypted;
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_vigenerecipher2_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Welcome to the new Vigenere Cipher Algoritm";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_vigenerecipher2_vigenere_encryptMessage(JNIEnv *env, jobject thiz, jstring message,
                                                         jstring key) {
    // TODO: implement encryptMessage()
    const char* nativeMess = env->GetStringUTFChars(message, nullptr);
    const char* nativeKey = env->GetStringUTFChars(key, nullptr);
    std::string c_key(nativeKey);
    std::string c_mess(nativeMess);
    env->ReleaseStringUTFChars(message, nativeMess);
    env->ReleaseStringUTFChars(key, nativeKey);
    std::string encrypted = encryptAlphaNumeric(c_mess, c_key);
    return env->NewStringUTF(encrypted.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_vigenerecipher2_vigenere_dencryptMessage(JNIEnv *env, jobject thiz,
                                                          jstring message, jstring key) {
    // TODO: implement dencryptMessage()
    const char* nativeMess = env->GetStringUTFChars(message, nullptr);
    const char* nativeKey = env->GetStringUTFChars(key, nullptr);
    std::string c_key(nativeKey);
    std::string c_mess(nativeMess);
    env->ReleaseStringUTFChars(message, nativeMess);
    env->ReleaseStringUTFChars(key, nativeKey);
    std::string encrypted = decryptAlphaNumeric(c_mess, c_key);
    return env->NewStringUTF(encrypted.c_str());
}
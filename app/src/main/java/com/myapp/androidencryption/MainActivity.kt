package com.myapp.androidencryption

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import androidx.annotation.RequiresApi
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class MainActivity : AppCompatActivity() {

    companion object {
        val TRANSFORMATION = "AES/GCM/NoPadding"
        val ANDROID_KEY_STORE = "AndroidKeyStore"
        val SAMPLE_ALIAS = "MYALIAS"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testSymmetric()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun testSymmetric() {
        val originalText = "I love apples"
        val encryptedString = Symmetric.encrypt(originalText)
        val decryptedString = Symmetric.decrypt(encryptedString!!)


        Log.d("Task", "Original Text: $originalText")
        Log.d("Task", "encrypt Text: $encryptedString")
        Log.d("Task", "decrypt Text: $decryptedString")

        val decryptedString3 = Symmetric.decrypt("nblJ+rM0zCIeTBATcwXdoIM/pXntY85pdb+Oosq2DdMOGmSqatpGQaIzs4jExEdq")
        Log.d("Task", "decrypt Text: $decryptedString3")
    }
}
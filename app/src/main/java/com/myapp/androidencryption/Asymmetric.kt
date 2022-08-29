package com.myapp.androidencryption

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.IOException
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

/**
 * Created by user on 29.08.2022.
 * Android encryption
 * eldorturgunov777@gmail.com
 */
class Asymmetric {

     var privateKey: PrivateKey
     var publicKey: PublicKey

    init {
        val keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(1024)
        val pair = keyGen.genKeyPair()
        privateKey = pair.private
        publicKey = pair.public
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(Exception::class)
        fun encryptMessage(plainText: String, publicKey: String): String {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey(publicKey))
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.toByteArray()))
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(Exception::class)
        fun decryptMessage(encryptText: String, privateKey: String): String {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey(privateKey))
            return String(cipher.doFinal(Base64.getDecoder().decode(encryptText)))
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(GeneralSecurityException::class,IOException::class)
        private fun loadPrivateKey(key64: String): PrivateKey {
            val clear = Base64.getDecoder().decode(key64.toByteArray())
            val keySpec = PKCS8EncodedKeySpec(clear)
            val fact = KeyFactory.getInstance("RSA")
            val priv = fact.generatePrivate(keySpec)
            Arrays.fill(clear, 0.toByte())
            return priv
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(GeneralSecurityException::class,IOException::class)
        private fun loadPublicKey(store: String): Key {
            val data = Base64.getDecoder().decode(store.toByteArray())
            val spec = X509EncodedKeySpec(data)
            val fact = KeyFactory.getInstance("RSA")
            return fact.generatePublic(spec)
        }
    }

}
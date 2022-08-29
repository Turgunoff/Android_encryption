package com.myapp.androidencryption

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.myapp.androidencryption.Asymmetric.Companion.decryptMessage
import com.myapp.androidencryption.Asymmetric.Companion.encryptMessage
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testAsymmetric()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun testSymmetric() {
        val originalText = "I love apples"
        val encryptedString = Symmetric.encrypt(originalText)
        val decryptedString = Symmetric.decrypt(encryptedString!!)

        Log.d("Task", "Original Text: $originalText")
        Log.d("Task", "encrypt Text: $encryptedString")
        Log.d("Task", "decrypt Text: $decryptedString")

        val decryptedString3 =
            Symmetric.decrypt("nblJ+rM0zCIeTBATcwXdoIM/pXntY85pdb+Oosq2DdMOGmSqatpGQaIzs4jExEdq")
        Log.d("Task", "decrypt Text: $decryptedString3")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun testAsymmetric() {

        val encryptedString =
            "JD9yPcl4tgrQULf1HfKcFW4zT4w0Isb/Dg38h+7gCZNFrSmDxlNAePHQDFqzbax9zMwD1dqiA6rxnWdO1I3NLwjYMbbocYiZkLMH5xbKt4jODyTmJylUNBBacPrLzV8yEBff7C+Cm/veMJEYBe8Qafzr0zeWxv1W4DTJ1yDKnWc="
        val privateKeyStr =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKs164xhodihpp2c8K0zGNAZqLFP3yqEw1VkXeilyz0TdGWFyE+WvhRfGNwbJMxA3h5P+UyVFzMAI5PDcJjyUTuWBKzGFkakVdcoMdNxW29XYmai78vaSfJ9jsWyakuV7HHJP/2clJNITCmhH2Pv/oUw5kNsEojRIr/PkAhymPx/AgMBAAECgYAPwt56JWYXVxugsy5q22MuNnZ+SuU3TlKHHWlnE0Ay/AQBRpVqp5dnBlwiWHNaREx5C5Feg0kdSPwoIbFPZdtOvda23C8WiGO9caQ92fNJo+z1jwCWFAM02UHg3rsoE/gHFmErwnvpLzmR4aB9O7XLOs0lcfTxhcs2RORbpuZFmQJBAPIaiwx2V3PuV0rMXeqmwDIQviw2/dXZD55MVJ5knxGKQ4ZI8b+S2zStZNdFjCv/7IJsz4GsGLV9VDiTbViQNCkCQQC1Ca278lI70GRCvNAeID7nXvYREANzBzcGCjiIZB8p0mPZptYv6zytpSEHDPVPpP8Z/t6/jGbBe2yIdJZ+dABnAkApakb9E8V0DIKcRgeO24WnStvtXl7f1Sjp0G7tRZM8geV5NEM+nVWoVj0Y4knPQRFpYknBCfqUGWNt5jJfYxO5AkA8W5+6282i95WV0pGomaOfTD/J7IbZ4PvtXZb/kjH5pZV4yXD7xPgUBMwAJ8fZ5+7NYfYt8seHlnjW/df3DQHdAkEA4UGfrC/e9n8eeSzkFAy3wAedLSFypqkr134KeAUVknxAPc6u89JmIM4fkIH0BtMyqZWGxd3zgxYarlFl05ltjg=="

        val secretText = "How are you?"
        val keyPairGenerator = Asymmetric()
//        val privateKey = Base64.getEncoder().encodeToString(keyPairGenerator.privateKey.encoded)
        val privateKey = privateKeyStr
        val publicKey = Base64.getEncoder().encodeToString(keyPairGenerator.publicKey.encoded)
//        Log.d("MainActivity", "privateKey Text: $privateKey")
//        Log.d("MainActivity", "publicKey Text: $publicKey")

        val encryptText = encryptMessage(secretText, publicKey)
        Log.d("MainActivity", "encryptValue Text: $encryptText")

        val decryptText = decryptMessage(encryptedString, privateKey)
        Log.d("MainActivity", "decryptText Text: $decryptText")

    }
}
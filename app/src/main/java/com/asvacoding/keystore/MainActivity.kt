package com.asvacoding.keystore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Base64
import android.util.Log
import com.asvacoding.keystore.security.DeCryptor
import com.asvacoding.keystore.security.EnCryptor
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.security.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.security.cert.CertificateException


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val SAMPLE_ALIAS = "MYALIAS"

    private var encryptor: EnCryptor? = null
    private var decryptor: DeCryptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        encryptor = EnCryptor()

        try {
            decryptor = DeCryptor()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }


        btnEncrypt.setOnClickListener {
            encryptText()
        }

        btnDecrypt.setOnClickListener {
            decryptText()
        }
    }

    private fun decryptText() {
        try {
            tvResult.text = decryptor?.decryptData(SAMPLE_ALIAS, encryptor?.encryption, encryptor?.iv)
        } catch (e: UnrecoverableEntryException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e("Main", "decryptData() called with: " + e.message, e)
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }

    }

    private fun encryptText() {

        try {
            val encryptedText = encryptor?.encryptText(SAMPLE_ALIAS, "Encrypt Me")
            tvResult.text = Base64.encodeToString(encryptedText, Base64.DEFAULT)
        } catch (e: UnrecoverableEntryException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e("Main", "onClick() called with: " + e.message, e)
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }

    }
}

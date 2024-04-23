package com.example.solutionx.common.data.repository.local.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EncryptionProviderTest{
    private val encryptionProvider = EncryptionProvider()

    @Test
    fun testEncryptAndDecryptData() {
        val originalData = "Hello, world!".toByteArray()
        val encryptedData = encryptionProvider.encryptData(originalData)
        val decryptedData = encryptionProvider.decryptData(encryptedData)
        assertArrayEquals(originalData, decryptedData)
    }

    @Test
    fun testGetSecretKey() {
        val secretKey = encryptionProvider.getKey()
        Assert.assertNotNull(secretKey)
    }
}
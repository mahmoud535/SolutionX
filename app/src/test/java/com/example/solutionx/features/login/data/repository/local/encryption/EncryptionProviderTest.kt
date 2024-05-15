package com.example.solutionx.features.login.data.repository.local.encryption

import com.example.solutionx.common.data.repository.local.encryption.EncryptionProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.security.KeyStore
import javax.crypto.SecretKey
import io.mockk.verify

class EncryptionProviderTest{
//    private val encryptionProvider = EncryptionProvider()
//
//    @Test
//    fun testEncryptAndDecryptData() {
//        val originalData = "Hello, world!".toByteArray()
//        val encryptedData = encryptionProvider.encryptData(originalData)
//        val decryptedData = encryptionProvider.decryptData(encryptedData)
//        assertArrayEquals(originalData, decryptedData)
//    }
//
//    @Test
//    fun testGetSecretKey() {
//        val secretKey = encryptionProvider.getKey()
//        Assert.assertNotNull(secretKey)
//    }



    private lateinit var encryptionProvider: EncryptionProvider
    private lateinit var keyStore: KeyStore

    @Before
    fun setUp() {
        keyStore = mockk<KeyStore>()
        encryptionProvider = EncryptionProvider()
    }

    @Test
    fun encryptData_decryptData() {
        val data = "Hello, World!".toByteArray()

        // Mock the KeyStore
        val secretKeyEntry = mockk<KeyStore.SecretKeyEntry>()
        every { keyStore.getEntry("secret_key", null) } returns secretKeyEntry

        // Encrypt the data
        val encryptedData = encryptionProvider.encryptData(data)

        // Decrypt the data
        val decryptedData = encryptionProvider.decryptData(encryptedData)

        // The decrypted data should be the same as the original data
        assertArrayEquals(data, decryptedData)

        // Verify that the KeyStore was used
        verify { keyStore.getEntry("secret_key", null) }
    }

}
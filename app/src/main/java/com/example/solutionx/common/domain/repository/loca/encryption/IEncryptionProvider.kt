package com.example.solutionx.common.domain.repository.loca.encryption

interface IEncryptionProvider {
    fun encryptData(bytes: ByteArray): ByteArray
    fun decryptData(bytes: ByteArray): ByteArray?
}
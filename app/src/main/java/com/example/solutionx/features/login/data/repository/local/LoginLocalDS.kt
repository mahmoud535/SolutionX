package com.example.solutionx.features.login.data.repository.local

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.solutionx.android.helpers.logging.getClassLogger
import com.example.solutionx.common.data.repository.local.localds.StorageKeyEnum
import com.example.solutionx.common.domain.repository.loca.encryption.IEncryptionProvider
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.google.gson.Gson
//import okhttp3.internal.http2.Http2Reader.Companion.logger
//import okhttp3.internal.concurrent.TaskRunner.Companion.logger
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
internal class LoginLocalDS (private val storageKV: ILocalDSProvider,private val encryptionProvider: IEncryptionProvider): ILoginLocalDS {

    override suspend fun saveUser(user: UserEntity) {
        val userJson = Gson().toJson(user)
        val bytesUser = userJson.toByteArray()
        val encryptUserData = Base64.getEncoder()
            .encodeToString(encryptionProvider.encryptData(bytes = bytesUser))
        logUserInfoAfterEncryption(userJson, encryptUserData)
        storageKV.save(StorageKeyEnum.USER, Gson().toJson(encryptUserData))
    }

    override suspend fun saveAccessToken(token: String) {
        val bytes = token.toByteArray()
        val encryptData = Base64.getEncoder()
            .encodeToString(encryptionProvider.encryptData(bytes = bytes))
        logAccessTokenAfterEncryption(token, encryptData)
        storageKV.save(StorageKeyEnum.ACCESS_TOKEN, encryptData)
    }

    override suspend fun getUser():UserEntity {
        val userJson = storageKV.get(StorageKeyEnum.USER, "")
        val decryptedBytes =
            encryptionProvider.decryptData(Base64.getDecoder().decode(userJson))?.decodeToString()
        val result = Gson().fromJson(decryptedBytes, UserEntity::class.java)
        logUserInfoAfterDecryption(userJson,result)
        return result
    }

    private fun logUserInfoAfterEncryption( userJson: String,encryptUserData: String){
        logger.warning("the userInfo is --> $userJson ")
        logger.warning("the User Info after encryption --> $encryptUserData ")
    }

    private fun logAccessTokenAfterEncryption( token: String,encryptData: String){
        logger.warning("the Token is --> $token ")
        logger.warning("the token after encryption --> $encryptData ")
    }

    private fun logUserInfoAfterDecryption( userJson: String,result: UserEntity){
        logger.warning("userInfo encryption $userJson ")
        logger.warning("userInfo after decryption  $result ")
    }

    companion object {
        private val logger = getClassLogger()
    }
}
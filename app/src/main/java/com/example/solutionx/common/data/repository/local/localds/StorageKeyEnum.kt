package com.example.solutionx.common.data.repository.local.localds

import com.example.solutionx.common.domain.repository.loca.localds.IStorageKeyEnum


enum class StorageKeyEnum(override val keyValue: String) : IStorageKeyEnum {
    ACCESS_TOKEN("accessToken"),
    USER("user")
}
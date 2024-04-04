package com.example.solutionx.common.data.repository.local

import com.example.solutionx.common.domain.repository.loca.IStorageKeyEnum


enum class StorageKeyEnum(override val keyValue: String) : IStorageKeyEnum {
    ACCESS_TOKEN("accessToken"),
    USER("user")
}
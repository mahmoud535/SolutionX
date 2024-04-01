package com.example.solutionx.features.services.presentation.productflavors.helpers.logging.writers

import com.example.solutionx.features.services.presentation.productflavors.helpers.logging.LogWriter


class DummyWriter : LogWriter {
    override val isDebugEnabled: Boolean
        get() = false

    override fun debug(clazz: Class<*>, message: String?) {}
    override fun info(clazz: Class<*>, message: String?) {
    }

    override fun warning(clazz: Class<*>, message: String?) {
    }

    override fun error(clazz: Class<*>, message: String?, throwable: Throwable?) {
    }
}
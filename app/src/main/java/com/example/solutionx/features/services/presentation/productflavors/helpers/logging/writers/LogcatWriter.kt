package am.leon.solutionx.android.helpers.logging.writers

import android.util.Log
import com.example.solutionx.features.services.presentation.productflavors.helpers.logging.LogWriter

class LogcatWriter(private val tagKey: String, override val isDebugEnabled: Boolean) :
    LogWriter {

    override fun debug(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.d(tagKey, formattedMessage)
        }
    }

    override fun info(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.i(tagKey, formattedMessage)
        }
    }

    override fun warning(clazz: Class<*>, message: String?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message)
            Log.w(tagKey, formattedMessage)
        }
    }

    override fun error(clazz: Class<*>, message: String?, throwable: Throwable?) {
        if (isDebugEnabled) {
            val formattedMessage = getFormattedMessage(clazz, message, throwable)
            Log.e(tagKey, formattedMessage)
        }
    }

    private fun getFormattedMessage(
        clazz: Class<*>, message: String?, throwable: Throwable? = null
    ): String {
        return if (throwable == null)
            String.format("[%s] %s", clazz.simpleName, message)
        else
            String.format("[%s] %s %s", clazz.simpleName, message, throwable.toString())
    }
}
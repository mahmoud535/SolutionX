package com.example.solutionx.common.presentation.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
fun <B : ViewBinding> LifecycleOwner.bindView(container: ViewGroup? = null): B {
    return if (this is Activity) {
        val inflateMethod = getClass<B>().getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.javaPrimitiveType
        )
        val invokeLayout = inflateMethod.invoke(null, layoutInflater, container, false) as B
        this.setContentView(invokeLayout.root)
        invokeLayout
    } else {
        val fragment = this as Fragment
        val inflateMethod = getClass<B>().getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.javaPrimitiveType
        )
        val invoke = inflateMethod.invoke(null, fragment.layoutInflater, container, false) as B
        invoke
    }
}


@Suppress("UNCHECKED_CAST")
private fun <T : Any> Any.getClass(): Class<T> {
    return this::class.java as Class<T>
}
package com.example.solutionx.common.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.example.solutionx.R
import com.example.solutionx.common.data.model.exception.LeonException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflate: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding ?: throw IllegalStateException("ViewBinding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflate.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActions()
        setUpObservers()
        setUpListeners()
        setUpRecyclerView()
        swipeRefreshLayout =
            requireView().findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        swipeRefreshLayout?.setOnRefreshListener {
            onRefresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        swipeRefreshLayout = null
    }

    abstract fun setUpActions()
    abstract fun setUpObservers()
    abstract fun setUpListeners()
    abstract fun setUpRecyclerView()


    fun showAlertDialog(@StringRes titleRes: Int, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(titleRes)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }

    fun showErrorSnackBar( @StringRes messageRes: Int, errorMessage: Boolean) {
        val message = getString(messageRes)
        val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSnackBarError
                )
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }


    private lateinit var mProgressDialog: Dialog
    fun showProgressBar(text: String) {
        mProgressDialog = Dialog(requireActivity())
        mProgressDialog.setContentView(R.layout.dialog_progress)
        // Find tv_progress_text within the Dialog's layout
        val tvProgressText = mProgressDialog.findViewById<TextView>(R.id.tv_progress_text)
        tvProgressText.text = text
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //start the dialog and display it on screen.
        mProgressDialog.show()
    }

    fun hideProgressBar() {
        mProgressDialog.dismiss()
    }

    private var doubleBackToExitPressedOnce = false
    fun doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            requireActivity().onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true

        Toast.makeText(
            requireContext(),
            resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT
        ).show()

        @Suppress("DEPRECATION")
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    // Abstract function to handle swipe-to-refresh action
    abstract fun onRefresh()

    // Function to enable/disable swipe-to-refresh
    protected fun setSwipeRefreshEnabled(enabled: Boolean) {
        swipeRefreshLayout?.isEnabled = enabled
    }

    // Function to show/hide the refresh indicator
    protected fun setRefreshing(refreshing: Boolean) {
        swipeRefreshLayout?.isRefreshing = refreshing
    }


    protected open fun handleException(exception: LeonException) {
        // Handle exceptions here
        when (exception) {
            is LeonException.Network -> {
                // Handle network exceptions
                when (exception) {
                    is LeonException.Network.Retrial -> {
                        showAlertDialog(exception.messageRes, " ")
                    }

                    is LeonException.Network.Unhandled -> {
                        showAlertDialog(exception.messageRes, " ")
                    }
                }
            }

            is LeonException.Client -> {

            }

            is LeonException.Server -> {
                if (exception is LeonException.Server.InternalServerError) {
                    // Handle internal server errors
                } else if (exception is retrofit2.HttpException) {
                    val httpErrorCode = exception.code()
                    when (httpErrorCode) {
                        404 -> {
                            showAlertDialog(R.string.not_found, "Resource not found")
                        }

                        401 -> {
                            showAlertDialog(R.string.unauthorized, "Unauthorized access")
                        }

                        else -> {

                        }
                    }
                }
            }

            is LeonException.Local -> {
            }

            is LeonException.Unknown -> {
                showAlertDialog(R.string.unknown_error, "Unknown error occurred")
            }

            is LeonException.Client.ResponseValidation -> {}

        }
    }

}
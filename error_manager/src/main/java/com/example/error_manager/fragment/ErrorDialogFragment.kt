package com.example.error_manager.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.common.ext.lazyNone
import com.example.common.ext.newInstance
import com.example.error_manager.R

class ErrorDialogFragment : DialogFragment() {

    companion object {

        const val TAG = "common_error_dialog"

        private const val ARG_MESSAGE = "arg_message"

        fun newInstance(message: String?): ErrorDialogFragment {
            return newInstance {
                putString(ARG_MESSAGE, message)
            }
        }
    }

    private val message: String? by lazyNone { requireArguments().getString(ARG_MESSAGE) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.error_dialog_title_fatal)
            .setMessage(message)
            .setPositiveButton(R.string.error_dialog_close) { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }
}
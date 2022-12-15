package com.github.zuev98.weatherapp.presentation.ui.current.dialogs

import android.content.Context
import android.widget.EditText
import com.github.zuev98.weatherapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogManager {

    fun searchByCityDialog(context: Context, onOkay: (String) -> Unit) {
        val editText = EditText(context)
        editText.hint = context.getString(R.string.enter_city_hint)

        MaterialAlertDialogBuilder(context)
            .setView(editText)
            .setTitle(R.string.city_name)
            .setPositiveButton(R.string.alert_confirm) { _, _ ->
                onOkay(editText.text.toString())
            }
            .setNeutralButton(R.string.alert_cancel, null)
            .create()
            .show()
    }

    fun showErrorDialog(
        context: Context,
        title: String,
        message: String,
        buttonText: String,
        onOkay: (() -> Unit)?,
        onCancel: (() -> Unit)?
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(buttonText) { _, _ ->
                onOkay?.invoke()
            }
            .setNeutralButton(R.string.alert_cancel) { _, _ ->
                onCancel?.invoke()
            }
            .create()
            .show()
    }
}
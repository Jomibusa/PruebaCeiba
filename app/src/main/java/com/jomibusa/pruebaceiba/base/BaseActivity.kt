package com.jomibusa.pruebaceiba.base

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jomibusa.pruebaceiba.R
import com.jomibusa.pruebaceiba.util.Util

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    open fun showMessageDialog(message: Int, positiveListener: (() -> Unit)? = null) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(
                Util.createSpannableString(
                    this,
                    R.string.text_general_title_dialog,
                    R.color.purple_500,
                    true
                )
            )
            .setMessage(getString(message))
            .setPositiveButton(android.R.string.ok) { view, _ ->
                positiveListener?.invoke()
                view.dismiss()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    open fun showMessageDialogComplete(message: Int, positiveListener: (() -> Unit)? = null) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(
                Util.createSpannableString(
                    this,
                    R.string.text_general_title_dialog,
                    R.color.purple_500,
                    true
                )
            )
            .setMessage(getString(message))
            .setPositiveButton(android.R.string.ok) { view, _ ->
                positiveListener?.invoke()
                view.dismiss()
            }.setNegativeButton(android.R.string.cancel) { view, _ ->
                view.dismiss()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

}

package com.bri.wealthmanager.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import com.and.base.ui.BaseActivity
import com.bri.wealthmanager.R

abstract class AppActivity : BaseActivity() {

    override fun createProgress(): Dialog {
        return AppCompatDialog(mContext, R.style.progress).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            lp.gravity = Gravity.CENTER
            window?.attributes = lp
            setContentView(layoutInflater.inflate(R.layout.loader, null, false))
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }
    }
}
package com.bri.wealthmanager.ui.category

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class AddCategoryContract : ActivityResultContract<Any?, Boolean>() {
    override fun createIntent(context: Context, input: Any?): Intent {
        return Intent(context, CategoryActivity::class.java)
    }

    // todo
    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return true
    }
}
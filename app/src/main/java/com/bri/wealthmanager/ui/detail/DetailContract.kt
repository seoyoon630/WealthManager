package com.bri.wealthmanager.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class DetailContract : ActivityResultContract<Int?, Boolean>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, DetailActivity::class.java).putExtra(DetailActivity.EXTRA.ID, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return resultCode == Activity.RESULT_OK
    }
}
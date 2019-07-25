package com.feigege.helptools

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.PermissionUtils

class Test : AppCompatActivity() {
    fun Test() {
        if (PermissionUtils.isGranted(*PERMISSIONS_STORAGE)) {

        }
    }

    companion object {

        private val PERMISSIONS_STORAGE =
            arrayOf("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")
    }
}

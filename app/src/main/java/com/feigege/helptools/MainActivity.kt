package com.feigege.helptools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.feigege.myhelptools.utils.image.ImageLoaderUtils

class MainActivity : AppCompatActivity() {

    var image: ImageView? = null
    var image1: ImageView? = null
    var save: Button? = null

    private val PERMISSIONS_STORAGES =
        arrayOf("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")

    private var imageUrl: String =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564051784350&di=75b1b6a01188f9522ed5c4bcfc0ca6be&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image = findViewById(R.id.image)
        image1 = findViewById(R.id.image1)
        save = findViewById(R.id.save)
        ImageLoaderUtils.loadImage(this, imageUrl, image)
        ImageLoaderUtils.loadRound(this, imageUrl, image1)
        save!!.setOnClickListener {
            if (PermissionUtils.isGranted(*PERMISSIONS_STORAGES)) {
                saveViewImage()
            } else {
                PermissionUtils.permission(*PERMISSIONS_STORAGES).callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        saveViewImage()
                    }

                    override fun onDenied() {
                        ToastUtils.showShort("您拒绝了授权,需要授权请前往设置")
                    }

                }).request()
            }
        }
    }

    fun saveViewImage() {
        if (ImageLoaderUtils.saveViewToSystem(this@MainActivity, image)) {
            ToastUtils.showShort("保存成功")
        } else {
            ToastUtils.showShort("保存失败")
        }
    }
}

package com.feigege.myhelptools.utils.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 网络图片加载
 */
public class ImageLoaderUtils {

    /**
     * 加载本地图片
     *
     * @param context   上下文
     * @param file      本地路径
     * @param imageView 图片控件
     */
    public static void loadSDKImage(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).into(imageView);
    }

    /**
     * 加载网络图片
     *
     * @param context   上下文
     * @param imageUri  图片网络路径
     * @param imageView 图片控件
     */
    public static void loadImage(Context context, String imageUri, ImageView imageView) {
        Glide.with(context).load(imageUri).into(imageView);
    }

    /**
     * 加载网络图片，并裁剪为圆形
     *
     * @param context   上下文
     * @param imageUri  图片网络路径
     * @param imageView 图片控件
     */
    public static void loadRound(Context context, String imageUri, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform());
        Glide.with(context).load(imageUri).apply(options).into(imageView);
    }

    /**
     * 将View保存到本地
     *
     * @param view    视图
     * @param context 上下文
     * @return
     */
    public static boolean saveViewToSystem(Context context, View view) {
        Bitmap bitmap = ImageUtils.view2Bitmap(view);
        boolean isSuccess = saveBitmapToSystem(context, bitmap);
        return isSuccess;
    }

    /**
     * 保存图片到相册
     *
     * @param bitmap  保存的图片
     * @param context 上下文
     * @return
     */
    public static boolean saveBitmapToSystem(Context context, Bitmap bitmap) {
        String storPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        File appDir = new File(storPath);
        if (!appDir.exists()) ;
        appDir.mkdir();
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

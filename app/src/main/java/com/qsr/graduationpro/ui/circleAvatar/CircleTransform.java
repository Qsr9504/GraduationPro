package com.qsr.graduationpro.ui.circleAvatar;

import android.graphics.Bitmap;

import com.qsr.graduationpro.utils.BitmapUtil;
import com.qsr.graduationpro.utils.UIUtil;
import com.squareup.picasso.Transformation;

/**************************************
 * FileName : com.qsr.graduationpro.ui.circleAvatar
 * Author : qsr
 * Time : 2017/5/4 23:43
 * Description :
 **************************************/
public class CircleTransform implements Transformation {
    /**
     * @param source :还未处理的矩形的Bitmap对象
     * @return ：返回的是处理后的圆形Bitmap对象
     */
    /**
     * @param source :还未处理的矩形的Bitmap对象
     * @return ：返回的是处理后的圆形Bitmap对象
     */
    @Override
    public Bitmap transform(Bitmap source) {
        //1.压缩处理
        Bitmap zoomBitmp = BitmapUtil.zoom(source, UIUtil.dpToPx(62), UIUtil.dpToPx(62));
        //2.圆形处理
        Bitmap bitmap = BitmapUtil.circleBitmap(zoomBitmp);
        //必须要回收source，否则会报错
        source.recycle();
        return bitmap;//返回圆形的Bitmap对象
    }

    /**
     * 该方法没有什么实际意义，但是要保证其返回的值不能为null！
     * @return
     */
    @Override
    public String key() {
        return "";
    }
}

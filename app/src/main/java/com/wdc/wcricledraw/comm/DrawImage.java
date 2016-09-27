package com.wdc.wcricledraw.comm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by WN on 2015/10/21.
 */
public class DrawImage {


    /**
     * 画头像 （圆形）
     * @param context
     * @param dpwidth 头像在xml中显示宽高（正方形）
     * @param text 图片上显示的文字
     * @return
     */
    public static Bitmap drawHeadimg(Context context, int dpwidth, String text){
        int pxwidth = (int)dip2px(context,dpwidth);

        Bitmap bitmap = Bitmap.createBitmap(pxwidth,pxwidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int radius = pxwidth/2;
        paint.setColor(getOtherColor());//圆的颜色
        canvas.drawCircle(radius, radius, radius, paint);

        paint.setTextSize((float)(pxwidth/3));//设置文字的大小textWid
        paint.setColor(Color.WHITE);//文字的颜色 白色
        Map<String,Integer> map =getTextWidthAndHeight(context,text,paint);
        //int textWidth =  map.get("width");
        int textHeight =  map.get("height");
        paint.setTextAlign(Paint.Align.CENTER);//字体居中，下面的dx代表字体具中心点距离

        float dx = pxwidth/2 ;
        float dy = ((pxwidth - textHeight)/2+textHeight)*0.9f;
        canvas.drawText(text, dx, dy, paint);//将文字写入。这里面的dy代表基准线

        return bitmap;
    }

    /**
     * 画圆角正方行
     * @param context
     * @param dpwidth
     * @param text
     * @return
     */
    public static Bitmap drawSquare(Context context, int dpwidth, String text, String color){
        int pxwidth = (int)dip2px(context,dpwidth);

        Bitmap bitmap = Bitmap.createBitmap(pxwidth,pxwidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int radius = pxwidth/8;
        paint.setColor(Color.parseColor(color));//圆的颜色
        RectF re1 = new RectF(0,0,pxwidth,pxwidth);
        canvas.drawRoundRect(re1, radius, radius, paint);

        paint.setTextSize((float)(pxwidth/3));//设置文字的大小textWid
        paint.setColor(Color.WHITE);//文字的颜色 白色
        Map<String,Integer> map =getTextWidthAndHeight(context,text,paint);
        //int textWidth =  map.get("width");
        int textHeight =  map.get("height");
        paint.setTextAlign(Paint.Align.CENTER);//字体居中，下面的dx代表字体具中心点距离

        float dx = pxwidth/2 ;
        float dy = ((pxwidth - textHeight)/2+textHeight)*0.9f;
        canvas.drawText(text, dx, dy, paint);//将文字写入。这里面的dy代表基准线

        return bitmap;
    }

    /**
     * 画矩形（圆角）
     * @param context
     * @param dpwidth 宽度
     * @param dpheight 高度
     * @param dpradio 角弧度
     * @param text 显示内容
     * @param color 背景颜色
     * @return
     */
    public static Bitmap drawRect(Context context, int dpwidth, int dpheight,int dpradio, String text, String color){
        int pxwidth = (int)dip2px(context,dpwidth);
        int pxheight = (int)dip2px(context,dpheight);
        int pxradio = (int)dip2px(context,dpradio);

        Bitmap bitmap = Bitmap.createBitmap(pxwidth,pxheight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int radius = pxradio;
        paint.setColor(Color.parseColor(color));//圆的颜色
        RectF re1 = new RectF(0,0,pxwidth,pxheight);
        canvas.drawRoundRect(re1, radius, radius, paint);

        paint.setTextSize((float)(pxwidth/3));//设置文字的大小textWid
        paint.setColor(Color.WHITE);//文字的颜色 白色
        Map<String,Integer> map =getTextWidthAndHeight(context,text,paint);
        //int textWidth =  map.get("width");
        int textHeight =  map.get("height");
        paint.setTextAlign(Paint.Align.CENTER);//字体居中，下面的dx代表字体具中心点距离

        float dx = pxwidth/2 ;
        float dy = ((pxheight - textHeight)/2+textHeight)*0.9f;
        canvas.drawText(text, dx, dy, paint);//将文字写入。这里面的dy代表基准线

        return bitmap;
    }



    /**
     * 画圆形图标
     * @param context
     * @param dpwidth
     * @param color 颜色
     * @return
     */
    public static Bitmap drawIcon(Context context, int dpwidth, int color){
        int pxwidth = (int)dip2px(context,dpwidth);

        Bitmap bitmap = Bitmap.createBitmap(pxwidth, pxwidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int radius = pxwidth/2;
        paint.setColor(color);//圆的颜色
        canvas.drawCircle(radius, radius, radius, paint);


        return bitmap;
    }



    /**
     * 获取文本宽度和高度
     * @param context
     * @param text
     * @return
     */
    public static Map<String,Integer> getTextWidthAndHeight(Context context, String text, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        int height = bounds.bottom + bounds.height();
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("width", width);
        map.put("height", height);
        return map;
    }


    public static Bitmap cutHeadimg(Bitmap source, int min){
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    //生成随即颜色
    public static int getOtherColor(){
        int[] allColors = {Color.parseColor("#E1B154"), Color.parseColor("#D2945B"), Color.parseColor("#E57257"), Color.parseColor("#38B1A2"),
                Color.parseColor("#76A174"), Color.parseColor("#5CA7C7"), Color.parseColor("#B758A9"), Color.parseColor("#F99A5A")};
        int sjs = new Random().nextInt(8);


        return allColors[sjs];

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
}

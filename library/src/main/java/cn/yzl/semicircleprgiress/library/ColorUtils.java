package cn.yzl.semicircleprgiress.library;

import android.graphics.Color;

/**
 * 颜色工具类
 * Created by 易点付 伊 on 2016/11/14.
 */
public class ColorUtils {

    /**
     * 根据fraction值来计算当前的颜色。
     */
    public static int getGradient(float fraction, int startColor, int endColor) {
        int redCurrent;
        int blueCurrent;
        int greenCurrent;
        int alphaCurrent;
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaStart = Color.alpha(startColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaEnd = Color.alpha(endColor);
        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaDifference = alphaEnd - alphaStart;
        redCurrent = (int) (redStart + fraction * redDifference);
        blueCurrent = (int) (blueStart + fraction * blueDifference);
        greenCurrent = (int) (greenStart + fraction * greenDifference);
        alphaCurrent = (int) (alphaStart + fraction * alphaDifference);
        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }
}

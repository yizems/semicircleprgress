package cn.yzl.semicircleprgiress.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * 半圆形 进度条
 * Created by 易点付 伊 on 2016/11/11.
 */
public class SemicircleProgress extends View {

    private int pStartColor;
    private int pEndColor;

    private int bStartColor;
    private int bEndColor;

    private int progress;

    private float unitRadio = 2.4f;
    private float pWidth;
    /**
     * 背景色 画笔
     */
    private Paint bgPaint = new Paint();

    /**
     * 选中部分 画笔
     */
    private Paint progressPaint = new Paint();

    public SemicircleProgress(Context context) {
        super(context);
        progress = 0;
    }

    public SemicircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SemicircleProgress);

        int defualtProColor = Color.parseColor("#0078D7");
        int defualtBgColor = Color.parseColor("#CCCCCC");

        pStartColor = typedArray.getColor(R.styleable.SemicircleProgress_progress_start_color, defualtProColor);
        pEndColor = typedArray.getColor(R.styleable.SemicircleProgress_progress_end_color, defualtProColor);
        bStartColor = typedArray.getColor(R.styleable.SemicircleProgress_bg_start_color, defualtBgColor);
        bEndColor = typedArray.getColor(R.styleable.SemicircleProgress_bg_end_color, defualtBgColor);
        progress = typedArray.getInt(R.styleable.SemicircleProgress_progress, 0);
        pWidth = typedArray.getDimension(R.styleable.SemicircleProgress_progress_width, DensityUtils.dp2px(context, 2));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(pWidth);

        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setDither(true);

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setDither(true);
        bgPaint.setStrokeWidth(pWidth);

        //画图的范围
        RectF pRectF;
        if (getMeasuredHeight() > getMeasuredWidth()) {
            pRectF = new RectF(
                    getPaddingLeft()  + pWidth
                    , (getMeasuredHeight() - getMeasuredWidth()) / 2  + pWidth,
                    getMeasuredWidth()  - pWidth,
                    (getMeasuredHeight() + getMeasuredWidth()) / 2  - pWidth);
        } else {
            pRectF = new RectF(
                    getMeasuredWidth() / 2 - getMeasuredHeight() / 2 + pWidth
                    , getPaddingTop()  + pWidth,
                    getMeasuredWidth() / 2 + getMeasuredHeight() / 2  - pWidth,
                    getMeasuredHeight() - getPaddingTop()  - pWidth);
        }


        //画背景
        for (int i = 1; i <= unitRadio * (100 - progress); i++) {
            bgPaint.setColor(ColorUtils.getGradient(i / unitRadio / (100 - progress),
                    bStartColor, bEndColor));
            //画进度
            canvas.drawArc(pRectF, 150 + progress * unitRadio + i,
                    1,
                    false
                    , bgPaint);
        }

        for (int i = 0; i <= unitRadio * progress; i++) {
            progressPaint.setColor(ColorUtils.getGradient(i / unitRadio / progress,
                    pStartColor, pEndColor));
            //画进度
            canvas.drawArc(pRectF, 150 + i,
                    1,
                    false
                    , progressPaint);
        }

    }

    public void setProgress(@IntRange(from = 0, to = 100) int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        drawARC方法会造成占用一个完整圆的空间,这里通过设置marginBottom来修复
        if (getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams lpR = new RelativeLayout.LayoutParams(getLayoutParams());
            lpR.setMargins(0, 0, 0, -getMeasuredHeight() / 4 + 10);
            setLayoutParams(lpR);
        }

        if (getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams lpL = new LinearLayout.LayoutParams(getLayoutParams());
            lpL.setMargins(0, 0, 0, -getMeasuredHeight() / 4 + 10);
            setLayoutParams(lpL);
        }
        if (getParent() instanceof FrameLayout) {
            FrameLayout.LayoutParams lpF = new FrameLayout.LayoutParams(getLayoutParams());
            lpF.setMargins(0, 0, 0, -getMeasuredHeight() / 4 + 10);
            setLayoutParams(lpF);
        }
    }
}

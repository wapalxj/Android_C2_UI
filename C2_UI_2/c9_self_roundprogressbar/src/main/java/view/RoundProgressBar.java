package view;


import com.example.testroundpb.R;

import android.R.attr;
import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class RoundProgressBar extends View {
	// 画笔对象的引用
	private final Paint paint;
	// 圆环的颜色
	private int roundBackgroundColor;
	// 圆环进度的颜色
	private int roundProgressColor;
	// 中间进度百分比的字符串的颜色
	private int textPercentColor;
	// 中间进度百分比的字符串的字体大小
	private float textPercentSize;
	// 文字字符串的颜色
	private int textColor;
	// 文字字符串的字体大小
	private float textSize;
	// 圆环的宽度
	private float roundWidth;
	// 最大进度
	private int max;
	// 当前进度
	private int progress;
	// 是否显示中间的进度
	private boolean textIsDisplayable;
	// 进度的风格，实心或者空心
	private int progressStrokeStyle;
	public static final int STROKE = 0;
	public static final int FILL = 1;
	DisplayMetrics dm = getResources().getDisplayMetrics();
	int dpi = dm.densityDpi;
	//圆心坐标和半径
	private int pointX;//圆心x坐标
	private int pointY;//圆心y坐标
	private int radius;

	public RoundProgressBar(Context context) {
		this(context,null);
	}
	
	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public RoundProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
		//获取在attr中定义的declare-styleable属性，并设置默认值
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);
		//圆环背景色
		roundBackgroundColor=mTypedArray.getColor(R.styleable.RoundProgressBar_roundBackgroundColor, Color.GRAY);
		
		//圆环进度色
		roundProgressColor=mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, 0xFF01a811);
		//进度百分比色
		textPercentColor=mTypedArray.getColor(R.styleable.RoundProgressBar_textPercentColor, 0xFF01a811);
		//进度百分比数字大小 
		textPercentSize=mTypedArray.getColor(R.styleable.RoundProgressBar_textPercentSize,15);
		//文字色
		textColor=mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.BLACK);
		//文字大小 
		textSize=mTypedArray.getColor(R.styleable.RoundProgressBar_textSize,8);
		//圆环宽度
		roundWidth=mTypedArray.getColor(R.styleable.RoundProgressBar_roundWidth, 5);
		//最大进度值
		max=mTypedArray.getColor(R.styleable.RoundProgressBar_max, 100);
		//当前进度值
		progress=mTypedArray.getColor(R.styleable.RoundProgressBar_progress, 0);
		//是否显示进度百分比文字值
		textIsDisplayable=mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
		// 进度的风格，实心或者空心
		progressStrokeStyle=mTypedArray.getInt(R.styleable.RoundProgressBar_progressStrokeStyle, STROKE);
		mTypedArray.recycle();
		
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		pointX=getWidth()/2;//圆心x坐标
		pointY=getHeight()/2;//圆心y坐标
		//1.画外层大圆环
		drawBackgroundCircle(canvas);
		//2.画进度百分比数字
		drawPercentText(canvas);
		//3.画文字
		drawText(canvas);
		//4.画进度
		drawProgress(canvas);
		
		
		
	}
	/**
	 * 1.画外层大圆环
	 */
	private void drawBackgroundCircle(Canvas canvas){
		
		roundWidth = 6 * getWidth() / 303; // 获取圆环的宽度
		radius = (int) (pointX - roundWidth / 2);
		paint.setStyle(Paint.Style.STROKE); // 设置空心
		paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		paint.setAntiAlias(true); // 消除锯齿
		paint.setColor(roundBackgroundColor);
		canvas.drawCircle(pointX, pointY, radius, paint); // 画出圆环
	}
	/**
	 * 2.画进度百分比数字
	 */
	private void drawPercentText(Canvas canvas){
		paint.setStrokeWidth(10);
		paint.setColor(textPercentColor);
		paint.setStyle(Paint.Style.FILL); // 设置实心
		paint.setTextSize(88 * getHeight() / 307);
		// paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体
		paint.setTypeface(Typeface.DEFAULT);
		int percent = (int) (((float) progress / (float) max) * 100); // 中间的进度百分比，先转换成float在进行除法运算，不然都为0
		// 测量字体宽度，我们需要根据字体的宽度设置在圆环中间
		float textWidth = paint.measureText(String.valueOf(percent)); 
//		if ((textIsDisplayable) && (progressStrokeStyle == STROKE)) {
		if (textIsDisplayable) {
			// 画出进度
			canvas.drawText(String.valueOf(percent),pointX - textWidth / 2, 181 * getHeight() / 307, paint);
			paint.setTextSize(38 * getHeight() / 307);
			// 画出百分号
			canvas.drawText("%", pointX + textWidth / 2,181 * getHeight() / 307, paint);
		}
	}
	/**
	 * 3.画文字
	 */
	private void drawText(Canvas canvas){
		paint.setColor(Color.BLACK);
		paint.setTextSize(18 * getHeight() / 307);
		paint.setStyle(Paint.Style.FILL); // 设置实心
		float textWidth = paint.measureText("剩余电量");
		canvas.drawText("剩余电量",pointX - textWidth / 2, 221 * getHeight() / 307, paint);
	}
	/**
	 * 4.画圆弧 ，画圆环的进度
	 */
	private void drawProgress(Canvas canvas) {
		// 设置进度是实心还是空心
		paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		paint.setColor(roundProgressColor); // 设置进度的颜色
		//用于定义的圆弧的形状和大小的界限
		RectF oval = new RectF(
				pointX - radius, //left
				pointY - radius, //top
				pointX+ radius, //right
				pointY + radius); //bottom
		
		switch (progressStrokeStyle) {
		case STROKE: 
			paint.setStyle(Paint.Style.STROKE);
			// 根据进度画圆弧
			canvas.drawArc(oval, 270, 360 * progress / max, false, paint); 
			break;
		
		case FILL: 
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			if (progress != 0){
				// 根据进度画圆弧
				canvas.drawArc(oval, 270, 360 * progress / max, true, paint); 
			}
			break;
		
		}
	}
	public synchronized int getMax() {
		return max;
	}

	/**
	 * 设置进度的最大值
	 * 
	 * @param max
	 */
	public synchronized void setMax(int max) {
		if (max < 0) {
			throw new IllegalArgumentException("max not less than 0");
		}
		this.max = max;
	}

	/**
	 * 获取进度.需要同步
	 * 
	 * @return
	 */
	public synchronized int getProgress() {
		return progress;
	}

	/**
	 * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
	 * 
	 * @param progress
	 */
	public synchronized void setProgress(int progress) {
		if (progress < 0) {
			throw new IllegalArgumentException("progress not less than 0");
		}
		if (progress > max) {
			progress = max;
		}
		if (progress <= max) {
			this.progress = progress;
			postInvalidate();
		}
	}

	public int getRoundBackgroundColor() {
		return roundBackgroundColor;
	}

	public void setroundBackgroundColor(int color) {
		this.roundBackgroundColor = color;
	}

	public int getRoundProgressColor() {
		return roundProgressColor;
	}

	public void setRoundProgressColorr(int color) {
		this.roundProgressColor = color;
	}
	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}
	public int getPercentColor() {
		return textPercentColor;
	}

	public void setPercentColor(int textPercentColor) {
		this.textPercentColor = textColor;
	}

	public float getPercentSize() {
		return textPercentSize;
	}

	public void setPercentSize(float textPercentSize) {
		this.textPercentSize = textSize;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

	public void setTextIsDisplayable(boolean textIsDisplayable) {
		this.textIsDisplayable = textIsDisplayable;
	}

	public boolean getTextIsDisplayable() {
		return textIsDisplayable;
	}

	public void setStyle(int style) {
		this.progressStrokeStyle = style;
	}

	public int getStyle() {
		return progressStrokeStyle;
	}
}


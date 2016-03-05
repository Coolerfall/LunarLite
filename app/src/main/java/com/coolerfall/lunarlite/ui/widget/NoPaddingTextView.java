package com.coolerfall.lunarlite.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.coolerfall.lunarlite.R;

/**
 * A text view without padding.
 *
 * @author Vincent Cheung (coolingfall@gmail.com)
 * @since Mar. 05, 2016
 */
public class NoPaddingTextView extends View {
	private int mTextSize = 16;
	private int mTextColor = Color.BLACK;

	private int mWidth;
	private int mHeight;
	private String mText;
	private Paint mPaint;
	private Rect mBounds = new Rect();

	public NoPaddingTextView(final Context context) {
		this(context, null);
	}

	public NoPaddingTextView(final Context context, final AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NoPaddingTextView(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);

		/* default attrs */
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, dm);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NoPaddingTextView);
		/* get system attrs */
		mTextSize = a.getDimensionPixelSize(R.styleable.NoPaddingTextView_android_textSize, mTextSize);
		mTextColor = a.getColor(R.styleable.NoPaddingTextView_android_textColor, mTextColor);
		mText = a.getString(R.styleable.NoPaddingTextView_android_text);
		a.recycle();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(mTextSize);

		calculateSize();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
			MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(mTextColor);
		mPaint.setTextSize(mTextSize);

		if (!TextUtils.isEmpty(mText)) {
			canvas.drawText(mText, -mBounds.left, getHeight() - mBounds.bottom, mPaint);
		}
		super.onDraw(canvas);
	}

	private void calculateSize() {
		if (!TextUtils.isEmpty(mText)) {
			mPaint.getTextBounds(mText, 0, mText.length(), mBounds);
			mHeight = mBounds.height();
			mWidth = mBounds.width();
		}
	}

	/**
	 * Set text size with sp unit.
	 *
	 * @param textSizeSp text size in sp
	 */
	public void setTextSize(int textSizeSp) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSizeSp, dm);
		invalidate();
	}

	/**
	 * Set text color.
	 *
	 * @param textColor text color
	 */
	public void setTextColor(@ColorInt int textColor) {
		mTextColor = textColor;
		invalidate();
	}

	/**
	 * Set text, so this text view can draw it.
	 *
	 * @param text text to set
	 */
	public void setText(String text) {
		mText = text;
		calculateSize();

		requestLayout();
	}
}
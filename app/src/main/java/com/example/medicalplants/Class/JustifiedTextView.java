package com.example.medicalplants.Class;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class JustifiedTextView extends AppCompatTextView {

	private static final String SPACE = " ";
	private TextPaint textPaint;
	private int lineHeight;

	public JustifiedTextView(Context context) {
		super(context);
		init();
	}

	public JustifiedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public JustifiedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		textPaint = getPaint(); // مقداردهی TextPaint
		lineHeight = getLineHeight();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		String text = getText().toString();
		Rect bounds = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), bounds);
		Layout layout = getLayout();
		if (layout == null) return;

		int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight(); // عرض ویو بدون padding

		for (int i = 0; i < layout.getLineCount(); i++) {
			int lineStart = layout.getLineStart(i);
			int lineEnd = layout.getLineEnd(i);
			String line = text.substring(lineStart, lineEnd);

			if (i == layout.getLineCount() - 1) { // اگر خط آخر است، بدون justify و فقط راست‌چین شود
				float lineWidth = textPaint.measureText(line);
				canvas.drawText(line, viewWidth - lineWidth, getPaddingTop() + i * lineHeight, textPaint);
				break;
			}

			// Justify و راست‌چین کردن بقیه خطوط
			String justifiedLine = justifyLine(line, viewWidth);
			canvas.drawText(justifiedLine, 0, getPaddingTop() + i * lineHeight, textPaint);
		}
	}

	private String justifyLine(String line, int viewWidth) {
		if (line.isEmpty() || line.endsWith("\n")) {
			return line;
		}

		float lineWidth = textPaint.measureText(line);
		float spaceWidth = textPaint.measureText(SPACE);
		int gaps = line.split(SPACE).length - 1;

		if (gaps > 0 && lineWidth < viewWidth) {
			float extraSpace = (viewWidth - lineWidth) / gaps; // فضای اضافی بین کلمات
			return line.replace(SPACE, SPACE + getSpaces(extraSpace / spaceWidth));
		}

		return line;
	}

	private String getSpaces(float count) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 0; i < count; i++) {
			spaces.append(SPACE);
		}
		return spaces.toString();
	}

	@Override
	public void setTypeface(Typeface tf) {
		if (textPaint != null && tf != null) {
			textPaint.setTypeface(tf);
		}
		super.setTypeface(tf);
	}
}

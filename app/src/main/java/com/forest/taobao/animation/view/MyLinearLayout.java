package com.forest.taobao.animation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.forest.taobao.animation.R;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(VERTICAL);
	}
	
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		//才采花大盗---把我需要的自定义属性取出来给自己用。
		return new MyLayoutParams(getContext(), attrs);
	}
	
	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		// 偷天换日
		MyLayoutParams p = (MyLayoutParams) params;
		if(!isDiscrollvable(p)){//判断子控件是否有自定义属性，没有则不包裹MyFrameLayout
			super.addView(child, index, params);
		}else{
			MyFrameLayout mf = new MyFrameLayout(getContext(), null);
			mf.setmDiscrollveAlpha(p.mDiscrollveAlpha);
			mf.setmDisCrollveTranslation(p.mDisCrollveTranslation);
			mf.setmDiscrollveFromBgColor(p.mDiscrollveFromBgColor);
			mf.setmDiscrollveToBgColor(p.mDiscrollveToBgColor);
			mf.setmDiscrollveScaleX(p.mDiscrollveScaleX);
			mf.setmDiscrollveScaleY(p.mDiscrollveScaleY);
			mf.addView(child);
			super.addView(mf, index, params);
		}
	}
	
	private boolean isDiscrollvable(MyLayoutParams p) {
		return p.mDiscrollveAlpha||
				p.mDiscrollveScaleX||
				p.mDiscrollveScaleY||
				p.mDisCrollveTranslation!=-1||
				(p.mDiscrollveFromBgColor!=-1&&
				p.mDiscrollveToBgColor!=-1);
	}
	
	public static class MyLayoutParams extends LayoutParams{

		public int mDiscrollveFromBgColor;//背景颜色变化开始值
		public int mDiscrollveToBgColor;//背景颜色变化结束值
		public boolean mDiscrollveAlpha;//是否需要透明度动画
		public int mDisCrollveTranslation;//平移值
		public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
		public boolean mDiscrollveScaleY;//是否需要y轴方向缩放
		public MyLayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
			mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
			mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
			mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
			mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
			mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
			mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
			a.recycle();
		}
		
		
	}

}

package com.forest.taobao.animation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.forest.taobao.animation.scroll.DiscrollInterface;

public class MyScrollView extends ScrollView {
	private MyLinearLayout mContent;
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		mContent = (MyLinearLayout) getChildAt(0);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		View first = mContent.getChildAt(0);
		first.getLayoutParams().height=getHeight();
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		//监听滑动---childView从下面冒出来的多少/childView.height();就是执行的百分比
		//
		int scrollveiwHeight = getHeight();
		for (int i = 0; i < mContent.getChildCount(); i++) {
			View child = mContent.getChildAt(i);
			if(!(child instanceof MyFrameLayout)){
				continue;
			}
			//回调，传递执行的百分比给MyFrameLayout
			DiscrollInterface discrollInterface = (DiscrollInterface) child;
			//child离parent顶部的高度
			int childTop = child.getTop();
			//什么时候执行动画呢？当child滑进屏幕的时候
			int childHeight = child.getHeight();
			//t:就是滑出去的高度
			//child离屏幕顶部的高度
			int absoluteTop = (childTop - t);
			
			if(absoluteTop<=scrollveiwHeight){
				//child浮现的高度=ScrollView的高度 - child离屏幕顶部的高度
				int visibleGap =scrollveiwHeight - absoluteTop;
//				float ratio = child浮现的高度/child的高度;
				float ratio = visibleGap/(float)childHeight;
				//确保ratio是在0~1的范围。得到ratio在0~1的中间值
				discrollInterface.onDiscroll(clamp(ratio, 1f, 0f));
			}else{
				//恢复
				discrollInterface.onResetDiscroll();
			}
		}
	}

		//求三个数的中间大小的一个数。
		public static float clamp(float value, float max, float min){
			return Math.max(Math.min(value, max), min);
		}
		
}

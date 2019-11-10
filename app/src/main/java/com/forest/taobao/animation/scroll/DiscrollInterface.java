package com.forest.taobao.animation.scroll;

public interface DiscrollInterface {

	/**
	 * 当滑动的时候调用该方法，用来控制里面的控件执行相应的动画
	 * @param ratio : 0~1的范围，动画的执行百分比
	 */
	public void onDiscroll(float ratio);
	
	/**
	 * 重置动画--让view所有属性恢复原来的值
	 */
	public void onResetDiscroll();
}

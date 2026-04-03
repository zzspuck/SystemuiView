package com.example.systemui_view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;



/**
 * 快速面板（下拉菜单）布局视图
 * -
 * 用于加载和初始化 下拉菜单中 开关图标、调节滑动条（亮度、音量）
 *
 * @author Jon Luo
 * @since 2022-7-7 14:22
 */

public class ShortcutPanelView extends ConstraintLayout {

    public ShortcutPanelView(Context context) {
        this(context, null);
    }

    public ShortcutPanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShortcutPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

}
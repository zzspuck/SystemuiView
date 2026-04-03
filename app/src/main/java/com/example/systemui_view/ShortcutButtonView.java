package com.example.systemui_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;


/**
 * 自定义快捷按钮视图
 * 将图标和文字分离，支持独立样式调整和主题切换
 */
public class ShortcutButtonView extends LinearLayout {
    private static final String TAG = "ShortcutButtonView";
    
    private ImageView mIconView;
    private TextView mTextView;
    private int mIconResId;
    private String mText;
    private int mIconSize = 53; // 默认图标大小
    private int mIconMarginBottom = 2; // 图标底部间距
    private int mTextSize = 8; // 文字大小


    public ShortcutButtonView(Context context) {
        super(context);
        init(context, null);
    }
    
    public ShortcutButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    
    public ShortcutButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShortcutButtonView);
            mIconSize = a.getDimensionPixelSize(R.styleable.ShortcutButtonView_iconSize, mIconSize);
            mIconMarginBottom = a.getDimensionPixelSize(R.styleable.ShortcutButtonView_iconMarginBottom, mIconMarginBottom);
            mTextSize = a.getDimensionPixelSize(R.styleable.ShortcutButtonView_textSize, mTextSize);
            a.recycle();
        }

        mIconView = new ImageView(context);
        LayoutParams iconParams = new LayoutParams(
                mIconSize, 
                mIconSize
        );
        iconParams.bottomMargin = mIconMarginBottom;
        iconParams.gravity = Gravity.CENTER_HORIZONTAL;
        mIconView.setLayoutParams(iconParams);

        mTextView = new TextView(context);
        LayoutParams textParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        textParams.gravity = Gravity.CENTER_HORIZONTAL;
        mTextView.setLayoutParams(textParams);
        mTextView.setTextSize(mTextSize);
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView.setMaxLines(3);
        mTextView.setEllipsize(null);
        mTextView.setSingleLine(false);

        addView(mIconView);
        addView(mTextView);

        applyDefaultStyles();
    }
    /**
     * 应用默认样式
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void applyDefaultStyles() {
        mTextView.setTextColor(getContext().getColor( R.color.text_color_shortcut_panel));

        if (mIconResId != 0){
            mIconView.setBackground(getContext().getDrawable(mIconResId));
        }
//        int paddingStartEnd = (int) getResources().getDimension(R.dimen.dp15);
        int paddingTop = (int) getResources().getDimension(R.dimen.dp24);
//        int paddingBottom = (int) getResources().getDimension(R.dimen.dp15);
        setPadding(0, paddingTop, 0, 0);
        postInvalidate();
    }

    /**
     * 设置图标资源
     */
    public void setIconResource(int resId) {
        mIconResId = resId;
        if (resId != 0 && mIconView != null && getContext() != null) {
            mIconView.setBackground(getContext().getDrawable(resId));
        }
    }

    public void setTextColor(int color) {
        if (mTextView != null && getContext() != null && color != 0){
            mTextView.setTextColor(getContext().getColor( R.color.text_color_shortcut_panel));
        }
    }
    
    /**
     * 设置图标底部间距
     */
    public void setIconMarginBottom(int marginBottom) {
        mIconMarginBottom = marginBottom;
        LayoutParams iconParams = (LayoutParams) mIconView.getLayoutParams();
        iconParams.bottomMargin = mIconMarginBottom;
        mIconView.setLayoutParams(iconParams);
        Log.d(TAG, "setIconMarginBottom: " + mIconMarginBottom);
        postInvalidate();
    }
    
    /**
     * 获取当前图标底部间距
     */
    public int getIconMarginBottom() {
        return mIconMarginBottom;
    }
    
    /**
     * 设置文字
     */
    public void setText(String text) {
        mText = text;
        mTextView.setText(text);
    }
    
    /**
     * 获取图标视图
     */
    public ImageView getIconView() {
        return mIconView;
    }
    
    /**
     * 获取文字视图
     */
    public TextView getTextView() {
        return mTextView;
    }
    
    /**
     * 刷新主题样式
     */
    public void refreshTheme() {
        Log.d(TAG, "refreshTheme: ");
        applyDefaultStyles();
    }
    
    /**
     * 刷新语言
     */
    public void refreshLanguage() {
        Log.d(TAG, "refreshLanguage: ");
        // 重新设置文字（如果需要多语言支持的话）
        if (mText != null) {
            mTextView.setText(mText);
        }
    }
    
    /**
     * 当配置改变时调用（如主题切换、语言切换）
     */
    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
        refreshTheme();
        refreshLanguage();
    }
}
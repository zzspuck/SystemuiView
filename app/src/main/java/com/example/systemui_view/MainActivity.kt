package com.example.systemui_view

import android.app.Activity
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : Activity() {

    private lateinit var slideView: SlideRevealView
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 初始化 View
        slideView = findViewById(R.id.slideView)
        seekBar = findViewById(R.id.seekBar)
        val bitmapA = BitmapFactory.decodeResource(resources, R.drawable.bg_quick_card_user_guide_step_6_fbp) // 前景图
        val bitmapB = BitmapFactory.decodeResource(resources, R.drawable.bg_home_wallpaper_default) // 背景图

        // 3. 设置图片
        slideView.setBitmaps(bitmapA, bitmapB)

        // 2. 加载图片
        // 注意：请确保你的 res/drawable 下有 img_a 和 img_b
        // 如果找不到资源，App 会崩溃，请替换成你项目中存在的图片


        // 4. 设置 SeekBar 监听器
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 将 SeekBar 的进度 (0-1000) 映射到屏幕宽度
                // 这里的逻辑是：进度越大，slidingDistance 越大（向右滑动）
                val screenWidth = slideView.width
                if (screenWidth > 0) {
                    // 计算滑动距离，范围从 -width 到 0 (从左侧滑入到对齐左侧)
                    // 你可以根据需要调整这个公式
                    val distance = (progress / 1000f) * screenWidth

                    Log.d("SeekBar", "Progress: $progress, Distance: $distance")
                    slideView.setSlidingDistance(distance)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


    }



}
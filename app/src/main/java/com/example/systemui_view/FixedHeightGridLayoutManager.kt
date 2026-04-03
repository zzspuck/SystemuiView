package com.example.systemui_view

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FixedHeightGridLayoutManager(
    context: Context,
    spanCount: Int,
) : GridLayoutManager(context, spanCount) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

        val parentHeight = height
        if (parentHeight > 0) {
            val itemHeight = (parentHeight - 100) / 3

            Log.i("TAG","temHeight: $itemHeight")

            for (i in 0 until childCount) {
                val child = getChildAt(i) ?: continue
                val layoutParams = child.layoutParams
                if (layoutParams.height != itemHeight) {
                    layoutParams.height = itemHeight
                    child.requestLayout()
                }
            }
        }
    }
}
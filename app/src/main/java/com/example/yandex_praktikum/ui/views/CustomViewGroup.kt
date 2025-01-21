package com.example.yandex_praktikum.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomViewGroup @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attributeSet, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0
        var maxWidth = 0

        for (index in 0 until childCount) {
            val childView = getChildAt(index)

            measureChild(childView, widthMeasureSpec, heightMeasureSpec)

            maxHeight += childView.measuredHeight
            maxWidth = maxOf(maxWidth, childView.measuredWidth)
        }

        val resolvedWidth = resolveSize(maxWidth, widthMeasureSpec)
        val resolvedHeight = resolveSize(maxHeight, heightMeasureSpec)

        setMeasuredDimension(resolvedWidth, resolvedHeight)
    }

    override fun addView(child: View?) {
        if (childCount > 2) throw IllegalStateException("CustomViewGroup может содержать только два дочерних элемента")
        super.addView(child)
    }

    override fun onLayout(
        changed: Boolean,
        leftPosition: Int,
        topPosition: Int,
        rightPosition: Int,
        bottomPosition: Int
    ) {

        val parentWidth = rightPosition - leftPosition
        val parentCenterY = (topPosition + bottomPosition) / 2


        for (index in 0 until childCount) {
            val childView = getChildAt(index)
            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight

            val childLeftPosition = (parentWidth - childWidth) / 2
            val childTopPosition = when (index) {
                0 -> topPosition
                1 -> bottomPosition - childHeight
                else -> topPosition
            }

            childView.layout(childLeftPosition, parentCenterY, childLeftPosition + childWidth, parentCenterY + childHeight)

            childView.alpha = ALPHA_START
            childView.animate()
                .y(childTopPosition.toFloat())
                .alpha(ALPHA_END)
                .setDuration(ANIMATION_DURATION)
                .start()
        }
    }

    companion object {
        const val ALPHA_START = 0F
        const val ALPHA_END = 1f
        const val ANIMATION_DURATION = 5000L
    }
}
package com.rygelouv.fragmentstepperproject.testcustomviews

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.rygelouv.fragmentstepperproject.R

/**
 * Created by rygelouv on 4/12/18.
 * <p>
 * FragmentStepperProject
 * Copyright (c) 2018 Makeba Inc All rights reserved.
 */


class TextMultiStateView : LinearLayout {
    lateinit var text: TextView
    lateinit var progress: ProgressBar
    lateinit var image: ImageView
    lateinit var refresh: ImageView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.text_multi_state_view_layout, this)
        text = findViewById(R.id.multi_state_text_text)
        progress = findViewById(R.id.multi_state_text_progress)
        image = findViewById(R.id.multi_state_text_image)
        refresh = findViewById(R.id.multi_state_text_refresh)
    }

    fun setText(text: String) {
        this.text.text = text
    }

    fun setState(state: State) {
        when (state) {
            State.DISABLED -> {
                image.setImageDrawable(resources.getDrawable(R.drawable.ic_more))
                image.visibility = View.VISIBLE
                progress.visibility = View.GONE
                text.setTextColor(resources.getColor(R.color.color_white_transp))
            }
            State.LOADING -> {
                image.visibility = View.GONE
                progress.visibility = View.VISIBLE
                this.progress.indeterminateDrawable.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.MULTIPLY)
                text.setTextColor(resources.getColor(R.color.white))
            }
            State.DONE -> {
                image.setImageDrawable(resources.getDrawable(R.drawable.ic_check_white_24dp))
                image.visibility = View.VISIBLE
                progress.visibility = View.GONE
                text.setTextColor(resources.getColor(R.color.white))
            }
            State.FAILED -> {
                image.setImageDrawable(resources.getDrawable(R.drawable.ic_close_red))
                image.visibility = View.VISIBLE
                refresh.visibility = View.VISIBLE
                progress.visibility = View.GONE
                text.setTextColor(resources.getColor(R.color.red_text))
            }
        }
    }
}

enum class State {
    DISABLED,
    LOADING,
    DONE,
    FAILED
}

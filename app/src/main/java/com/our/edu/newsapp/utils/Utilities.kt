package com.our.edu.newsapp.utils

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.our.edu.newsapp.R
import es.dmoral.toasty.Toasty


object Utilities {

    fun runAnimation(recyclerView: RecyclerView, type: Int) {
        val context: Context = recyclerView.context
        var controller: LayoutAnimationController? = null
        controller = if (type == Constants.ANIMATION_RISE_UP) {
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_rise_up)
        } else {
            AnimationUtils.loadLayoutAnimation(
                context,
                R.anim.layout_animation_slide_from_right
            )
        }
        recyclerView.layoutAnimation = controller
        recyclerView.scheduleLayoutAnimation()
    }

    fun toastyError(context: Context, message: String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun toastySuccess(context: Context, message: String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, false).show()
    }

}

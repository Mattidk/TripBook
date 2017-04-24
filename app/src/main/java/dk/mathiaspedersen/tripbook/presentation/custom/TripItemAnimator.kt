package dk.mathiaspedersen.tripbook.presentation.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator

import dk.mathiaspedersen.tripbook.presentation.util.getScreenHeight
import android.view.animation.Animation
import android.view.animation.ScaleAnimation



class TripItemAnimator : DefaultItemAnimator() {

    private var lastAddAnimatedItem = -2

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun animateAdd(viewHolder: RecyclerView.ViewHolder): Boolean {
        if (viewHolder.layoutPosition > lastAddAnimatedItem) {
            lastAddAnimatedItem++
            runEnterAnimation(viewHolder as TripViewHolder)
            return false
        }

        dispatchAddFinished(viewHolder)
        return false
    }

    private fun runEnterAnimation(holder: TripViewHolder) {
        val screenHeight = getScreenHeight(holder.itemView.context) / 4
        holder.itemView.translationY = screenHeight.toFloat()
        holder.itemView.scaleX = 0f
        holder.itemView.scaleY = 0f
        holder.itemView.animate()
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(DecelerateInterpolator(5f))
                .setDuration(500)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        dispatchAddFinished(holder)
                    }
                }).start()
    }
}

package com.mataku.scrobscrob.app.ui.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.mataku.scrobscrob.R

class TopArtistsRecyclerView : EpoxyRecyclerView {
    constructor(
        context: Context
    ) : this(context, null)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        context.let {
            val gridLayoutManager = GridLayoutManager(it, 2)
            this.addItemDecoration(ArtistItemDecoration.createDefaultDecoration(it))

//        val staggeredLayoutManager = StaggeredGridLayoutManager(12, StaggeredGridLayoutManager.VERTICAL)
//        staggeredLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

//        staggeredLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            this.layoutManager = gridLayoutManager
        }
    }

    private class ArtistItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
        private var space: Int = space

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: State
        ) {
            outRect.top = space
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
        }

        companion object {
            fun createDefaultDecoration(context: Context): ArtistItemDecoration {
                val spacingInPixels = context.resources.getDimensionPixelSize(R.dimen.card_view_spacing)
                return ArtistItemDecoration(spacingInPixels)
            }
        }
    }
}
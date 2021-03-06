package com.mataku.scrobscrob.app.ui.widget

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.mataku.scrobscrob.R
import com.mataku.scrobscrob.core.GlideApp
import com.mataku.scrobscrob.core.api.endpoint.Album
import com.mataku.scrobscrob.databinding.ModelTopAlbumViewBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TopAlbumView : ConstraintLayout {

    private lateinit var binding: ModelTopAlbumViewBinding

    constructor(
        context: Context?
    ) : this(context, null)

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        context ?: return
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.model_top_album_view, this, true)
//        val padding: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, resources.displayMetrics).toInt()
//        val imageSize = context.resources.displayMetrics.widthPixels / 2 - (padding * 2)
//
//
//        val lp = binding.modelTopAlbumArtwork.layoutParams
//        lp.width = imageSize
//        lp.height = imageSize
//        binding.modelTopAlbumArtwork.layoutParams = lp
    }

    @ModelProp
    fun setAlbum(album: Album) {
        binding.modelTopAlbumArtist.text = album.artist.name
        binding.modelTopAlbumTrack.text = album.name

        val list = album.imageList

        val imageUrl = if (list == null) {
            ""
        } else if (list.size < 3) {
            list.last().imageUrl
        } else {
            list[3].imageUrl
        }

        GlideApp.with(context)
            .load(imageUrl)
            .fitCenter()
            .error(R.drawable.no_image)
            .into(binding.modelTopAlbumArtwork)

        if (!TextUtils.isEmpty(album.url)) {
            binding.modelTopAlbumCard.setOnClickListener {
                val customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(context, Uri.parse(album.url))
            }
        }
    }

    @ModelProp
    fun setImageSize(size: Int) {
        val padding: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, resources.displayMetrics).toInt()
        val lp = binding.modelTopAlbumArtwork.layoutParams
        val imageSize = size - (padding)
        lp.width = imageSize
        lp.height = imageSize
        binding.modelTopAlbumArtwork.layoutParams = lp
    }
}
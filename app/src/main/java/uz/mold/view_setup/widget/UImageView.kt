package uz.mold.view_setup.widget

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uz.mold.view_setup.VS
import uz.mold.view_setup.VSUtil

class UImageView : AppCompatImageView {

    private var dowloandErrorImageRec: Int = 0
    private var dowloandPlaceHolderImageRec: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setViewId(id: Int): UImageView {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    fun setViewImageUrl(url: String, dowloandErrorImageRec: Int = 0, dowloandPlaceHolderImageRec: Int = 0): UImageView {
        var requestOption: RequestOptions? = null
        if (dowloandErrorImageRec != 0) {
            this.dowloandErrorImageRec = dowloandErrorImageRec
            requestOption = RequestOptions().error(dowloandErrorImageRec)
        }
        if (dowloandPlaceHolderImageRec != 0) {
            this.dowloandPlaceHolderImageRec = dowloandPlaceHolderImageRec
            requestOption = requestOption?.placeholder(dowloandPlaceHolderImageRec) ?: RequestOptions().placeholder(
                dowloandErrorImageRec
            )
        }
        if (requestOption != null)
            Glide.with(context)
                .load(url.trim())
                .apply(requestOption)
                .into(this)
        else Glide.with(context)
            .load(url.trim())
            .into(this)

        return this
    }

    fun setViewImageRes(imgRes: Int): UImageView {
        if (imgRes != 0)
            this.setImageResource(imgRes)
        return this
    }

    fun setViewImageBitmap(bitmap: Bitmap): UImageView {
        this.setImageBitmap(bitmap)
        return this
    }

    fun setViewSrc(src: Any? = null, dowloandErrorImageRec: Int, dowloandPlaceHolderImageRec: Int): UImageView {
        when (src ?: return this) {
            is Int -> this.setViewImageRes(src as Int)
            is Bitmap -> this.setViewImageBitmap(src as Bitmap)
            is String -> setViewImageUrl(
                src as String,
                dowloandErrorImageRec = dowloandErrorImageRec,
                dowloandPlaceHolderImageRec = dowloandPlaceHolderImageRec
            )
            else -> throw UnsupportedOperationException("text is not Int(String Resource) or CharSequence")
        }

        return this
    }


    fun setSize(height: Int = 0, width: Int = 0): UImageView {
        var params: LayoutParams? = this.layoutParams
        if (params == null) {
            params = LayoutParams(
                if (width > 0) VSUtil.dpToPx(
                    context,
                    width
                ) else if (width == LayoutParams.WRAP_CONTENT) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT,
                if (height > 0) VSUtil.dpToPx(
                    context,
                    height
                ) else if (height == LayoutParams.WRAP_CONTENT) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT
            )
        } else {
            if (height != 0)
                params.height = VSUtil.dpToPx(this.context, height)
            if (width != 0)
                params.width = VSUtil.dpToPx(this.context, width)
        }
        this.layoutParams = params
        return this
    }

    fun setScalerType(type: ScaleType): UImageView {
        this.scaleType = type
        return this
    }

    fun setOnClickListener(command: () -> Unit): UImageView {
        this.setOnClickListener { v -> command.invoke() }
        return this
    }

    companion object {

        @JvmOverloads
        fun create(
            content: Any,
            resId: Int = -1,
            width: Int = 0,
            height: Int = 0,
            src: Any? = null,
            command: (() -> Unit)? = null,
            scaleType: ScaleType = ScaleType.CENTER_CROP,
            dowloandErrorImageRec: Int = 0,
            dowloandPlaceHolderImageRec: Int = 0
        ): UImageView {

            val view = UImageView(VS.castToContext(content))
                .setViewId(resId)
                .setSize(height, width)
                .setScalerType(scaleType)


            src?.let { view.setViewSrc(it, dowloandErrorImageRec, dowloandPlaceHolderImageRec) }
            command?.let { view.setOnClickListener(it) }

            return view
        }
    }

}

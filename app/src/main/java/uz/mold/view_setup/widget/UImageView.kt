package uz.mold.view_setup.widget

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uz.mold.view_setup.VS

/**
 * UImageView child AppCompatImageView
 * this component use show images and frames
 * and automatic dowloand image from url
 * */
class UImageView : AppCompatImageView {

    private var dowloandErrorImageRec: Int = -1
    private var dowloandPlaceHolderImageRec: Int = -1

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * fun setViewId set id to View
     *
     * @param id is view id
     *
     *@return UImageView
     * */
    fun setViewId(id: Int): UImageView {
        if (id != -1) {
            super.setId(id)
        }
        return this
    }

    /**
     * fun  setViewImageUrl  set view image url and automatic download Image from url. Download image use Glide
     *
     *@param url is  image url
     *@param dowloandErrorImageRec when an error occurs when downloading an image
     *@param dowloandPlaceHolderImageRec is download place holder image
     *
     *@return UImageView
     * */
    fun setViewImageUrl(
        url: String,
        dowloandErrorImageRec: Int = -1,
        dowloandPlaceHolderImageRec: Int = -1
    ): UImageView {
        var requestOption: RequestOptions? = null
        if (dowloandErrorImageRec != -1) {
            this.dowloandErrorImageRec = dowloandErrorImageRec
            requestOption = RequestOptions().error(dowloandErrorImageRec)
        }
        if (dowloandPlaceHolderImageRec != -1) {
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

    /**
     * fun set Image Resource to the ImageView
     *
     * @param imgRes the image resource
     *
     *@return UImageView
     * */
    fun setViewImageResource(imgRes: Int): UImageView {
        if (imgRes != -1)
            this.setImageResource(imgRes)
        return this
    }

    /**
     * fun set Bitmap image to the ImageView
     *
     * @param bitmap the bitmapImage
     *
     *@return UImageView
     * */
    fun setViewImageBitmap(bitmap: Bitmap): UImageView {
        this.setImageBitmap(bitmap)
        return this
    }

    /**
     * fun  setViewSrc  set image to the ImageView and automatic download Image from url or set Bitmap resource. Download image use Glide
     *
     *@param src image source
     *@param dowloandErrorImageRec when an error occurs when downloading an image
     *@param dowloandPlaceHolderImageRec download place holder image
     *
     *@return UImageView
     * */
    fun setViewSrc(src: Any? = null, dowloandErrorImageRec: Int, dowloandPlaceHolderImageRec: Int): UImageView {
        when (src ?: return this) {
            is Int -> this.setViewImageResource(src as Int)
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


    fun setViewLayoutParams(params: ViewGroup.LayoutParams?): UImageView {
        if (layoutParams != null)
            this.layoutParams = params
        return this
    }

    /**
     * set image sclaer Type
     *
     * @param type image view scaleType
     *
     *@return UImageView
     * */
    fun setViewScaleType(type: ScaleType?): UImageView {
        if (type != null)
            this.scaleType = type
        return this
    }

    /**
     * set clickListener to imageView
     *
     * @param command is clickAction listener
     *
     *@return UImageView
     * */
    fun setViewOnClickListener(command: () -> Unit): UImageView {
        this.setOnClickListener { v -> command.invoke() }
        return this
    }

    companion object {
        /**
         * create UImageView
         *
         * @param content is Activity or Fragment
         * @param resId is view unique resource id
         * @param layoutParams Layout Paramas  child ViewGroup.LayoutParams
         * @param src is Int(ResourceId) or String or Bitmap image src
         * @param command is view click action listener
         * @param scaleType is image view scaleType
         * @param dowloandErrorImageRec is image view dowloand error image
         * @param dowloandPlaceHolderImageRec is image view dowloand place holder image

         * @return UImageView
         */
        @JvmOverloads
        fun create(
            content: Any,
            resId: Int = -1,
            src: Any? = null,
            command: (() -> Unit)? = null,
            scaleType: ScaleType? = null,
            dowloandErrorImageRec: Int = -1,
            dowloandPlaceHolderImageRec: Int = -1,
            layoutParams: ViewGroup.LayoutParams? = null
        ): UImageView {

            val view = UImageView(VS.castToContext(content))
                .setViewId(resId)
                .setViewLayoutParams(layoutParams)
                .setViewScaleType(scaleType)


            src?.let { view.setViewSrc(it, dowloandErrorImageRec, dowloandPlaceHolderImageRec) }
            command?.let { view.setViewOnClickListener(it) }

            return view
        }
    }

}

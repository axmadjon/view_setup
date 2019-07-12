package uz.mold.view_setup.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.ImageView
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.variable.ValueString

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val variable = ValueString(100, "This is text in variable")

        val view = VS.ULinearLayout_Vertical(
            this, R.id.vs_content,
            VS.UToolbar(this, R.id.vs_toolbar, "Hello")
                .addMenu("dsfsdfs", iconResourse = R.drawable.ic_search_clear_24dp)
                .addMenu("asdfkasd", isSubMenu = true)
                .create(),
            VS.UTextView(this, R.id.test_text_view, "TextView"),
            VS.UEditText(this, R.id.vs_edit_text, variable),
            VS.UImageView(
                this,
                src = "https://images.pexels.com/photos/1020315/pexels-photo-1020315.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                width = ViewGroup.LayoutParams.MATCH_PARENT,
                height = 150,
                scaleType = ImageView.ScaleType.CENTER_CROP
            )
        )
        setContentView(view)
    }
}

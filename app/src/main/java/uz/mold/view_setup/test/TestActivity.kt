package uz.mold.view_setup.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import uz.mold.view_setup.R
import uz.mold.view_setup.VS
import uz.mold.view_setup.variable.ValueString

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val variable = ValueString(100, "This is text in variable")

        val view = VS.ULinearLayout_Vertical(
            this, R.id.vs_content,
            VS.UToolbar(this, R.id.vs_toolbar, "Hello"),
            VS.UTextView(this, R.id.test_text_view, "TextView"),
            VS.UEditText(this, R.id.vs_edit_text, variable)
        )
        setContentView(view)
    }
}

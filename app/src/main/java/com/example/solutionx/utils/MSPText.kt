package com.example.designersstore.presentation.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/**
 * This class will be used for Custom font text using the TextView which inherits the AppCompatTextView class.
 */
class MSPText (context: Context, attrs: AttributeSet)  : AppCompatTextView(context, attrs) {

    /**
     * The init block runs every time the class is instantiated.
     */
    init {
        // cal the function to apply the font to the components.
        applyFont()
    }

    /**
     * Applies a font to a TextView.
     */
    private fun applyFont(){
        // this is used to get file from the assets folder and set it to the title textView.
        val typeface:Typeface=
            Typeface.createFromAsset(context.assets,"Amsterdam-ZVGqm.ttf")
        setTypeface(typeface)
    }

}
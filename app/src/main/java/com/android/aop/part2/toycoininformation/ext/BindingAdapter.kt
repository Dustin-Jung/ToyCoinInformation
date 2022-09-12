package com.android.aop.part2.toycoininformation.ext

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("onEditorActionListener")
fun EditText.onEditorActionListener(
    f: Function1<Boolean, Unit>?
){
    if(f == null) setOnEditorActionListener(null)
    else setOnEditorActionListener{ _, ime,  _ ->
        f(ime == EditorInfo.IME_ACTION_DONE)
        false
    }
}
package com.example.someone.int20htesttask

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by someone on 22.02.17.
 */

class DateInputWatcher(val editText: EditText):TextWatcher{
    override fun afterTextChanged(s: Editable?) {
        var text = editText.text
        if(editText.text.length == 2 || text.length == 4 ) {
            editText.setText("${editText.text}.")
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}

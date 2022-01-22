package com.example.chromdb

import android.view.View
import com.google.android.material.snackbar.Snackbar

 fun View.showSnackBar(
    rString: Int,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, rString, length).show()
}
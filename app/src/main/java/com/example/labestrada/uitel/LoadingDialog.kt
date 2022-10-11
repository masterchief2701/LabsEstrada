package com.example.labestrada.uitel

import android.app.Activity
import android.app.AlertDialog
import com.example.labestrada.R

class LoadingDialog(val mActivity:Activity) {
    private lateinit var isDialog: AlertDialog
    fun startLoading(){
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item, null)

        val bulider = AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isDialog= bulider.create()
        isDialog.show()

    }
    fun isDismiss(){
        isDialog.dismiss()
    }
}
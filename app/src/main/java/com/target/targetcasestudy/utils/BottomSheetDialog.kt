package com.target.targetcasestudy.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.target.targetcasestudy.R

class BottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.bottom_sheet_layout,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.txt_description).text = arguments?.getString(Constants.INTENT_DESCRIPTION)
        view.findViewById<TextView>(R.id.txt_close).setOnClickListener { dismiss() }
        super.onViewCreated(view, savedInstanceState)
    }
}
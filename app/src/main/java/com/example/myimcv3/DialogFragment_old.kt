package com.example.myimcv3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.myimcv3.databinding.ActivityMainBinding
import com.example.myimcv3.databinding.DialogFragmentBinding
import com.google.android.material.snackbar.Snackbar

class SimpleDialog : DialogFragment() {
    private lateinit var binding: DialogFragmentBinding
    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): SimpleDialog {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = SimpleDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
       binding.tvTitle.text = arguments?.getString(KEY_TITLE)
        binding.tvSubTitle.text = arguments?.getString(KEY_SUBTITLE)
    }

    private fun setupClickListeners(view: View) {
        binding.btnPositive.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
        binding.btnNegative.setOnClickListener {

            dismiss()
        }
    }

}
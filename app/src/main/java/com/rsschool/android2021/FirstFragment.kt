package com.rsschool.android2021

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue : EditText
    private lateinit var maxValue : EditText
    private lateinit var fragmentMinMax: OnFragmentMinMax


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentMinMax = context as OnFragmentMinMax
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)
        SecondFragment.flagBack = false

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min: Int = if(minValue.text.isNotEmpty()) minValue.text.toString().toInt() else 0
            val max: Int = if(maxValue.text.isNotEmpty()) maxValue.text.toString().toInt() else 0
            fragmentMinMax.transferFragmentMinMax(min,max)
        }

        val textWatcher = object : TextWatcher {

            //до изменения
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            //во время текста
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count < 1 && count > Int.MAX_VALUE) generateButton?.let { it.isEnabled = false }
            }

            //после ввода текста
            override fun afterTextChanged(s: Editable?) {

                val min = if(minValue.text.isNotEmpty()) minValue.text.toString().toInt() else null
                val max = if(maxValue.text.isNotEmpty()) maxValue.text.toString().toInt() else null

                if(min==null || max==null || min>max ){
                    generateButton?.let { it.isEnabled = false }
                }else{
                    generateButton?.let { it.isEnabled = true }
                }
            }
        }

        minValue.addTextChangedListener(textWatcher)
        maxValue.addTextChangedListener(textWatcher)
    }

   interface OnFragmentMinMax {
        fun transferFragmentMinMax (min: Int, max : Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            return FirstFragment().apply {
                arguments = bundleOf(PREVIOUS_RESULT_KEY to previousResult)
            }
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}
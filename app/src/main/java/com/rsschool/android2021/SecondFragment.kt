package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private var backButton: Button? = null
    private var result: TextView? = null
    private lateinit var fragmentValue: OnFragmentValue


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentValue = context as OnFragmentValue
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)
        flagBack = true

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            fragmentValue.transferFragmentValue(result?.text.toString().toInt())
            //TODO

        }
    }

    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }

    interface OnFragmentValue{
        fun transferFragmentValue(value: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            return SecondFragment().apply {
                arguments = bundleOf(MIN_VALUE_KEY to min, MAX_VALUE_KEY to max)
            }
        }
        @JvmStatic
        var flagBack = false

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}
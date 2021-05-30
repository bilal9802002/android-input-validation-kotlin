package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.rules.Rules
import com.example.myapplication.rules.Status

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    lateinit var viewBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MainFragmentBinding.inflate(inflater).apply {
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.textInputLayout1.apply {
            val rule =
                Rules.notEmpty("Can't be empty") and Rules.alphabetsOnly("Invalid input") and Rules.allLowerCase("Must be in lowercase")

            editText?.doAfterTextChanged { input ->
                rule.validate(input?.toString()).let {
                    error = it.getErrorMessage()
                    isErrorEnabled = it.status == Status.ERROR
                }
            }
        }

        viewBinding.textInputLayout2.apply {
            val rule =
                Rules.notEmpty("Can't be empty") and Rules.numbersRange(500.0..5000.0, "Value must be in range [500, 5000]")

            editText?.doAfterTextChanged { input ->
                rule.validate(input?.toString()).let {
                    error = it.getErrorMessage()
                    isErrorEnabled = it.status == Status.ERROR
                }
            }
        }
    }
}
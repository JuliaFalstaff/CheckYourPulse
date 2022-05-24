package com.example.checkyourpulse.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.checkyourpulse.databinding.AddDialogFragmentBinding
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.utils.getCurrentDate
import com.example.checkyourpulse.utils.getCurrentTime
import java.util.*

class AddDialogFragment : DialogFragment() {

    private var _binding: AddDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSaveDialogListener: IOnSaveDialogListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            val current = Calendar.getInstance().time
            onSaveDialogListener?.onSave(
                getCurrentDate()?.let { date ->
                    HealthInfo(
                        date = date,
                        time = getCurrentTime(),
                        pressureLow = binding.pressureLowEditText.text.toString().toInt(),
                        pressureHigh = binding.pressureHighEditText.text.toString().toInt(),
                        pulse = binding.pulseEditText.text.toString().toInt()
                    )
                }
            )
            dismiss()
        }
    }

    fun setOnAddDialogListener(listener: IOnSaveDialogListener) {
        onSaveDialogListener = listener
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {
        fun newInstance() = AddDialogFragment()
    }
}
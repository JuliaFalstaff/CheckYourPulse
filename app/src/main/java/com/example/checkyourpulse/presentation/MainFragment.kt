package com.example.checkyourpulse.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.checkyourpulse.databinding.FragmentMainBinding
import com.example.checkyourpulse.domain.AppState
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.presentation.adapter.HealthAdapter
import com.example.checkyourpulse.presentation.dialog.AddDialogFragment
import com.example.checkyourpulse.presentation.dialog.IOnSaveDialogListener
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MainFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by inject()
    private var adapter: HealthAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.loadData()
        binding.addFab.setOnClickListener { openDialogFragment() }
    }

    private fun openDialogFragment() {
        val dialogFragment = AddDialogFragment.newInstance()
        dialogFragment.setOnAddDialogListener(object : IOnSaveDialogListener {
            override fun onSave(health: HealthInfo) {
                viewModel.saveData(health)
            }
        })
        dialogFragment.show(parentFragmentManager.beginTransaction(), TAG_ADD)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                val listData = appState.dataHealth as? List<HealthInfo>
                listData?.let {
                    binding.pulseInfoRecyclerView.adapter = adapter
                    adapter = HealthAdapter(it)
                    adapter?.setData(it)
                    Log.d("TAG Fragment Success", "list: $listData")
                }
            }
            is AppState.Loading -> {
                Toast.makeText(
                    requireContext(),
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG Fragment Error", "Error: ${appState.error.message}")
            }

        }
    }


    companion object {
        fun newInstance() = MainFragment()
        private const val TAG_ADD = "Add"
    }
}
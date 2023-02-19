package com.example.mafia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mafia.R
import com.example.mafia.databinding.FragmentHomeBinding
import com.example.mafia.language.Language
import com.example.mafia.language.SharedPrefsManager
import com.example.mafia.viewModel.HomeViewModel
import com.example.mafia.viewModel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) {"View was destroyed"}

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModel by viewModel<HomeViewModel>()

    private val prefsManager: SharedPrefsManager by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            toolbarHome.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.info_button -> {
                        true
                    }
                    R.id.language_button -> {
                        when (prefsManager.language) {
                            Language.RU -> {
                                prefsManager.language = Language.EN
                            }
                            Language.EN -> {
                                prefsManager.language = Language.RU
                            }
                        }
                        activity?.recreate()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }


            btnPlay.setOnClickListener {
                if(editText.text.isBlank() || editText.text.isEmpty() || editText.text.toString() == ""){
                    Toast.makeText(requireContext(), getText(R.string.enter_players_number), Toast.LENGTH_LONG)
                        .show()
                }else if(editText.text.toString().toInt() < 6){
                    Toast.makeText(requireContext(), getString(R.string.warning_need_more_players), Toast.LENGTH_LONG)
                        .show()
                }else{
                    sharedViewModel.select(editText.text.toString().toInt())
                    clearDB()
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToAddPlayers()
                    )
                }

            }
        }
    }

    private fun clearDB(){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.clearDB()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
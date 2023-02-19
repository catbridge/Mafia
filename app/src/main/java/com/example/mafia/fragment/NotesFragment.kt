package com.example.mafia.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mafia.adapter.RoleAdapter
import com.example.mafia.databinding.FragmentNotesBinding
import com.example.mafia.extensions.addHorizontalSpaceDecoration
import com.example.mafia.viewModel.NotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment: Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<NotesViewModel>()


    private val adapter = RoleAdapter(){
        viewLifecycleOwner.lifecycleScope.launch{
            withContext(Dispatchers.IO){
                viewModel.deleteRole(it)
                updateUI()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNotesBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding

            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recyclerView.adapter = adapter
            recyclerView.addHorizontalSpaceDecoration(50)
            showPlayers()

            finishButton.setOnClickListener {
                findNavController().navigate(
                    NotesFragmentDirections.actionNotesToHome()
                )
            }
        }

    }

    private suspend fun updateUI(){
        adapter.submitList(viewModel.getRoles())
    }

    private fun showPlayers(){
        viewModel.dataFlow
            .onEach { it ->
                adapter.submitList(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}
package com.example.mafia.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mafia.databinding.FragmentAddPlayersBinding
import androidx.navigation.ui.setupWithNavController
import com.example.mafia.R
import com.example.mafia.extensions.setImageDrawableWithAnimation
import com.example.mafia.utils.Constants
import com.example.mafia.utils.DialogManager
import com.example.mafia.viewModel.AddPlayersViewModel
import com.example.mafia.viewModel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddPlayersFragment: Fragment() {
    private var _binding: FragmentAddPlayersBinding? = null
    private val binding get() = requireNotNull(_binding) {"View was destroyed"}

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel by viewModel<AddPlayersViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddPlayersBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbarAdd.setupWithNavController(findNavController())
            imageView.setImageResource(R.drawable.unknown)

            sharedViewModel.numberPlayers.observe(viewLifecycleOwner, Observer<Int> {
                    val list = viewModel.createRoleList(it).toMutableList()
                    var role = list.random()
                    button.setOnClickListener {
                        var name = nameText.text.toString()
                        if (nameText.text.isNotEmpty() && list.size >= 1) {
                            if (role != Constants.LEADER) addPlayer(name, role)
                            if (list.size != 1) {
                                imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.unknown), duration = 100)
                                list.remove(role)
                            }
                            role = list.random()
                            if (list.size == 1) {
                                button.text = getText(R.string.start)
                                button.setOnClickListener {
                                    name = nameText.text.toString()
                                    if (role != Constants.LEADER) addPlayer(name, role)
                                    showDialog()
                                }
                            }
                            nameText.text.clear()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.warning_enter_name),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    imageView.setOnClickListener {
                        showPictures(role)
                    }
            })

        }
    }

    private fun addPlayer(name: String, role: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.addPlayer(name, role)
        }

    }

    private fun showPictures(role: String){
        with(binding){
            when(role){
                Constants.MAFIA -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.mafiya))
                Constants.POLICE -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.kop))
                Constants.PROSTITUTE -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.prostitutka))
                Constants.CIVIL -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.mirniy))
                Constants.DOCTOR -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.vrach))
                Constants.LEADER -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.vedushchiy))
                Constants.DON -> imageView.setImageDrawableWithAnimation(requireContext().getDrawable(R.drawable.don))
            }
        }
    }

    private fun showDialog() {
        DialogManager.showDialog(
            activity as AppCompatActivity,
            R.string.dialog_txt,
            object : DialogManager.Listener{
                override fun onClick() {
                    findNavController().navigate(
                        AddPlayersFragmentDirections.actionAddPlayersToNotes()
                    )
                }

            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
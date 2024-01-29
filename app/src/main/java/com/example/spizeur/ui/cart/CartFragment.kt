package com.example.spizeur.ui.cart

import CartAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentCartBinding
import com.example.spizeur.domain.UserRepository
import com.example.spizeur.ui.commandInfo.CommandInfoActivity
import com.example.spizeur.ui.settings.SettingsActivity
import timber.log.Timber

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: CartViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val isCartEmpty = vm.isCartEmpty()
        if (isCartEmpty) {
            val layoutResourceId = R.layout.fragment_empty_cart
            val rootView = inflater.inflate(layoutResourceId, container, false)
            _binding = FragmentCartBinding.inflate(inflater, container, false)
            return rootView

        }
        
        else {
            val layoutResourceId = R.layout.fragment_cart
            val rootView = inflater.inflate(layoutResourceId, container, false)
            _binding = FragmentCartBinding.inflate(inflater, container, false)
            recyclerView = rootView.findViewById(R.id.listProductCart)
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = CartAdapter(vm.getCartProducts())
            val commandButton = rootView.findViewById<Button>(R.id.CommandButton)
            commandButton.setOnClickListener {
                val intent = Intent(requireContext(), CommandInfoActivity::class.java)
                startActivity(intent)
            }
            return rootView
        }





    }

}
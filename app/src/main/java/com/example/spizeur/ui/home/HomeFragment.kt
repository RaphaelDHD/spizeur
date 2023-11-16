package com.example.spizeur.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spizeur.R
import com.example.spizeur.databinding.FragmentHomeBinding
import com.example.spizeur.ui.adapter.CategoryAdapter
import timber.log.Timber


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: HomeViewModel
    private lateinit var parentRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this).get(HomeViewModel::class.java)
        vm.fetchProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize the RecyclerView
        parentRecyclerView = root.findViewById(R.id.parent_recycler_view)
        parentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter()
        parentRecyclerView.adapter = categoryAdapter

        setUpViews()
        vm.productsLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response != null && response.isSuccessful && response.body() != null) {
                // Assuming sortProductsByCategory returns a list of categories
                renderCategoryList()
            } else {
                // Handle the error case if needed
                Timber.e("Error fetching products")
            }
        })
        return root
    }

    private fun setUpViews() {
        parentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter()
        parentRecyclerView.adapter = categoryAdapter

    }

    private fun renderCategoryList() {
        val list = vm.sortProductsByCategory()
        categoryAdapter.addData(list)
        categoryAdapter.notifyDataSetChanged()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
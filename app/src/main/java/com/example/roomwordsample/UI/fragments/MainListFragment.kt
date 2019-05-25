package com.example.roomwordsample.UI.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.books_list_fragment.*
import kotlinx.android.synthetic.main.books_list_fragment.view.*

import java.lang.RuntimeException
import androidx.appcompat.widget.SearchView
import com.example.roomwordsample.Constants.AppConstants
import com.example.roomwordsample.R
import com.example.roomwordsample.adapters.RVBookAdapter
import com.example.roomwordsample.models.Book
import com.example.roomwordsample.viewModel.BookViewModel


class MainListFragment: Fragment() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var booksAdapter: RVBookAdapter
    var listenerTool : ClickedBookListener? = null

    interface ClickedBookListener{
        fun managePortraitItemClick(book: Book)
        fun managedLandscapeItemClick(book: Book)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedBookListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(com.example.roomwordsample.R.layout.books_list_fragment, container, false)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        initRecyclerView(resources.configuration.orientation, view)




        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = container.rv_list

        booksAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            RVBookAdapter(books = AppConstants.emptybooks, clickListener = { book: Book -> listenerTool?.managePortraitItemClick(book)})
        }else {
            RVBookAdapter(books = AppConstants.emptybooks, clickListener = { book: Book -> listenerTool?.managedLandscapeItemClick(book)})
        }

        recyclerview.apply {
            adapter = booksAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true






    }

}

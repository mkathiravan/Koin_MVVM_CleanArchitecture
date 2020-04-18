package net.kathir.koinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.kathir.koinmvvm.adapter.UserListAdapter
import net.kathir.koinmvvm.utils.LoadingState
import net.kathir.koinmvvm.viewModel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()

    private lateinit var adapter: UserListAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        adapter = UserListAdapter(this)
        recyclerView?.adapter = adapter
        getIssuesList()

    }


    private fun getIssuesList() {

                userViewModel.data.observe(this, Observer {

                        issueList ->
                    adapter.setIssuesList(issueList)        })

    }
}

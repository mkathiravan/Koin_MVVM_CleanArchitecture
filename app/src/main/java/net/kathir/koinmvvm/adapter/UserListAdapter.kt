package net.kathir.koinmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.issue_child.view.*
import net.kathir.koinmvvm.R
import net.kathir.koinmvvm.model.entity.GithubUser

class UserListAdapter(var ctx: Context) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>()
{

    private var mList: List<GithubUser> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.issue_child,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setIssuesList(items: List<GithubUser>) {
        this.mList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userInfo?.text = mList[position].login
        holder.userNoteId?.text = mList[position].node_id
        holder.userType?.text = mList[position].type
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val userInfo = view.user_login
        val userNoteId = view.user_noteId
        val userType = view.user_type


    }
}
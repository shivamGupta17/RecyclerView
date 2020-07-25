package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.list_layout.*


class MainActivity : AppCompatActivity()
{

    lateinit var mRecyclerView : RecyclerView
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        mRecyclerView = findViewById(R.id.listView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = layoutManager
//        var muserName = findViewById<TextView>(R.id.userName)
//        var muserStatus = findViewById<TextView>(R.id.userStatus)
        logRecyclerView()

    }

        private fun logRecyclerView()
        {
            var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Users , UsersViewHolder>(

                Users::class.java,
                R.layout.list_layout,
                UsersViewHolder::class.java,
                mDatabase
            ){
                override fun populateViewHolder(viewHolder: UsersViewHolder, model: Users, position: Int)
                {
                    viewHolder.mView.userName.setText(model.name)
                }

            }

            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }

    class UsersViewHolder(var mView: View?) : RecyclerView.ViewHolder(mView!!) {

    }



}
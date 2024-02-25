package com.example.project1702.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project1702.Adapter.LeaderAdapter
import com.example.project1702.Domain.UserModel
import com.example.project1702.R
import com.example.project1702.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this@LeaderActivity.window
        window.statusBarColor = ContextCompat.getColor(this@LeaderActivity, R.color.grey)


        binding.apply {
            scoreTop1Txt.text = loadData().get(0).score.toString()
            scoreTop2Txt.text = loadData().get(1).score.toString()
            scoreTop3Txt.text = loadData().get(2).score.toString()
            titleTop1Txt.text = loadData().get(0).name
            titleTop2Txt.text = loadData().get(1).name
            titleTop3Txt.text = loadData().get(2).name

            val drawableResourceId1: Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(pic1)

            val drawableResourceId2: Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(pic2)

            val drawableResourceId3: Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(pic3)


            bottomMenu.setItemSelected(R.id.Board)
            bottomMenu.setOnItemSelectedListener {
                if (it == R.id.home) {
                    startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                }
            }

            var list: MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)

            leaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }
        }
    }

    // you can get below list from your API service,this is a example list
    private fun loadData(): MutableList<UserModel> {
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1, "Sophia", "person1", 4750))
        users.add(UserModel(2, "Hector", "person2", 4230))
        users.add(UserModel(3, "Arthur", "person3", 3673))
        users.add(UserModel(4, "JKenyon Pather", "person4", 3020))
        users.add(UserModel(5, "Emilyfirst", "person5", 3015))
        users.add(UserModel(6, "Owen", "person6", 2950))
        users.add(UserModel(7, "Anna", "person7", 2865))
        users.add(UserModel(8, "Michael55Davis", "person8", 2670))
        users.add(UserModel(9, "Cybill12", "person9", 2180))
        users.add(UserModel(10, "Wendy144", "person9", 1980))
        return users
    }
}
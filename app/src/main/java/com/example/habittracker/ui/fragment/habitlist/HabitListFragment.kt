package com.example.habittracker.ui.fragment.habitlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.data.models.Habits
import com.example.habittracker.ui.fragment.habitlist.adapters.HabitListAdapter
import com.example.habittracker.ui.viewmodels.HabitsViewModel
import kotlinx.android.synthetic.main.fragment_habit_list.*

class HabitListFragment : Fragment(R.layout.fragment_habit_list) {
    private lateinit var habitList: List<Habits>
    private lateinit var habitViewModel: HabitsViewModel
    private lateinit var adapter: HabitListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HabitListAdapter()
        rv_habits.adapter = adapter
        rv_habits.layoutManager = LinearLayoutManager(context)
        habitViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        habitViewModel.getAllHabits.observe(viewLifecycleOwner, {
            adapter.setData(it)
            habitList = it
            if (it.isEmpty()) {
                rv_habits.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_habits.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
        setHasOptionsMenu(true)
        swipeToRefresh.setOnRefreshListener {
            adapter.setData(habitList)
            swipeToRefresh.isRefreshing = false
        }

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_createHabitItem)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_delete -> habitViewModel.deleteAllHabits()
        }
        return super.onOptionsItemSelected(item)
    }


}
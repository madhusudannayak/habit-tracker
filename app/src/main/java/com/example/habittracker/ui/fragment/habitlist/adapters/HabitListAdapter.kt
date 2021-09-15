package com.example.habittracker.ui.fragment.habitlist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.data.models.Habits
import com.example.habittracker.ui.fragment.habitlist.HabitListFragmentDirections
import com.example.habittracker.utils.Calculation
import kotlinx.android.synthetic.main.recycler_habit_item.view.*

class HabitListAdapter : RecyclerView.Adapter<HabitListAdapter.MyViewHolder>() {
    var habitList = emptyList<Habits>()
    val TAG = "HabitListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at : $position ")
                Log.d(TAG, "ID: ${habitList[position].id} ")
                val action =
                    HabitListFragmentDirections.actionHabitListToUpdateHabitItem(habitList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_habit_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentHabit = habitList[position]
        holder.itemView.iv_habit_icon.setImageResource(currentHabit.imageId)
        holder.itemView.tv_item_title.text = currentHabit.habit_title
        holder.itemView.tv_item_description.text = currentHabit.habit_description
        holder.itemView.tv_timeElapsed.text =
            Calculation.calculateTimeBetweenDates(currentHabit.habit_startTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Since ${currentHabit.habit_startTime}"

    }

    override fun getItemCount() = habitList.size

    fun setData(habitList : List<Habits>){
        this.habitList = habitList
        notifyDataSetChanged()

    }


}
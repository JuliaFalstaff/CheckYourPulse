package com.example.checkyourpulse.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checkyourpulse.databinding.ItemPulseDataRecyclerBinding
import com.example.checkyourpulse.databinding.ItemPulseDateRecyclerBinding
import com.example.checkyourpulse.databinding.ItemPulseNoDataRecyclerBinding
import com.example.checkyourpulse.domain.model.HealthInfo
import com.example.checkyourpulse.utils.convertDateInMillis
import com.example.checkyourpulse.utils.convertToDDMMYYYOnly
import com.example.checkyourpulse.utils.convertToDayString
import com.example.checkyourpulse.utils.convertToString
import java.lang.RuntimeException


class HealthAdapter(var list: List<HealthInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setData(newList: List<HealthInfo>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEALTH_NO_DATE_INFO_TYPE -> {
                val binding: ItemPulseNoDataRecyclerBinding = ItemPulseNoDataRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                NoDateViewHolder(binding)
            }
            HEALTH_DATA_INFO_TYPE -> {
                val binding: ItemPulseDataRecyclerBinding = ItemPulseDataRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                DataInfoViewHolder(binding)
            }
            else -> throw RuntimeException(VIEWHOLDER_TYPE_ERROR)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoDateViewHolder -> holder.bind(list[position])
            is DataInfoViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return HEALTH_DATA_INFO_TYPE
        val currentPosition = list[position].date.convertToDayString()

        val prevPosition = list[position-1].date.convertToDayString()
        Log.d("TAG ADAPTER", "current: $currentPosition  prev: $prevPosition")
        return if (currentPosition > prevPosition) {
            HEALTH_DATA_INFO_TYPE
        } else {
            HEALTH_NO_DATE_INFO_TYPE
        }

//        val currentDate = convertDateInMillis(list[position].date)
//        val lastDate = convertDateInMillis(list[position - 1].date)
//
////        val currentDate = LocalDate.parse(list[position].time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault()))
////        val lastDate = LocalDate.parse(list[position - 1].time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault()))
////        val currentDate = LocalDate.parse(list[position].time)
////        val lastDate = LocalDate.parse(list[position - 1].time)
//        return when (currentDate == lastDate) {
//            true -> HEALTH_DATA_INFO_TYPE
//            false -> DATE_VIEW_TYPE
//        }
    }

    inner class NoDateViewHolder(private val binding: ItemPulseNoDataRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(health: HealthInfo) {
            binding.timeOfDay.text = health.time
            binding.pressureHigh.text = health.pressureHigh.toString()
            binding.pressureLow.text = health.pressureLow.toString()
            binding.heartPulse.text = health.pulse.toString()
        }
    }

    inner class DataInfoViewHolder(private val binding: ItemPulseDataRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(health: HealthInfo) {
            binding.dateHeaderView.text = health.date.convertToString()
            binding.timeOfDay.text = health.time
            binding.pressureHigh.text = health.pressureHigh.toString()
            binding.pressureLow.text = health.pressureLow.toString()
            binding.heartPulse.text = health.pulse.toString()
        }
    }

    companion object {
        private const val HEALTH_NO_DATE_INFO_TYPE = 0
        private const val HEALTH_DATA_INFO_TYPE = 1
        private const val VIEWHOLDER_TYPE_ERROR = "Unknown type of viewholder"
    }

}
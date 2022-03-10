package com.sofiamarchinskya.cleanarchapi.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sofiamarchinskya.cleanarchapi.databinding.PersonItemBinding
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel

class PeopleListAdapter(
    private var onClick: (UIModel) -> Unit
) :
    RecyclerView.Adapter<PeopleListAdapter.PersonViewHolder>() {
    private var list: List<UIModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(list: List<UIModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class PersonViewHolder(private val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UIModel) {
            binding.apply {
                name.text = data.name
                if (data.isFavourite)
                    star.visibility = View.VISIBLE
                else star.visibility = View.INVISIBLE
                root.setOnClickListener { onClick.invoke(data) }
            }
        }
    }
}
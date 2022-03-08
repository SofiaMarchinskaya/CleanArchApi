package com.sofiamarchinskya.cleanarchapi.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sofiamarchinskya.cleanarchapi.databinding.PersonItemBinding
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

class PeopleListAdapter(
    private var onClick: (DomainPersonModel) -> Unit
) :
    RecyclerView.Adapter<PeopleListAdapter.PersonViewHolder>() {
    private var list: List<DomainPersonModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(list: List<DomainPersonModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class PersonViewHolder(private val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DomainPersonModel) {
            binding.apply {
                name.text = data.name
                root.setOnClickListener { onClick.invoke(data) }
            }
        }
    }
}
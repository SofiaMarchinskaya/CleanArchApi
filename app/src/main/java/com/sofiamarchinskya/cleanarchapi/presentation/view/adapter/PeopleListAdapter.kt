package com.sofiamarchinskya.cleanarchapi.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.databinding.PersonItemBinding

class PeopleListAdapter(
    private var onClick: (String) -> Unit,
    private var onCheck: (Person, Boolean) -> Unit
) :
    RecyclerView.Adapter<PeopleListAdapter.PersonViewHolder>() {
    private var list: List<Person> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(list: List<Person>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class PersonViewHolder(private val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Person) {
            binding.apply {
                name.text = data.name
                star.isChecked = data.isfavorite
                star.setOnClickListener {
                    onCheck.invoke(data, (it as CheckBox).isChecked)
                }
                root.setOnClickListener { onClick.invoke(data.url) }
            }
        }
    }
}

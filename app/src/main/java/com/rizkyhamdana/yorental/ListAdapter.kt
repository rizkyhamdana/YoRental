package com.rizkyhamdana.yorental

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizkyhamdana.yorental.data.DataResponse
import com.rizkyhamdana.yorental.databinding.ListItemBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var listKendaraan = ArrayList<DataResponse>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataResponse)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(kendaraan: List<DataResponse>?) {
        if (kendaraan == null) return
        this.listKendaraan.clear()
        this.listKendaraan.addAll(kendaraan)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val products = listKendaraan[position]
        holder.bind(products)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listKendaraan[position])
        }
    }

    override fun getItemCount(): Int = listKendaraan.size


    class ListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kendaraan: DataResponse) {
            with(binding) {
                tvNamaKendaraan.text = kendaraan.nama
                tvJenisKendaraan.text = kendaraan.jenis
                Glide.with(itemView.context)
                    .load(kendaraan.gambar)
                    .into(imgKendaraan)
            }
        }
    }
}
package com.interrapidisimo.android.core.valueObjet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interrapidisimo.android.R
import com.interrapidisimo.android.core.utils.BaseViewHolder
import com.interrapidisimo.android.databinding.LocalitiesCardViewBinding
import com.interrapidisimo.android.localities.data.provider.remote.model.LocalitiesResponseItem
import java.text.NumberFormat

class AdapterLocalities(
    private val context: Context,
    private val localitiesResponseItem: List<Any>,
    private val onItemClickListener: (LocalitiesResponseItem) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(context)
        return when (viewType) {

            VIEW_TYPE_HEADER -> {
                val view = inflater.inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }

            VIEW_TYPE_MOVIE -> {
                val itemBinding = LocalitiesCardViewBinding.inflate(inflater, parent, false)
                MainViewHolder(itemBinding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = localitiesResponseItem.size


    override fun getItemViewType(position: Int): Int {
        return if (localitiesResponseItem[position] is String) VIEW_TYPE_HEADER else VIEW_TYPE_MOVIE
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(localitiesResponseItem[position] as String, position)
            is MainViewHolder -> holder.bind(localitiesResponseItem[position] as LocalitiesResponseItem, position)
        }
    }

    // ViewHolder para el Header
    inner class HeaderViewHolder(view: View) : BaseViewHolder<String>(view) {
        private val title: TextView = view.findViewById(R.id.headerTitle)
        override fun bind(item: String, position: Int) {
            title.text = item
        }
    }

    inner class MainViewHolder(private val binding: LocalitiesCardViewBinding) :
        BaseViewHolder<LocalitiesResponseItem>(binding.root) {

        override fun bind(item: LocalitiesResponseItem, position: Int) = with(binding) {

            tvTitleMovie.text = item.nombre

            val formattedNumber = NumberFormat.getInstance().format(item.idCentroServicio)
            tvDescripShort.text = formattedNumber


            binding.contentCardView.setOnClickListener {
                onItemClickListener(item)
            }

        }

    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_MOVIE = 1
    }

}
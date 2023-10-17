package com.frogs.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.frogs.R
import com.frogs.data.FrogModel

interface ClickListener {
    fun click(id: Int)
}

class FrogAdapter(
    private val context: Context,
    private val list: List<FrogModel>,
    private val listener: ClickListener
) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = list[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        if (convertView == null) {
            view = View(context)

            val inflaiter = LayoutInflater.from(context)
            view = inflaiter.inflate(R.layout.frog_item_layout, parent, false)
        } else {
            view = convertView
        }

        if (list[position].isOpen) {
            view.findViewById<ImageView>(R.id.image).setImageResource(list[position].imageId)
        } else view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.frog_new)

        view.setOnClickListener {
            listener.click(list[position].id)
        }


        return view
    }


}
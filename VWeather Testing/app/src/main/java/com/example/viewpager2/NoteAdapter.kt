package com.example.viewpager2

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item_note.view.*
import kotlinx.android.synthetic.main.card_items_city.view.*


class NoteAdapter(var note_remove: ImageButton)  : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var mode: Boolean = false
    private var noteList: ArrayList<Note> = ArrayList()
    private var onClickItem: ((Note) -> Unit)? = null
    private var onClickDeleteItem: ((Note) -> Unit)? = null
    var activate: Boolean = false

    fun addItems(items: ArrayList<Note>) {
//        this.cityList = items
//        notifyDataSetChanged()
        noteList.clear();
        noteList.addAll(items);
        this.notifyDataSetChanged();
    }

    fun setOnClickItem(callback: (Note) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnClickDeleteitem(callback: (Note) -> Unit) {
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(inflater.inflate(R.layout.card_item_note, parent, false))

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.bindView(note)
        holder.itemView.setOnClickListener { onClickItem?.invoke(note) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(note) }

        note_remove.setOnClickListener(){

            if (activate == false){
                activate = true
            }else{
                activate = false
            }
            notifyDataSetChanged()
        }
        if (activate == true) {
            holder.btnDelete.setVisibility(View.VISIBLE)
                holder.btnDelete.animate()
                    .translationX(0.0f)
                    .alpha(1.0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)

                        }
                    })
        } else{
            holder.btnDelete.animate()
                .translationX(holder.btnDelete.getHeight().toFloat())
                .alpha(0.0f)
                .setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        holder.btnDelete.setVisibility(View.INVISIBLE)
                    }
                })
        }
        if(mode== true){
            holder.itemView.note_item_linearLayout.setBackgroundResource(R.drawable.layout_city_item_light)
            holder.itemView.note_item_linearLayout.elevation = 10F
            holder.itemView.note_title.setTextColor(Color.parseColor("#121212"))
            holder.itemView.note_content.setTextColor(Color.parseColor("#121212"))
            holder.itemView.note_time.setTextColor(Color.parseColor("#121212"))
        }
        else{
            holder.itemView.note_item_linearLayout.setBackgroundResource(R.drawable.layout_city_item)
            holder.itemView.note_title.setTextColor(Color.parseColor("#dfdfdf"))
            holder.itemView.note_content.setTextColor(Color.parseColor("#dfdfdf"))
            holder.itemView.note_time.setTextColor(Color.parseColor("#dfdfdf"))
        }
    }
    fun activateButtons(activate: Boolean) {
        this.activate=activate
        notifyDataSetChanged() //need to call it for the child views to be re-created with buttons.
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class NoteViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


        private var id = view.findViewById<TextView>(R.id.note_id)
        private var title = view.findViewById<TextView>(R.id.note_title)
        private var content = view.findViewById<TextView>(R.id.note_content)
        private var time = view.findViewById<TextView>(R.id.note_time)
        var btnDelete = view.findViewById<ImageButton>(R.id.note_delete)

        fun bindView(note: Note) {
            id.text = note.id
            title.text = note.title
            content.text = note.content
            time.text = note.time

//            btnDelete.animate()
//                .translationX(btnDelete.getHeight().toFloat())
//                .alpha(0.0f)
//                .setDuration(300)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator) {
//                        super.onAnimationEnd(animation)
//                        btnDelete.setVisibility(View.INVISIBLE)
//                    }
//                })
        }
    }
    fun change_mode(){
        notifyDataSetChanged()
    }
}
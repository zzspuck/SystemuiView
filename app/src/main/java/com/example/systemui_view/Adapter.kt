package com.example.systemui_view

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var shortcutButtonItemList: List<ShortcutButtonItem> = emptyList()

    var context: Context? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType : Int
    ): RecyclerView.ViewHolder {

        println("onCreateViewHolder.....")

        context = viewGroup.context
        return when(viewType){

            ItemType.BRIGHTNESS.value -> {
                val view = View.inflate(viewGroup.context, R.layout.layout_shortcut_brightness_bar, null)
                BrightNessViewHolder(view)
            }

            ItemType.VOLUME.value -> {
                val view = View.inflate(viewGroup.context, R.layout.layout_volume_bar, null)
                VolumeViewHolder(view)
            }

            else -> {
                val view = View.inflate(viewGroup.context, R.layout.item_shortcut_button, null)
                ButtonViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        viewHolder : RecyclerView.ViewHolder,
        position: Int
    ) {

        when(viewHolder){
           is BrightNessViewHolder -> viewHolder.bind(shortcutButtonItemList[position])
           is VolumeViewHolder -> viewHolder.bind(shortcutButtonItemList[position])
           is ButtonViewHolder -> viewHolder.bind(shortcutButtonItemList[position])
       }
    }

    override fun getItemCount(): Int {
        Log.i("getItemCount", shortcutButtonItemList.size.toString())
        return shortcutButtonItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return shortcutButtonItemList[position].itemType.value
    }

    fun setShortcutButtonItemList(shortcutButtonItemList: List<ShortcutButtonItem>) {
        this.shortcutButtonItemList = shortcutButtonItemList
        notifyDataSetChanged()
    }


    inner class BrightNessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.iv_brightness_icon)
        }

        fun bind(shortcutButtonItem: ShortcutButtonItem) {

            imageView?.setImageResource(shortcutButtonItem.iconResId)
        }
    }



    inner class VolumeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.iv_volume_type)
        }

        fun bind(shortcutButtonItem: ShortcutButtonItem) {
            imageView?.setImageResource(shortcutButtonItem.iconResId)
        }
    }



   inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var button: ShortcutButtonView? = null

       init {
           button = itemView as ShortcutButtonView
       }

       fun bind(shortcutButtonItem: ShortcutButtonItem) {
            button?.setText(context?.resources?.getText(shortcutButtonItem.textResId) as String?)
            button?.setIconResource(shortcutButtonItem.iconResId )
        }
    }
}
package com.example.apiapp

import Product
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdaptor(private val context : Activity, private val productArrayList:List<Product>) :
RecyclerView.Adapter<MyAdaptor.MyViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem  = productArrayList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description

        holder.category.text = currentItem.category

      // image is in url form so
        // picaso we use

        Picasso.get().load(currentItem.thumbnail).into(holder.image)

    }
    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var title:TextView
        var image:ShapeableImageView
        var category:TextView
        var description:TextView

        init {
            title  = itemView.findViewById(R.id.productName)
            image = itemView.findViewById(R.id.productImage)
             description = itemView.findViewById(R.id.productDesc)
            category  = itemView.findViewById(R.id.productCat)
        }

    }

}
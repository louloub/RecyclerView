package com.example.recyclerView

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    // class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    class MyViewHolder(val webView: WebView) : RecyclerView.ViewHolder(webView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val webView = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false) as WebView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(webView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.textView.text = myDataset[position]

        holder.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return loadUrl(view, url)
            }

            override fun onPageFinished(
                webView: WebView,
                url: String
            ) {
                super.onPageFinished(webView, url)
            }

            private fun loadUrl(view: WebView, url: String): Boolean {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url)
                    return false
                } else if (url.startsWith("tel:")) {
                    val tel = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                    //startActivity(tel)
                    return true
                }
                return true
            }
        }

        holder.webView.loadUrl(myDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
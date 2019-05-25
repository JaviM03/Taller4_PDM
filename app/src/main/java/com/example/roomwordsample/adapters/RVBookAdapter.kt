package com.example.roomwordsample.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomwordsample.R
import com.example.roomwordsample.models.Book
import kotlinx.android.synthetic.main.cardview_book.view.*

class RVBookAdapter(var books: List<Book>, val clickListener: (Book) -> Unit) : RecyclerView.Adapter<RVBookAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_book, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(books[position], clickListener)

    fun changeDataSet(newBookList: List<Book>) {
        books = newBookList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(book: Book, clickListener: (Book) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(book.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(book_image_cv)
            book_title_cv.text = book.Title
            book_plot_cv.text = book.Plot
            book_publisher_cv.text = book.imdbRating
            book_isbn_cv.text = book.Runtime
            this.setOnClickListener { clickListener(book) }
        }
    }
}
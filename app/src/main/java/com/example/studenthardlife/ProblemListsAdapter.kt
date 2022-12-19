package com.example.studenthardlife

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

// Adapter is a file that manages data displayed onto recycler view, creating and binding view holders
class ProblemListsAdapter() :
    RecyclerView.Adapter<ProblemListsAdapter.ProblemListViewHolder>() {
    class ProblemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.problem_list_title)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val problemListCardView: CardView = itemView.findViewById(R.id.problem_list_card_view)
    }

    private var problemLists : List<ProblemList> = listOf()

    fun setData(newProblemLists: List<ProblemList>) {
        this.problemLists = newProblemLists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.problem_list_item, parent, false)
        return ProblemListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProblemListViewHolder, position: Int) {
        val list = problemLists[position]
        holder.titleTextView.text = list.listName
        holder.imageView.setImageURI(Uri.parse(list.photoPath))

        holder.problemListCardView.setOnClickListener {
            // argument has to be set on target fragment
            val action = ProblemListsFragmentDirections.actionProblemListsFragmentToDetailFragment(
                listName = list.listName // passing argument to next fragment
            )

            // navigating to next fragment with defined action
            holder.problemListCardView.findNavController()
                .navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return problemLists.size
    }
}


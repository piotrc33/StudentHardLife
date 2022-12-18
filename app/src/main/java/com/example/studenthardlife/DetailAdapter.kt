package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// Adapter is a file that manages data displayed onto recycler view, creating and binding view holders
class DetailAdapter(private val parentListName : String) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.problem_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.problem_description)
        val problemCardView: CardView = itemView.findViewById(R.id.problem_card_view)
    }

    // list of all problems from all lists
    private var problems : List<Problem> = listOf()

    fun setData(newProblems: List<Problem>) {
        this.problems = newProblems.filter { it.listName == parentListName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.problem_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
//        val problemsFiltered = problems.filter { it.listName == parentListName }
        if(problems.isNotEmpty()) {
            val problem = problems[position]
            holder.titleTextView.text = problem.problemTitle
            holder.descriptionTextView.text = problem.description
        }


//        holder.problemCardView.setOnClickListener {
//            val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(
//                problemPosition = position, // passing argument to next fragment
//                listPosition = listPosition.toInt()
//            )
//
////             navigating to next fragment
//            holder.problemCardView.findNavController()
//                .navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return problems.size
    }
}
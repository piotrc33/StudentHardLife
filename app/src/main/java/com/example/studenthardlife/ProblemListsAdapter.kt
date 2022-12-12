package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

// Adapter is a file that manages data displayed onto recycler view, creating and binding view holders
class ProblemListsAdapter(private val problemLists: ArrayList<ProblemList>) :
    RecyclerView.Adapter<ProblemListsAdapter.ProblemListViewHolder>() {
    class ProblemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.problem_list_title)

        //        val dateTextView : TextView = itemView.findViewById(R.id.crime_date)
        val crimeCardView: CardView = itemView.findViewById(R.id.problem_list_card_view)
//        private val viewModel = ViewModelProvider(MainAc)[CrimeViewModel::class.java]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.problem_list_item, parent, false)
        return ProblemListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProblemListViewHolder, position: Int) {
//        val crime = crimeList[position]
        val list = problemLists[position]
        holder.titleTextView.text = list.name
//        holder.dateTextView.text = .date
//        if(crime.isSolved)
//            holder.imageView.setImageResource(R.drawable.checkmark)
//        else
//            holder.imageView.setImageResource(0)

        holder.crimeCardView.setOnClickListener {
            // argument has to be set on target fragment
            val action = ProblemListsFragmentDirections.actionProblemListsFragmentToDetailFragment(
                listPosition = position
            )
            holder.crimeCardView.findNavController()
                .navigate(action)
//            handler.handleUserData(crime)
        }
    }

    override fun getItemCount(): Int {
        return problemLists.size
    }
}
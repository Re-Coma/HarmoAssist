package kr.sweetcase.harmoassist.listMaterials

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.sweetcase.harmoassist.R
import kr.sweetcase.harmoassist.TechnicSummaryActivity
import kr.sweetcase.harmoassist.databinding.ItemTechnicBinding

/**  기능 설명 리스트를 만들 때 쓰는 리사이클 어댑터 **/
class TechnicAdapter(
    private val items : ArrayList<TechnicalInfo>,
    private val context : Context
) : RecyclerView.Adapter<TechnicAdapter.TechnicViewHolder>() {

    class TechnicViewHolder(val binding : ItemTechnicBinding) : RecyclerView.ViewHolder(binding.root)


    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_technic, parent, false)
        return TechnicViewHolder(ItemTechnicBinding.bind(view))
    }

    override fun onBindViewHolder(holder: TechnicViewHolder, position: Int) {
        holder.binding.technicName = items[position]

        // 클릭 리스너
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TechnicSummaryActivity::class.java)
            intent.putExtra("tech_info", items[position])
            context.startActivity(intent)
        }
    }
}
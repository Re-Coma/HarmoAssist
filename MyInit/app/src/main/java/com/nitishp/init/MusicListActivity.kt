
package com.nitishp.init

import android.graphics.Color
import android.graphics.Color.BLUE
import android.os.Bundle
import android.text.Layout
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitishp.init.databinding.ItemMusicBinding
import kotlinx.android.synthetic.main.activity_musiclist.*

class MusicListActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musiclist)

        val spaceDecoration:RecyclerDecoration= RecyclerDecoration(1)

        val music = arrayListOf<Music>()
        for(i in 0..30){
            music.add(Music("곡 제목"))
        }
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MusicListActivity)
            adapter = MusicAdapter(music){music->
                Toast.makeText(this@MusicListActivity,"${music.Name} 선택됨",Toast.LENGTH_SHORT).show()
            }
            recycler_view.addItemDecoration(DividerItemDecoration(this@MusicListActivity,LinearLayoutManager.VERTICAL))


            recycler_view.addItemDecoration(spaceDecoration)
        }


    }
}
//네트워크 시에는 별도로 하기
class MusicAdapter(val items :List<Music>,
            private  val clickListener: (music: Music)->Unit)
    : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>(){
    val mSelectedItems :SparseBooleanArray = SparseBooleanArray(0)
    class MusicViewHolder(val binding: ItemMusicBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_music,parent,false)

        val viewHolder = MusicViewHolder(ItemMusicBinding.bind(view))
        view.setOnClickListener{
            clickListener.invoke(items[viewHolder.adapterPosition])
            val itemPosition = viewHolder.adapterPosition
            if(mSelectedItems.get(itemPosition,false)){
                mSelectedItems.put(itemPosition,false)
                view.setBackgroundColor(Color.WHITE)
            }else {
                mSelectedItems.put(itemPosition,true)
                view.setBackgroundColor(Color.BLUE)
            }
            }

        return viewHolder

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
       holder.binding.music = items[position]
    }

}
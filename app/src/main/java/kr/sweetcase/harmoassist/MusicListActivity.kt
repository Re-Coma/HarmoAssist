
package kr.sweetcase.harmoassist

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kr.sweetcase.harmoassist.databinding.ItemMusicBinding
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_musiclist.*
import kr.sweetcase.harmoassist.listMaterials.Music
import kr.sweetcase.harmoassist.listMaterials.RecyclerDecoration
import java.util.*


var selectedIdx = -1 // 선택이 안되어 있는 경우 -1 처리
// 이 인덱스와 music 변수를 이용해 제목을 추출하고
// 이를 이용해 DB에 접근함으로써 미디 데이터를 가져올 수 있다.

var preSelectedIdx = -1 // 이전 선택 버퍼

class MusicListActivity :AppCompatActivity() {

    val music = ArrayList<Music>()
    val context = this

    // 타이틀에 대한 인덱스 찾기
    // 선택이 안되어 있다면 -1 추출
    private fun getSelectedIndexByTitle(title : String) : Int {
        for(i in 0 until music.size) {
            if( music[i].title == title)
                return i
        }
        return -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musiclist)

        val spaceDecoration =
            RecyclerDecoration(1)

        var titleArray : ArrayList<String> = ArrayList()



        for (i in 0..10) {
            music.add(
                Music(
                    "곡 제목 $i",
                    "곡 내용"
                )
            )

            // 곡 제목 추가
            titleArray.add("곡 제목 $i")
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MusicListActivity)
            adapter = MusicAdapter(
                // Music Adapter 멤버변수에 선택, 이전에 선택된 인덱스추가
                music,
                context=context
            ) { music ->

                // 선택된 인덱스 갱신
                preSelectedIdx = selectedIdx
                selectedIdx = getSelectedIndexByTitle(music.title)
                if(preSelectedIdx == selectedIdx)
                    selectedIdx = -1
            }
            recycler_view.addItemDecoration(
                DividerItemDecoration(
                    this@MusicListActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            recycler_view.addItemDecoration(spaceDecoration)
        }
    }

    //네트워크 시에는 별도로 하기
    class MusicAdapter(
        private val items :List<Music>,
        private val context : Context,
        private  val clickListener: (music: Music)->Unit
    )  : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

        lateinit var parentGroup : ViewGroup
        class MusicViewHolder(val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_music, parent, false)

            /**
             *
             * 여기에서 출력된 리스트들의 정보 주소값을 복사
             * onBindViewHolder에서 처리
             */
            parentGroup = parent
            // 클릭 리스너
            return MusicViewHolder(ItemMusicBinding.bind(view))

        }

        override fun getItemCount() = items.size

        // TODO 클릭에 대한 상태변화 구현은 여기서
        private fun itemClickEvent(view : View) {
            view.setBackgroundColor(Color.BLUE)
        }
        private fun itemUnClickEvent(view : View) {
            view.setBackgroundColor(Color.WHITE)
        }

        override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
            holder.binding.music = items[position]


            // 새롭게 등장한 아이템이 선택된 아이템인지
            // 확인한다.
            if(position == selectedIdx)
                itemClickEvent(holder.itemView)
            else
                itemUnClickEvent(holder.itemView)

            // 아이템에 대한 클릭 리스너
            holder.itemView.setOnClickListener {

                clickListener.invoke(items[position])

                // 일단 보이는 뷰들부터 하얀색(클릭 안됨)으로 칠함
                for(i in 0 until parentGroup.size)
                    itemUnClickEvent(parentGroup[i])
                // 선택된 포지션이 selectedIdx와 일치하다면
                // itemClickEvent 발생
                if(position == selectedIdx)
                    Log.d("titles", "$selectedIdx")
                itemClickEvent(it)
            }
        }
    }
}


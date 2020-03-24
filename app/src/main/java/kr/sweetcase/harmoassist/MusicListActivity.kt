
package kr.sweetcase.harmoassist

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import kr.sweetcase.harmoassist.dialogs.SelectedMusicDialog
import kr.sweetcase.harmoassist.listMaterials.Music
import kr.sweetcase.harmoassist.listMaterials.RecyclerDecoration
import java.util.*


var selectedIdx = -1 // 선택이 안되어 있는 경우 -1 처리
// 이 인덱스와 music 변수를 이용해 제목을 추출하고
// 이를 이용해 DB에 접근함으로써 미디 데이터를 가져올 수 있다.

var preSelectedIdx = -1 // 이전 선택 버퍼

class MusicListActivity :AppCompatActivity() {

    val music = ArrayList<Music>()
    private val activity = this

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
                    "곡 내용",
                    "C Major",
                    180,
                    "3/4"
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
                activity=activity
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
        private val activity: MusicListActivity,
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

        override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
            holder.binding.music = items[position]

            // 아이템에 대한 클릭 리스너
            holder.itemView.setOnClickListener {
                clickListener.invoke(items[position])

                // 곡 선택 다이얼로그 소환
                var dialog = SelectedMusicDialog(activity, items[selectedIdx])
                dialog.setDialog()
                dialog.setListener()
                dialog.show()
            }
        }
    }
}


package kr.sweetcase.harmoassist.dialogs

import android.app.Dialog
import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import kr.sweetcase.harmoassist.R
import kr.sweetcase.harmoassist.listMaterials.Music
import org.w3c.dom.Text

/**
 * 음약을 선택했을 때 뜨는 다이얼로그
 */
class SelectedMusicDialog : Dialog {

    private var music : Music
    private var titleText : TextView
    private var tempoText : TextView
    private var timeSignatureText : TextView
    private var chordText : TextView
    private var summaryText : TextView

    private var isSummaryOpened = false

    private var summaryBtn : Button
    private var openBtn : Button
    private var closeBtn : Button
    private var statisticBtn : Button

    constructor(context : Context, music: Music) : super(context) {
        this.music = music
        setContentView(R.layout.selected_list_dialog)
        titleText = this.findViewById(R.id.selected_music_title)
        tempoText = this.findViewById(R.id.selected_music_tempo)
        timeSignatureText = this.findViewById(R.id.selected_music_timesignature)
        chordText = this.findViewById(R.id.selected_music_chord)
        summaryText = this.findViewById(R.id.selected_music_comment)

        summaryBtn = this.findViewById(R.id.selected_music_comment_btn)
        openBtn = this.findViewById(R.id.selected_music_open_btn)
        closeBtn = this.findViewById(R.id.selected_music_close_btn)
        statisticBtn = this.findViewById(R.id.selected_music_statistic_btn)

    }

    // 시각화 데이터 세팅
    fun setDialog() {
        titleText.text = music.title
        tempoText.text = music.tempo.toString()
        timeSignatureText.text = music.timeSignature
        chordText.text = music.chord
        summaryText.text = music.summary
    }

    // 리스터 세팅
    fun setListener() {

        summaryBtn.setOnClickListener {
            if(!isSummaryOpened) {
                summaryText.visibility = View.VISIBLE
                isSummaryOpened = true
            }
            else {
                summaryText.visibility = View.GONE
                isSummaryOpened = false
            }

        }

        openBtn.setOnClickListener {
            // TODO 데이터를 이용해 악보 인터페이스로 접근
            // TODO 그러므로 악보 인터페이스 관련 함수가 여기 들어가야함
        }

        // 닫기
        closeBtn.setOnClickListener {
            this.dismiss()
        }

        statisticBtn.setOnClickListener {
            // TODO DB에 접속해 미디 데이터를 받고
            // TODO 악보 분석 페이지로 이동
        }
    }
}
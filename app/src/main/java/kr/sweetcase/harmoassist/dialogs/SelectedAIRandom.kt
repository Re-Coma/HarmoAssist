package kr.sweetcase.harmoassist.dialogs

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kr.sweetcase.harmoassist.R
import kr.sweetcase.harmoassist.AiRandomactivity


class SelectedAIRandom : Dialog {
    var spBtn: Button
    var btnBtn: Button
    var scalBtn: Button
    var balBtn: Button
    var cancleBtn: Button

    constructor(context: Context) : super(context) {
        setContentView(R.layout.selected_ai_dialog)

        spBtn = this.findViewById(R.id.classic_sp)
        btnBtn = this.findViewById(R.id.classic_btb)
        scalBtn = this.findViewById(R.id.classic_sclt)
        balBtn = this.findViewById(R.id.bal)
        cancleBtn = this.findViewById(R.id.cancle)

    } //사용할 버튼 정의 (this를 사용하지 못함)

//    fun setListener(){
//        spBtn.setOnClickListener {
//
//        }
//        btnBtn.setOnClickListener {
//            dismiss()
//        }
//        scalBtn.setOnClickListener {
//            dismiss()
//        }
//        balBtn.setOnClickListener {
//            dismiss()
//        }
//        cancleBtn.setOnClickListener {
//            dismiss()
//        } // 사용하지 않음
    }



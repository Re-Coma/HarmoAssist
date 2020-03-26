package kr.sweetcase.harmoassist.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.widget.Button
import kotlinx.android.synthetic.main.activity_combo.*
import kotlinx.android.synthetic.main.selected_ai_dialog.*
import kr.sweetcase.harmoassist.R

class SelectedAIRandom : Dialog {
    private var windowSize : Point
    lateinit var spBtn : Button
    lateinit var btnBtn : Button
    lateinit var scalBtn : Button
    lateinit var balBtn : Button
    lateinit var cancleBtn : Button

    constructor(context : Context, windowSize : Point) : super(context) {

        setContentView(R.layout.selected_ai_dialog)
        this.windowSize = windowSize

        spBtn =this.findViewById(R.id.classic_sp)
        btnBtn= this.findViewById(R.id.classic_btb)
        scalBtn =this.findViewById(R.id.classic_sclt)
        balBtn =this.findViewById(R.id.bal)
        cancleBtn=this.findViewById(R.id.cancle)
        spBtn.setOnClickListener {
            dismiss()
        }
        btnBtn.setOnClickListener {
            dismiss()
        }
        scalBtn.setOnClickListener {
            dismiss()
        }
        balBtn.setOnClickListener {
            dismiss()
        }
        cancleBtn.setOnClickListener {
            dismiss()
        }
        // 비율 조정
        window?.setLayout((windowSize.x * 0.5f).toInt(), this.window?.attributes!!.height)
    }


}
package kr.sweetcase.harmoassist.modules.technicDictionary

import kr.sweetcase.amclib.midiManager.midiController.data.Pitch
import kr.sweetcase.harmoassist.modules.technicDictionary.labels.TechnicLabel

class TechnicGenerator {
    fun generate() : ArrayList<TechnicalInfo> {

        val technicList = ArrayList<TechnicalInfo>()

        technicList.add(
            TechnicalInfo(
                "도미넌트 베리에이션 I",
                arrayOf(TechnicalChord(
                    arrayOf(TechnicLabel._7_SUS_4, TechnicLabel.MAJ_7, TechnicLabel.MAJ),
                    arrayOf(Pitch.G, Pitch.G, Pitch.C)
                )),
                "곡의 종지를 효과적으로 만들 수 있는 도미넌트 베리에이션 중 하나이며 도미넌트 모션을 강조할 때 사용한다.",
                "tech_test.png"
            )
        )

        return technicList
    }
}
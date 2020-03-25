package kr.sweetcase.harmoassist.listMaterials

import android.media.Image
import java.io.Serializable

/** 화음 기능을 설명할 때 설명 대상의 클래스 데이터  *
 *
 *  프로토타입 때는 파라미터가 3개지만 실제 데모 때는
 *  재생할 미디 데이터도 추가해야 한다.
 *  @param
 *      techName : 기능 이름
 *      comment : 설명
 *      imgAssetName : 에셋 루트
 *      MidiArray : 미디 재생용 데이터 (추후에 추가예정)
 */
class TechnicalInfo(val techName : String, val comment : String, val imgAssetName : String)
    : Serializable {
}
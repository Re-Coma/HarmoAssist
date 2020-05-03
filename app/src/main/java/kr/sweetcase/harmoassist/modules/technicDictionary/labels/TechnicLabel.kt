package kr.sweetcase.harmoassist.modules.technicDictionary.labels

import kr.sweetcase.amclib.midiManager.midiController.data.Pitch

enum class TechnicLabel(val degreeArr : ByteArray) {

    MAJ (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E.dec.toByte(),
        Pitch.G.dec.toByte()
    )),
    MIN (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.G.dec.toByte()
    )),
    DIM (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.G_FLAT.dec.toByte()
    )),
    AUG (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E.dec.toByte(),
        Pitch.G_SHARP.dec.toByte()
    )),
    MAJ_MAJ_7 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.B.dec.toByte()
    )),
    MAJ_7 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.B_FLAT.dec.toByte()
    )),
    MIN_7 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.B_FLAT.dec.toByte()
    )),
    MIN_7_FIFTH (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.G_FLAT.dec.toByte(),
        Pitch.B_FLAT.dec.toByte()
    )),
    DIM_7 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.F_SHARP.dec.toByte(),
        Pitch.A.dec.toByte()
    )),
    _7_SUS_4 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.F.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.B_FLAT.dec.toByte()
    )),
    MAJ_6 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.A.dec.toByte()
    )),
    MIN_6 (byteArrayOf(
        Pitch.C.dec.toByte(),
        Pitch.E_FLAT.dec.toByte(),
        Pitch.G.dec.toByte(),
        Pitch.A.dec.toByte()
    ))
}
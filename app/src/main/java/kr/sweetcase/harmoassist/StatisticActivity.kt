package kr.sweetcase.harmoassist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import kr.sweetcase.harmoassist.statisticFragments.StatisticInfoFragment

class StatisticActivity : AppCompatActivity() {

    lateinit var statisticInfoFragment: StatisticInfoFragment

    lateinit var transaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        statisticInfoFragment = StatisticInfoFragment()

        val fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.statistic_fragment, statisticInfoFragment)
        transaction.commit()
    }
}

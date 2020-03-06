package com.wuyr.litepagertest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wuyr.litepager.LitePager.*
import kotlinx.android.synthetic.main.act_main_view.*

/**
 * @author wuyr
 * @github https://github.com/wuyr/LitePager
 * @since 2019-03-21 下午5:36
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main_view)
        initListeners()
    }

    private lateinit var toast: Toast

    @SuppressLint("ShowToast")
    private fun initListeners() {
        toast = Toast.makeText(this, "", Toast.LENGTH_LONG)
        litePager.setOnItemSelectedListener { onItemSelected(it) }
        litePager.setOnScrollListener {
            status.text = getString(
                R.string.status, getString(
                    when (it) {
                        STATE_IDLE -> R.string.idle
                        STATE_DRAGGING_LEFT -> R.string.dragging_left
                        STATE_DRAGGING_RIGHT -> R.string.dragging_right
                        STATE_DRAGGING_TOP -> R.string.dragging_top
                        STATE_DRAGGING_BOTTOM -> R.string.dragging_bottom
                        STATE_SETTLING_LEFT -> R.string.settling_left
                        STATE_SETTLING_RIGHT -> R.string.settling_right
                        STATE_SETTLING_TOP -> R.string.settling_top
                        STATE_SETTLING_BOTTOM -> R.string.settling_bottom
                        else -> return@setOnScrollListener
                    }
                )
            )
        }
        orientationSwitch.setOnCheckedChangeListener { _, isChecked ->
            litePager.setOrientation(if (isChecked) ORIENTATION_VERTICAL else ORIENTATION_HORIZONTAL)
        }
        childCountSwitch.setOnCheckedChangeListener { _, isChecked ->
            litePager.removeAllViews()
            if (isChecked) {
                litePager.addViews(
                    R.layout.layout_card_view_0,
                    R.layout.layout_card_view_1,
                    R.layout.layout_card_view_2,
                    R.layout.layout_card_view_3,
                    R.layout.layout_card_view_4
                )
            } else {
                litePager.addViews(
                    R.layout.layout_card_view_0,
                    R.layout.layout_card_view_1,
                    R.layout.layout_card_view_2
                )
            }
        }
        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val value = progress / 100F
                when (seekBar.id) {
                    R.id.topScaleBar -> litePager.setTopScale(value)
                    R.id.topAlphaBar -> litePager.setTopAlpha(1F - value)
                    R.id.middleScaleBar -> litePager.setMiddleScale(value)
                    R.id.middleAlphaBar -> litePager.setMiddleAlpha(1F - value)
                    R.id.bottomScaleBar -> litePager.setBottomScale(value)
                    R.id.bottomAlphaBar -> litePager.setBottomAlpha(1F - value)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        }
        topScaleBar.setOnSeekBarChangeListener(listener)
        topAlphaBar.setOnSeekBarChangeListener(listener)
        middleScaleBar.setOnSeekBarChangeListener(listener)
        middleAlphaBar.setOnSeekBarChangeListener(listener)
        bottomScaleBar.setOnSeekBarChangeListener(listener)
        bottomAlphaBar.setOnSeekBarChangeListener(listener)
    }

    fun onItemSelected(view: View) {
        toast.setText(
            "${(((view as ViewGroup).getChildAt(0) as ViewGroup)
                .getChildAt(1) as TextView).text} selected"
        )
        toast.show()
    }

    fun onItemClick(view: View) {
        toast.setText(
            "${(((view as ViewGroup).getChildAt(0) as ViewGroup)
                .getChildAt(1) as TextView).text} clicked"
        )
        toast.show()
    }
}

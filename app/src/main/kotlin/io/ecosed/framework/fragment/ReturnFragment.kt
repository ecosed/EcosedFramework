package io.ecosed.framework.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import com.idlefish.flutterboost.containers.FlutterBoostFragment
import io.ecosed.framework.utils.AppCompatFlutter
import io.flutter.embedding.android.FlutterView

class ReturnFragment : FlutterBoostFragment() {

    private var flutterView: FlutterView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = Space(requireContext()).apply {
        super.onCreateView(inflater, container, savedInstanceState)?.let {parent ->
            flutterView = findFlutterView(view = parent)
            when (val activity = requireActivity()) {
                is AppCompatFlutter -> {
                    (activity as AppCompatFlutter).apply {
                        onFlutterCreated(
                            flutterView = flutterView
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        when (val activity = requireActivity()) {
            is AppCompatFlutter -> {
                (activity as AppCompatFlutter).apply {
                    onFlutterDestroy(flutterView = flutterView)
                }
            }
        }
        flutterView = null
    }

    private fun findFlutterView(view: View): FlutterView? {
        when (view) {
            is FlutterView -> return view
            is ViewGroup -> for (i in 0 until view.childCount) {
                return findFlutterView(
                    view = view.getChildAt(i)
                ) ?: errorFindView()
            }

            else -> errorViewType()
        }
        return null
    }

    private fun errorFindView(): Nothing {
        error("无法获取控件")
    }

    private fun errorViewType(): Nothing {
        error("未知的类型")
    }
}
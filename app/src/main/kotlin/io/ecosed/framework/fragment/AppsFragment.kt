package io.ecosed.framework.fragment

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ecosed.framework.BuildConfig
import io.ecosed.framework.adapter.AppsAdapter
import io.ecosed.framework.model.AppsModel
import io.ecosed.framework.receiver.AppsReceiver

class AppsFragment : Fragment() {

    private lateinit var appReceiver: AppsReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return RecyclerView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) view.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                4,
                LinearLayoutManager.VERTICAL,
                false
            )

            loadApp(recyclerView = this@apply)

            appReceiver = AppsReceiver {
                loadApp(recyclerView = this@apply)
            }

            requireActivity().registerReceiver(
                appReceiver,
                IntentFilter().apply {
                    addAction(Intent.ACTION_PACKAGE_ADDED)
                    addAction(Intent.ACTION_PACKAGE_REMOVED)
                    addDataScheme("package")
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().unregisterReceiver(appReceiver)
    }

    @Suppress("DEPRECATION")
    private fun loadApp(recyclerView: RecyclerView) {
        // 应用列表
        val applicationList: MutableList<AppsModel> = ArrayList()

        // 添加自己
        applicationList.add(
            AppsModel(pkgName = BuildConfig.APPLICATION_ID)
        )

        // 获取启动器列表
        for (resolveInfo in if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        ) requireActivity().packageManager.queryIntentActivities(
            Intent().setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER),
            PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong())
        ) else requireActivity().packageManager.queryIntentActivities(
            Intent().setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER),
            PackageManager.MATCH_ALL
        )) {
            val pkg = resolveInfo.activityInfo.packageName
            if (pkg != BuildConfig.APPLICATION_ID) {
                applicationList.add(
                    AppsModel(pkgName = pkg)
                )
            }
        }

        // 设置适配器
        recyclerView.apply {
            adapter = AppsAdapter.newInstance(
                applicationList = applicationList,
                current = {

                }
            )
        }
    }
}
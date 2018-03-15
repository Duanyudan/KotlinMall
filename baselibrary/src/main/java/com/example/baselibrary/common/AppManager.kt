package com.example.baselibrary.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by user on 2018/3/15.
 */
class AppManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy {
            //            直接调用构造方法
            AppManager()
        }
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获取当前栈顶
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 清空栈
     */
    fun finishAllActivity(){
        for(activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /*
    退出应用
     */
    @SuppressLint("MissingPermission")
    fun exitApp(context: Context){
        finishAllActivity()
       val activityManager= context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}
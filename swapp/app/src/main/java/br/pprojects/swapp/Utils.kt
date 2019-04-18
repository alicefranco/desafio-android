package br.pprojects.swapp

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity?.addFragment(@IdRes id: Int, fragment: Fragment, tag: String){
    this?.supportFragmentManager?.beginTransaction()?.add(id, fragment, tag)?.addToBackStack(tag)?.commit()
}

fun AppCompatActivity?.replaceFragment(@IdRes id: Int, fragment: Fragment, tag: String){
    this?.supportFragmentManager?.beginTransaction()?.replace(id, fragment, tag)?.addToBackStack(tag)?.commit()
}

fun Fragment.addFragment(@IdRes id: Int, fragment: Fragment, tag: String){
    activity?.supportFragmentManager?.beginTransaction()?.add(id, fragment, tag)?.addToBackStack(tag)?.commit()
}

fun Fragment.replaceFragment(@IdRes id: Int, fragment: Fragment, tag: String){
    activity?.supportFragmentManager?.beginTransaction()?.replace(id, fragment, tag)?.addToBackStack(tag)?.commit()
}

fun View?.visible() { this?.visibility = View.VISIBLE }

fun View?.gone() { this?.visibility = View.GONE }

fun View?.invisible() { this?.visibility = View.INVISIBLE }
package com.douyao.demokotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import com.douyao.demokotlin.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView = root.findViewById<TextView>(R.id.text_home)
        homeViewModel!!.text.observe(this, Observer { s -> text_home.text = s })
        return root
    }
}
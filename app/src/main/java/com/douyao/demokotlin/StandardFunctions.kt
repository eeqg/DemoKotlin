package com.douyao.demokotlin

/**
 * Created by wp on 2019/9/9.
 *
 * 作用域函数
 *
 * Kotlin中的标准库函数: run、with、let、also和apply
 */
fun main() {
    val str = "abc"

    //run
    testRun()
    //with
    testWith()
    //let
    testLet(str)
    //also
    testAlso(str)
    //apply
    testApply()
}

fun printLog(msg: String) {
    println("- - - - - - - -↓↓ $msg↓↓- - - - - - - - - -")
}

fun testRun() {
    printLog("run")

    val mood = "I am sad"

    run {
        val mood = "I am happy"
        println(mood) // I am happy
    }
    println(mood)  // I am sad
}

fun testWith() {
    printLog("with")
    val signals = SignalTonDemo.get()
    with(signals) {
        testField = "-100"
        println()
        println("-----testField : $testField")
    }
}

fun testLet(original: String) {
    //演变操作(返回一个不同类型的值)
    printLog("let")
    original.let {
        println("The original String is $it") // "abc"
        it.reversed() // evolve it as parameter to send to next let
    }.let {
        println("The reverse String is $it") // "cba"
        it.length  // can be evolve to other type
    }.let {
        println("The length of the String is $it") // 3
    }
}

fun testAlso(original: String) {
    //对相同的变量执行操作, 可以非常强大的进行自我操作(返回T类型本身)
    printLog("also")
    original.also {
        println("The original String is $it") // "abc"
        it.reversed() // even if we evolve it, it is useless
    }.also {
        println("The reverse String is ${it}") // "abc"
        it.length  // even if we evolve it, it is useless
    }.also {
        println("The length of the String is ${it}") // "abc"
    }.also {
        println("length: ${it.length}")//3
    }
}

fun testApply() {
    //可以让无链对象创建链式调用
    printLog("apply")

    SignalTonDemo.get().foo()
        .apply {
            println("-------> 1")
            println("-------> $this")
        }.apply {
            println("-------> 2")
        }.apply {
            println("-------> 3")
        }
}
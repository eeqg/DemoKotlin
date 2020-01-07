package com.douyao.demokotlin.kotlin

/**
 * Created by wp on 2019/9/9.
 *
 * 作用域(内联)函数
 *
 * Kotlin中的标准库函数: run、with、let、also和apply (with不是扩展函数)
 * 1, run, with, and apply refer to the context object as a lambda receiver - by keyword this(都有参数this);
 * 2, let and also have the context object as a lambda argument by it(都有参数it).
 * 3, apply and also return the context object(返回对象本身).
 * 4, let, run, and with return the lambda result(返回最后以后的值).
 * (let和also 使用方式结构一样, 区别是返回值不一样)
 * (apply和run 使用方式结构一样, 区别是返回值不一样)
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

fun testWith() {
    //argument -> this (返回值为最后一行的值或者指定的return的表达式)
    printLog("with")
    val signals = SignalTonDemo.get()
    val a = with(signals) {
        testField = "-100"
        println()
        println("-----testField : $testField")
        100000
    }
}

fun testLet(original: String) {
    //演变操作(返回值为最后一行的值或者指定的return的表达式)
    //argument -> it
    printLog("let")
    val a = original.let {
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
    //对相同的变量执行操作, 可以非常强大的进行自我操作(The return value is the object itself)(返回传入对象的本身)
    //argument -> it
    printLog("also")
    val a = original.also {
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


fun testRun() {
    //argument -> this (返回值为最后一行的值或者指定的return的表达式)
    printLog("run")

    val mood = "I am sad"

    val a = mood.run { println(this.length) }

    run {
        val mood = "I am happy"
        println(mood) // I am happy
    }
    println(mood)  // I am sad
}

fun testApply() {
    //可以让无链对象创建链式调用 (返回的是传入对象的本身)
    //obj.apply的传参: this -- 当前对象
    printLog("apply")

    val a = SignalTonDemo.get().foo()
        .run {
            println("-------> 1")
            println("-------> $this")
        }.apply {
            println("-------> 2")
        }.apply {
            println("-------> 3")
        }
}
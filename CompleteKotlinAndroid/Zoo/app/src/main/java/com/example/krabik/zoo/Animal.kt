package com.example.krabik.zoo

/**
 * Created by Krabik on 09.09.2017.
 */
class Animal{
    var name:String? = null
    var description:String?= null
    var image:Int? = null
    var isKiller:Boolean? = null

    constructor(name:String?, description:String?, image:Int?, isKiller:Boolean?
    ){
        this.name = name
        this.description = description
        this.image = image
        this.isKiller = isKiller
    }
}
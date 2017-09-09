package com.example.krabik.pockemonandroid

import android.location.Location

/**
 * Created by Krabik on 09.09.2017.
 */
class  Pockemon{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null
    var location:Location?=null
    var IsCatch:Boolean?=false
    constructor(image:Int,name:String,des:String,power:Double,lat:Double,log:Double){
        this.name=name
        this.des=des
        this.image=image
        this.power=power
        this.location= Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=log
        this.IsCatch=false
    }

}
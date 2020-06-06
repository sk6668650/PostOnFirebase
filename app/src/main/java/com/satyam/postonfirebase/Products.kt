package com.satyam.postonfirebase


class Product {

    var name: String? = null
    var last: String? = null
    var url: String? = null

    constructor(){}
    constructor(name:String,last:String,url:String){
        this.name=name
        this.last=last
        this.url=url
    }
}

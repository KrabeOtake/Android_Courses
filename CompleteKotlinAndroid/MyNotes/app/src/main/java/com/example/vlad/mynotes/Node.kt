package com.example.vlad.mynotes

/**
 * Created by Vlad on 04.09.2017.
 */
class Node{
    var nodeID: Int? = null
    var nodeTitle: String? = null
    var nodeDesc: String? = null

    constructor(nodeID: Int, nodeTitle: String, nodeDesc: String){
        this.nodeID = nodeID
        this.nodeTitle = nodeTitle
        this.nodeDesc = nodeDesc
    }
}
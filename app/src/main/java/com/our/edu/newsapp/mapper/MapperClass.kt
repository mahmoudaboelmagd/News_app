package com.our.edu.newsapp.mapper

interface MapperClass <Server, DomainModel>{

    fun mapFromServer(server: Server): DomainModel

}
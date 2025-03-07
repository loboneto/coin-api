package br.com.loboneto.coinapi.core

interface IMapper<in I, in C, out O> {
    fun map(from: I, comp: C): O
}

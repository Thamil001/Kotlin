package packages

fun main(){
    var a:Any=10
    var b:Any=10
    a=a.toString().toDouble()
    b=b.toString().toDouble()
    val m1=Mathematics(a,b)
    println(m1.add())
    println(m1.mutiplication())
    println(m1.division())
    println(m1.sub())
}
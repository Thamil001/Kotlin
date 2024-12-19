package constructor

fun main(){
    val student1=Students("Navin","7376211Cs314","IT")
    val student2=Students("Thamil","7376211Cs314")
    val student3=Students(rollNumber = "78437765", department = "yui")
    student1.display()
    student2.display()
    student3.display()
}
//primary constructor property values is do not change directly using functions and secondary contractor
//secondary constructor changed values reassign the primary constructor property values
class Students(
    private val stuName:String,
    private val rollNumber:String,

    private var department:String
){

    constructor(stuName: String,rollNumber: String):this(stuName,rollNumber,"cse")
    constructor(rollNumber: Any,department: String):this("Mahesh",rollNumber.toString(),department)

    fun display(){
        println("name:$stuName rollNumber:$rollNumber  department:$department")
    }

}
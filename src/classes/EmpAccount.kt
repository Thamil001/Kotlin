package classes

import java.time.LocalDate
import java.time.LocalTime
import kotlin.system.exitProcess


lateinit var accountEmp: EmpAccount
fun main(){
    accountEmp = EmpAccount(643651276531,"thamil", 0.0)
    showStatus()
}
fun showStatus(){
    do {
        println("1 - Deposit\n2 - Withdraw\n3 - AccountBalance\n4 - TransactionHistory\n5 - Close")
        print("Chose the choice:")
        try {
            when(val input= readln().toInt()){
                1 -> depositEmp(input)
                2 -> withdrawEmp(input)
                3 -> displayEmpBalance(accountEmp)
                4 -> displayEmpTransactions(accountEmp)
                5 -> exitProcess(0)
                else -> println("Invalid Choice")
            }
        }catch (e:NumberFormatException){
            println("Invalid Choice please Correct the Choice")
        }
        println()
    }while (true)

}

fun depositEmp(choice: Int){
    println(Transactions.deposit(userMoney(choice), accountEmp).message)
}
fun withdrawEmp(choice: Int){
    println(Transactions.withdraw(userMoney(choice), accountEmp).message)
}
fun displayEmpBalance(target:EmpAccount){
    println("Balance :${target.balance}")
}
fun displayEmpTransactions(target: EmpAccount){
    println("Transaction History :")
    target.transactionStatus.forEach {
        println(it)
    }
}

fun userMoney(choice:Int): Double{
    when(choice){
        1 -> print("Enter the Deposit Amount :")
        2 -> print("Enter the Withdraw Amount :")
    }
    return readln().toDouble()
}

data class EmpAccount(
    val accountNo:Long,
    val userName:String,
    var balance:Double,
    val transactionStatus: MutableList<String> = mutableListOf()
)
data class TransactionStatus(
    val isSuccess:Boolean,
    val message: String
)

object Transactions{
    fun deposit(money:Double,target:EmpAccount):TransactionStatus{
        if(money>0){
            target.balance+=money
            target.transactionStatus.add("Account NO :${target.accountNo} ,Account Name:${target.userName},date:${LocalDate.now()} and time: ${LocalTime.now()} credited amount $money")
            return TransactionStatus(true,"Deposit successfully")
        }else{
            return TransactionStatus(false,"Transaction Failed : Deposit Unsuccessfully!!")
        }
    }

    fun withdraw(money:Double,target: EmpAccount):TransactionStatus{
        if(money>0 && target.balance>=money){
            target.balance-=money
            target.transactionStatus.add("Account NO :${target.accountNo} ,Account Name:${target.userName} ,date:${LocalDate.now()} and time: ${LocalTime.now()} Debited amount $money")
            return TransactionStatus(true,"Debited Successfully")
        }else{
            return TransactionStatus(false,"Transaction Failed : Debited Unsuccessfully!!")
        }
    }
}

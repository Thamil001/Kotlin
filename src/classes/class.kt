package classes

import java.time.LocalDate
import java.time.LocalTime
import kotlin.system.exitProcess

fun main() {
    start()
}

fun start() {
    val user1 = Account(1001, "Vijay")
    user1.reload()
}
private class Account(private var acNo: Int, private var userName: String) {
    private var balance: Double = 0.0
    private var transaction: Double = 0.0
    private var transactionHistory = emptyList<String>().toMutableList()

    fun reload() {
        print("Deposit - 1\nWithdrawal - 2\nBalanceCheck - 3\nTransactionHistory - 4\nclose and exit - 5\n")
        print("Chose the choice: ")
        val input = readln()
        try {
            val choice = input.toInt()
            if (5 >= choice) {
                when (choice) {
                    1 -> deposit(userInput(choice).toInt())
                    2 -> withdral(userInput(choice).toInt())
                    3 -> balanceCheck()
                    4 -> transactionHistoryCheck()
                    5 -> exitProcess(0)
                }
            } else {
                println("Invalid choice")
                reload()
            }

        } catch (e: NumberFormatException) {
            println("Invalid input! Please enter a valid integer.")
            reload()
        }
    }

    fun balanceCheck() {
        if (balance >= 0) {
            println("Balance:$balance")
        } else {
            println("Is Amount not in there account:Available balance is $balance")
        }
        reload()
    }

    fun deposit(money: Int) {
        if (money > 0) {
            transaction += money
            balance = transaction
            println("Message : AcNo:xxx$acNo and AcName:$userName credited $money and account balance is $balance")
            transactionHistory.add("Successes :transaction Date :${LocalDate.now()} and time Time:${LocalTime.now()}")
        } else {
            println("The value is negative and precision")
            transactionHistory.add("Failed :transaction Date :${LocalDate.now()} and time Time:${LocalTime.now()}")
        }
        reload()
    }

    fun withdral(money: Int) {
        if (balance >= money) {
            transaction -= money
            balance = transaction
            println("Message : AcNo:xxx$acNo and AcName:$userName Debited $money and account balance is $balance")
            transactionHistory.add("Success : transaction Date :${LocalDate.now()} and time Time:${LocalTime.now()}")
        } else {
            println("Is Amount not in there account:Available balance is $balance")
            transactionHistory.add("Failed :transaction Date :${LocalDate.now()} and time Time:${LocalTime.now()}")
        }
        reload()
    }

    fun transactionHistoryCheck() {
        print("TransactionHistory: ")
        if (transactionHistory.isEmpty()) {
            print("No transaction history\n")
        } else {
            for (i in transactionHistory) {
                println("      $i")
            }
        }
        reload()
    }

    fun userInput(check: Int): Double {
        when (check) {
            1 -> print("Enter the Deposit amount    :")
            2 -> print("Enter the withdrawal amount :")
        }
        return money()
    }

    fun money(): Double {
        val amount: Double = readln().toDouble(); return amount
    }

}

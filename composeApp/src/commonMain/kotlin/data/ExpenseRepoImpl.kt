package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

class ExpenseRepoImpl(private val expenseManger: ExpenseManager) : ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return expenseManger.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManger.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManger.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManger.getCategories()
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }

}
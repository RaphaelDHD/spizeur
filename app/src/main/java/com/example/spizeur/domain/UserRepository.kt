package com.example.spizeur.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spizeur.domain.database.DBDataSource
import com.example.spizeur.models.Address
import com.example.spizeur.models.Order
import com.example.spizeur.models.PaymentInformation
import com.example.spizeur.models.Product
import com.example.spizeur.models.User
import timber.log.Timber
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object UserRepository {

    private var _currentUserOrder : MutableLiveData<Order?> = MutableLiveData<Order?>()
    val currentUserOrder = _currentUserOrder

    private var _currentUser : MutableLiveData<User?> = MutableLiveData<User?>()
    val currentUser = _currentUser


    suspend fun login(email: String, password: String): Boolean {
            val user = DBDataSource.getUser(email)
            if (user.password == password) {
                _currentUser.value = user
                createOrderIfNoCurrent(user.userId!!)
                return true
            }
            return false
        }

    suspend fun getUser(email: String): User {
        return DBDataSource.getUser(email)
    }

    fun registerUserToSharedPreferences(context: Context, email: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SpizeurSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }
    fun disconnectUserFromSharedPreference(context: Context, email: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SpizeurSharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.apply()
    }

    fun getUserFromSharedPreferences(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SpizeurSharedPreference", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", null)
        return email
    }

    suspend fun userExist(email: String): Boolean {
        val User = DBDataSource.getUser(email)
        if (User != null) {
            return true
        }
        return false
    }


    suspend fun createAccount(username: String, email: String, password: String) {
        val user = User(userId= Random.nextInt(0,100000),username = username, email = email, password = password)
        _currentUser.value = user
        createOrderIfNoCurrent(user.userId!!)
        DBDataSource.insertUser(user)
        }

    fun logout() {
        _currentUser.value = null
        _currentUserOrder.value = null
    }

    suspend fun createOrderIfNoCurrent(userId: Int) {
        if (_currentUserOrder.value == null) {
            // check in database if there is an order for the user
            // take the only order that has no deliveryDate
            val orderInDB = DBDataSource.getOrderByUserId(userId)
            if (orderInDB != null) {
                _currentUserOrder.value = orderInDB
                if (_currentUserOrder.value?.productList == null) {
                    _currentUserOrder.value?.productList = mutableListOf<Product>()
                }
            }
            else {
                // set random number between 0 and 100000
                val orderId = Random.nextInt(0, 100000)
                val order = Order(orderId = orderId,userCommandId = userId)
                _currentUserOrder.value = order
                DBDataSource.insertOrder(order)
            }
        }
    }
    
    suspend fun setUserNewUsername(username: String, userId: Int)
    {
        return DBDataSource.setUserNewUsername(username, userId)
    }

    suspend fun setUserNewEmail(email: String, userId: Int)
    {
        return DBDataSource.setUserNewEmail(email, userId)
    }

    suspend fun setUserNewPassword(password: String, userId: Int)
    {
        return DBDataSource.setUserNewPassword(password, userId)
    }

    

    suspend fun addToCart(product: Product) {
        _currentUserOrder.value?.productList?.add(product)
        _currentUserOrder.value?.fullPrice = _currentUserOrder.value?.fullPrice?.plus(product.price)
        saveCartToDB()
    }

    suspend fun saveCartToDB() {
        _currentUserOrder.value?.productList?.forEach {
            DBDataSource.updateOrder(_currentUserOrder.value!!)
        }
    }


    fun setCurrentUser(user: User) {
        _currentUser.postValue(user)
    }

    suspend fun command() {
        _currentUserOrder.value?.commandDate = Date()
        if (currentUserOrder.value?.commandDate != null) {
            val commandDateTimeMillis = currentUserOrder.value?.commandDate!!.time
            val deliveryDateTimeMillis = commandDateTimeMillis + TimeUnit.DAYS.toMillis(5)
            currentUserOrder.value?.deliveryDate = Date(deliveryDateTimeMillis)
        }
        DBDataSource.updateOrder(currentUserOrder.value!!)
        // set random number between 0 and 100000
        val orderId = Random.nextInt(0, 100000)
        val order = Order(orderId = orderId,userCommandId = _currentUser.value?.userId)
        _currentUserOrder.postValue(order)
        DBDataSource.insertOrder(order)
    }

    suspend fun removeFromCart(position: Int): MutableList<Product> {
        _currentUserOrder.value?.productList?.removeAt(position)
        saveCartToDB()
        return _currentUserOrder.value?.productList!!
    }

    fun setCommandAddress(address: Address) {
        _currentUserOrder.value?.deliveryAddress = address
        if (_currentUser.value?.address == null) {
            var user = _currentUser?.value
            user?.address = address
            _currentUser.postValue(user!!)
        }
    }

    fun setCommandPaymentInformation(paymentInformation: PaymentInformation) {
        _currentUserOrder.value?.paymentInformation = paymentInformation
        if (_currentUser.value?.paymentInformation == null) {
            var user = _currentUser?.value
            user?.paymentInformation = paymentInformation
            _currentUser.postValue(user!!)
        }
    }

    suspend fun updateUser() {
        DBDataSource.updateUser(_currentUser.value!!)
    }

    fun isProductFavorite(id: Int): Boolean {
        return _currentUser.value?.favoriteProducts?.contains(id)!!
    }

    fun addProductToFavorite(id: Int) {
        _currentUser.value?.favoriteProducts?.add(id)
    }

    fun removeProductFromFavorite(id: Int) {
        _currentUser.value?.favoriteProducts?.remove(id)
    }


}
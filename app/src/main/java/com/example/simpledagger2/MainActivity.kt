package com.example.simpledagger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//MainActivity class
class MainActivity : AppCompatActivity() {

    @Inject lateinit var info: Info //late init variable that will recieve dependency from our Info class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // set current screen with activity_main layout
        DaggerMagicBox.create().poke(this) //Dagger auto-generated class from @Component and with poke() function called after create() method
        tv_greetings.text = info.text //sets view text with @Inject-ed texted in our info:Info variable
    }
}
//Class with variable initialization and @Inject before constructor method
class Info @Inject constructor() {
    val text = "Hello Dagger 2" //variable initialization
}

//Interface for DI in MainActivity class
@Component
interface MagicBox {
    //function for injection which recieves MainActivity class type
    fun poke(app: MainActivity)
}

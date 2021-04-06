package `in`.example.creditcardinputexercise

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.DialogCompat
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {


    lateinit var cardNumber: EditText
    lateinit var mmyyy: EditText
    lateinit var securityCode: EditText
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var btnPayNow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cardNumber = findViewById(R.id.cardNumber)
        mmyyy = findViewById(R.id.mmyy)
        securityCode =  findViewById(R.id.securityCode)
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)

        btnPayNow= findViewById(R.id.btnpayNow)

        val builder = AlertDialog.Builder(this)

        builder.setMessage("Payment Succcessful!")

        builder.setPositiveButton("Ok"){dialogInterface, which ->
        }

        btnPayNow.setOnClickListener {
            if ((cardValidation(cardNumber.text.toString()) && cvvValidation(securityCode.text.toString())
                            && expireValidation(mmyyy.text.toString()) && nameValidation(firstName.text.toString()))
                                        && nameValidation(lastName.text.toString())) {
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            else if((!cardValidation(cardNumber.text.toString()))){
                    cardNumber.setError("Enter Valid Card No.")
                 }
            else if((!cvvValidation(securityCode.text.toString()))){
                    securityCode.setError("Enter Valid CVV no.")
                }
            else if((!expireValidation(mmyyy.text.toString()))){
                    securityCode.setError("Enter Valid Date")
                }
            else if((!nameValidation(firstName.text.toString()))){
                firstName.setError("Enter Valid Name")
            }
            else if((!nameValidation(lastName.text.toString()))){
                lastName.setError("Enter Valid Name")
            }
        }
    }

    private fun cardValidation(text: String?):Boolean{
        val p:Pattern = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?\$")
        val m:Matcher = p.matcher(text)
        return  m.matches()
    }

    private fun cvvValidation(text: String?):Boolean{
        val p:Pattern = Pattern.compile("^[0-9]{3,4}\$")
        val m:Matcher = p.matcher(text)
        return  m.matches()
    }

    private fun expireValidation(text: String?):Boolean{
        val p:Pattern = Pattern.compile("^(0[1-9]|1[0-2])\\/?([0-9]{2}|[0-9]{2})\$")
        val m:Matcher = p.matcher(text)
        return  m.matches()
    }
    private fun nameValidation(text: String?):Boolean{
        val p:Pattern = Pattern.compile("^[A-Za-z]\\w{2,20}\$")
        val m:Matcher = p.matcher(text)
        return  m.matches()
    }

}
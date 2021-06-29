package com.target.targetcasestudy.ui.payment

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dagger.App
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding
import com.target.targetcasestudy.network.data.CardDetails
import com.target.targetcasestudy.utils.Constants
import com.target.targetcasestudy.utils.SharedPrefsHelper
import javax.inject.Inject

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment(), View.OnClickListener {

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    lateinit var binding: DialogPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)

        binding = DialogPaymentBinding.inflate(inflater)
        bindExistingCardNumber()

        binding.cancel.setOnClickListener(this)
        binding.submit.setOnClickListener(this)

        binding.etCardNumber.addTextChangedListener {
            if (validateCreditCard(it.toString())) {
                binding.submit.isClickable = true
                binding.submit.setTextColor(Color.parseColor("#CC0000"))
            } else {
                binding.submit.isClickable = false
                binding.submit.setTextColor(Color.parseColor("#E0E0E0"))
            }
        }
        return binding.root
    }

    private fun bindExistingCardNumber() {
        var cardString = sharedPrefsHelper[Constants.INTENT_CARD_NUMBER, ""]
        if (!TextUtils.isEmpty(cardString)) {
            binding.cardData = CardDetails(cardString!!, "")
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.cancel -> {
                dismiss()
            }
            binding.submit -> {
                var cardNumber = binding.etCardNumber.text.toString()
                sharedPrefsHelper.put(Constants.INTENT_CARD_NUMBER, cardNumber)
                Toast.makeText(activity, "Card number added!!", Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }
}
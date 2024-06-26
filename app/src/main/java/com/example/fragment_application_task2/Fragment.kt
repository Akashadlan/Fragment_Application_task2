package com.example.fragment_application_task2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fragment_application_task2.databinding.FragmentBinding
import java.text.SimpleDateFormat

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
abstract class Fragment : Fragment(), ActivityInterface {
    private var param1: String? = null
    private var param2: String? = null
    var mainActivity: MainActivity? = null
    var btnchange: Button? = null
    var etvalue: EditText? = null
    var binding: FragmentBinding? = null
    var simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy")
    var timeFormat = SimpleDateFormat("hh:mm aa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity?.activityInterface = this

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBinding.inflate(layoutInflater)
        return binding?.root
        // return inflater.inflate(R.layout.fragment_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // btnchange = view.findViewById(R.id.btnchange)

        binding?.btnchange?.setOnClickListener {
            if (binding?.etvalue?.text?.toString()?.isNullOrEmpty() == true) {
                binding?.etvalue?.error = resources.getString(R.string.value)
            }
            else {
                mainActivity?.changeActivity("${binding?.etvalue?.text?.toString()}")
            }
        }
            binding?.btndate?.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    { _, year, month, date ->
                        Log.e(TAG, "year $year month $month date $date")
                        var calendar = Calendar.getInstance()
                        calendar.set(year, month, date)
                        var formattedDate = simpleDateFormat?.format(calendar.time)
                        binding?.btndate?.setText(formattedDate)
                        if (calendar.timeInMillis > calendar.timeInMillis + 10 && calendar.timeInMillis < calendar.timeInMillis - 10) {
                            Toast.makeText(requireContext(), "Exit", Toast.LENGTH_SHORT).show()
                        }
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DATE),
                ).show()
            }
        binding?.btnchange?.setOnClickListener {
            if(binding?.etvalue?.text?.toString()?.isNullOrEmpty() == true){
                binding?.etvalue?.error = resources.getString(R.string.value)
            }
            else{
                mainActivity?.changeActivity("${binding?.etvalue?.text?.toString()}")
            }
        }
            binding?.btntime?.setOnClickListener {
                TimePickerDialog(
                    requireContext(), { _, hour, minute ->
                        Log.e(TAG, "hour $hour minute $minute")
                        var calendar = Calendar.getInstance()
                        calendar.set(Calendar.HOUR_OF_DAY, hour)
                        calendar.set(Calendar.MINUTE, minute)
                        binding?.btntime?.setText(timeFormat.format(calendar.time))

                        if (calendar.timeInMillis < 9 && calendar.timeInMillis > 6) {
                            Toast.makeText(requireContext(), "Exit", Toast.LENGTH_SHORT).show()
                        }
                    },

                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    false
                ).show()
            }
        }

            companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment Fragment.
             */
            fun newInstance(param1: String, param2: String) =
                Fragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
            override fun changeFragmentText(string: String) {
                binding?.btnchange?.setText(string.toString())
            }
        }
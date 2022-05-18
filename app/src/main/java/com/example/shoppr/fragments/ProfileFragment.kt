package com.example.shoppr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.shoppr.R
import com.example.shoppr.activities.HomeActivity
import com.firebase.ui.auth.AuthUI

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Logs user out
        val logout: AppCompatButton = view.findViewById(R.id.log_out)
        logout.setOnClickListener{
            AuthUI.getInstance().signOut(requireContext())
        }


        //Deletes users account
        val deleteAccount: AppCompatButton = view.findViewById(R.id.delete_account)
        deleteAccount.setOnClickListener{
            AuthUI.getInstance().delete(requireContext());
        }

    }


}